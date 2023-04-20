package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class BinaryExpNode extends ExpNode {
    private int _operator;
    private String _operatorLiteral;

    @Override
    public int get_arity() {
        return 2;
    }

    public int get_operator() { return _operator; }
    public void set_operator(int _operator) { this._operator = _operator; }

    public String get_operatorLiteral() { return _operatorLiteral; }
    public void set_operatorLiteral(String operatorLiteral) { this._operatorLiteral = operatorLiteral; }

    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
