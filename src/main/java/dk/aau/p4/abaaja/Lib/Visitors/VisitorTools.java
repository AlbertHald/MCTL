package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.TypeNode;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlArrayTypeDescriptor;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;

public class VisitorTools {
    private final SymbolTable _symbolTable;

    public VisitorTools(SymbolTable symbolTable) {
        this._symbolTable = symbolTable;
    }

    public boolean isDeclared(String id) {
        return (_symbolTable.searchSymbol(id) != null);
    }

    public MctlTypeDescriptor getTypeDescriptor(TypeNode typeNode) {
        MctlTypeDescriptor typeDescriptor;

        // Check if the type is an array
        if (typeNode.get_arrayDegree() > 0) {
            typeDescriptor = new MctlArrayTypeDescriptor(
                    _symbolTable.searchType(typeNode.get_type()),
                    typeNode.get_arrayDegree());
        } else {
            typeDescriptor = _symbolTable.searchType(typeNode.get_type());
        }

        return typeDescriptor;
    }
}
