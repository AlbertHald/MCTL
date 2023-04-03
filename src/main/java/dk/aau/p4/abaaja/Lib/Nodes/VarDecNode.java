package dk.aau.p4.abaaja.Lib.Nodes;

public class VarDecNode extends DecNode {

    private String _id;
    private TypeNode _type;

    //Getters and setters
    public String get_varDecId() {
        return _id;
    }
    public void set_varDecId(String id){
        this._id = id;
    }
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
