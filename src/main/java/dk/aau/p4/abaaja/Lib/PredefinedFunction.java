package dk.aau.p4.abaaja.Lib;

import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;

import java.util.List;

public class PredefinedFunction {
    private String _id;
    private List<List<MctlTypeDescriptor>> _parameters;
    private MctlTypeDescriptor _returnType;

    public PredefinedFunction(String id, List<List<MctlTypeDescriptor>> parameters, MctlTypeDescriptor returnType) {
        this._id = id;
        this._parameters = parameters;
        this._returnType = returnType;
    }

    public String get_id() { return _id; }
    public List<List<MctlTypeDescriptor>> get_parameters() { return _parameters; }
    public MctlTypeDescriptor get_returnType() { return _returnType; }
}
