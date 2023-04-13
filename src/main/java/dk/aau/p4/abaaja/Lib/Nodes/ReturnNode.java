package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class ReturnNode extends StateNode {

    private ExpNode _returnExp;

    public ExpNode get_returnExp() {return _returnExp; }
    public void set_returnExp(ExpNode exp) {this._returnExp = exp; }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
