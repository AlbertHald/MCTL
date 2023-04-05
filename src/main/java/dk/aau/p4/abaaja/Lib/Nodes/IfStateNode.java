package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

import java.util.ArrayList;
import java.util.List;

public class IfStateNode extends StateNode {
    //Private variables
    private List<ExpNode> _expChildren = new ArrayList<>();
    private List<BlockNode> _blockChildren = new ArrayList<>();

    public List<ExpNode> get_expChildren() {
        return _expChildren;
    }
    public void set_expChildren(List<ExpNode> _expChildren) {
        this._expChildren = _expChildren;
    }
    public void add_expChild(ExpNode _exp) { this._expChildren.add(_exp); }

    public List<BlockNode> get_blockChildrenNode() {
        return _blockChildren;
    }
    public void set_blockChildren(List<BlockNode> _blockChildren) {
        this._blockChildren = _blockChildren;
    }
    public void add_blockChild(BlockNode _blockChild) { this._blockChildren.add(_blockChild); }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
