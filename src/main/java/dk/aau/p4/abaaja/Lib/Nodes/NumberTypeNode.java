package dk.aau.p4.abaaja.Lib.Nodes;

public class NumberTypeNode extends TypeNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
