package dk.aau.p4.abaaja.Lib.Nodes;

public class NothingTypeNode extends TypeNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
