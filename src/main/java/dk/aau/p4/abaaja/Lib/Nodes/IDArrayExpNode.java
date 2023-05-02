package dk.aau.p4.abaaja.Lib.Nodes;

public class IDArrayExpNode extends IDExpNode {
    private ExpNode _accessor;
    public ExpNode get_accessor(){
        return this._accessor;
    }
    public void set_accessor(ExpNode accessor){
        this._accessor = accessor;
    }

    @Override
    public String get_id() {
        return get_idNode().get_id() + "[]";
    }

    @Override
    public int get_degree() { return get_idNode().get_degree() + 1; }
}
