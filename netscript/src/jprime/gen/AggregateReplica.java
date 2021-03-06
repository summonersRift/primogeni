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
public abstract class AggregateReplica extends jprime.ModelNodeReplica implements jprime.gen.IAggregate {

	/* used to enforce the minimum/maximum child requirement */

	public void enforceChildConstraints() {
		super.enforceChildConstraints();

	}
	public AggregateReplica(String name, IModelNode parent, jprime.Aggregate.IAggregate referencedNode) {
		super(name,parent,referencedNode);
	}
	public AggregateReplica(ModelNodeRecord rec){ super(rec); }
	public AggregateReplica(PyObject[] v, String[] s){super(v,s);}
	/**
	 * @return the interface which this node implements
	 */
	public Class<?> getNodeType() {
		return jprime.Aggregate.IAggregate.class;
	}
	/**
	 * @param used by replicas to do a deep copy of the node.
	 */
	public jprime.ModelNode deepCopy(jprime.ModelNode parent) {
		doing_deep_copy=true;
		jprime.Aggregate.AggregateReplica c = new jprime.Aggregate.AggregateReplica(this.getName(), (IModelNode)parent,(jprime.Aggregate.IAggregate)this);
		doing_deep_copy=false;
		return c;
	}
	public static boolean isSubType(IModelNode n) {
		return isSubType(n.getTypeId());
	}
	public static boolean isSubType(int id) {
		switch(id) {
			case 1126: //AggregateReplica
			case 1127: //VizAggregateReplica
			case 1182: //AggregateAliasReplica
			case 1183: //VizAggregateAliasReplica
				return true;
		}
		return false;
	}

	/* (non-Javadoc)
	* @see jprime.IModelNode#getTypeId()
	 */
	public abstract int getTypeId();

	/**
	 * @return the id of the variable which will be aggregated.
	 */
	public jprime.variable.IntegerVariable getVarId() {
		jprime.variable.IntegerVariable temp = (jprime.variable.IntegerVariable)getAttributeByName(ModelNodeVariable.var_id());
		if(null!=temp) return temp;
		return (jprime.variable.IntegerVariable)this.getReplicatedNode().getAttributeByName(ModelNodeVariable.var_id());
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setVarId(String value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.var_id());
		if(temp==null){
			temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.var_id(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.IntegerVariable)){
				temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.var_id(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.IntegerVariable)temp).setValue(value); }
		}
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setVarId(long value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.var_id());
		if(temp==null){
			temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.var_id(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.IntegerVariable)){
				temp=new jprime.variable.IntegerVariable(jprime.gen.ModelNodeVariable.var_id(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.IntegerVariable)temp).setValue(value); }
		}
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setVarId(jprime.variable.SymbolVariable value) {
		if(value==null)throw new RuntimeException("attr was null");
		if(value.getDBName() != -1) throw new RuntimeException("the attr was already attached to another model node!");
		value.attachToNode(this,jprime.gen.ModelNodeVariable.var_id());
		addAttr(value);
	}

	/**
	 * @return a list of entity relative ids which will have their state aggregated
	 */
	public ResourceIdentifierVariable getToAggregate() {
		ResourceIdentifierVariable temp = (ResourceIdentifierVariable)getAttributeByName(ModelNodeVariable.to_aggregate());
		if(null!=temp) return temp;
		return (ResourceIdentifierVariable)this.getReplicatedNode().getAttributeByName(ModelNodeVariable.to_aggregate());
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setToAggregate(String value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.to_aggregate());
		if(temp==null){
			temp=new ResourceIdentifierVariable(jprime.gen.ModelNodeVariable.to_aggregate(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof ResourceIdentifierVariable)){
				temp=new ResourceIdentifierVariable(jprime.gen.ModelNodeVariable.to_aggregate(),value);
				addAttr(temp);
			}
			else { ((ResourceIdentifierVariable)temp).setValue(value); }
		}
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setToAggregate(jprime.ResourceIdentifier.ResourceID value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.to_aggregate());
		if(temp==null){
			temp=new ResourceIdentifierVariable(jprime.gen.ModelNodeVariable.to_aggregate(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof ResourceIdentifierVariable)){
				temp=new ResourceIdentifierVariable(jprime.gen.ModelNodeVariable.to_aggregate(),value);
				addAttr(temp);
			}
			else { ((ResourceIdentifierVariable)temp).setValue(value); }
		}
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setToAggregate(jprime.variable.SymbolVariable value) {
		if(value==null)throw new RuntimeException("attr was null");
		if(value.getDBName() != -1) throw new RuntimeException("the attr was already attached to another model node!");
		value.attachToNode(this,jprime.gen.ModelNodeVariable.to_aggregate());
		addAttr(value);
	}

	/**
	 * @return min of all the values at the sample time.
	 */
	public jprime.variable.FloatingPointNumberVariable getMin() {
		jprime.variable.FloatingPointNumberVariable temp = (jprime.variable.FloatingPointNumberVariable)getAttributeByName(ModelNodeVariable.min());
		if(null!=temp) return temp;
		return (jprime.variable.FloatingPointNumberVariable)this.getReplicatedNode().getAttributeByName(ModelNodeVariable.min());
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setMin(String value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.min());
		if(temp==null){
			temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.min(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.FloatingPointNumberVariable)){
				temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.min(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.FloatingPointNumberVariable)temp).setValue(value); }
		}
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setMin(float value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.min());
		if(temp==null){
			temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.min(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.FloatingPointNumberVariable)){
				temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.min(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.FloatingPointNumberVariable)temp).setValue(value); }
		}
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setMin(jprime.variable.SymbolVariable value) {
		if(value==null)throw new RuntimeException("attr was null");
		if(value.getDBName() != -1) throw new RuntimeException("the attr was already attached to another model node!");
		value.attachToNode(this,jprime.gen.ModelNodeVariable.min());
		addAttr(value);
	}

	/**
	 * @return max of all the values at the sample time.
	 */
	public jprime.variable.FloatingPointNumberVariable getMax() {
		jprime.variable.FloatingPointNumberVariable temp = (jprime.variable.FloatingPointNumberVariable)getAttributeByName(ModelNodeVariable.max());
		if(null!=temp) return temp;
		return (jprime.variable.FloatingPointNumberVariable)this.getReplicatedNode().getAttributeByName(ModelNodeVariable.max());
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setMax(String value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.max());
		if(temp==null){
			temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.max(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.FloatingPointNumberVariable)){
				temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.max(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.FloatingPointNumberVariable)temp).setValue(value); }
		}
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setMax(float value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.max());
		if(temp==null){
			temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.max(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.FloatingPointNumberVariable)){
				temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.max(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.FloatingPointNumberVariable)temp).setValue(value); }
		}
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setMax(jprime.variable.SymbolVariable value) {
		if(value==null)throw new RuntimeException("attr was null");
		if(value.getDBName() != -1) throw new RuntimeException("the attr was already attached to another model node!");
		value.attachToNode(this,jprime.gen.ModelNodeVariable.max());
		addAttr(value);
	}

	/**
	 * @return the number of samples.
	 */
	public jprime.variable.FloatingPointNumberVariable getSampleCount() {
		jprime.variable.FloatingPointNumberVariable temp = (jprime.variable.FloatingPointNumberVariable)getAttributeByName(ModelNodeVariable.sample_count());
		if(null!=temp) return temp;
		return (jprime.variable.FloatingPointNumberVariable)this.getReplicatedNode().getAttributeByName(ModelNodeVariable.sample_count());
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setSampleCount(String value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.sample_count());
		if(temp==null){
			temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.sample_count(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.FloatingPointNumberVariable)){
				temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.sample_count(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.FloatingPointNumberVariable)temp).setValue(value); }
		}
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setSampleCount(float value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.sample_count());
		if(temp==null){
			temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.sample_count(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.FloatingPointNumberVariable)){
				temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.sample_count(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.FloatingPointNumberVariable)temp).setValue(value); }
		}
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setSampleCount(jprime.variable.SymbolVariable value) {
		if(value==null)throw new RuntimeException("attr was null");
		if(value.getDBName() != -1) throw new RuntimeException("the attr was already attached to another model node!");
		value.attachToNode(this,jprime.gen.ModelNodeVariable.sample_count());
		addAttr(value);
	}

	/**
	 * @return total of all the values at the sample time.
	 */
	public jprime.variable.FloatingPointNumberVariable getSum() {
		jprime.variable.FloatingPointNumberVariable temp = (jprime.variable.FloatingPointNumberVariable)getAttributeByName(ModelNodeVariable.sum());
		if(null!=temp) return temp;
		return (jprime.variable.FloatingPointNumberVariable)this.getReplicatedNode().getAttributeByName(ModelNodeVariable.sum());
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setSum(String value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.sum());
		if(temp==null){
			temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.sum(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.FloatingPointNumberVariable)){
				temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.sum(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.FloatingPointNumberVariable)temp).setValue(value); }
		}
	}

	/**
	 * Set the attribute to the static value 'value'.
	 * @param value the value
	 */
	public void setSum(float value) {
		jprime.variable.ModelNodeVariable temp = getAttributeByName(jprime.gen.ModelNodeVariable.sum());
		if(temp==null){
			temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.sum(),value);
			addAttr(temp);
		}
		else{
			if(! (temp instanceof jprime.variable.FloatingPointNumberVariable)){
				temp=new jprime.variable.FloatingPointNumberVariable(jprime.gen.ModelNodeVariable.sum(),value);
				addAttr(temp);
			}
			else { ((jprime.variable.FloatingPointNumberVariable)temp).setValue(value); }
		}
	}

	/**
	 * Have the attribute be bound to the value of the symbol at model instantiation.
	 * @param value the value
	 */
	public void setSum(jprime.variable.SymbolVariable value) {
		if(value==null)throw new RuntimeException("attr was null");
		if(value.getDBName() != -1) throw new RuntimeException("the attr was already attached to another model node!");
		value.attachToNode(this,jprime.gen.ModelNodeVariable.sum());
		addAttr(value);
	}

	/**
	 * @return a list of ids of the possible type of attribute this model node type can have
	 */
	public java.util.ArrayList<Integer> getAttrIds() {
		return jprime.gen.Aggregate.attrIds;
	}

	/**
	 * @param visitor a generic visitor
	 */
	public abstract void accept(jprime.visitors.IGenericVisitor visitor);
}
