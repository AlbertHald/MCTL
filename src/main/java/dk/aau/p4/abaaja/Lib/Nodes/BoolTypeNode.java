package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class BoolTypeNode extends TypeNode {
    public String get_type() {
        return "BOOLEAN";
    }

    public BoolTypeNode() {}
    public BoolTypeNode(int arrayDegree) { this.set_arrayDegree(arrayDegree); }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
