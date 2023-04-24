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

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
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

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(StateNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(VarDecNode node) {
        Symbol symbol = new Symbol(node.get_id(), node.get_varDecType().get_type(), node.get_varDecType().get_arrayDegree());
        symbolTable.InsertSymbol(symbol);

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
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

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(RepeatStateNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(AssStateNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(InvokeNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(FuncInvokeNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(VarMethodInvokeNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(StringMethodInvokeNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(ReturnNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(FormalParamNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(StopNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(TypeNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(BoolTypeNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(NumTypeNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(StringTypeNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(NothingTypeNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(IDTypeNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(ExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(UnaryExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(TypecastExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(BinaryExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(MulExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(AddExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(AndExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(OrExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(CompExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(EqualExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(IDExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(ActualIDExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(IDArrayExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(IDStructNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(BoolExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(NumExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(StringExpNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

}
