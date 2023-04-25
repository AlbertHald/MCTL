package dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors;

import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;

public class StructDescriptor extends TypeDescriptor {
    //ID is also the struct type
    private final String _typeDescriptor;
    private SymbolTable _symboltable;

    public StructDescriptor(String typeDescriptor) {
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
}
