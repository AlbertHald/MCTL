package dk.aau.p4.abaaja.Lib.Nodes;


import javax.swing.plaf.nimbus.State;
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

    public List<BlockNode> get_blockChildrenNode() {
        return _blockChildren;
    }

    public void set_blockChildren(List<BlockNode> _blockChildren) {
        this._blockChildren = _blockChildren;
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
