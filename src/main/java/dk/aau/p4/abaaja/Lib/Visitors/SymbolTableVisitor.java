package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.Symbols.Symbol;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;

public class SymbolTableVisitor implements INodeVisitor {
    private ProblemCollection problemCollection;
    SymbolTable symbolTable;

    public SymbolTableVisitor (ProblemCollection problemCollection) {
        this.problemCollection = problemCollection;
    }

    public void visit(MctlNode node) {
        //Initializes symboltable and sets current scope to "Global"
        symbolTable = new SymbolTable();

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    public void visit(LineNode node) {

    }

    @Override
    public void visit(BlockNode node) {
        symbolTable.CreateScope();

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }

        symbolTable.CloseScope();
    }

    @Override
    public void visit(DecNode node) {

    }

    @Override
    public void visit(StateNode node) {

    }

    @Override
    public void visit(VarDecNode node) {
        Symbol symbol = new Symbol(node.get_id(), node.get_varDecType().get_type(), node.get_varDecType().get_arrayDegree());
        symbolTable.InsertSymbol(symbol);
    }

    @Override
    public void visit(FuncDecNode node) {
        Symbol symbol = new Symbol();

        symbol.set_type(node.get_id());
        

    }

    @Override
    public void visit(StructDecNode node) {
        Symbol symbol = new Symbol();
        symbolTable.InsertSymbol(symbol);
    }

    @Override
    public void visit(IfStateNode node) {

    }

    @Override
    public void visit(RepeatStateNode node) {

    }

    @Override
    public void visit(AssStateNode node) {

    }

    @Override
    public void visit(InvokeNode node) {

    }

    @Override
    public void visit(FuncInvokeNode node) {

    }

    @Override
    public void visit(VarMethodInvokeNode node) {

    }

    @Override
    public void visit(StringMethodInvokeNode node) {

    }

    @Override
    public void visit(ReturnNode node) {

    }

    @Override
    public void visit(FormalParamNode node) {

    }

    @Override
    public void visit(StopNode node) {

    }

    @Override
    public void visit(TypeNode node) {

    }

    @Override
    public void visit(BoolTypeNode node) {

    }

    @Override
    public void visit(NumTypeNode node) {

    }

    @Override
    public void visit(StringTypeNode node) {

    }

    @Override
    public void visit(NothingTypeNode node) {

    }

    @Override
    public void visit(IDTypeNode node) {

    }

    @Override
    public void visit(ExpNode node) {

    }

    @Override
    public void visit(UnaryExpNode node) {

    }

    @Override
    public void visit(TypecastExpNode node) {

    }

    @Override
    public void visit(BinaryExpNode node) {

    }

    @Override
    public void visit(MulExpNode node) {

    }

    @Override
    public void visit(AddExpNode node) {

    }

    @Override
    public void visit(AndExpNode node) {

    }

    @Override
    public void visit(OrExpNode node) {

    }

    @Override
    public void visit(CompExpNode node) {

    }

    @Override
    public void visit(EqualExpNode node) {

    }

    @Override
    public void visit(IDExpNode node) {

    }

    @Override
    public void visit(ActualIDExpNode node) {

    }

    @Override
    public void visit(IDArrayExpNode node) {

    }

    @Override
    public void visit(IDStructNode node) {

    }

    @Override
    public void visit(BoolExpNode node) {

    }

    @Override
    public void visit(NumExpNode node) {

    }

    @Override
    public void visit(StringExpNode node) {

    }


    /*
    * This section contains the visit functions that does not utilize the symboltable
    */


}
