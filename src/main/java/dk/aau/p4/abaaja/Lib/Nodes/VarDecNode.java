package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class VarDecNode extends DecNode {
    private TypeNode _type;

    //Getters and setters
    public TypeNode get_varDecType(){
        return _type;
    }
    public void set_varDecType(TypeNode type){
        this._type = type;
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
