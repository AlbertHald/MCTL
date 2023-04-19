package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class UnaryExpNode extends ExpNode {
    private ExpNode _expression;
    private int _operator;
    private String _operatorLiteral;

    @Override
    public int get_arity(){
        return 1;
    }

    public ExpNode get_unaryExp(){
        return _expression;
    }
    public void set_unaryExp(ExpNode expression) {
        this._expression = expression;
    }

    public int get_operator() { return _operator; }
    public void set_operator(int _operator) { this._operator = _operator; }

    public String get_operatorLiteral() { return _operatorLiteral; }
    public void set_operatorLiteral(String operatorLiteral) { this._operatorLiteral = operatorLiteral; }

    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
