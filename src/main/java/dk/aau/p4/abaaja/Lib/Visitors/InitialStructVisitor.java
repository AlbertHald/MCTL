package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemType;
import dk.aau.p4.abaaja.Lib.Symbols.Symbol;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlStructDescriptor;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InitialStructVisitor implements INodeVisitor {
    private ProblemCollection _problemCollection;
    private SymbolTable _symbolTable;
    private VisitorTools _visitorTools;

    public InitialStructVisitor(ProblemCollection problemCollection, SymbolTable symbolTable) {
        this._problemCollection = problemCollection;
        this._symbolTable = symbolTable;
        this._visitorTools = new VisitorTools(symbolTable);
    }

    private void visitChildren(List<BaseNode> nodes) {
        for (BaseNode child : nodes) {
            child.accept(this);
        }
    }

    @Override
    public void visit(StructDecNode node) {
        MctlStructDescriptor mctlStructDescriptor = new MctlStructDescriptor(node.get_id(), node);
        _symbolTable.insertType(mctlStructDescriptor);
        _symbolTable.get_currentScope().add_structType(node.get_id());
    }

    @Override
    public void visit(InvokeExpNode node) {}
    @Override
    public void visit(FuncDecNode node) {}
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
    @Override
    public void visit(CommentNode node) {}
}
