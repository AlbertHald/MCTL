package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

import java.util.ArrayList;
import java.util.List;

public class BlockNode extends BaseNode {
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
