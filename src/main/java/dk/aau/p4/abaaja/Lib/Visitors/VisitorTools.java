package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;

public class VisitorTools {
    private final SymbolTable _symbolTable;

    public VisitorTools(SymbolTable symbolTable) {
        this._symbolTable = symbolTable;
    }

    public boolean isDeclared(String id) {
        return (_symbolTable.searchSymbol(id) != null);
    }
}
