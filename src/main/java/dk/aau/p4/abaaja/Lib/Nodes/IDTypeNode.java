package dk.aau.p4.abaaja.Lib.Nodes;

public class IDTypeNode extends TypeNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
