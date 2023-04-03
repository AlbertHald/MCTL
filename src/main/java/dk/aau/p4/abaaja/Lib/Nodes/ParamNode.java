package dk.aau.p4.abaaja.Lib.Nodes;

public class ParamNode extends FuncDecNode {


    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
