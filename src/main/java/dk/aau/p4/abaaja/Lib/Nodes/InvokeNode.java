package dk.aau.p4.abaaja.Lib.Nodes;

public class InvokeNode extends StateNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
