package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class ParenExpNode extends ExpNode {

    public ExpNode get_contained_node() {
        return _node;
    }
    public void set_contained_node(ExpNode node) {
        this._node = node;
    }

    private ExpNode _node;
    
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
