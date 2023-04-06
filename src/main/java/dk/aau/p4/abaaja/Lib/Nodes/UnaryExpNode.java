package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class UnaryExpNode extends ExpNode {
    private ExpNode _expression;
    private int _operator;

    public ExpNode get_unaryExp(){
        return _expression;
    }
    public void set_unaryExp(ExpNode expression) {
        this._expression = expression;
    }

    public int get_operator() { return _operator; }
    public void set_operator(int _operator) { this._operator = _operator; }

    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
