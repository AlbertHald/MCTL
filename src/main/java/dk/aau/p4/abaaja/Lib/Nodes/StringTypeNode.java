package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class StringTypeNode extends TypeNode {
    @Override
    public String get_type() { return "STRING"; }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
