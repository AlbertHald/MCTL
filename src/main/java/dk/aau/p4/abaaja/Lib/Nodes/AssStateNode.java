package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class AssStateNode extends StateNode {

    //Private variables
    private String _id;
    private ExpNode _expression;

    //getters and setters
    public String get_assignId(){
        return _id;
    }
    public void set_assignId(String id) {
        this._id = id;
    }
    public ExpNode get_assignExp(){
        return _expression;
    }
    public void set_assignExp(ExpNode expression) {
        this._expression = expression;
    }
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
