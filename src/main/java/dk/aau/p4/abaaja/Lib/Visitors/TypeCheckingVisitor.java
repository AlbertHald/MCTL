package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;

public class TypeCheckingVisitor {
    private ProblemCollection _problemCollection;
    private SymbolTable _symbolTable;
    private VisitorTools _visitorTools;

    public TypeCheckingVisitor(ProblemCollection problemCollection, SymbolTable symbolTable) {
        this._problemCollection = problemCollection;
        this._symbolTable = symbolTable;
        this._visitorTools = new VisitorTools(symbolTable);
    }

    public MctlTypeDescriptor visit(MctlNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(LineNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(BlockNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(DecNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(StateNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(VarDecNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(FuncDecNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(StructDecNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(IfStateNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(RepeatStateNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(AssStateNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(InvokeNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(FuncInvokeNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(VarMethodInvokeNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(StringMethodInvokeNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(ReturnNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(FormalParamNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(StopNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(TypeNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(BoolTypeNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(NumTypeNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(StringTypeNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(NothingTypeNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(IDTypeNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(ExpNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(UnaryExpNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(TypecastExpNode node) {
        return null; // this visitor should not be visited, probably, I think.
    }

    public MctlTypeDescriptor visit(BinaryExpNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(MulExpNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(AddExpNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(AndExpNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(OrExpNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(CompExpNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(EqualExpNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(IDExpNode node) {
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(ActualIDExpNode node) {

    }

    public MctlTypeDescriptor visit(IDArrayExpNode node) {

    }

    public MctlTypeDescriptor visit(IDStructNode node) {

    }

    public MctlTypeDescriptor visit(BoolExpNode node) {
        return _symbolTable.searchType("BOOLEAN");
    }

    public MctlTypeDescriptor visit(NumExpNode node) {
        return _symbolTable.searchType("NUMBER");
    }

    public MctlTypeDescriptor visit(StringExpNode node) {
        return _symbolTable.searchType("STRING");
    }

    public MctlTypeDescriptor visit(CommentNode node) { return null; }
}
