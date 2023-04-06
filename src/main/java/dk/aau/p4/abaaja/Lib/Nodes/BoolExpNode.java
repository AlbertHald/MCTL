package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class BoolExpNode extends ExpNode {
    private boolean _result;

    public boolean get_result() {
        return _result;
    }
    public void set_result(boolean result) {
        this._result = result;
    }



    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
