package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public abstract class ExpNode extends BaseNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
