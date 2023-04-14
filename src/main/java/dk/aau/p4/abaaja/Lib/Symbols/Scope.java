package dk.aau.p4.abaaja.Lib.Symbols;

import java.util.HashMap;

public class Scope {

    //A hashmap of symbols in the scope.
    private HashMap<String, HashMapEntry> _symbols = new HashMap<>();
    private String _name;
    private Scope _parent;

    //Constructor
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

    public HashMap<String, HashMapEntry> get_Symbols() {
        return _symbols;
    }

    public void set_Symbols(String symbolName, HashMapEntry entry) {
        _symbols.put(symbolName, entry);
    }
}
