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

    }

    public MctlTypeDescriptor visit(LineNode node) {

    }

    public MctlTypeDescriptor visit(BlockNode node) {

    }

    public MctlTypeDescriptor visit(DecNode node) {

    }

    public MctlTypeDescriptor visit(StateNode node) {

    }

    public MctlTypeDescriptor visit(VarDecNode node) {

    }

    public MctlTypeDescriptor visit(FuncDecNode node) {

    }

    public MctlTypeDescriptor visit(StructDecNode node) {

    }

    public MctlTypeDescriptor visit(IfStateNode node) {

    }

    public MctlTypeDescriptor visit(RepeatStateNode node) {

    }

    public MctlTypeDescriptor visit(AssStateNode node) {

    }

    public MctlTypeDescriptor visit(InvokeNode node) {

    }

    public MctlTypeDescriptor visit(FuncInvokeNode node) {

    }

    public MctlTypeDescriptor visit(VarMethodInvokeNode node) {

    }

    public MctlTypeDescriptor visit(StringMethodInvokeNode node) {

    }

    public MctlTypeDescriptor visit(ReturnNode node) {

    }

    public MctlTypeDescriptor visit(FormalParamNode node) {

    }

    public MctlTypeDescriptor visit(StopNode node) {

    }

    public MctlTypeDescriptor visit(TypeNode node) {

    }

    public MctlTypeDescriptor visit(BoolTypeNode node) {

    }

    public MctlTypeDescriptor visit(NumTypeNode node) {

    }

    public MctlTypeDescriptor visit(StringTypeNode node) {

    }

    public MctlTypeDescriptor visit(NothingTypeNode node) {

    }

    public MctlTypeDescriptor visit(IDTypeNode node) {

    }

    public MctlTypeDescriptor visit(ExpNode node) {

    }

    public MctlTypeDescriptor visit(UnaryExpNode node) {

    }

    public MctlTypeDescriptor visit(TypecastExpNode node) {
        return null; // this visitor should not be visited, probably, I think.
    }

    public MctlTypeDescriptor visit(BinaryExpNode node) {

    }

    public MctlTypeDescriptor visit(MulExpNode node) {

    }

    public MctlTypeDescriptor visit(AddExpNode node) {

    }

    public MctlTypeDescriptor visit(AndExpNode node) {

    }

    public MctlTypeDescriptor visit(OrExpNode node) {

    }

    public MctlTypeDescriptor visit(CompExpNode node) {

    }

    public MctlTypeDescriptor visit(EqualExpNode node) {

    }

    public MctlTypeDescriptor visit(IDExpNode node) {

    }

    public MctlTypeDescriptor visit(ActualIDExpNode node) {

    }

    public MctlTypeDescriptor visit(IDArrayExpNode node) {

    }

    public MctlTypeDescriptor visit(IDStructNode node) {

    }

    public MctlTypeDescriptor visit(BoolExpNode node) {

    }

    public MctlTypeDescriptor visit(NumExpNode node) {

    }

    public MctlTypeDescriptor visit(StringExpNode node) {

    }

    public MctlTypeDescriptor visit(CommentNode node) { return null; }
}
