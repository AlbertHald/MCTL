package dk.aau.p4.abaaja.Lib.Nodes;

public class IDArrayExpNode extends IDExpNode {
    private IDExpNode _IDNode;
    public void set_IDNode(IDExpNode IDNode){
        this._IDNode = IDNode;
    }
    public IDExpNode get_IDNode(){
        return this._IDNode;
    }
}
