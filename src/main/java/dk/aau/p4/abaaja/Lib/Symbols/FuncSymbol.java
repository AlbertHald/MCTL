package dk.aau.p4.abaaja.Lib.Symbols;

import dk.aau.p4.abaaja.Lib.Nodes.BlockNode;
import dk.aau.p4.abaaja.Lib.Nodes.FormalParamNode;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlNothingDescriptor;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;

import java.util.List;

public class FuncSymbol extends Symbol{
    private BlockNode _funcBlock;
    private List<FormalParamNode> _formalParams;
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
        this.set_name(name);
        this._type = type;
        this._isVarFunction = isVarFunc;
        this._isStringFunction = isStringFunc;
        this._expectedVarType = varType;
    }

    public void set_funcBlock(BlockNode funcBlock){
        this._funcBlock = funcBlock;
    }
    public BlockNode get_funcBlock(){
        return this._funcBlock;
    }

    public void set_formalParams(List<FormalParamNode> formalParams){
        this._formalParams = formalParams;
    }
    public List<FormalParamNode> get_formalParams(){
        return this._formalParams;
    }

    public void setIsVarFunction(boolean isVarFunction) { this._isVarFunction = isVarFunction; }
    public boolean getIsVarFunction() { return _isVarFunction; }
    public void setIsStringFunction(boolean isStringFunction) { this._isStringFunction = isStringFunction; }
    public boolean getIsStringFunction() { return _isStringFunction; }
    public void setExpectedVarType(MctlTypeDescriptor expectedVarType) { this._expectedVarType = expectedVarType; }
    public MctlTypeDescriptor getExpectedVarType() { return _expectedVarType; }
}
