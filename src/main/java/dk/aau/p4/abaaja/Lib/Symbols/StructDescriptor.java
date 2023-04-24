package dk.aau.p4.abaaja.Lib.Symbols;

public class StructDescriptor {

    //ID is also the struct type
    private String _id;
    private SymbolTable _symboltable;

    public StructDescriptor(String id) {
        this._id = id;
    }

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
}
