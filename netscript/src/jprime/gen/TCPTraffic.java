/* ------------------------- */
/* ------------------------- */
/*         WARNING: */
/*  THIS FILE IS GENERATED! */
/*        DO NOT EDIT! */
/* ------------------------- */
/* ------------------------- */


package jprime.gen;

import jprime.*;
import jprime.variable.*;
import jprime.ModelNodeRecord;
import org.python.core.PyObject;
import org.python.core.Py;
public abstract class TCPTraffic extends jprime.StaticTrafficType.StaticTrafficType implements jprime.gen.ITCPTraffic {

	/* used to enforce the minimum/maximum child requirement */

	public void enforceChildConstraints() {
		super.enforceChildConstraints();

	}
	public TCPTraffic(PyObject[] v, String[] s){super(v,s);}
	public TCPTraffic(ModelNodeRecord rec){ super(rec); }
	public TCPTraffic(IModelNode parent){ super(parent); }
	/**
	 * @return the interface which this node implements
	 */
	public Class<?> getNodeType() {
		return jprime.TCPTraffic.ITCPTraffic.class;
	}
	/**
	 * @param used by replicas to do a deep copy of the node.
	 */
	public jprime.ModelNode deepCopy(jprime.ModelNode parent) {
		jprime.TCPTraffic.TCPTrafficReplica c = new jprime.TCPTraffic.TCPTrafficReplica(this.getName(),(IModelNode)parent,(jprime.TCPTraffic.ITCPTraffic)this);
		return c;
	}
	public static boolean isSubType(IModelNode n) {
		return isSubType(n.getTypeId());
	}
	public static boolean isSubType(int id) {
		switch(id) {
			case 1028: //TCPTraffic
			case 1084: //TCPTrafficAlias
			case 1140: //TCPTrafficReplica
			case 1196: //TCPTrafficAliasReplica
				return true;
		}
		return false;
	}

	/* (non-Javadoc)
	* @see jprime.IModelNode#getTypeId()
	 */
	public abstract int getTypeId();
	public final static java.util.ArrayList<Integer> attrIds=new java.util.ArrayList<Integer>();
	static {
		attrIds.add(34);
		attrIds.add(39);
		attrIds.add(38);
		attrIds.add(155);
		attrIds.add(95);
		attrIds.add(24);
		attrIds.add(131);
	}

	/**
	 * @return The destination port for an HTTP connection.
	 */
	public jprime.variable.IntegerVariable getDstPort() {
		return (jprime.variable.IntegerVariable)getAttributeByName(ModelNodeVariable.dst_port());
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setDstPort(String value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.dst_port());
		if(temp==null){
			temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.dst_port(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.IntegerVariable)){
				temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.dst_port(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.IntegerVariable)temp).setValue(value); }
		}
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setDstPort(long value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.dst_port());
		if(temp==null){
			temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.dst_port(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.IntegerVariable)){
				temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.dst_port(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.IntegerVariable)temp).setValue(value); }
		}
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setDstPort(jprime.variable.SymbolVariable value) {
		if(value==null)throw new RuntimeException("attr was null");
		if(value.getDBName() != -1) throw new RuntimeException("the attr was already attached to another model node!");
		value.attachToNode(this,jprime.gen.ModelNodeVariable.dst_port());
		addAttr(value);
	}

	/**
	 * @return The size to request to the server.
	 */
	public jprime.variable.IntegerVariable getFileSize() {
		return (jprime.variable.IntegerVariable)getAttributeByName(ModelNodeVariable.file_size());
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setFileSize(String value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.file_size());
		if(temp==null){
			temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.file_size(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.IntegerVariable)){
				temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.file_size(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.IntegerVariable)temp).setValue(value); }
		}
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setFileSize(long value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.file_size());
		if(temp==null){
			temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.file_size(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.IntegerVariable)){
				temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.file_size(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.IntegerVariable)temp).setValue(value); }
		}
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setFileSize(jprime.variable.SymbolVariable value) {
		if(value==null)throw new RuntimeException("attr was null");
		if(value.getDBName() != -1) throw new RuntimeException("the attr was already attached to another model node!");
		value.attachToNode(this,jprime.gen.ModelNodeVariable.file_size());
		addAttr(value);
	}

	/**
	 * @return Whether the file size are modeled by a concatenation of bounded Weibull and Pareto distributions
	 */
	public jprime.variable.BooleanVariable getFilePareto() {
		return (jprime.variable.BooleanVariable)getAttributeByName(ModelNodeVariable.file_pareto());
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setFilePareto(String value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.file_pareto());
		if(temp==null){
			temp=new jprime.variable.BooleanVariable(jprime.gen.ModelNodeVariable.file_pareto(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.BooleanVariable)){
				temp=new jprime.variable.BooleanVariable(jprime.gen.ModelNodeVariable.file_pareto(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.BooleanVariable)temp).setValue(value); }
		}
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setFilePareto(boolean value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.file_pareto());
		if(temp==null){
			temp=new jprime.variable.BooleanVariable(jprime.gen.ModelNodeVariable.file_pareto(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.BooleanVariable)){
				temp=new jprime.variable.BooleanVariable(jprime.gen.ModelNodeVariable.file_pareto(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.BooleanVariable)temp).setValue(value); }
		}
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setFilePareto(jprime.variable.SymbolVariable value) {
		if(value==null)throw new RuntimeException("attr was null");
		if(value.getDBName() != -1) throw new RuntimeException("the attr was already attached to another model node!");
		value.attachToNode(this,jprime.gen.ModelNodeVariable.file_pareto());
		addAttr(value);
	}

	/**
	 * @return Whether this host will request packets from a simulated or emulated entity.
	 */
	public jprime.variable.BooleanVariable getToEmulated() {
		return (jprime.variable.BooleanVariable)getAttributeByName(ModelNodeVariable.to_emulated());
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setToEmulated(String value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.to_emulated());
		if(temp==null){
			temp=new jprime.variable.BooleanVariable(jprime.gen.ModelNodeVariable.to_emulated(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.BooleanVariable)){
				temp=new jprime.variable.BooleanVariable(jprime.gen.ModelNodeVariable.to_emulated(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.BooleanVariable)temp).setValue(value); }
		}
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setToEmulated(boolean value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.to_emulated());
		if(temp==null){
			temp=new jprime.variable.BooleanVariable(jprime.gen.ModelNodeVariable.to_emulated(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.BooleanVariable)){
				temp=new jprime.variable.BooleanVariable(jprime.gen.ModelNodeVariable.to_emulated(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.BooleanVariable)temp).setValue(value); }
		}
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setToEmulated(jprime.variable.SymbolVariable value) {
		if(value==null)throw new RuntimeException("attr was null");
		if(value.getDBName() != -1) throw new RuntimeException("the attr was already attached to another model node!");
		value.attachToNode(this,jprime.gen.ModelNodeVariable.to_emulated());
		addAttr(value);
	}

	/**
	 * @return Number of session.
	 */
	public jprime.variable.IntegerVariable getNumberOfSessions() {
		return (jprime.variable.IntegerVariable)getAttributeByName(ModelNodeVariable.number_of_sessions());
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setNumberOfSessions(String value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.number_of_sessions());
		if(temp==null){
			temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.number_of_sessions(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.IntegerVariable)){
				temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.number_of_sessions(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.IntegerVariable)temp).setValue(value); }
		}
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setNumberOfSessions(long value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.number_of_sessions());
		if(temp==null){
			temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.number_of_sessions(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.IntegerVariable)){
				temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.number_of_sessions(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.IntegerVariable)temp).setValue(value); }
		}
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setNumberOfSessions(jprime.variable.SymbolVariable value) {
		if(value==null)throw new RuntimeException("attr was null");
		if(value.getDBName() != -1) throw new RuntimeException("the attr was already attached to another model node!");
		value.attachToNode(this,jprime.gen.ModelNodeVariable.number_of_sessions());
		addAttr(value);
	}

	/**
	 * @return Number of connections within a session.
	 */
	public jprime.variable.IntegerVariable getConnectionsPerSession() {
		return (jprime.variable.IntegerVariable)getAttributeByName(ModelNodeVariable.connections_per_session());
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setConnectionsPerSession(String value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.connections_per_session());
		if(temp==null){
			temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.connections_per_session(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.IntegerVariable)){
				temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.connections_per_session(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.IntegerVariable)temp).setValue(value); }
		}
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setConnectionsPerSession(long value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.connections_per_session());
		if(temp==null){
			temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.connections_per_session(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.IntegerVariable)){
				temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.connections_per_session(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.IntegerVariable)temp).setValue(value); }
		}
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setConnectionsPerSession(jprime.variable.SymbolVariable value) {
		if(value==null)throw new RuntimeException("attr was null");
		if(value.getDBName() != -1) throw new RuntimeException("the attr was already attached to another model node!");
		value.attachToNode(this,jprime.gen.ModelNodeVariable.connections_per_session());
		addAttr(value);
	}

	/**
	 * @return Session timeout in seconds.
	 */
	public jprime.variable.IntegerVariable getSessionTimeout() {
		return (jprime.variable.IntegerVariable)getAttributeByName(ModelNodeVariable.session_timeout());
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setSessionTimeout(String value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.session_timeout());
		if(temp==null){
			temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.session_timeout(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.IntegerVariable)){
				temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.session_timeout(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.IntegerVariable)temp).setValue(value); }
		}
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setSessionTimeout(long value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.session_timeout());
		if(temp==null){
			temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.session_timeout(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.IntegerVariable)){
				temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.session_timeout(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.IntegerVariable)temp).setValue(value); }
		}
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setSessionTimeout(jprime.variable.SymbolVariable value) {
		if(value==null)throw new RuntimeException("attr was null");
		if(value.getDBName() != -1) throw new RuntimeException("the attr was already attached to another model node!");
		value.attachToNode(this,jprime.gen.ModelNodeVariable.session_timeout());
		addAttr(value);
	}

	/**
	 * @return a list of ids of the possible type of attribute this model node type can have
	 */
	public java.util.ArrayList<Integer> getAttrIds() {
		return attrIds;
	}

	/**
	 * @param visitor a generic visitor
	 */
	public abstract void accept(jprime.visitors.IGenericVisitor visitor);
}
