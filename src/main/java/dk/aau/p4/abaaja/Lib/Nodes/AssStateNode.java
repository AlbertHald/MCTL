package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class AssStateNode extends StateNode {

    //Private variables
    private IDExpNode _id;
    private ExpNode _expression;
    private String _literalPostfixOperator;

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

    public String get_literalPostfixOperator(){
        return this._literalPostfixOperator;
    }
    public void set_literalPostfixOperator(String literalPostfixOperator){
        this._literalPostfixOperator = literalPostfixOperator;
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
