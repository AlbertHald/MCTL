package dk.aau.p4.abaaja.Lib.Nodes;

public class BlockNode extends BaseNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
