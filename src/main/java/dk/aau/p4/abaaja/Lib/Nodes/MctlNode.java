package dk.aau.p4.abaaja.Lib.Nodes;

import java.util.List;

public class MctlNode extends BaseNode {

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
