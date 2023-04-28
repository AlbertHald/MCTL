package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemType;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlStructDescriptor;
import dk.aau.p4.abaaja.Lib.Symbols.Symbol;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;

import java.util.List;

public class SymbolTableVisitor implements INodeVisitor {
    private ProblemCollection problemCollection;
    SymbolTable symbolTable;
    VisitorTools visitorTools;
    TypeCheckingVisitor typeCheckingVisitor;

    public SymbolTableVisitor (ProblemCollection problemCollection) {
        this.symbolTable = new SymbolTable();
        this.visitorTools = new VisitorTools(this.symbolTable);
        this.problemCollection = problemCollection;
        typeCheckingVisitor = new TypeCheckingVisitor(this.problemCollection, this.symbolTable);
    }

    private void visitChildren(List<BaseNode> nodes) {
        for (BaseNode child : nodes) {
            child.accept(this);
        }
    }

    private void initialScopeVisit(List<BaseNode> nodes) {
        // Checking struct declarations in code block
        for (BaseNode child : nodes) {
            child.accept(new InitialStructVisitor(problemCollection, symbolTable));
        }

        // Checking function declarations in code block
        for (BaseNode child : nodes) {
            child.accept(new InitialFuncVisitor(problemCollection, symbolTable));
        }

        // Finalize struct type declarations
        List<String> structTypes = symbolTable.get_currentScope().get_structTypes();
        for (String structTypeLiteral : structTypes) {
            MctlStructDescriptor mctlStructDescriptor = (MctlStructDescriptor) symbolTable.searchType(structTypeLiteral);

            // Add each variable declaration for the struct to its descriptor
            for (VarDecNode node : mctlStructDescriptor.get_nodeReference().get_declarations()) {
                MctlTypeDescriptor typeDescriptor = visitorTools.getTypeDescriptor(node.get_varDecType());

                // Check if the type exists
                if (typeDescriptor == null) {
                    problemCollection.addProblem(ProblemType.ERROR_UNKNOWN_TYPE, "The type \"" + node.get_varDecType().get_type() + "\" is unknown", node.get_lineNumber());
                } else {
                    mctlStructDescriptor.add_structVariables(node.get_id(), typeDescriptor);
                }
            }
        }

    }

    public void visit(MctlNode node) {
        initialScopeVisit(node.get_children());

        // Visit remaining lines
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
        symbolTable.createScope();

        // Visit all function and struct declarations and add them to the symbol table
        initialScopeVisit(node.get_children());

        // Visit all remaining line
        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }

        symbolTable.closeScope();
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
        MctlTypeDescriptor typeDescriptor;

        if (visitorTools.isDeclared(node.get_id())) {
            problemCollection.addProblem(ProblemType.ERROR_IDENTIFIER_CANNOT_BE_REUSED, "The identifier \"" + node.get_id() + "\" cannot be redeclared", node.get_lineNumber());
        } else {
            typeDescriptor = visitorTools.getTypeDescriptor(node.get_varDecType());
            if (typeDescriptor != null) {
                Symbol symbol = new Symbol(node.get_id(), typeDescriptor);
                symbol.set_isInstantiated(true);
                this.symbolTable.insertSymbol(symbol);
            } else {
                problemCollection.addProblem(ProblemType.ERROR_UNKNOWN_TYPE, "The type \"" + node.get_varDecType().get_type() + "\" does not exist", node.get_lineNumber());
            }

        }
    }

    @Override
    public void visit(FuncDecNode node) {
        // Create scope for function block and create parameter symbols
        symbolTable.createScope();
        for (FormalParamNode formalParam : node.get_paramList()) {
            Symbol paramSymbol = new Symbol(formalParam.get_id());
            MctlTypeDescriptor tempType = visitorTools.getTypeDescriptor(formalParam.get_type());
            if (tempType == null) {
                problemCollection.addProblem(ProblemType.ERROR_UNKNOWN_TYPE, "The type \"" + formalParam.get_type().get_type() + "\" is unknown", node.get_lineNumber());
            } else {
                paramSymbol.set_type(tempType);
            }

            // Adding parameter to functionSymbol and current symbol table
            paramSymbol.set_isInstantiated(true);
            symbolTable.insertSymbol(paramSymbol);
        }

        initialScopeVisit(node.get_funcBlock().get_children());

        // Check lines of the function block
        for (BaseNode line : node.get_funcBlock().get_children()) {
            line.accept(this);
        }

        symbolTable.closeScope();
    }

    @Override
    public void visit(StructDecNode node) {
        MctlStructDescriptor structDeclaration = new MctlStructDescriptor(node.get_id(), node);
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
        Symbol variable;

        MctlTypeDescriptor idTypeDescriptor = typeCheckingVisitor.visit(node.get_assignId());
        MctlTypeDescriptor expTypeDescriptor = typeCheckingVisitor.visit(node.get_assignExp());

        IDExpNode idExpNode = node.get_assignId();

        while (!(idExpNode instanceof ActualIDExpNode actualIDNode)) {
            idExpNode = idExpNode.get_idNode();
        }

        if (idTypeDescriptor == null) {
            problemCollection.addProblem(
                    ProblemType.ERROR_UNDEFINED_IDENTIFIER,
                    "The variable \"" + actualIDNode.get_id() + "\" has not yet been declared",
                    node.get_lineNumber()
            );
        } else if (expTypeDescriptor == null) {
            problemCollection.addProblem(
                    ProblemType.ERROR_UNDEFINED_IDENTIFIER,
                    "The variable \"" + actualIDNode.get_id() + "\" cannot be assigned to an expression containing undeclared variables",
                    node.get_lineNumber()
            );
        } else if (idTypeDescriptor.get_type_literal().equals(expTypeDescriptor.get_type_literal())) {
            variable = symbolTable.searchSymbol(actualIDNode.get_id());

            if (variable == null) {
                problemCollection.addProblem(
                        ProblemType.ERROR_UNDEFINED_IDENTIFIER,
                        "The variable \"" + actualIDNode.get_id() + "\" has not yet been declared",
                        node.get_lineNumber()
                );
            } else {
                variable.set_isInstantiated(true);
            }
        } else {
            problemCollection.addProblem(
                    ProblemType.ERROR_TYPE_MISMATCH,
                    "Expected " + idTypeDescriptor.get_type_literal() + " but got " + expTypeDescriptor.get_type_literal(),
                    node.get_lineNumber()
            );
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
    public void visit(InvokeExpNode node) {

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

    @Override
    public void visit(CommentNode node) {

        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }
}
