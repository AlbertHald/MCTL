package dk.aau.p4.abaaja.Lib.Nodes;

public class TypecastExpNode extends ExpNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
