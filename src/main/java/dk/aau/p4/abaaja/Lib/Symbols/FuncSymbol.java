package dk.aau.p4.abaaja.Lib.Symbols;

import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlNothingDescriptor;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;

public class FuncSymbol extends Symbol{
    private String _name;
    private MctlTypeDescriptor _type;
    private boolean _isVarFunction;
    private boolean _isStringFunction;
    private MctlTypeDescriptor _expectedVarType;

    public FuncSymbol() {
        _isVarFunction = false;
        _isStringFunction = false;
        _expectedVarType = new MctlNothingDescriptor();
    }

    public FuncSymbol(String name, MctlTypeDescriptor type, boolean isStringFunc, boolean isVarFunc, MctlTypeDescriptor varType) {
        this._name = name;
        this._type = type;
        this._isVarFunction = isVarFunc;
        this._isStringFunction = isStringFunc;
        this._expectedVarType = varType;
    }

    public void setIsVarFunction(boolean isVarFunction) { this._isVarFunction = isVarFunction; }
    public boolean getIsVarFunction() { return _isVarFunction; }
    public void setIsStringFunction(boolean isStringFunction) { this._isStringFunction = isStringFunction; }
    public boolean getIsStringFunction() { return _isStringFunction; }
    public void setExpectedVarType(MctlTypeDescriptor expectedVarType) { this._expectedVarType = expectedVarType; }
    public MctlTypeDescriptor getExpectedVarType() { return _expectedVarType; }
}
