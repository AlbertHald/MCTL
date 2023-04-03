package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class StopNode extends StateNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
