package dk.aau.p4.abaaja.Lib.Nodes;

public class IDStructNode extends IDExpNode {
    private IDExpNode _primaryId;
    private IDExpNode _secondaryId;

    public IDExpNode get_primaryId() { return _primaryId; }
    public void set_primaryId(IDExpNode _primaryId) { this._primaryId = _primaryId; }

    public IDExpNode get_secondaryId() { return _secondaryId; }
    public void set_secondaryId(IDExpNode _secondaryId) { this._primaryId = _primaryId; }
}
