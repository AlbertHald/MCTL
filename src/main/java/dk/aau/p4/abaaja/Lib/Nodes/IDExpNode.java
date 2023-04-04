package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class IDExpNode extends ExpNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}