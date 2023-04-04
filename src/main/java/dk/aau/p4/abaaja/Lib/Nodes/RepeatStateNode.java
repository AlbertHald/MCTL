package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class RepeatStateNode extends StateNode {
    //Private variables
    private ExpNode _expression;
    private BlockNode _block;

    //Getters and setters
    public ExpNode get_repeatExp(){
        return _expression;
    }
    public void set_repeatExp(ExpNode expression) {
        this._expression = expression;
    }

    public BlockNode get_expBlock() {
        return _block;
    }
    public  void set_expBlock(BlockNode block) {
        this._block = block;
    }
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
