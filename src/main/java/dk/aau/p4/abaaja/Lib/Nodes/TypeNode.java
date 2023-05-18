package dk.aau.p4.abaaja.Lib.Nodes;

public abstract class TypeNode extends BaseNode {
    private int _arrayDegree = 0;

    public int get_arrayDegree() { return _arrayDegree; }
    public void set_arrayDegree(int _arrayDegree) { this._arrayDegree = _arrayDegree; }

    public TypeNode() {}
    public TypeNode(int arrayDegree) { this.set_arrayDegree(arrayDegree); }

    public abstract String get_type();
    public void set_type(String type) {};
}
