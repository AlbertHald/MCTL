package dk.aau.p4.abaaja.Lib.Nodes;

public class IDTypeNode extends TypeNode {

    /**
     * This needs to accept custom struct types, so we can't return a hard coded one.
     */

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
