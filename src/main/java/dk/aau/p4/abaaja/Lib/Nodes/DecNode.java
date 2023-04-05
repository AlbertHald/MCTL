package dk.aau.p4.abaaja.Lib.Nodes;

public abstract class DecNode extends LineNode {
    private String _id;

    public String get_id() {
        return _id;
    }
    public void set_id(String id) {
        this._id = id;
    }
}
