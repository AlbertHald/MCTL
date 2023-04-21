package dk.aau.p4.abaaja.Lib.Nodes;

public class StringMethodInvokeNode extends InvokeNode {
    private StringExpNode _string;
    public StringExpNode get_string(){
        return _string;
    }
    public void set_string(StringExpNode string) {
        this._string = string;
    }
}
