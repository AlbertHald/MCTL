package dk.aau.p4.abaaja.Lib.Nodes;

public class IDStructNode extends IDExpNode {
    private IDExpNode _accessor;
    public IDExpNode get_accessor(){
        return this._accessor;
    }
    public void set_accessor(IDExpNode accessor){
        this._accessor = accessor;
    }
}
