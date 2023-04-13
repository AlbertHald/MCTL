package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class IDExpNode extends ExpNode {
    private String _ID;

    public String get_ID() { return _ID; }
    public void set_ID(String _ID) { this._ID = _ID; }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
