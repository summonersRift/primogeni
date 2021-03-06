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

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @author Miguel Erazol Erazo
 *
 */
public class SimEDSSSessionHandler extends IoHandlerAdapter{
	//private final SimEDSS phyEDSSOwner;
	
	SimEDSSSessionHandler(SimEDSS owner){
		//this.phyEDSSOwner = owner;
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
    	
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		
	}

	@Override
    public void sessionCreated(IoSession session) throws Exception {
		
    }

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		
	}
	
    @Override
	public void messageSent(IoSession session, Object message) throws Exception {
    	
	}

	@Override
    public void messageReceived(IoSession session, Object message) throws Exception {
		
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
    	
    }
}
