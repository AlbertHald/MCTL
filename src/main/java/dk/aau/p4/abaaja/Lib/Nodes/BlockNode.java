package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

import java.util.ArrayList;
import java.util.List;

public class BlockNode extends BaseNode {
    private List<LineNode> _line = new ArrayList<>();

    public List<LineNode> get_line() {
        return _line;
    }
    public void set_line(List<LineNode> line) {
        this._line = line;
    }
    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
