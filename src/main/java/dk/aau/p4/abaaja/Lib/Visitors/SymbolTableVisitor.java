package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemType;
import dk.aau.p4.abaaja.Lib.Symbols.Symbol;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SymbolTableVisitor implements INodeVisitor {
    private ProblemCollection problemCollection;
    SymbolTable symbolTable;

    public SymbolTableVisitor (ProblemCollection problemCollection) {
        this.problemCollection = problemCollection;
    }

    private void visitChildren(List<BaseNode> nodes) {
        for (BaseNode child : nodes) {
            child.accept(this);
        }
    }

    private boolean isDeclared(String id) {
        return symbolTable.SearchSymbol(id) == null;
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

        if(isDeclared(node.get_id())) {
            problemCollection.addProblem(ProblemType.ERROR_IDENTIFIER_CANNOT_BE_REUSED, "The identifier \"" + node.get_id() + "\" cannot be redeclared", node.get_lineNumber());
        } else {
            Symbol symbol = new Symbol(node.get_id(), node.get_varDecType().get_type(), node.get_varDecType().get_arrayDegree());
            symbolTable.InsertSymbol(symbol);
        }

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(FuncDecNode node) {
        Symbol functionSymbol = new Symbol();

        // Check if symbol is declared
        if (isDeclared(node.get_id())) {
            problemCollection.addProblem(ProblemType.ERROR_IDENTIFIER_CANNOT_BE_REUSED, "The identifier \"" + node.get_id() + "\" cannot be redeclared", node.get_lineNumber());
        }
        else {
            // Set function ID and return type
            functionSymbol.set_name(node.get_id());
            functionSymbol.set_type(node.get_returnType().get_type());

            // Creating temporary list containing the function parameters
            List<List<String>> functionParamList = new ArrayList<>();

            // Create scope for function block and create parameter symbols
            symbolTable.CreateScope();
            for (FormalParamNode formalParam : node.get_paramList()) {
                Symbol paramSymbol = new Symbol(formalParam.get_id());
                paramSymbol.set_type(formalParam.get_type().get_type());

                // Adding parameter to functionSymbol and current symbol table
                functionParamList.add(Arrays.asList(formalParam.get_type().get_type()));
                symbolTable.InsertSymbol(paramSymbol);
            }

            for (BaseNode line : node.get_funcBlock().get_children()) {
                line.accept(this);
            }

            functionSymbol.set_types(functionParamList);
            symbolTable.CloseScope();
        }
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

        //if (symbolTable.SearchSymbol(node.get_assignId().))
        // Check Expression Types
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
        //Check if Return is of same type as functionDec
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
