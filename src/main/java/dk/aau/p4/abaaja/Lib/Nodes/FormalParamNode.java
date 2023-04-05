package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class FormalParamNode extends BaseNode {
    private TypeNode _type;
    private String _id;

    public TypeNode get_type() { return _type; }
    public void set_type(TypeNode _returnType) { this._type = _returnType; }

    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
