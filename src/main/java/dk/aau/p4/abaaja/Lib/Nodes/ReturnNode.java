package dk.aau.p4.abaaja.Lib.Nodes;

public class ReturnNode extends StateNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
