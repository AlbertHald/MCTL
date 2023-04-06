package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class InvokeNode extends StateNode {
    //Private variables
    private String _id;
    private ExpNode _actualParameters;

    //getters and setters
    public String get_invokeId(){
        return _id;
    }
    public void set_invokeId(String id) {
        this._id = id;
    }
    public ExpNode get_paramExp(){
        return _actualParameters;
    }
    public void set_paramExp(ExpNode expression) {
        this._actualParameters = expression;
    }
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
