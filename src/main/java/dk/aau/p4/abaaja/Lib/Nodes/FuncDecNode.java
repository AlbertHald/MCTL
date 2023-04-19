package dk.aau.p4.abaaja.Lib.Nodes;

import java.util.ArrayList;
import java.util.List;
import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class FuncDecNode extends DecNode {
    //Private variables
    private final List<FormalParamNode> _paramList = new ArrayList<>();
    private BlockNode _funcBlock;
    private TypeNode _returnType;

    //Getters and setters
    public List<FormalParamNode> get_paramList() {
        return _paramList;
    }
    public void add_param(FormalParamNode paramNode) {
        this._paramList.add(paramNode);
    }

    public BlockNode get_funcBlock() {
        return _funcBlock;
    }
    public void set_funcBlock(BlockNode _funcBlock) {
        this._funcBlock = _funcBlock;
    }

    public TypeNode get_returnType(){
        return _returnType;
    }
    public void set_returnType(TypeNode returnType) {
        this._returnType = returnType;
    }


    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
