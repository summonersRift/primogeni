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
public abstract class HTTPServerAlias extends jprime.ApplicationSession.ApplicationSessionAlias implements jprime.gen.IHTTPServerAlias {
	public HTTPServerAlias(IModelNode parent, jprime.HTTPServer.IHTTPServer referencedNode) {
		super(parent,(jprime.HTTPServer.IHTTPServer)referencedNode);
	}
	public HTTPServerAlias(ModelNodeRecord rec){ super(rec); }
	public HTTPServerAlias(PyObject[] v, String[] s){super(v,s);}
	public HTTPServerAlias(IModelNode parent){
		super(parent);
	}
	/**
	 * @return the interface which this node implements
	 */
	public Class<?> getNodeType() {
		return jprime.HTTPServer.IHTTPServer.class;
	}
	/**
	 * @param used by replicas to do a deep copy of the node.
	 */
	public jprime.ModelNode deepCopy(jprime.ModelNode parent) {
		jprime.HTTPServer.HTTPServerAliasReplica c = new jprime.HTTPServer.HTTPServerAliasReplica(this.getName(),(IModelNode)parent,this);
		return c;
	}
	public static boolean isSubType(IModelNode n) {
		return isSubType(n.getTypeId());
	}
	public static boolean isSubType(int id) {
		switch(id) {
			case 1109: //HTTPServerAlias
			case 1221: //HTTPServerAliasReplica
				return true;
		}
		return false;
	}

	/* (non-Javadoc)
	* @see jprime.IModelNode#getTypeId()
	 */
	public abstract int getTypeId();

	/**
	 * @return Listening port for incoming connections.
	 */
	public jprime.variable.IntegerVariable getListeningPort() {
		return (jprime.variable.IntegerVariable)((IHTTPServer)deference()).getListeningPort();
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setListeningPort(String value) {
		((IHTTPServer)deference()).setListeningPort(value);
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setListeningPort(long value) {
		((IHTTPServer)deference()).setListeningPort(value);
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setListeningPort(jprime.variable.SymbolVariable value) {
		((IHTTPServer)deference()).setListeningPort(value);
	}

	/**
	 * @return Number of bytes received so far from all sessions
	 */
	public jprime.variable.IntegerVariable getBytesReceived() {
		return (jprime.variable.IntegerVariable)((IHTTPServer)deference()).getBytesReceived();
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setBytesReceived(String value) {
		((IHTTPServer)deference()).setBytesReceived(value);
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setBytesReceived(long value) {
		((IHTTPServer)deference()).setBytesReceived(value);
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setBytesReceived(jprime.variable.SymbolVariable value) {
		((IHTTPServer)deference()).setBytesReceived(value);
	}

	/**
	 * @return Number of requests received so far from all sessions
	 */
	public jprime.variable.IntegerVariable getRequestsReceived() {
		return (jprime.variable.IntegerVariable)((IHTTPServer)deference()).getRequestsReceived();
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setRequestsReceived(String value) {
		((IHTTPServer)deference()).setRequestsReceived(value);
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setRequestsReceived(long value) {
		((IHTTPServer)deference()).setRequestsReceived(value);
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setRequestsReceived(jprime.variable.SymbolVariable value) {
		((IHTTPServer)deference()).setRequestsReceived(value);
	}

	/**
	 * @return a list of ids of the possible type of attribute this model node type can have
	 */
	public java.util.ArrayList<Integer> getAttrIds() {
		return jprime.gen.HTTPServer.attrIds;
	}

	/**
	 * @param kid the child to add
	 */

	/**
	 * @param visitor a generic visitor
	 */
	public abstract void accept(jprime.visitors.IGenericVisitor visitor);
}
