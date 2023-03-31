package dk.aau.p4.abaaja.Lib.Nodes;

public class StringTypeNode extends TypeNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
