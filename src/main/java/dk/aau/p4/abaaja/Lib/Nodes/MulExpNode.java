package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class MulExpNode extends BinaryExpNode {
    private Number _result;
    public Number get_result() {
        return _result;
    }
    public void set_result(Number result) {
        this._result = result;
    }
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
