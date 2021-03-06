/* ------------------------- */
/* ------------------------- */
/*         WARNING: */
/*  THIS FILE IS GENERATED! */
/*        DO NOT EDIT! */
/* ------------------------- */
/* ------------------------- */


package jprime.gen;

import jprime.*;
import jprime.ModelNodeRecord;
import jprime.variable.*;
import org.python.core.PyObject;
import org.python.core.Py;
public abstract class CBRAlias extends jprime.ApplicationSession.ApplicationSessionAlias implements jprime.gen.ICBRAlias {
	public CBRAlias(IModelNode parent, jprime.CBR.ICBR referencedNode) {
		super(parent,(jprime.CBR.ICBR)referencedNode);
	}
	public CBRAlias(ModelNodeRecord rec){ super(rec); }
	public CBRAlias(PyObject[] v, String[] s){super(v,s);}
	public CBRAlias(IModelNode parent){
		super(parent);
	}
	/**
	 * @return the interface which this node implements
	 */
	public Class<?> getNodeType() {
		return jprime.CBR.ICBR.class;
	}
	/**
	 * @param used by replicas to do a deep copy of the node.
	 */
	public jprime.ModelNode deepCopy(jprime.ModelNode parent) {
		jprime.CBR.CBRAliasReplica c = new jprime.CBR.CBRAliasReplica(this.getName(),(IModelNode)parent,this);
		return c;
	}
	public static boolean isSubType(IModelNode n) {
		return isSubType(n.getTypeId());
	}
	public static boolean isSubType(int id) {
		switch(id) {
			case 1105: //CBRAlias
			case 1217: //CBRAliasReplica
				return true;
		}
		return false;
	}

	/* (non-Javadoc)
	* @see jprime.IModelNode#getTypeId()
	 */
	public abstract int getTypeId();

	/**
	 * @return Listening port port UDP datagrams.
	 */
	public jprime.variable.IntegerVariable getListeningPort() {
		return (jprime.variable.IntegerVariable)((ICBR)deference()).getListeningPort();
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setListeningPort(String value) {
		((ICBR)deference()).setListeningPort(value);
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setListeningPort(long value) {
		((ICBR)deference()).setListeningPort(value);
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setListeningPort(jprime.variable.SymbolVariable value) {
		((ICBR)deference()).setListeningPort(value);
	}

	/**
	 * @return Number of bytes received from all sessions
	 */
	public jprime.variable.IntegerVariable getTotalBytesReceived() {
		return (jprime.variable.IntegerVariable)((ICBR)deference()).getTotalBytesReceived();
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setTotalBytesReceived(String value) {
		((ICBR)deference()).setTotalBytesReceived(value);
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setTotalBytesReceived(long value) {
		((ICBR)deference()).setTotalBytesReceived(value);
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setTotalBytesReceived(jprime.variable.SymbolVariable value) {
		((ICBR)deference()).setTotalBytesReceived(value);
	}

	/**
	 * @return Number of bytes sent from all sessions
	 */
	public jprime.variable.IntegerVariable getTotalBytesSent() {
		return (jprime.variable.IntegerVariable)((ICBR)deference()).getTotalBytesSent();
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setTotalBytesSent(String value) {
		((ICBR)deference()).setTotalBytesSent(value);
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setTotalBytesSent(long value) {
		((ICBR)deference()).setTotalBytesSent(value);
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setTotalBytesSent(jprime.variable.SymbolVariable value) {
		((ICBR)deference()).setTotalBytesSent(value);
	}

	/**
	 * @return a list of ids of the possible type of attribute this model node type can have
	 */
	public java.util.ArrayList<Integer> getAttrIds() {
		return jprime.gen.CBR.attrIds;
	}

	/**
	 * @param kid the child to add
	 */

	/**
	 * @param visitor a generic visitor
	 */
	public abstract void accept(jprime.visitors.IGenericVisitor visitor);
}
