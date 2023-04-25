package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemType;
import dk.aau.p4.abaaja.Lib.Symbols.Symbol;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.TypeDescriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InitialScopeVisitor implements INodeVisitor {
    private ProblemCollection _problemCollection;
    private SymbolTable _symbolTable;

    public InitialScopeVisitor(ProblemCollection problemCollection, SymbolTable symbolTable) {
        this._problemCollection = problemCollection;
        this._symbolTable = symbolTable;
    }

    private boolean isDeclared(String id) {
        return _symbolTable.searchSymbol(id) == null;
    }

    private void visitChildren(List<BaseNode> nodes) {
        for (BaseNode child : nodes) {
            child.accept(this);
        }
    }

    @Override
    public void visit(FuncDecNode node) {
        Symbol functionSymbol = new Symbol();

        // Check if symbol is declared
        if (isDeclared(node.get_id())) {
            _problemCollection.addProblem(ProblemType.ERROR_IDENTIFIER_CANNOT_BE_REUSED, "The identifier \"" + node.get_id() + "\" cannot be redeclared", node.get_lineNumber());
        }
        else {
            // Set function ID and return type
            functionSymbol.set_name(node.get_id());

            TypeDescriptor typeDescriptor = _symbolTable.searchType(node.get_returnType().get_type());
            if (typeDescriptor == null) {
                _problemCollection.addProblem(ProblemType.ERROR_UNKNOWN_TYPE, "The type \"" + node.get_returnType().get_type() + "\" is unknown", node.get_lineNumber());
            } else {
                functionSymbol.set_type(typeDescriptor);
            }

            // Creating temporary list containing the function parameters
            List<List<String>> functionParamList = new ArrayList<>();

            // Create parameter symbols
            for (FormalParamNode formalParam : node.get_paramList()) {
                // Adding parameter to functionSymbol
                functionParamList.add(Arrays.asList(formalParam.get_type().get_type()));
            }

            functionSymbol.set_types(functionParamList);
        }
    }

    @Override
    public void visit(StructDecNode node) {


        visitChildren(node.get_children());

    }

    @Override
    public void visit(MctlNode node) {}
    @Override
    public void visit(LineNode node) {}
    @Override
    public void visit(BlockNode node) {}
    @Override
    public void visit(DecNode node) {}
    @Override
    public void visit(StateNode node) {}
    @Override
    public void visit(VarDecNode node) {}
    @Override
    public void visit(IfStateNode node) {}
    @Override
    public void visit(RepeatStateNode node) {}
    @Override
    public void visit(AssStateNode node) {}
    @Override
    public void visit(InvokeNode node) {}
    @Override
    public void visit(FuncInvokeNode node) {}
    @Override
    public void visit(VarMethodInvokeNode node) {}
    @Override
    public void visit(StringMethodInvokeNode node) {}
    @Override
    public void visit(ReturnNode node) {}
    @Override
    public void visit(FormalParamNode node) {}
    @Override
    public void visit(StopNode node) {}
    @Override
    public void visit(TypeNode node) {}
    @Override
    public void visit(BoolTypeNode node) {}
    @Override
    public void visit(NumTypeNode node) {}
    @Override
    public void visit(StringTypeNode node) {}
    @Override
    public void visit(NothingTypeNode node) {}
    @Override
    public void visit(IDTypeNode node) {}
    @Override
    public void visit(ExpNode node) {}
    @Override
    public void visit(UnaryExpNode node) {}
    @Override
    public void visit(TypecastExpNode node) {}
    @Override
    public void visit(BinaryExpNode node) {}
    @Override
    public void visit(MulExpNode node) {}
    @Override
    public void visit(AddExpNode node) {}
    @Override
    public void visit(AndExpNode node) {}
    @Override
    public void visit(OrExpNode node) {}
    @Override
    public void visit(CompExpNode node) {}
    @Override
    public void visit(EqualExpNode node) {}
    @Override
    public void visit(IDExpNode node) {}
    @Override
    public void visit(ActualIDExpNode node) {}
    @Override
    public void visit(IDArrayExpNode node) {}
    @Override
    public void visit(IDStructNode node) {}
    @Override
    public void visit(BoolExpNode node) {}
    @Override
    public void visit(NumExpNode node) {}
    @Override
    public void visit(StringExpNode node) {}
}
