package dk.aau.p4.abaaja.Lib.Nodes;

public class ExpNode extends BaseNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
