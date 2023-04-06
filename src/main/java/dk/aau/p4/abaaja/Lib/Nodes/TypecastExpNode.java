package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class TypecastExpNode extends ExpNode {
    private ExpNode _node;
    private TypeNode _typeNode;

    public ExpNode get_expression_node() {
        return _node;
    }
    public void set_expression_node(ExpNode node) {
        this._node = node;
    }

    public TypeNode get_typeNode() { return _typeNode; }
    public void set_typeNode(TypeNode _typeNode) { this._typeNode = _typeNode; }
    
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
