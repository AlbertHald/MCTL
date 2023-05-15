package dk.aau.p4.abaaja.Lib.Nodes;

import java.util.List;
import java.util.ArrayList;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public abstract class BaseNode {
    /**
     * Get / Set methods for private variables
     */
    public List<BaseNode> get_children() {
        return _children;
    }
    public void set_children(List<BaseNode> _children) {
        this._children = _children;
    }
    public void add_child(BaseNode child) { this._children.add(child); }

    public int get_lineNumber() {
        return _lineNumber;
    }
    public void set_lineNumber(int _lineNumber) {
        this._lineNumber = _lineNumber;
    }
    public int get_lineEndNumber() {
        return _lineEndNumber;
    }
    public void set_lineEndNumber(int lineEndNumber) {
        this._lineEndNumber = lineEndNumber;
    }

    /**
     * Private variables
     */
    private int _lineNumber = 0;
    private int _lineEndNumber = 0;
    private List<BaseNode> _children = new ArrayList<>();

    /**
     * Method for returning the unique hash code for the specific instance
     * @return String representation of the unique hash code
     */
    public String getNodeHash() {
        return String.valueOf(this.hashCode());
    }

    /**
     * Accept method to make visitor visit the specific this node
     * @param visitor
     */
    public abstract void accept(INodeVisitor visitor);
}
