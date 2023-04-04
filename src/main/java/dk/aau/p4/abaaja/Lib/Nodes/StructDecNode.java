package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

import java.util.ArrayList;
import java.util.List;

public class StructDecNode extends DecNode {
    //Private
    private String _id;
    private List<VarDecNode> _declarations = new ArrayList<>();

    public String get_structDecId() {
        return _id;
    }
    public void set_structDecId(String id) {
        this._id = id;
    }

    public List<VarDecNode> get_declarations() {
        return _declarations;
    }
    public void set_declarations(List<VarDecNode> declarations){
        this._declarations = declarations;
    }
    public void add_declaration(VarDecNode declaration) { this._declarations.add(declaration); }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}