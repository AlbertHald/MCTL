package dk.aau.p4.abaaja.Lib.Symbols;

import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.TypeDescriptor;

import java.util.ArrayList;
import java.util.List;

//Symbol table entries
public class Symbol {
    private String _name;
    private TypeDescriptor _type;
    private int _dimension;
    private boolean _isInstantiated;
    private List<List<String>> _types = new ArrayList<>();

    public Symbol() {}
    public Symbol(String name) {
        this._name = name;
    }
    public Symbol(String name, TypeDescriptor type) {
        this._name = name;
        this._type = type;
    }
    public Symbol(String name, TypeDescriptor type, int dimension) {
        this._name = name;
        this._type = type;
        this._dimension = dimension;
    }

    public String get_name() {return _name; }

    public TypeDescriptor get_type() {
        return _type;
    }

    public int get_dimension() {
        return _dimension;
    }

    /**
     * Getter / Setters for the types ArrayList
     */
    public List<List<String>> get_types() { return _types; }
    public void set_types(List<List<String>> types) { this._types = types; }

    public void set_name(String _name) {
        this._name = _name;
    }
    public void set_type(TypeDescriptor _type) {
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
