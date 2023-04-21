package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract class InvokeNode extends StateNode {
    private ActualIDExpNode _id;
    public ActualIDExpNode get_id(){
        return _id;
    }
    public void set_id(ActualIDExpNode id) {
        this._id = id;
    }

    private final List<ExpNode> _actualParameters = new ArrayList<>();
    public List<ExpNode> get_paramExps(){
        return _actualParameters;
    }
    public void add_paramExp(ExpNode expression) { this._actualParameters.add(expression); }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
