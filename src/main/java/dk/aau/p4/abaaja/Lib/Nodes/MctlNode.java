package dk.aau.p4.abaaja.Lib.Nodes;

import java.util.ArrayList;
import java.util.List;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class MctlNode extends BaseNode {

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
