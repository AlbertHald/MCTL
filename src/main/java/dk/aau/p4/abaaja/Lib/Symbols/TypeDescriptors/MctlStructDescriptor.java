package dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors;

import dk.aau.p4.abaaja.Lib.Symbols.Symbol;

import java.util.HashMap;

public class MctlStructDescriptor extends MctlTypeDescriptor {
    //ID is also the struct type
    private final String _typeDescriptor;
    private final HashMap<String, Symbol> _structVariables = new HashMap<>();

    public MctlStructDescriptor(String typeDescriptor) {
        this._typeDescriptor = typeDescriptor;
    }

    @Override
    public String get_type_literal() {
        return _typeDescriptor;
    }

    @Override
    public void set_type_literal(String typeLiteral) {
        throw new RuntimeException("Cant change type descriptor of Struct.");
    }

    public HashMap<String, Symbol> get_structVariables() {
        return _structVariables;
    }
    public void set_structVariables(Symbol entry) {
        _structVariables.put(entry.get_name(), entry);
    }

    public Symbol get_structsymbol(String key) {
        return _structVariables.get(key);
    }
}
