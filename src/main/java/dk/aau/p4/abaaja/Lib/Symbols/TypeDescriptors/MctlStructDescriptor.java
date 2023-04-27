package dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors;

import dk.aau.p4.abaaja.Lib.Nodes.StructDecNode;
import dk.aau.p4.abaaja.Lib.Symbols.Symbol;

import java.util.HashMap;

public class MctlStructDescriptor extends MctlTypeDescriptor {
    //ID is also the struct type
    private final String _typeDescriptor;
    private final HashMap<String, MctlTypeDescriptor> _structVariables = new HashMap<>();
    private final StructDecNode _nodeReference;

    public MctlStructDescriptor(String typeDescriptor, StructDecNode nodeReference) {
        this._typeDescriptor = typeDescriptor;
        this._nodeReference = nodeReference;
    }

    @Override
    public String get_type_literal() {
        return _typeDescriptor;
    }

    public HashMap<String, MctlTypeDescriptor> get_structVariables() {
        return _structVariables;
    }
    public void add_structVariables(String id, MctlTypeDescriptor entry) {
        _structVariables.put(id, entry);
    }

    // TODO: Return null if entry does not exist
    public MctlTypeDescriptor get_structsymbol(String key) {
        return _structVariables.get(key);
    }

    public StructDecNode get_nodeReference() {
        return _nodeReference;
    }
}
