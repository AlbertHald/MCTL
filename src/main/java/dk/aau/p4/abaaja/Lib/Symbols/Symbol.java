package dk.aau.p4.abaaja.Lib.Symbols;

import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlArrayTypeDescriptor;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//Symbol table entries
public class Symbol<T> {
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

    public boolean get_isInstantiated() {
        return _isInstantiated;
    }
    public void set_isInstantiated(boolean _isInstantiated) {
        this._isInstantiated = _isInstantiated;
    }

    /**
     * When interpreting the language, this is the value of the symbol after any calculations and accesses have been made.
     * Example: (where `k[2] = 4`),
     * expression: `k[2]` results in `value = 4`.
     * expression: `k[2] + 5` results in `value = 9`.
     */
    private T _value;
    public void set_value(T value){
        this._value = value;
    }
    public T get_value(){
        return this._value;
    }

    /**
     * If the symbol is a list, the values of each list index is saved here
     */
    private List<Symbol> _list = new ArrayList<>();
    public void set_indexes(List<Symbol> indexes){
        this._list = indexes;
    }
    public void set_index(int index, Symbol symbol){
        while(index >= this._list.size()){
            this._list.add(null);
        }
        this._list.set(index, symbol);
    }
    public void add_index(Symbol symbol){
        this._list.add(symbol);
    }
    public List<Symbol> get_indexes(){
        return this._list;
    }
    public Symbol get_index(int index){
        if(index < this._list.size()){
            return this._list.get(index);
        }else{
            return null;
        }
    }
    public int get_listLength(){
        return this._list.size();
    }

    /**
     * If the symbol is a struct, the values of each field is saved here
     */
    private List<Symbol> _fields = new ArrayList<>();
    public void set_fields(List<Symbol> fields){
        this._fields = fields;
    }
    public void set_field(Symbol symbol){
        int index = get_field_index(symbol.get_name());
        if(index == -1) {
            this._fields.add(symbol);
        }else{
            this._fields.set(index, symbol);
        }
    }
    public List<Symbol> get_fields(){
        return this._fields;
    }
    public Symbol get_field(String name){
        int index = get_field_index(name);
        if(index == -1){
            return null;
        }else{
            return this._fields.get(index);
        }
    }
    private int get_field_index(String name){
        for(int i = 0; i < this._fields.size(); i++){
            Symbol symbol = this._fields.get(i);
            if(Objects.equals(symbol.get_name(), name)) return i;
        }
        return -1;
    }
}
