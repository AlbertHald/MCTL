package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class IDTypeNode extends TypeNode {
    private String _type;

    public IDTypeNode() {}
    public IDTypeNode(int arrayDegree) { this.set_arrayDegree(arrayDegree); }

    public String get_type() { return _type; }
    @Override
    public void set_type(String _type) { this._type = _type; }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
