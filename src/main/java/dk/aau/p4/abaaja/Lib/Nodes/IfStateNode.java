package dk.aau.p4.abaaja.Lib.Nodes;


public class IfStateNode extends StateNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
