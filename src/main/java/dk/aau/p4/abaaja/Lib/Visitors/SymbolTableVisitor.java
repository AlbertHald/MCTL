package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.Problem;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemType;
import dk.aau.p4.abaaja.Lib.Symbols.FuncSymbol;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlArrayTypeDescriptor;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlStructDescriptor;
import dk.aau.p4.abaaja.Lib.Symbols.Symbol;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;

import java.util.ArrayList;
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
        for (BaseNode child : node.get_children()) { child.accept(this); }
    }

    public void visit(LineNode node) {
        for (BaseNode child : node.get_children()) { child.accept(this); }
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

    // Fully Implemented
    @Override
    public void visit(DecNode node) {
        for (BaseNode child : node.get_children()) { child.accept(this); }
    }

    // Fully Implemented
    @Override
    public void visit(StateNode node) {
        for (BaseNode child : node.get_children()) { child.accept(this); }
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
        boolean returnNodesPresent = false;
        List<Problem> problems = new ArrayList<Problem>();
        FuncSymbol funcSymbol = (FuncSymbol) symbolTable.searchSymbol(node.get_id());

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
        for (BaseNode child : node.get_funcBlock().get_children()) {
            if (child instanceof ReturnNode returnNode) {
                // Add problems for each return node it the function should return a value
                if (!funcSymbol.get_type().get_type_literal().equals("NOTHING")) {
                    // Get expression return type and function symbol
                    MctlTypeDescriptor returnNodeType = typeCheckingVisitor.visit(returnNode.get_returnExp());

                    // Check if return node expression is of the correct type
                    if (!returnNodeType.get_type_literal().equals(funcSymbol.get_type().get_type_literal())) {
                        problemCollection.addProblem(ProblemType.ERROR_TYPE_MISMATCH,
                                "The return expression resolves to type \"" + funcSymbol.get_type().get_type_literal() + "\" but resolves to the type \"" + returnNodeType.get_type_literal() + "\"",
                                returnNode.get_lineNumber());
                    }
                } else {
                    // Encountered unexpected return nodes
                    problemCollection.addProblem(ProblemType.ERROR_UNEXPECTED_RETURN, "Encountered an unexpected return. The function is defined to return NOTHING", returnNode.get_lineNumber());
                }
            } else {
                child.accept(this);
            }
        }

        symbolTable.closeScope();
    }

    // Fully Implemented
    @Override
    public void visit(StructDecNode node) {}

    @Override
    public void visit(IfStateNode node) {
        // Check if and else if statements for types
        for (ExpNode expressionNode: node.get_expChildren()) {
            MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(expressionNode);

            // Check expression type
            if (!typeDescriptor.get_type_literal().equals("BOOLEAN")) {
                problemCollection.addProblem(
                        ProblemType.ERROR_TYPE_MISMATCH,
                        "Expected type \"BOOLEAN\" but got \"" + typeDescriptor.get_type_literal() + "\"",
                        expressionNode.get_lineNumber()
                );
            }
        }
    }

    @Override
    public void visit(RepeatStateNode node) {
        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(node.get_repeatExp());

        // Check expression type
        if (!(typeDescriptor.get_type_literal().equals("BOOLEAN") || typeDescriptor.get_type_literal().equals("NUMBER"))) {
            problemCollection.addProblem(
                    ProblemType.ERROR_TYPE_MISMATCH,
                    "Expected type \"NUMBER\" or \"BOOLEAN\" but got \"" + typeDescriptor.get_type_literal() + "\"",
                    node.get_lineNumber()
            );
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

    // Fully Implemented
    @Override
    public void visit(InvokeNode node) {
        if (node instanceof FuncInvokeNode) { visit((FuncInvokeNode) node); }
        else if (node instanceof VarMethodInvokeNode) { visit((VarMethodInvokeNode) node); }
        else if (node instanceof StringMethodInvokeNode) { visit((StringMethodInvokeNode) node); }
    }

    private void checkFunctionParams (List<ExpNode> expresionNodes, FuncSymbol funcSymbol, int lineNumber) {
        int counter = 0;

        // Check if the parameter types match
        for (ExpNode expressionNode : expresionNodes) {
            boolean typeMatched = false;

            MctlTypeDescriptor expressionType = typeCheckingVisitor.visit(expressionNode);

            // Check if the expression is also an invoke node
            if (expressionNode instanceof InvokeExpNode) { visit(expressionNode); }

            // Check if type of parameter is ANY or one of the expected types
            if (!funcSymbol.get_types().get(counter).get(0).get_type_literal().equals("ANY")){
                for (MctlTypeDescriptor typeDescriptor : funcSymbol.get_types().get(counter)) {
                    if(typeDescriptor.get_type_literal().equals(expressionType.get_type_literal())) {
                        // Parameter type matched
                        typeMatched = true;
                        break;
                    }
                }
            }
            else {
                typeMatched = true;
            }

            // Add problem
            if (!typeMatched) {
                StringBuilder typeLiterals = new StringBuilder();
                for (MctlTypeDescriptor typeDescriptor : funcSymbol.get_types().get(counter)) {
                    typeLiterals.append("\"").append(typeDescriptor.get_type_literal()).append("\", ");
                }

                problemCollection.addProblem(
                        ProblemType.ERROR_TYPE_MISMATCH,
                        "Expected one of the following types " + typeLiterals + " for parameter " + counter + " but got \"" + expressionType.get_type_literal() + "\"",
                        lineNumber
                );
            }

            counter++;
        }
    }

    @Override
    public void visit(FuncInvokeNode node) {
        Symbol symbol = symbolTable.searchSymbol(node.get_id().get_id());

        if (symbol == null) {
            // Function has not been declared
            problemCollection.addProblem(
                    ProblemType.ERROR_UNDEFINED_IDENTIFIER,
                    "The function \"" + node.get_id().get_id() + "\" has not yet been declared",
                    node.get_lineNumber()
            );
        } else if (!(symbol instanceof FuncSymbol)) {
            // ID refers to variable
            problemCollection.addProblem(
                    ProblemType.ERROR_ID_NOT_FUNCTION,
                    "The ID \"" + node.get_id().get_id() + "\" refers to a variable and can therefore not be invoked",
                    node.get_lineNumber()
            );
        } else {
            // Is valid function symbol
            FuncSymbol funcSymbol = (FuncSymbol) symbol;
            if (funcSymbol.getIsStringFunction() || funcSymbol.getIsVarFunction()) {
                // Function should be called on string or var
                problemCollection.addProblem(
                        ProblemType.ERROR_UTILITY_FUNCTION_INVOKED_IN_WRONG_CONTEXT,
                        "The function \"" + node.get_id().get_id() + "\" is called in the wrong context",
                        node.get_lineNumber()
                );
            } else if ((funcSymbol.get_types().size() == 0 && node.get_paramExps().size() != 0) || funcSymbol.get_types().size() != node.get_paramExps().size()) {
                // Number of parameters does not match
                problemCollection.addProblem(
                        ProblemType.ERROR_PARAMETERS_DOES_NOT_MATCH,
                        "The provided number of parameters: " + node.get_paramExps().size() + " does not match the expected: " + funcSymbol.get_types().size() + " parameters",
                        node.get_lineNumber()
                );
            }
            else {
                // Check function parameters
                checkFunctionParams(node.get_paramExps(), funcSymbol, node.get_lineNumber());
            }
        }
    }

    @Override
    public void visit(VarMethodInvokeNode node) {
        Symbol symbol = symbolTable.searchSymbol(node.get_id().get_id());

        if (symbol == null) {
            // Function does not exist
            problemCollection.addProblem(
                    ProblemType.ERROR_UNDEFINED_IDENTIFIER,
                    "The function \"" + node.get_id().get_id() + "\" doesnt exist",
                    node.get_lineNumber()
            );
        } else if (!(symbol instanceof FuncSymbol)) {
            // ID refers to variable
            problemCollection.addProblem(
                    ProblemType.ERROR_ID_NOT_FUNCTION,
                    "The ID \"" + node.get_id().get_id() + "\" refers to a variable and can therefore not be invoked",
                    node.get_lineNumber()
            );
        } else {
            // Is valid function symbol
            FuncSymbol funcSymbol = (FuncSymbol) symbol;
            MctlTypeDescriptor expectedVarType = funcSymbol.getExpectedVarType();
            MctlTypeDescriptor varType = typeCheckingVisitor.visit(node.get_varId());

            // Check if the var types match
            boolean notDeclared = (expectedVarType == null || varType == null);
            boolean typesMatch = false;

            // Check if types match (If they are not null)
            if (!notDeclared) {
                if (expectedVarType.get_type_literal().equals(varType.get_type_literal())) {
                    typesMatch = true;
                } else if (funcSymbol.getIsStringFunction() && varType.get_type_literal().equals("STRING")) {
                    typesMatch = true;
                } else if (varType instanceof MctlArrayTypeDescriptor varArrayType &&
                        expectedVarType instanceof MctlArrayTypeDescriptor expectedVarArrayType) {
                    if (expectedVarArrayType.get_contained_type_literal().equals("ANY") || varArrayType.get_contained_type_literal().equals(expectedVarArrayType.get_contained_type_literal())) {
                        typesMatch = true;
                    }
                }
            }

            if (!funcSymbol.getIsVarFunction() ) {
                // Function should be called on var
                problemCollection.addProblem(
                        ProblemType.ERROR_UTILITY_FUNCTION_INVOKED_IN_WRONG_CONTEXT,
                        "The function \"" + node.get_id().get_id() + "\" cannot be called on variables",
                        node.get_lineNumber()
                );
            } else if (notDeclared) {
                // Variable has not been declared
                problemCollection.addProblem(
                        ProblemType.ERROR_UNDEFINED_IDENTIFIER,
                        "The function \"" + node.get_id().get_id() + "\" cannot be called on undeclared variables",
                        node.get_lineNumber()
                );
            } else if (!typesMatch) {
                // Type is not the expected
                problemCollection.addProblem(
                        ProblemType.ERROR_TYPE_MISMATCH,
                        "Unsupported variable type \"" + varType.get_type_literal() + "\" for function " + node.get_id().get_id(),
                        node.get_lineNumber()
                );
            } else if ((funcSymbol.get_types().size() == 0 && node.get_paramExps().size() != 0) || funcSymbol.get_types().size() != node.get_paramExps().size()) {
                // Number of parameters does not match
                problemCollection.addProblem(
                        ProblemType.ERROR_PARAMETERS_DOES_NOT_MATCH,
                        "The provided number of parameters: " + node.get_paramExps().size() + " does not match the expected: " + funcSymbol.get_types().size() + " parameters",
                        node.get_lineNumber()
                );
            }
            else {
                // Check function parameters
                checkFunctionParams(node.get_paramExps(), funcSymbol, node.get_lineNumber());
            }
        }
    }

    @Override
    public void visit(StringMethodInvokeNode node) {
        Symbol symbol = symbolTable.searchSymbol(node.get_id().get_id());

        if (symbol == null) {
            // Function does not exist
            problemCollection.addProblem(
                    ProblemType.ERROR_UNDEFINED_IDENTIFIER,
                    "The function \"" + node.get_id().get_id() + "\" doesnt exist",
                    node.get_lineNumber()
            );
        } else if (!(symbol instanceof FuncSymbol)) {
            // ID refers to variable
            problemCollection.addProblem(
                    ProblemType.ERROR_ID_NOT_FUNCTION,
                    "The ID \"" + node.get_id().get_id() + "\" refers to a variable and can therefore not be invoked",
                    node.get_lineNumber()
            );
        } else {
            // Is valid function symbol
            FuncSymbol funcSymbol = (FuncSymbol) symbol;
            if (!funcSymbol.getIsStringFunction()) {
                // Function should be called on string
                problemCollection.addProblem(
                        ProblemType.ERROR_UTILITY_FUNCTION_INVOKED_IN_WRONG_CONTEXT,
                        "The function \"" + node.get_id().get_id() + "\" cannot be called on type STRING",
                        node.get_lineNumber()
                );
            }
            else if ((funcSymbol.get_types().size() == 0 && node.get_paramExps().size() != 0) || funcSymbol.get_types().size() != node.get_paramExps().size()) {
                // Number of parameters does not match
                problemCollection.addProblem(
                        ProblemType.ERROR_PARAMETERS_DOES_NOT_MATCH,
                        "The provided number of parameters: " + node.get_paramExps().size() + " does not match the expected: " + funcSymbol.get_types().size() + " parameters",
                        node.get_lineNumber()
                );
            }
            else {
                // Check function parameters
                checkFunctionParams(node.get_paramExps(), funcSymbol, node.get_lineNumber());
            }
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
    public void visit(InvokeExpNode node) { visit(node.getInvokeNode()); }

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
