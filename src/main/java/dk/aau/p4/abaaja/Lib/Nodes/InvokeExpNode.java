package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class InvokeExpNode extends ExpNode {
    private InvokeNode invokeNode;

    public InvokeNode getInvokeNode() { return invokeNode; }
    public void setInvokeNode(InvokeNode invokeNode) { this.invokeNode = invokeNode; }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
