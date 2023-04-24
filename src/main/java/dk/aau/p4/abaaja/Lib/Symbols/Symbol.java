package dk.aau.p4.abaaja.Lib.Symbols;

import java.util.ArrayList;

//Symbol table entries
public class Symbol {
    private String _name;
    private String _type;
    private int _dimension;
    private boolean _isInstantiated;
    private ArrayList<Type> _types = new ArrayList<>();

    public Symbol() {}
    public Symbol(String name) {
        this._name = name;
    }
    public Symbol(String name, String type) {
        this._name = type;
        this._type = type;
    }
    public Symbol(String name, String type, int dimension) {
        this._name = name;
        this._type = type;
        this._dimension = dimension;
    }

    public String get_name() {return _name; }

    public String get_type() {
        return _type;
    }

    public int get_dimension() {
        return _dimension;
    }

    /**
     * Getter / Setters for the types ArrayList
     */
    public ArrayList<Type> get_types() { return _types; }
    public void add_types_element(Type type) { this._types.add(type); }

    public void set_name(String _name) {
        this._name = _name;
    }
    public void set_type(String _type) {
        this._type = _type;
    }
    public void set_dimension(int _dimension) { this._dimension = _dimension; }

    public boolean get_is_isInstantiated() {
        return _isInstantiated;
    }
    public void set_isInstantiated(boolean _isInstantiated) {
        this._isInstantiated = _isInstantiated;
    }
}
