package dk.aau.p4.abaaja.Lib.Nodes;

public class StringTypeNode extends TypeNode {

    public String get_type() {
        return "STRING";
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
