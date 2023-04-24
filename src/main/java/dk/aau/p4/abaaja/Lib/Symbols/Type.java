package dk.aau.p4.abaaja.Lib.Symbols;

public class Type {
    private String _type;
    private String _id;

    public Type(String id, String type) {
        this._id = id;
        this._type = type;
    }

    public String get_id() { return _id; }
    public void set_id(String id) { this._id = id; }

    public String get_type() { return _type; }
    public void set_type(String type) { this._type = type; }
}
