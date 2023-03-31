package dk.aau.p4.abaaja.Lib.Nodes;

public class UnaryExpNode extends ExpNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
