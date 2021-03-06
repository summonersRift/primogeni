package monitor.core;

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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.ArrayBlockingQueue;

import monitor.commands.AbstractCmd;
import monitor.commands.AreaOfInterestUpdate;
import monitor.commands.CreateDynamicModelNode;
import monitor.commands.HostCmd;
import monitor.commands.MonitorCmd;
import monitor.commands.PrimeAreaOfInterestUpdate;
import monitor.commands.PrimeStateExchangeCmd;
import monitor.commands.PrimeUpdateVizExportRate;
import monitor.commands.SetupContainerCmd;
import monitor.commands.SetupContainerCmd.NIC;
import monitor.commands.SetupSlaveCmd;
import monitor.commands.StateExchangeCmd;
import monitor.commands.UpdateVizExportRate;
import monitor.util.Utils;
import monitor.util.Utils.CmdRv;

import org.apache.mina.core.session.IoSession;

/**
 * @author Nathanael Van Vorst
 * 
 */
public class SlaveCommandExec extends Thread {
	private final Monitor monitor;
	private final ArrayBlockingQueue<AbstractCmd> cmd_queue = new ArrayBlockingQueue<AbstractCmd>(Utils.MAX_OUTSTANDING_CMDS);

	public SlaveCommandExec(Monitor monitor) {
		this.monitor = monitor;
		
	}

	public void processCmd(AbstractCmd cmd) {
		while(true) {
			try {
				cmd_queue.put(cmd);
				return;
			} catch (InterruptedException e) {
			}
		}
	}

	public void run() {
		 int container_count=0;
		while (true) {
			AbstractCmd cmd;
			try {
				cmd = cmd_queue.take();
				String debug_messages=null;
				if(Utils.DEBUG)System.out.println("Running cmd with type=" + cmd.getCommandType());
				debug_messages="\n\nC="+container_count++ + " "+debug_messages+" Running cmd with type=" + cmd.getCommandType();
				String scmd = "";
				CmdRv rv = null;
				//System.out.println("RUNNING:SlaveCommandExec2");
				switch(cmd.getCommandType()) {
				case SETUP_CONTAINER:{
					if(Utils.DEBUG)System.out.println("\n\n Implementing SETUP_CONTAINER cmd:" + cmd);
					debug_messages+="\n\n\n Implementing SETUP_CONTAINER (case for slaves)cmd:" + cmd;
					//Create folders mounted upon the base template using funionfs
					long cont_id = ((SetupContainerCmd)cmd).getContainerId();
					scmd = "perl " + Utils.PRIMOGENI_FOLDER + "/createFolders.pl " + cont_id + " " + cont_id;
					if(Utils.DEBUG) System.out.println("\t\t***Create Folders command:" + scmd);
					debug_messages+="\n\t\t***Create Folders command:" + scmd;
					rv = Utils.executeCommand(scmd);
					if(rv.status != 0){
						if(Utils.DEBUG)System.out.println("Failed to create overlayed folders");
						debug_messages+="\nFAILED to create overlayed folders";
						monitor.commandFinished(true,cmd.getSerialNumber(), rv.status, "Failed to create overlayed folders: "+rv.msg);
						break;
					}

					//Start containers
					scmd = "perl " + Utils.PRIMOGENI_FOLDER + "/startVEs.pl " + cont_id + " " + cont_id;
					
					debug_messages+="\n\t\t***Start containers command:" + scmd;
					

					if(Utils.DEBUG)System.out.println("\t\t***Start containers command:" + scmd);
					rv = Utils.executeCommand(scmd);
					System.out.println("\t\t***Start containers command:" + scmd);
					if(rv.status != 0){
						if(Utils.DEBUG)System.out.println("Failed to start containers");
							
						debug_messages=debug_messages+"\n\t\t***Failed to start containers";
						
						monitor.commandFinished(true,cmd.getSerialNumber(), rv.status, "Failed to start containers: "+rv.msg);
						break;
					}

					//Configure containers' network
					for(NIC nic :  ((SetupContainerCmd)cmd).getNics()) {
						if(Utils.DEBUG)System.out.println("Configure veth" + nic.getId() + ", ip=" + nic.getIp() + ", mac=" + nic.getMac());
						scmd = "perl " + Utils.PRIMOGENI_FOLDER + "/confVENetwork.pl " + cont_id + " veth" + nic.getId() + " " + nic.getIp() + " " + nic.getMac() + " " + "66" + nic.getMac().substring(2);
						
						if(Utils.DEBUG)System.out.println("\t\t***Conf container network command: " + scmd);
						debug_messages=debug_messages+"\n\t\t***Conf container network command: " + scmd;
						
						rv = Utils.executeCommand(scmd);
						if(rv.status != 0){
							if(Utils.DEBUG)System.out.println("Failed to configure container's network");
							debug_messages=debug_messages+"\n\tFailed to configure container's network";
							
							monitor.commandFinished(true,cmd.getSerialNumber(), rv.status,"Failed to configure container's network: "+rv.msg);
							break;
						}
					}

					if(Utils.DEBUG)System.out.println("Commands to setup containers finished sucessfully!");
					
					debug_messages=debug_messages+"\n\t\tCommands to setup containers finished sucessfully!";
					monitor.commandFinished(true,cmd.getSerialNumber());//success
				}
				
				String path="/tmp/pgc_debug_msg";
				
				File file = new File(path);
		        FileWriter fileWriter = null;
				try {
					String hostname=InetAddress.getLocalHost().getHostName();
					fileWriter = new FileWriter(file,true);
					BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
					fileWriter.append("Jprime.monitor(SlaveCommandExec.java)Hostname:"+hostname+"\nDebug Messages:\n"+debug_messages);
			        bufferFileWriter.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				break;
				
				case SETUP_SLAVE:
				{
					String setup_slave_debug_msg=null;
					SetupSlaveCmd setup_slave_cmd = (SetupSlaveCmd)cmd;
					if(Utils.DEBUG)System.out.println("\tExecuting SETUP_SLAVE ");	
					setup_slave_debug_msg="\n\n\tExecuting SETUP_SLAVE ";
					
					
					if(Utils.GEN_SSH_KEYS) {
						setup_slave_debug_msg+="\n  Utils.GEN_SSH_KEYS==true";
						File f = new File(Utils.ROOT_SSH);
						if(!f.exists()) {
							f.mkdirs();
						}
						rv = Utils.executeCommand("chmod 700 "+Utils.ROOT_SSH);
						if( 0 != rv.status) {
							monitor.commandFinished(true,cmd.getSerialNumber(), rv.status, rv.msg);//failure
						}
						if(!Utils.writeToFile(Utils.ROOT_SSH+"id_dsa.pub", setup_slave_cmd.getPublicKey(), "640")) {
							setup_slave_debug_msg+="\n (KeysWroteToSlaves) FAIL: SlaveCommandExec->Utils.writeToFile id_dsa.pub="+setup_slave_cmd.getPublicKey();
							monitor.commandFinished(true,cmd.getSerialNumber(), 100, "failed to write "+Utils.ROOT_SSH+"id_dsa.pub");//failure
							 
						}
						if(!Utils.writeToFile(Utils.ROOT_SSH+"id_dsa", setup_slave_cmd.getPrivateKey(),"600")) {
							setup_slave_debug_msg+="\n FAIL: PRIVATE KEY: id_dsa="+setup_slave_cmd.getPrivateKey();
							monitor.commandFinished(true,cmd.getSerialNumber(), 100, "failed to write "+Utils.ROOT_SSH+"id_dsa");//failure
						}
						if(!Utils.writeToFile(Utils.ROOT_SSH+"authorized_keys2", setup_slave_cmd.getPublicKey(),"640")) {
							setup_slave_debug_msg+="\n updated authorized_keys";
							monitor.commandFinished(true,cmd.getSerialNumber(), 100, "failed to write "+Utils.ROOT_SSH+"authorized_keys2");//failure
						}
						
					}
					if(!Utils.writeToFile(Utils.PVS_CONFIG, setup_slave_cmd.getPvfsConfig().getBytes(),"644")) {
						monitor.commandFinished(true,cmd.getSerialNumber(), 100, "failed to write "+Utils.PVS_CONFIG);//failure
					}
					monitor.commandFinished(true,cmd.getSerialNumber());//success
					monitor.setupDataDB(Utils.BASE_EXP_DIR+"/"+setup_slave_cmd.getExpName(), setup_slave_cmd.getMachineId());

					String path2="/tmp/pgc_debug_msg"; //xxxxxx
					
					File file2 = new File(path2);
			        FileWriter fileWriter2 = null;
					try {
						fileWriter2 = new FileWriter(file2,true);
						BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter2);
						fileWriter2.append("\n----------SETUP_SLAVE Debug Messages:\n"+setup_slave_debug_msg+"\n----------\n");
				        bufferFileWriter.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}

				break;
				
				case MONITOR_CMD: {
					monitor.getBackgroundCmdExecutor().schedule((MonitorCmd)cmd);
				}
				break;
				
				case HOST_CMD: {
					monitor.getBackgroundCmdExecutor().schedule((HostCmd)cmd);
				}
				break;
				
				case STATE_EXCHANGE_CMD:
				{
					StateExchangeCmd se = (StateExchangeCmd)cmd;
					IoSession ps = monitor.getPrimeSession(se.getMachineId(), se.getCommunity_id());
					if(ps == null) {
						monitor.commandFinished(true,
								cmd.getSerialNumber(),
								100, "[slave]Unable to process StateExchange for uid="+se.getUid()
								+". Couldn't find the prime session for part="+se.getMachineId()+", com="+se.getCommunity_id()
								+". coms="+monitor.debug_get_session_info());//failure
					}
					else {
						ps.write(new PrimeStateExchangeCmd(se));
					}
				}
				break;
				
				case CREATE_DYNAMIC_MODEL_NODE:
				{
					final CreateDynamicModelNode cdmn = (CreateDynamicModelNode)cmd;
					final IoSession ps = monitor.getPrimeSessionWithMinCommunityId(cdmn.part_id);
					if(ps == null) {
						monitor.commandFinished(true,
								cmd.getSerialNumber(),
								100, "Unable to process CreateDynamicModelNode for part_id="+cdmn.part_id);//failure
					}
					else {
						ps.write(cdmn);
					}
				}
				break;
				
				case AREA_OF_INTEREST_UPDATE: {
					if(null == monitor.getInitialAOI()) {
						monitor.setInitialAOI(((AreaOfInterestUpdate)cmd).uids);
					}
					PrimeAreaOfInterestUpdate aoi = new PrimeAreaOfInterestUpdate(((AreaOfInterestUpdate)cmd).uids, ((AreaOfInterestUpdate)cmd).add);
					//just broad cast this....
					monitor.broadcastCommand(aoi);
					//master doesn't expect a result from this
					if(Utils.DEBUG)System.out.println("\tbroadcasted AOI:"+aoi);
				}
				break;
				
				case VIZ_EXPORT_RATE_UPDATE: {
					PrimeUpdateVizExportRate vei = new PrimeUpdateVizExportRate(((UpdateVizExportRate)cmd).new_rate);
					//just broad cast this....
					monitor.broadcastCommand(vei);
					//master doesn't expect a result from this
					//if(Utils.DEBUG)System.out.println("\tbroadcasted viz export rate update:"+vei);
				}
				break;
					
				case START_GATEWAY:
				case SHUTOWN_CMD:
				case START_EXPERIMENT:
				case BLOCKING_CMD_RESULT:
				case NON_BLOCKING_CMD_RESULT:
				case CONNNECT_SLAVES:
				case SETUP_SLAVES:
				case SETUP_EXPERIMENT:
					//should not happen
					monitor.commandFinished(true,cmd.getSerialNumber(), 100, "Slaves should not see cmds of type "+cmd.getClass().getSimpleName());//failure
					break;
				default:
					//should not happen
					if(Utils.DEBUG)System.out.println("Received an unknown command type! type="+cmd.getClass().getSimpleName());
					monitor.commandFinished(true,cmd.getSerialNumber(), 100, "[Slave]Unknown command type "+cmd.getClass().getSimpleName());//failure
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}



}
