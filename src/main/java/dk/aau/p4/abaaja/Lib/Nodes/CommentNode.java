package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public class CommentNode extends LineNode {

    private String _text;
    public String get_text() {
        return _text;
    }
    public void set_text(String text) {
        this._text = text;
    }

    @Override
    public void accept(INodeVisitor visitor) {
        visitor.visit(this);
    }
}
