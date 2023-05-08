package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.Problem;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemType;
import dk.aau.p4.abaaja.Lib.Symbols.FuncSymbol;
import dk.aau.p4.abaaja.Lib.Symbols.Scope;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlArrayTypeDescriptor;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlStructDescriptor;
import dk.aau.p4.abaaja.Lib.Symbols.Symbol;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;

import java.util.ArrayList;
import java.util.List;

public class SymbolTableVisitor implements INodeVisitor {
    private ProblemCollection problemCollection;
    private SymbolTable symbolTable;
    private VisitorTools visitorTools;
    private TypeCheckingVisitor typeCheckingVisitor;

    public SymbolTableVisitor (ProblemCollection problemCollection) {
        this.symbolTable = new SymbolTable();
        this.visitorTools = new VisitorTools(this.symbolTable);
        this.problemCollection = problemCollection;
        typeCheckingVisitor = new TypeCheckingVisitor(this.problemCollection, this.symbolTable);
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
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
                MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(node.get_varDecType());

                // Check if the type exists
                if (typeDescriptor == null) {
                    problemCollection.addFormattedProblem(ProblemType.ERROR_UNKNOWN_TYPE, "The type \"" + node.get_varDecType().get_type() + "\" is unknown", node.get_lineNumber());
                } else {
                    mctlStructDescriptor.add_structVariables(node.get_id(), typeDescriptor);
                }
            }
        }

    }

    public void visit(MctlNode node) {
        initialScopeVisit(node.get_children());

        // Visit remaining lines
        for (BaseNode child : node.get_children()) { child.accept(this); }
    }

    public void visit(LineNode node) {
        for (BaseNode child : node.get_children()) { child.accept(this); }
    }

    public void visit(BlockNode node) {
        symbolTable.createScope();

        // Visit all function and struct declarations and add them to the symbol table
        initialScopeVisit(node.get_children());

        // Visit all remaining line
        for (BaseNode child : node.get_children()) { child.accept(this); }

        symbolTable.closeScope();
    }

    // Method for opening and visiting a new scope with a name
    private void visitNewScope(BlockNode node, String name) {
        symbolTable.createScope(name);

        // Visit all function and struct declarations and add them to the symbol table
        initialScopeVisit(node.get_children());

        // Visit all remaining line
        for (BaseNode child : node.get_children()) { child.accept(this); }

        symbolTable.closeScope();
    }

    // Fully Implemented
    public void visit(DecNode node) {
        for (BaseNode child : node.get_children()) { child.accept(this); }
    }

    // Fully Implemented
    public void visit(StateNode node) {
        for (BaseNode child : node.get_children()) { child.accept(this); }
    }

    public void visit(VarDecNode node) {
        MctlTypeDescriptor typeDescriptor;

        if (visitorTools.isDeclared(node.get_id())) {
            problemCollection.addFormattedProblem(ProblemType.ERROR_IDENTIFIER_CANNOT_BE_REUSED, "The identifier \"" + node.get_id() + "\" cannot be redeclared", node.get_lineNumber());
        } else {
            typeDescriptor = typeCheckingVisitor.visit(node.get_varDecType());
            if (typeDescriptor != null) {
                Symbol symbol = new Symbol(node.get_id(), typeDescriptor);
                this.symbolTable.insertSymbol(symbol);
            } else {
                problemCollection.addFormattedProblem(ProblemType.ERROR_UNKNOWN_TYPE, "The type \"" + node.get_varDecType().get_type() + "\" does not exist", node.get_lineNumber());
            }

        }
    }

    public void visit(FuncDecNode node) {
        boolean returnNodesPresent = false;
        List<Problem> problems = new ArrayList<Problem>();
        Symbol symbol = symbolTable.searchSymbol(node.get_id());

        // Only check the scope if has not yet been declared
        if (symbol != null && symbol instanceof FuncSymbol funcSymbol) {
            // Create scope for function block and create parameter symbols
            symbolTable.createScope("function");
            symbolTable.get_currentScope().set_returnType(funcSymbol.get_type());
            for (FormalParamNode formalParam : node.get_paramList()) {
                Symbol paramSymbol = new Symbol(formalParam.get_id());
                MctlTypeDescriptor tempType = typeCheckingVisitor.visit(formalParam.get_type());
                if (tempType == null) {
                    problemCollection.addFormattedProblem(ProblemType.ERROR_UNKNOWN_TYPE, "The type \"" + formalParam.get_type().get_type() + "\" is unknown", node.get_lineNumber());
                } else {
                    paramSymbol.set_type(tempType);
                }

                paramSymbol.set_isInstantiated(true);

                // Adding parameter to functionSymbol and current symbol table
                symbolTable.insertSymbol(paramSymbol);
            }

            initialScopeVisit(node.get_funcBlock().get_children());

            // Check lines of the function block
            for (BaseNode child : node.get_funcBlock().get_children()) {
                child.accept(this);
            }

            symbolTable.closeScope();
        }
    }

    // Fully Implemented
    public void visit(StructDecNode node) {}

    public void visit(IfStateNode node) {
        // Check if and else if statements for types
        for (ExpNode expressionNode: node.get_expChildren()) {
            MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(expressionNode);

            // Check expression type
            if (!typeDescriptor.get_type_literal().equals("BOOLEAN")) {
                problemCollection.addFormattedProblem(
                        ProblemType.ERROR_TYPE_MISMATCH,
                        "Expected type \"BOOLEAN\" but got \"" + typeDescriptor.get_type_literal() + "\"",
                        expressionNode.get_lineNumber()
                );
            }
        }
    }

    public void visit(RepeatStateNode node) {
        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(node.get_repeatExp());

        // Check expression type
        if (!(typeDescriptor.get_type_literal().equals("BOOLEAN") || typeDescriptor.get_type_literal().equals("NUMBER"))) {
            problemCollection.addFormattedProblem(
                    ProblemType.ERROR_TYPE_MISMATCH,
                    "Expected type \"NUMBER\" or \"BOOLEAN\" but got \"" + typeDescriptor.get_type_literal() + "\"",
                    node.get_lineNumber()
            );
        }

        // Visit the repeat block
        visitNewScope(node.get_expBlock(), "repeat");
    }

    public void visit(AssStateNode node) {
        Symbol variable;

        MctlTypeDescriptor idTypeDescriptor = typeCheckingVisitor.visit(node.get_assignId());
        MctlTypeDescriptor expTypeDescriptor = typeCheckingVisitor.visit(node.get_assignExp());

        IDExpNode idExpNode = node.get_assignId();

        while (!(idExpNode instanceof ActualIDExpNode actualIDNode)) {
            idExpNode = idExpNode.get_idNode();
        }

        if (idTypeDescriptor == null) {
            problemCollection.addFormattedProblem(
                    ProblemType.ERROR_UNDEFINED_IDENTIFIER,
                    "The variable \"" + actualIDNode.get_id() + "\" has not yet been declared",
                    node.get_lineNumber()
            );
        } else if (expTypeDescriptor == null) {
            problemCollection.addFormattedProblem(
                    ProblemType.ERROR_UNDEFINED_IDENTIFIER,
                    "The variable \"" + actualIDNode.get_id() + "\" cannot be assigned to an expression containing undeclared variables",
                    node.get_lineNumber()
            );
        } else {
            variable = symbolTable.searchSymbol(actualIDNode.get_id());

            if (variable == null) {
                problemCollection.addFormattedProblem(
                        ProblemType.ERROR_UNDEFINED_IDENTIFIER,
                        "The variable \"" + actualIDNode.get_id() + "\" has not yet been declared",
                        node.get_lineNumber()
                );
            } else {
                variable.set_isInstantiated(true);
                idTypeDescriptor = typeCheckingVisitor.visit(node.get_assignId());
                if (!(idTypeDescriptor.get_type_literal().equals(expTypeDescriptor.get_type_literal()))) {
                    problemCollection.addFormattedProblem(
                            ProblemType.ERROR_TYPE_MISMATCH,
                            "Expected " + idTypeDescriptor.get_type_literal() + " but got " + expTypeDescriptor.get_type_literal(),
                            node.get_lineNumber()
                    );
                    variable.set_isInstantiated(false);
                }
            }
        }
    }

    // Fully Implemented
    public void visit(InvokeNode node) {
        if (node instanceof FuncInvokeNode funcInvokeNode) { visit(funcInvokeNode); }
        else if (node instanceof VarMethodInvokeNode varMethodInvokeNode) { visit(varMethodInvokeNode); }
        else if (node instanceof StringMethodInvokeNode stringMethodInvokeNode) { visit(stringMethodInvokeNode); }
    }

    public void visit(FuncInvokeNode node) { typeCheckingVisitor.visit(node); }
    public void visit(VarMethodInvokeNode node) { typeCheckingVisitor.visit(node); }
    public void visit(StringMethodInvokeNode node) { typeCheckingVisitor.visit(node); }

    // Fully Implemented
    public void visit(ReturnNode node) {
        Scope scope = symbolTable.searchScopeName("function");

        // Check if the return node is within a function scope
        if (scope == null) {
            problemCollection.addFormattedProblem(ProblemType.ERROR_UNEXPECTED_RETURN,
                    "Encountered an unexpected \"return\" statement. \"return\" statements can only be defined within a function",
                    node.get_lineNumber()
            );
        }
        // Add problems for each return node if the function should return a value
        else if (!scope.get_returnType().get_type_literal().equals("NOTHING") && node.get_returnExp() != null) {
            // Get expression return type and function symbol
            MctlTypeDescriptor returnNodeType = typeCheckingVisitor.visit(node.get_returnExp());

            // Check if return node expression is of the correct type
            if (!returnNodeType.get_type_literal().equals(scope.get_returnType().get_type_literal())) {
                problemCollection.addFormattedProblem(
                        ProblemType.ERROR_TYPE_MISMATCH,
                        "The return type should be of type \"" + scope.get_returnType().get_type_literal() + "\" but the expression resolves to the type \"" + returnNodeType.get_type_literal() + "\"",
                        node.get_lineNumber()
                );
            }
        } else if (!scope.get_returnType().get_type_literal().equals("NOTHING") && node.get_returnExp() == null) {
            MctlTypeDescriptor returnNodeType = typeCheckingVisitor.visit(node.get_returnExp());

            // The return node should return a type but does not.
            problemCollection.addFormattedProblem(
                    ProblemType.ERROR_TYPE_MISMATCH,
                    "The return type should be of type \"" + scope.get_returnType().get_type_literal() + "\" but is \"NOTHING\"",
                    node.get_lineNumber()
            );

        } else if (scope.get_returnType().get_type_literal().equals("NOTHING") && node.get_returnExp() != null) {
            MctlTypeDescriptor returnNodeType = typeCheckingVisitor.visit(node.get_returnExp());

            // The return node should return a type but does not.
            problemCollection.addFormattedProblem(
                    ProblemType.ERROR_TYPE_MISMATCH,
                    "The return type should be of type \"" + scope.get_returnType().get_type_literal() + "\" but is \"" + returnNodeType.get_type_literal() + "\"",
                    node.get_lineNumber()
            );
        }
    }

    // Fully implemented in InitialFuncVisitor
    public void visit(FormalParamNode node) {}

    public void visit(InvokeExpNode node) { visit(node.getInvokeNode()); }
    public void visit(StopNode node) {
        // If the current scope is not a repeat statement an error should be created
        if (symbolTable.searchScopeName("repeat") == null) {
            problemCollection.addFormattedProblem(ProblemType.ERROR_UNEXPECTED_STOP,
                    "Encountered an unexpected \"stop\" statement. Only repeat statements can contain \"stop\" nodes",
                    node.get_lineNumber()
            );
        }
    }
    public void visit(TypeNode node) {}
    public void visit(BoolTypeNode node) {}
    public void visit(NumTypeNode node) {}
    public void visit(StringTypeNode node) {}
    public void visit(NothingTypeNode node) {}
    public void visit(IDTypeNode node) {}
    public void visit(ExpNode node) {}
    public void visit(UnaryExpNode node) {}
    public void visit(TypecastExpNode node) {}
    public void visit(BinaryExpNode node) {}
    public void visit(MulExpNode node) {}
    public void visit(AddExpNode node) {}
    public void visit(AndExpNode node) {}
    public void visit(OrExpNode node) {}
    public void visit(CompExpNode node) {}
    public void visit(EqualExpNode node) {}
    public void visit(IDExpNode node) {}
    public void visit(ActualIDExpNode node) {}
    public void visit(IDArrayExpNode node) {}
    public void visit(IDStructNode node) {}
    public void visit(BoolExpNode node) {}
    public void visit(NumExpNode node) {}
    public void visit(StringExpNode node) {}
    public void visit(CommentNode node) {}
}