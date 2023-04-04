package dk.aau.p4.abaaja.Lib.Nodes;

import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

public abstract class TypeNode extends BaseNode {
    private int _arrayDegree = 0;

    public int get_arrayDegree() { return _arrayDegree; }
    public void set_arrayDegree(int _arrayDegree) { this._arrayDegree = _arrayDegree; }
}
