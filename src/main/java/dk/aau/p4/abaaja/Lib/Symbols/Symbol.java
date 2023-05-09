package dk.aau.p4.abaaja.Lib.Symbols;

import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;

import java.util.ArrayList;
import java.util.List;

//Symbol table entries
public class Symbol<T, B> {
    private String _name;
    private MctlTypeDescriptor _type;
    private boolean _isInstantiated = false;
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

    /**
     * When interpreting the language, this is the value of the symbol after any calculations and acesses have been made.
     * Example: (where `k[2] = 4`),
     * expression: `k[2]` results in `value = 4`.
     * expression: `k[2] + 5` results in `value = 9`.
     */
    private T _value;
    public void set_value(T value){
        this.set_baseValue((B) value);
        this.set_scoped_value(value);
    }
    public void set_scoped_value(T value){
        this._value = value;
    }
    public T get_value(){
        return this._value;
    }

    /**
     * When interpreting the language, this is the base value of the symbol before any accesses have been made.
     * This is used in conjunction with `accessors` when assigning a value to a nested structure.
     * Example: (where `k[2][0] = 4`),
     * expression: `k[2][0]` results in `baseValue = [null, null, [4]]`.
     */
    private B _baseValue;
    public void set_baseValue(B baseValue){
        this._baseValue = baseValue;
    }
    public B get_baseValue(){
        return this._baseValue;
    }

    /**
     * When interpreting the language, this lists the accessors that are defined on a list's `baseValue` to get to the primitive `value`.
     * This is used in conjunction with `baseValue` when assigning a value to a nested list structure.
     * Example: (where `k[2][0] = 4`),
     * expression: `k[2][0]` results in `accessors = [2, 0]`.
     */
    private final ArrayList<Integer> _accessors = new ArrayList<>();
    public void add_accessor(int accessor){
        this._accessors.add(accessor);
    }
    public void clear_accessors(){
        this._accessors.clear();
    }
    public ArrayList<Integer> get_accessors(){
        return this._accessors;
    }

    public boolean get_isInstantiated() {
        return _isInstantiated;
    }
    public void set_isInstantiated(boolean _isInstantiated) {
        this._isInstantiated = _isInstantiated;
    }
}
