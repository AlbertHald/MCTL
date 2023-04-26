package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class AssStateNode extends StateNode {

    //Private variables
    private IDExpNode _id;
    private ExpNode _expression;
    private String _literalIncrement;

    //getters and setters
    public IDExpNode get_assignId(){
        return _id;
    }
    public void set_assignId(IDExpNode id) {
        this._id = id;
    }

    public ExpNode get_assignExp(){
        return _expression;
    }
    public void set_assignExp(ExpNode expression) {
        this._expression = expression;
    }

    public String get_literalIncrement(){
        return this._literalIncrement;
    }
    public void set_literalIncrement(String literalIncrement){
        this._literalIncrement = literalIncrement;
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
