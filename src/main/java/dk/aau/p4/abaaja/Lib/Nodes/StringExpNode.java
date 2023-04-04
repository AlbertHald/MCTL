package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class StringExpNode extends ExpNode {

    public String get_result() {
        return _result;
    }

    public void set_result(String result) {
        this._result = result;
    }

    private String _result;

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
