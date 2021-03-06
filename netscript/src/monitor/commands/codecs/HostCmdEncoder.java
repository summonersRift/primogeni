package monitor.commands.codecs;

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

import java.nio.charset.CharacterCodingException;

import monitor.commands.CommandType;
import monitor.commands.HostCmd;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
/**
 * @author Nathanael Van Vorst
 *
 * @param <T>>
 */
public class HostCmdEncoder<T extends HostCmd> extends AbstractCmdEncoder<T> {
    public HostCmdEncoder() {
        super(CommandType.HOST_CMD);
    }
    @Override
    protected void encodeBody(IoSession session, T message, IoBuffer out) {
    	out.putLong(message.getHostId());
    	out.putLong(message.getDelay());
    	out.putLong(message.getMaxRuntime());
    	out.putInt(message.shouldCheckReturnCode()?1:0);
    	out.putInt(message.getCmd().length());
    	try {
			out.putString(message.getCmd(),message.getCmd().length(),cse);
		} catch (CharacterCodingException e) {
			throw new RuntimeException(e);
		}
    }
}
