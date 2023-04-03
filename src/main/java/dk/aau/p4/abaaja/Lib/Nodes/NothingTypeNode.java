package dk.aau.p4.abaaja.Lib.Nodes;

public class NothingTypeNode extends TypeNode {

    public String get_type() {
        return "NOTHING";
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
