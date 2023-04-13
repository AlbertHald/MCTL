package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class NothingTypeNode extends TypeNode {
    public String get_type() {
        return "NOTHING";
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
