package jprime.partitioning;

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

/**
 * @author Nathanael Van Vorst
 */
public class UIDRange extends IUIDRange {
	protected long low;
	protected long high;
	
	/**
	 * for javax.persistence
	 */
	public UIDRange(){
		super();
	}
	
	/**
	 * @param low
	 * @param high
	 */
	public UIDRange(long low, long high){
		super();
		this.low=low;
		this.high=high;
	}

	/* (non-Javadoc)
	 * @see jprime.partitioning.IUIDRange#getLow()
	 */
	public long getLow() {
		return low;
	}

	/* (non-Javadoc)
	 * @see jprime.partitioning.IUIDRange#getHigh()
	 */
	public long getHigh() {
		return high;
	}

	/* (non-Javadoc)
	 * @see jprime.partitioning.IUIDRange#setLow(long)
	 */
	public void setLow(long low) {
		this.low = low;
	}

	/* (non-Javadoc)
	 * @see jprime.partitioning.IUIDRange#setHigh(long)
	 */
	public void setHigh(long high) {
		this.high = high;
	}
}
