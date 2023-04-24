package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.MctlNode;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;

public class SymbolTableVisitor implements INodeVisitor {
    private ProblemCollection problemCollection;

    public SymbolTableVisitor (ProblemCollection problemCollection) {
        this.problemCollection = problemCollection;
    }

    public void visit(MctlNode node) {

    }

}
