package dk.aau.p4.abaaja.Lib.Nodes;

public class IDStructNode extends IDExpNode {
    private ExpNode _accessor;
    public ExpNode get_accessor(){
        return this._accessor;
    }
    public void set_accessor(ExpNode accessor){
        this._accessor = accessor;
    }
}
