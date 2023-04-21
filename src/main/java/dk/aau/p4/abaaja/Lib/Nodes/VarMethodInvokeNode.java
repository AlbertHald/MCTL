package dk.aau.p4.abaaja.Lib.Nodes;

public class VarMethodInvokeNode extends InvokeNode {
    private IDExpNode _varId;
    public IDExpNode get_varId(){
        return _varId;
    }
    public void set_varId(IDExpNode varId) {
        this._varId = varId;
    }
}
