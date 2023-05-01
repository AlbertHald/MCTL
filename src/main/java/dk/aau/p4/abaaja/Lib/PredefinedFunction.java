package dk.aau.p4.abaaja.Lib;

import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;

import java.util.List;

public class PredefinedFunction {
    private String _id;
    private List<List<MctlTypeDescriptor>> _parameters;
    private MctlTypeDescriptor _returnType;
    private boolean _isVarFunction;
    private boolean _isStringFunction;
    private MctlTypeDescriptor _expectedVarType;

    public PredefinedFunction(String id,
                              List<List<MctlTypeDescriptor>> parameters,
                              MctlTypeDescriptor returnType,
                              boolean isVarFunction,
                              boolean isStringFunction,
                              MctlTypeDescriptor expectedVarType) {
        this._id = id;
        this._parameters = parameters;
        this._returnType = returnType;
        this._isVarFunction = isVarFunction;
        this._isStringFunction = isStringFunction;
        this._expectedVarType = expectedVarType;
    }

    public String get_id() { return _id; }
    public List<List<MctlTypeDescriptor>> get_parameters() { return _parameters; }
    public MctlTypeDescriptor get_returnType() { return _returnType; }
    public boolean getIsStringFunction() { return _isStringFunction; }
    public boolean getIsVarFunction() { return _isVarFunction; }
    public MctlTypeDescriptor getExpectedVarType() { return _expectedVarType; }
}
