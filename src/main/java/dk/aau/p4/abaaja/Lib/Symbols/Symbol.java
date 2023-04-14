package dk.aau.p4.abaaja.Lib.Symbols;

//Symbol table entries
public class Symbol {
    private String _name;
    private String _type;
    private int _dimension;


    public String get_name() {
        return _name;
    }

    public String get_type() {
        return _type;
    }

    public int get_dimension() {
        return _dimension;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
    public void set_type(String _type) {
        this._type = _type;
    }
    public void set_dimension(int _dimension) { this._dimension = _dimension; }
}
