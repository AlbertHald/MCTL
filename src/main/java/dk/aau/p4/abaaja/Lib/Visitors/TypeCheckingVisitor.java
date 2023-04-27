package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.Symbols.Symbol;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.*;

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

    public MctlTypeDescriptor visit(IDExpNode node){
        // TODO: Why are these nodes not automatically visited by the correct visitor?
        if(node instanceof IDArrayExpNode){
            return this.visit((IDArrayExpNode) node);
        }else if(node instanceof IDStructNode){
            return this.visit((IDStructNode) node);
        }else if(node instanceof ActualIDExpNode){
            return this.visit((ActualIDExpNode) node);
        }

        if(node.get_idNode() != null){
            return visit(node.get_idNode());
        } else {
            return null;
        }
    }

    public MctlTypeDescriptor visit(ActualIDExpNode node) {
        Symbol symbol = _symbolTable.searchSymbol(node.get_id());

        if (!symbol.get_is_isInstantiated()) {
            return _symbolTable.searchType("NOTHING");
        }

        return symbol.get_type();
    }

    public MctlTypeDescriptor visit(IDArrayExpNode node) {
        return visit(node.get_idNode());
    }

    public MctlTypeDescriptor visit(IDStructNode node) {
        boolean foundPrimitiveType = false;

        IDStructNode currNode = node;
        MctlTypeDescriptor accessorType;
        String accessorTypeLiteral;

        MctlStructDescriptor mctlTypeDescriptor;
        mctlTypeDescriptor = (MctlStructDescriptor) visit(currNode.get_idNode());

        if (mctlTypeDescriptor.get_type_literal().equals("NOTHING")) {
            return mctlTypeDescriptor;
        }

        System.out.println(currNode.get_idNode());

        do {
            // Get id of accessor
            IDExpNode accessor = currNode.get_accessor();
            while (!(accessor instanceof ActualIDExpNode)) {
                accessor = accessor.get_idNode();
            }

            // Get type of accessor
            String accessorId = ((ActualIDExpNode) accessor).get_id();
            if (mctlTypeDescriptor.get_structVariables().containsKey(accessorId)) {
                accessorType = mctlTypeDescriptor.get_structsymbol(accessorId);
                accessorTypeLiteral = accessorType.get_type_literal();
            } else {
                // Type does not exist on this Struct type error
                // TODO: Add error of type does not exist
                return null;
            }

            // Check if accessor type is a primitive type
            if (accessorTypeLiteral.equals("BOOLEAN") || accessorTypeLiteral.equals("STRING") || accessorTypeLiteral.equals("NUMBER") || accessorTypeLiteral.equals("NOTHING")) {
                foundPrimitiveType = true;
            }
            else {
                mctlTypeDescriptor = (MctlStructDescriptor) accessorType;
                currNode = (IDStructNode) currNode.get_accessor();
            }
        } while(!foundPrimitiveType);

        return accessorType;
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
