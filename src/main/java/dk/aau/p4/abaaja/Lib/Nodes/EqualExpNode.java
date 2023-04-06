package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;
import dk.aau.p4.abaaja.mctlParser;

public class EqualExpNode extends ExpNode {
    private boolean _result;
    private int _operator;

    public boolean get_result() {
        return _result;
    }
    public void set_result(boolean result) {
        this._result = result;
    }

    public int get_operator() { return _operator; }
    public void set_operator(int _operator) { this._operator = _operator; }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
