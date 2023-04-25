package dk.aau.p4.abaaja.Lib.Symbols;

import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.TypeDescriptor;

import java.util.ArrayList;
import java.util.HashMap;

public class Scope {

    //A hashmap of symbols in the scope.
    private final HashMap<String, Symbol> _symbols = new HashMap<>();
    private final HashMap<String, TypeDescriptor> _types = new HashMap<>();
    private String _name;
    private Scope _parent;

    //Constructor
    public Scope() {}
    public Scope(String name) {
        this._name = name;
    }

    //Getters and setters
    public String get_Name() {
        return _name;
    }
    public void set_Name(String name) {
        this._name = name;
    }

    public Scope get_Parent() {
        return _parent;
    }
    public void set_parent(Scope _parent) {
        this._parent = _parent;
    }

    public HashMap<String, Symbol> get_symbols() {
        return _symbols;
    }
    public void add_symbol(Symbol entry) {
        _symbols.put(entry.get_name(), entry);
    }

    public HashMap<String, TypeDescriptor> get_types() {
        return _types;
    }
    public void add_type(TypeDescriptor entry) {
        _types.put(entry.get_type_literal(), entry);
    }
}
