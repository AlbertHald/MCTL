package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class CompExpNode extends ExpNode {
    private boolean _result;
    private int _compOperator;

    public boolean get_result() {
        return _result;
    }
    public void set_result(boolean result) {
        this._result = result;
    }

    public int get_compOperator() {
        return _compOperator;
    }
    public void set_compOperator(int compOperator) { this._compOperator = _compOperator; }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
