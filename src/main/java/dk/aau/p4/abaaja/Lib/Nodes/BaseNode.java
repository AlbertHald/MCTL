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

    public int get_lineNumber() {
        return _lineNumber;
    }
    public void set_lineNumber(int _lineNumber) {
        this._lineNumber = _lineNumber;
    }

    public String get_type() {
        return _type;
    }
    public void set_type(String _type) {
        this._type = _type;
    }

    /**
     * Private variables
     */
    private int _lineNumber = 0;
    private List<BaseNode> _children = new ArrayList<>();
    private String _type;


    /**
     * Method for returning the unique hash code for the specific instance
     * @return String representation of the unique hash code
     */
    public String getNodeHash() {
        return String.valueOf(this.hashCode());
    }

    /**
     * Accept method to make visitor visit the specific Node // TODO: Confirm this?
     * @param visitor
     */
    public abstract void accept(INodeVisitor visitor);
}
