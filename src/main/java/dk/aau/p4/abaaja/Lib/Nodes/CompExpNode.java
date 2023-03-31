package dk.aau.p4.abaaja.Lib.Nodes;

public class CompExpNode extends ExpNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
