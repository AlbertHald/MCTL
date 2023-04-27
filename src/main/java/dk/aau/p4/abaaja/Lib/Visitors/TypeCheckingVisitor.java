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
        System.out.println("Mctl");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(LineNode node) {
        System.out.println("Line");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(BlockNode node) {
        System.out.println("Block");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(DecNode node) {
        System.out.println("DecNode");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(StateNode node) {
        System.out.println("State");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(VarDecNode node) {
        System.out.println("VarDec");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(FuncDecNode node) {
        System.out.println("FuncDec");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(StructDecNode node) {
        System.out.println("StructDec");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(IfStateNode node) {
        System.out.println("IfState");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(RepeatStateNode node) {
        System.out.println("RepeatState");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(AssStateNode node) {
        System.out.println("AssState");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(InvokeNode node) {
        System.out.println("Invoke");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(FuncInvokeNode node) {
        System.out.println("FuncInvoke");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(VarMethodInvokeNode node) {
        System.out.println("VarMethodInvoke");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(StringMethodInvokeNode node) {
        System.out.println("StringMethodInvoke");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(ReturnNode node) {
        System.out.println("Return");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(FormalParamNode node) {
        System.out.println("FormalParam");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(StopNode node) {
        System.out.println("Stop");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(TypeNode node) {
        System.out.println("Type");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(BoolTypeNode node) {
        System.out.println("BoolType");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(NumTypeNode node) {
        System.out.println("NumType");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(StringTypeNode node) {
        System.out.println("StringType");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(NothingTypeNode node) {
        System.out.println("NothingType");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(IDTypeNode node) {
        System.out.println("IDType");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(ExpNode node) {
        if (node instanceof IDExpNode) {
            return visit((IDExpNode) node);
        }


        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(UnaryExpNode node) {
        System.out.println("UnaryExp");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(TypecastExpNode node) {
        System.out.println("TypecastExp");
        return null; // this visitor should not be visited, probably, I think.
    }

    public MctlTypeDescriptor visit(BinaryExpNode node) {
        System.out.println("BinaryExp");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(MulExpNode node) {
        System.out.println("MulExp");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(AddExpNode node) {
        System.out.println("AddExp");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(AndExpNode node) {
        System.out.println("AndExp");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(OrExpNode node) {
        System.out.println("OrExp");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(CompExpNode node) {
        System.out.println("CompExp");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(EqualExpNode node) {
        System.out.println("EqualExp");
        return null; // TODO: Implement
    }

    public MctlTypeDescriptor visit(IDExpNode node){
        System.out.println("IDExp");
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
        System.out.println("ActualIDExp");
        Symbol symbol = _symbolTable.searchSymbol(node.get_id());

        if (!symbol.get_is_isInstantiated()) {
            return _symbolTable.searchType("NOTHING");
        }

        return symbol.get_type();
    }

    public MctlTypeDescriptor visit(IDArrayExpNode node) {
        System.out.println("IDArrayExp");
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
        System.out.println("BoolExp");
        return _symbolTable.searchType("BOOLEAN");
    }

    public MctlTypeDescriptor visit(NumExpNode node) {
        System.out.println("NumExp");
        return _symbolTable.searchType("NUMBER");
    }

    public MctlTypeDescriptor visit(StringExpNode node) {
        System.out.println("StringExp");
        return _symbolTable.searchType("STRING");
    }

    public MctlTypeDescriptor visit(CommentNode node) {
        System.out.println("Comment");
        return null;
    }
}
