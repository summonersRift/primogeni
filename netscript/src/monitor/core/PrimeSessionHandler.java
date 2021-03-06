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

import monitor.commands.ComPartAdvertCmd;
import monitor.commands.PrimeStateExchangeCmd;
import monitor.util.Utils;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;


/**
 * @author Nathanael Van Vorst
 *
 */
public class PrimeSessionHandler extends IoHandlerAdapter
{
    public static final String COM_ID="com_id";
    private final IPrimeStateListener listener;
    
    public PrimeSessionHandler(IPrimeStateListener listener){
    	this.listener=listener;
    }
    
    @Override
	public void sessionClosed(IoSession session) throws Exception {
    	//if(Utils.DEBUG)System.out.println("simulator disconencted");
//    	listener.;
		
		//File file = new File("/tmp/pgc_debug_msg");
        FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(new File("/tmp/pgc_debug_msg"),true);
			BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
			fileWriter.append("PrimeSessionhandler.java GOT_EXCEPTION");
	        bufferFileWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	super.sessionClosed(session);
    	
    	//Obaida
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		super.sessionIdle(session, status);
	}

	@Override
    public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
    }

	@Override
	public void sessionOpened(IoSession session) throws Exception {
    	//if(Utils.DEBUG)System.out.println("simulator conencted");
		super.sessionOpened(session);
	}
	
    @Override
	public void messageSent(IoSession session, Object message) throws Exception {
    	super.messageSent(session, message);
	}
    
	@Override
    public void messageReceived(IoSession session, Object message) throws Exception {
		super.messageReceived(session, message);
		
		if(message instanceof PrimeStateExchangeCmd) {
			listener.handleStateUpdate((PrimeStateExchangeCmd)message,(Integer)session.getAttribute(COM_ID));
		}
		else if(message instanceof ComPartAdvertCmd) {
			session.setAttribute(COM_ID, new Integer(((ComPartAdvertCmd)message).getCom_id()));
			listener.handleAdvert((ComPartAdvertCmd)message,session);
		}
		else{
			throw new RuntimeException("Invalid message:"+message);
		}
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
    	super.exceptionCaught(session, cause);
        // close the connection on exceptional situation
    	if(Utils.DEBUG)System.out.println("An un-caught expection was enountered! in "+this.getClass().getSimpleName());
    	cause.printStackTrace();
    }
}