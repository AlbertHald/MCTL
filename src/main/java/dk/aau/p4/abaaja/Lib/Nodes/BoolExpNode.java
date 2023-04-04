package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class BoolExpNode extends ExpNode {

    public boolean get_result() {
        return _result;
    }

    public void set_result(boolean result) {
        this._result = result;
    }

    private boolean _result;

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
