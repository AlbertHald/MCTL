package dk.aau.p4.abaaja.Lib.Nodes;

public class ActualIDExpNode extends IDExpNode {
    private String _id;
    public String get_id() {
        return _id;
    }
    public void set_id(String id) {
        this._id = id;
    }

    @Override
    public int get_degree() { return 0; }

    @Override
    public IDExpNode get_idNode() {
        return null;
    }
}
