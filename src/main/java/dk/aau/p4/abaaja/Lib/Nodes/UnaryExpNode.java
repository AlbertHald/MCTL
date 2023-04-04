package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class UnaryExpNode extends ExpNode {

    public Number get_result() {
        return _result;
    }

    public void set_result(Number result) {
        this._result = result;
    }

    private Number _result;
    
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
