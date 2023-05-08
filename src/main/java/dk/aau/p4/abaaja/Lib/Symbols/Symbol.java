package dk.aau.p4.abaaja.Lib.Symbols;

import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;

import java.util.ArrayList;
import java.util.List;

//Symbol table entries
public class Symbol<T> {
    private String _name;
    private MctlTypeDescriptor _type;
    private boolean _isInstantiated = false;
    private T _value;
    private List<List<MctlTypeDescriptor>> _types = new ArrayList<>();

    public Symbol() {}
    public Symbol(String name) {
        this._name = name;
    }
    public Symbol(MctlTypeDescriptor type) {
        this._type = type;
    }
    public Symbol(MctlTypeDescriptor type, T value) {
        this._type = type;
        this._value = value;
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
    public List<List<MctlTypeDescriptor>> get_types() {
        if (_types == null) return new ArrayList<>();
        return _types;
    }
    public void set_types(List<List<MctlTypeDescriptor>> types) { this._types = types; }

    public void set_name(String _name) {
        this._name = _name;
    }
    public void set_type(MctlTypeDescriptor _type) {
        this._type = _type;
    }

    public void set_value(T value){
        this._value = value;
    }
    public T get_value(){
        return this._value;
    }

    public boolean get_isInstantiated() {
        return _isInstantiated;
    }
    public void set_isInstantiated(boolean _isInstantiated) {
        this._isInstantiated = _isInstantiated;
    }
}
