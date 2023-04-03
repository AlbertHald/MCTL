package dk.aau.p4.abaaja.Lib.Nodes;

public class NumberTypeNode extends TypeNode {

    public String get_type() {
        return "NUMBER";
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
