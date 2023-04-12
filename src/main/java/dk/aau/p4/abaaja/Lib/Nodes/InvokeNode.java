package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;
import java.util.List;
import java.util.ArrayList;

public class InvokeNode extends StateNode {
    //Private variables
    private IDExpNode _id;
    private List<ExpNode> _actualParameters = new ArrayList<>();

    //getters and setters
    public IDExpNode get_invokeId(){
        return _id;
    }
    public void set_invokeId(IDExpNode id) {
        this._id = id;
    }
    public List<ExpNode> get_paramExp(){
        return _actualParameters;
    }
    public void set_paramExp(List<ExpNode> expression) {
        this._actualParameters = expression;
    }
    public void add_paramExp(ExpNode expression) { this._actualParameters.add(expression); }
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
