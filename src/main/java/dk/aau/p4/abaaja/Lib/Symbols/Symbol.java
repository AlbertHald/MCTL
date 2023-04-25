package dk.aau.p4.abaaja.Lib.Symbols;

import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;

import java.util.ArrayList;
import java.util.List;

//Symbol table entries
public class Symbol {
    private String _name;
    private MctlTypeDescriptor _type;
    private boolean _isInstantiated;
    private List<List<String>> _types = new ArrayList<>();

    public Symbol() {}
    public Symbol(String name) {
        this._name = name;
    }
    public Symbol(String name, MctlTypeDescriptor type) {
        this._name = name;
        this._type = type;
    }

    public String get_name() {return _name; }

    public MctlTypeDescriptor get_type() {
        return _type;
    }

    /**
     * Getter / Setters for the types ArrayList
     */
    public List<List<String>> get_types() { return _types; }
    public void set_types(List<List<String>> types) { this._types = types; }

    public void set_name(String _name) {
        this._name = _name;
    }
    public void set_type(MctlTypeDescriptor _type) {
        this._type = _type;
    }

    public boolean get_is_isInstantiated() {
        return _isInstantiated;
    }
    public void set_isInstantiated(boolean _isInstantiated) {
        this._isInstantiated = _isInstantiated;
    }
}
