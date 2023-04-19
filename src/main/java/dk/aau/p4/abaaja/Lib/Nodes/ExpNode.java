package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public abstract class ExpNode extends BaseNode {
    public int get_arity(){
        return 0;
    }
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
