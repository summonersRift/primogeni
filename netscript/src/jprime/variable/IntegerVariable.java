package jprime.variable;

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

/* $if SEPARATE_PROP_TABLE $
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import jprime.EntityFactory;
import jprime.IModelNode;
import jprime.Metadata;
import jprime.variable.Dataset.SimpleDatum;
import jprime.visitors.TLVVisitor;
import jprime.visitors.TLVVisitor.TLVType;
$else$ */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import jprime.EntityFactory;
import jprime.IModelNode;
import jprime.ModelNode;
import jprime.variable.Dataset.SimpleDatum;
import jprime.visitors.TLVVisitor;
import jprime.visitors.TLVVisitor.TLVType;
/* $endif$ */


/**
 * @author Nathanael Van Vorst
 */
public class IntegerVariable extends ModelNodeVariable implements Serializable {
	private static final long serialVersionUID = 1807741001838289875L;

	/* $if false == SEPARATE_PROP_TABLE $ */
	private long value;
	/* $endif$ */
	
	/* $if SEPARATE_PROP_TABLE $
	public IntegerVariable(Metadata meta, long dbid, long db_owner_id, int db_type, int db_name, String value) {
		super(meta,dbid,db_owner_id,EntityFactory.IntegerVariable, db_name,value);
	}
	$else$ */
	public IntegerVariable(ModelNode owner, int db_name, int value) {
		super(owner,db_name);
		this.value=value;
	}
	public IntegerVariable(ModelNode owner, int db_name, long value) {
		super(owner,db_name);
		this.value=value;
	}
	public IntegerVariable(ModelNode owner, int db_name, String value) {
		super(owner,db_name);
		this.value=Long.parseLong(value);
	}
	/* $endif$ */
	
	/* $if SEPARATE_PROP_TABLE $
	public IntegerVariable(int db_name, int value) {
		super(getClassTypeId(), db_name, Long.toString(value));
	}
	public IntegerVariable(int db_name, long value) {
		super(getClassTypeId(), db_name, Long.toString(value));
	}
	$else$ */
	public IntegerVariable(int db_name, int value) {
		super(db_name);
		this.value=value;
	}
	public IntegerVariable(int db_name, long value) {
		super(db_name);
		this.value=value;
	}
	/* $endif$ */
	
	/* $if SEPARATE_PROP_TABLE $
	public IntegerVariable(int db_name, String value) {
		super(getClassTypeId(),db_name, value);
	}
	$else$ */
	public IntegerVariable(int db_name, String value) {
		super(db_name);
		this.value=Long.parseLong(value);
	}
	/* $endif$ */
	
	/* $if SEPARATE_PROP_TABLE $
	public IntegerVariable() {
		super();
	}
	$else$ */
	public IntegerVariable() {
		super();
		this.value=0;
	}
	/* $endif$ */
	
	/* $if false == SEPARATE_PROP_TABLE $ */
	public void setValueAsString(String val) {
		this.value=Long.parseLong(val);
		modified();
	}
    public boolean equals(Object obj) {
    	return (obj instanceof IntegerVariable && value == ((IntegerVariable)obj).value);
    }
    public String toString() {
    	return Long.toString(value);
    }
	/* $endif$ */

	/* $if SEPARATE_PROP_TABLE $
	public void setValue(String v) {
		this.value=v;
		modified(Modified.ALL);
	}
	public void setValue(int v) {
		value=Long.toString(v);
		modified(Modified.ALL);
	}
	public void setValue(long v) {
		value=Long.toString(v);
		modified(Modified.ALL);
	}
	public long getValue() {
		return Long.parseLong(value);
	}
	$else$ */
	public void setValue(String v) {
		value=Long.parseLong(v);
		modified();
	}
	public void setValue(int v) {
		value=v;
		modified();
	}
	public void setValue(long v) {
		value=v;
		modified();
	}
	public long getValue() {
		return value;
	}
	/* $endif$ */
	
	/**
	 * @param ds
	 * @return
	 */
	public long getRuntimeValue(IModelNode owner, Dataset ds) {
		if(ds == null)
			return getValue();
		SimpleDatum rv = ds.getMostRecentDatum(this.getDBName(), owner.getUID());
		if(rv==null) {
			return getValue();
		}
		return Long.parseLong(rv.value);
	}

	/* (non-Javadoc)
	 * @see jprime.variable.ModelNodeVariable#getTypeId()
	 */
	public int getTypeId() {
		return getClassTypeId();
	}
	
	/**
	 * @return
	 */
	public static int getClassTypeId() {
		return EntityFactory.IntegerVariable;
	}
	
	/* (non-Javadoc)
	 * @see jprime.variable.ModelNodeVariable#getTLVType()
	 */
	@Override
	public TLVVisitor.TLVType getTLVType() {
		return TLVVisitor.TLVType.LONG;
	}
	
	/* (non-Javadoc)
	 * @see jprime.variable.ModelNodeVariable#encodeTLV()
	 */
	@Override
	public String encodeTLV() {
		String attr = TLVVisitor.makeTLV(TLVType.LONG,Integer.toString(this.getDBName()).length(),Integer.toString(this.getDBName()));
		String value = this.toString();
		attr+= TLVVisitor.makeTLV(TLVType.LONG,value.length(),value);
		return TLVVisitor.makeTLV(TLVType.PROPERTY,attr.length(),attr);
	}

	public void flushObject(DataOutputStream out) throws Exception {
		/* $if SEPARATE_PROP_TABLE $
		out.writeLong(getValue());
		$else$ */
		out.writeLong(value);
		/* $endif$ */
	}
	public void initObject(DataInputStream in) throws Exception {
		/* $if SEPARATE_PROP_TABLE $
		value=Long.toString(in.readLong());
		$else$ */
		value=in.readLong();
		/* $endif$ */
	}
	public int packingSize() {
		return Long.SIZE/8;
	}

}
