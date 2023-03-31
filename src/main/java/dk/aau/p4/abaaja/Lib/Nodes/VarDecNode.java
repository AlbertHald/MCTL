package dk.aau.p4.abaaja.Lib.Nodes;

public class VarDecNode extends DecNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
