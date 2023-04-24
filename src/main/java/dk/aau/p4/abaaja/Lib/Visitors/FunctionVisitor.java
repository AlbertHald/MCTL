package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.PredefinedFunction;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.Symbols.Symbol;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;

import java.util.Arrays;
import java.util.List;

public class FunctionVisitor implements INodeVisitor {
    private ProblemCollection _problemCollection;
    private SymbolTable _symbolTable;

    public FunctionVisitor(ProblemCollection problemCollection, SymbolTable symbolTable) {
        this._problemCollection = problemCollection;
        this._symbolTable = symbolTable;
    }

    private void visitChildren(List<BaseNode> nodes) {
        for (BaseNode child : nodes) {
            child.accept(this);
        }
    }

    @Override
    public void visit(MctlNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(LineNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(BlockNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(DecNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(StateNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(VarDecNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(FuncDecNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(StructDecNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(IfStateNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(RepeatStateNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(AssStateNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(InvokeNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(FuncInvokeNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(VarMethodInvokeNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(StringMethodInvokeNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(ReturnNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(FormalParamNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(StopNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(TypeNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(BoolTypeNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(NumTypeNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(StringTypeNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(NothingTypeNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(IDTypeNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(ExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(UnaryExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(TypecastExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(BinaryExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(MulExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(AddExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(AndExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(OrExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(CompExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(EqualExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(IDExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(ActualIDExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(IDArrayExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(IDStructNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(BoolExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(NumExpNode node) { visitChildren(node.get_children()); }
    @Override
    public void visit(StringExpNode node) { visitChildren(node.get_children()); }
}
