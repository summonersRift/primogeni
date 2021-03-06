package monitor.deployers;

/*
 * Copyright (c) 2011 Florida International University.
 *
 * Permission is hereby granted, free of charge, to any individual or
 * institution obtaining a copy of this software and associated
 * documentation files (the "software"), to use, copy, modify, and
 * distribute without restriction.
 *
 * The software is provided "as is", without warranty of any kind,
 * express or implied, including but not limited to the warranties of
 * merchantability, fitness for a particular purpose and
 * non-infringement.  In no event shall Florida International
 * University be liable for any claim, damages or other liability,
 * whether in an action of contract, tort or otherwise, arising from,
 * out of or in connection with the software or the use or other
 * dealings in the software.
 *
 * This software is developed and maintained by
 *
 *   Modeling and Networking Systems Research Group
 *   School of Computing and Information Sciences
 *   Florida International University
 *   Miami, Florida 33199, USA
 *
 * You can find our research at http://www.primessf.net/.
 */


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jprime.Experiment;
import jprime.ModelNode;
import jprime.Host.IHost;
import jprime.Interface.IInterface;
import jprime.partitioning.Partition;
import jprime.util.ComputeNode;
import jprime.util.CreateVPNConfig;
import jprime.util.NetAggPair;
import jprime.util.PartTlvPair;
import monitor.commands.HostCmd;
import monitor.commands.SetupContainerCmd;
import monitor.commands.SetupContainerCmd.NIC;
import monitor.commands.SetupExperimentCmd;
import monitor.commands.SetupSlavesCmd;
import monitor.commands.StartGatewayCmd;
import monitor.core.Deployer;
import monitor.core.IController;
import monitor.core.IExpListenter;
import monitor.core.Provisioner;
import monitor.core.RemoteController;
import monitor.util.Utils;


/**
 * @author Nathanael Van Vorst
 *
 */
public class ProtoGeniDeployer extends Deployer{
	static class compute_node_part {
		final ComputeNode c;
		Partition p;
		public compute_node_part(ComputeNode c, Partition p) {
			super();
			this.c = c;
			this.p = p;
		}
	}
	private IController controller;
	private boolean deployed;
	private boolean started;
	public ProtoGeniDeployer(String ipRange,final IExpListenter listener, final Experiment exp, final PartTlvPair parting, final Provisioner provisioner, Map<String,String> runtimeSymbols) {
		super(ipRange,listener,exp,parting,provisioner,runtimeSymbols);
		this.controller=null;
		this.deployed=false;
		this.started=false;
	}
	
	public IController deploy(final String keyStore, final long timeout, String openvpndir) throws GeneralSecurityException, IOException
	{
		if(deployed) throw new RuntimeException("You already deployed!");
		deployed=true;
		final int num_machines = parting.partitioning.getProcessingNodes().size()+1;

		listener.println("Contacting "+provisioner.getNumNodes()+" nodes to run the experiment on."); 
		provisioner.allocate();
		List<ComputeNode> compute_nodes = provisioner.getComputeNodes();
		if(compute_nodes == null || compute_nodes.size() == 0 || compute_nodes.size() != provisioner.getNumNodes()) {
			String msg="";
			if(compute_nodes == null) msg="ips are null!";
			else if(compute_nodes.size() == 0) msg="compute_nodes.size() == 0!{"+provisioner+"}";
			else msg="ips.size()["+compute_nodes.size()+"] != provisioner.getNumNodes()["+provisioner.getNumNodes()+"]{"+provisioner+"}";
			throw new RuntimeException("Allocation failed! \tReason:"+msg+"\n\tAllocator:"+provisioner);
		}
		HashMap<Integer, compute_node_part> mapping = checkComputeNodeMaping(parting, compute_nodes);

		ComputeNode master = compute_nodes.remove(0);
		
		String vpngateway = vpns.size()>0?master.getControl_ip():"";
		this.controller= new RemoteController(
				parting.partitioning,
				vmMap,
				vpngateway,
				timeout,
				parting.expName,
				listener);
		if(Utils.enableSSL) {
			if(keyStore == null) {
				throw new RuntimeException("Must specify a keystore!");
			}
		}
		
		listener.println("Controller is connecting (num_machines="+num_machines+", master="+master+")!");
		controller.connect(master, compute_nodes, keyStore);

		listener.println("\tThere are "+controller.getNumOutstandingCommands()+" outstanding commands; waiting for them to finish.");
		while(controller.getNumOutstandingCommands()>0 && controller.getFailures()==0) {
			//wait for it to connect...
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}

		listener.println("Setting up slave machines.");
		controller.sendCommand(new SetupSlavesCmd(exp.getName()));
		
		listener.println("Setting up virtual machines (i.e. containers) on slaves with emulated hosts.");
		ArrayList<HostCmd> routingCommands = new ArrayList<HostCmd>();
		
		for(Partition p : parting.partitioning.getProcessingNodes().values()) {
			compute_node_part cnp = mapping.get(p.getId());
			if(cnp == null) {
				throw new RuntimeException("couldn't locate the compute node which is running partition "+p.getId());
			}
			cnp.p=p;
			listener.println("Looking at Partition "+p.getId()+", node="+cnp.c.toString());
			
			if(vmMap.containsKey(p.getId())) {
				listener.println("\tPartition "+p.getId()+" has "+vmMap.get(p.getId()).size()+ "emulated Hosts");
				listener.println("\t\tSending VM setup commands");
				//we have containers
				for(IHost h : vmMap.get(p.getId())) {
					ArrayList<NIC> nics = new ArrayList<NIC>();
					for(ModelNode k : h.getAllChildren()) {
						if(k instanceof IInterface) {
							nics.add(new NIC(k.getUID(),((IInterface)k).getIpAddress().toString()));
						}
					}
					listener.println("\t\t\tHost="+h.getUniqueName()+"["+h.getUID()+"] nic count="+nics.size());
					SetupContainerCmd scmd = new SetupContainerCmd(p.getId(), h.getUID(), nics);
					listener.println("\t\t\tcmd="+scmd+"[machine="+scmd.getMachineId()+"]");
					controller.sendCommand(scmd);
					setupRouting(h, p, portalRoutes, routingCommands);
				}
			}
			else {
				listener.println("\tPartition "+p.getId()+" has NO emulated Hosts");
			}
		}
		listener.println("There are "+controller.getNumOutstandingCommands()+" outstanding commands; waiting for them to finish.");
		while(controller.getNumOutstandingCommands()>0 && controller.getFailures()==0) {
			//wait for all commands to finish...
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
		if(vpns.size()>0) {
			listener.println("Generating OpenVPN emulation setup files.");
			final CreateVPNConfig vpnCreator = new CreateVPNConfig(parting.resultsDir, master.getControl_ip(), openvpndir);
			if(!vpnCreator.createVPNConfig(this.exp, vpns)) {
				throw new RuntimeException("Error generating vpn setup files!");
			}
			controller.sendCommand(new StartGatewayCmd(vpnCreator.generateServerAndClientConfigs(), this.exp.getName()));
		}
		listener.println("There are "+controller.getNumOutstandingCommands()+" outstanding commands; waiting for them to finish.");
		while(controller.getNumOutstandingCommands()>0 && controller.getFailures()==0) {
			//wait for all commands to finish...
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
		listener.println("Setting up routes for the virtual machines.");
		int command_count=1;
		for(HostCmd sb : routingCommands) {
			//listener.println("\n\n\t***Sending setupbridge command, machineid=" + sb.getMachineId()
			//		+ " bridgeName=" + sb.getBridgeName());
			controller.sendCommand(sb);
			listener.println("   ProtoGeniDeployer Command sent"+command_count++ +":"+sb);
		}
		routingCommands.clear();
		listener.println("\tThere are "+controller.getNumOutstandingCommands()+" outstanding commands; waiting for them to finish.");
		
		while(controller.getNumOutstandingCommands()>0&& controller.getFailures()==0) {
			//wait for all commands to finish...
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
		
		deployed=true;
		listener.println("Done Setting up. Total Failures="+controller.getFailures());
		return controller;
	} 
	
	private void setupRouting(IHost h, Partition p, LinkedList<String> portalRoutes, ArrayList<HostCmd> routingCommands) {
		//Add routes to the virtual network
		/*{
			routingCommands.add(new HostCmd("ip route add "+ipRange+" dev eth0", h.getUID(), p.getId(), 500, false, 0));
			
			//XXX hack --- send a few more times to make sure it works!!!
			routingCommands.add(new HostCmd("ip route add "+ipRange+" dev eth0", h.getUID(), p.getId(), 501, false, 0));
			routingCommands.add(new HostCmd("ip route add "+ipRange+" dev eth0", h.getUID(), p.getId(), 502, false, 0));
		}*/
		//add routes to networks attached via portals
		/*{
			int d=4;
			for(String route : portalRoutes) {
				routingCommands.add(new HostCmd("ip route add "+route+" dev eth0", h.getUID(), p.getId(), 500+(++d), false, 0));
				
				//XXX hack --- send a few more times to make sure it works!!!
				routingCommands.add(new HostCmd("ip route add "+route+" dev eth0", h.getUID(), p.getId(), 500+(++d), false, 0));
				routingCommands.add(new HostCmd("ip route add "+route+" dev eth0", h.getUID(), p.getId(), 500+(++d), false, 0));
			}
		}*/
	}

	public void setupExperiment() {
		if(!deployed) throw new RuntimeException("You have not yet deployed!");
		if(started) throw new RuntimeException("You already started the experiment!");
		started=true;
		listener.println("Sending setup exp");
		controller.sendCommand(new SetupExperimentCmd(parting.expName,runtimeSymbols,parting.tlvs, NetAggPair.getAggMap(parting.partitioning)));
		while(controller.getNumOutstandingCommands()>0 && controller.getFailures()==0) {
			//wait for all commands to finish...
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		listener.println("Done setting up exp");
	}
	
	private HashMap<Integer, compute_node_part> checkComputeNodeMaping(PartTlvPair parting, List<ComputeNode> nodes) {
		listener.println("checking compute node <--> partition mapping");
		HashMap<Integer, compute_node_part> rv = new HashMap<Integer, ProtoGeniDeployer.compute_node_part>();
		if(nodes.get(0).getPartitionId()!=null) {
			throw new RuntimeException("The master must have a partition id of 'null', found '"+nodes.get(0).getPartitionId()+"'.");
		}
		else {
			listener.println("\t[Master] "+nodes.get(0).toString());
		}
		for(int i =1;i<nodes.size();i++) {
			if(nodes.get(i).getPartitionId()==null || nodes.get(i).getPartitionId()<=0) {
				throw new RuntimeException("Slave "+nodes.get(i)+" has and invalid partition id '"+nodes.get(i).getPartitionId()+"'.");
			}
			else {
				if(rv.containsKey(nodes.get(i).getPartitionId())) {
					throw new RuntimeException("found two compute nodes running the same partition!");
				}
				rv.put(nodes.get(i).getPartitionId(), new compute_node_part(nodes.get(i), null));
				listener.println("\t[Slave](Part="+nodes.get(i).getPartitionId()+") is on "+nodes.get(i).toString());
			}
		}
		return rv;
	}

}
