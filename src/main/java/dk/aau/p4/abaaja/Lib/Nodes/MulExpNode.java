package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class MulExpNode extends ExpNode {

    public Number get_result() {
        return _result;
    }

    public void set_result(Number result) {
        this._result = result;
    }

    public int get_operator() { return _operator; }
    public void set_operator(int _operator) { this._operator = _operator; }
    private Number _result;
    private int _operator;

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
