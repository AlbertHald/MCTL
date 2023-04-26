package dk.aau.p4.abaaja.Lib.Visitors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ParseTree;

import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemType;
import dk.aau.p4.abaaja.mctlBaseVisitor;
import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.mctlParser;
import org.antlr.v4.runtime.tree.TerminalNode;

public class AstVisitor extends mctlBaseVisitor<BaseNode> {
    private ProblemCollection problemCollection;

    public AstVisitor(ProblemCollection problemCollection) {
        this.problemCollection = problemCollection;
    }

    private void addProblem(ParserRuleContext ctx, String message) {
        problemCollection.addProblem(ProblemType.ERROR_AST_BUILDER,
                message != "" ? message : "The AST builder encountered an unexpected error at line: " + ctx.start.getLine(),
                ctx.start.getLine());
    }


    @Override public BaseNode visitMctl(mctlParser.MctlContext ctx) {
        MctlNode program = new MctlNode();
        program.set_lineNumber(ctx.start.getLine());
        program.set_lineEndNumber(ctx.getStop().getLine());

        // Create array of ParseTree objects representing each line
        for (ParseTree child : ctx.children.toArray(ParseTree[]::new)) {
            // Check if the line is an instance of CommonToken (Removing EOF and other unimportant characters).
            if (child.getPayload() instanceof CommonToken) {
                continue;
            }
            program.add_child(visit(child));
        }

        return program;
    }

    @Override public BaseNode visitBlock(mctlParser.BlockContext ctx) {
        BlockNode blockNode = new BlockNode();
        blockNode.set_lineNumber(ctx.start.getLine());
        blockNode.set_lineEndNumber(ctx.getStop().getLine());

        for (ParseTree child : ctx.children) {
            // Skips square brackets
            if (child instanceof TerminalNode) { continue; }

            // Add line to block
            BaseNode tempNode = visit(child);
            if (tempNode instanceof LineNode) {
                blockNode.add_child((LineNode) tempNode);
            } else {
                addProblem(ctx, "");
            }
        }

        return blockNode;
    }

    @Override public CommentNode visitComment(mctlParser.CommentContext ctx) {
        CommentNode commentNode = new CommentNode();
        commentNode.set_lineNumber(ctx.getStart().getLine());
        commentNode.set_lineEndNumber(ctx.getStop().getLine());

        commentNode.set_text(ctx.COMMENT().getText());

        return commentNode;
    }

    @Override public BaseNode visitVarDecl(mctlParser.VarDeclContext ctx) { return visitVariableDeclaration(ctx.variableDeclaration()); }

    @Override public BaseNode visitVariableDeclaration(mctlParser.VariableDeclarationContext ctx) {
        // Set id of the variable node
        VarDecNode varDecNode = new VarDecNode();
        varDecNode.set_id(ctx.ID().getText());
        varDecNode.set_lineNumber(ctx.start.getLine());
        varDecNode.set_lineEndNumber(ctx.getStop().getLine());

        // Get variable type
        BaseNode varTypeNode = visit(ctx.variableType());

        if (varTypeNode instanceof TypeNode) {
            varDecNode.set_varDecType((TypeNode) varTypeNode);
        }

        return varDecNode;
    }

    @Override public BaseNode visitStructDeclaration(mctlParser.StructDeclarationContext ctx) {
        StructDecNode structDecNode = new StructDecNode();
        structDecNode.set_lineNumber(ctx.start.getLine());
        structDecNode.set_lineEndNumber(ctx.getStop().getLine());

        // Set struct ID
        structDecNode.set_id(ctx.ID().getText());

        // Iterate over variable declarations
        for (ParseTree child: ctx.variableDeclaration()) {
            BaseNode tempNode = visit(child);

            if (tempNode instanceof VarDecNode) {
                structDecNode.add_declaration((VarDecNode) tempNode);
            }
            else {
                addProblem(ctx, "");
            }
        }

        return structDecNode;
    }

    @Override public BaseNode visitIdStruct(mctlParser.IdStructContext ctx) {
        IDStructNode idStructNode = new IDStructNode();
        idStructNode.set_lineNumber(ctx.start.getLine());
        idStructNode.set_lineEndNumber(ctx.getStop().getLine());

        // Add the two individual ID's
        BaseNode tempIDNode = visit(ctx.id().get(0));
        if (tempIDNode instanceof IDExpNode) {
            idStructNode.set_idNode((IDExpNode) tempIDNode);
        }
        else {
            addProblem(ctx, "");
        }

        BaseNode tempAccessorNode = visit(ctx.id().get(1));
        if (tempAccessorNode instanceof ExpNode) {
            idStructNode.set_accessor((ExpNode) tempAccessorNode);
        }
        else {
            addProblem(ctx, "");
        }

        return idStructNode;
    }

    @Override public BaseNode visitIdArray(mctlParser.IdArrayContext ctx) {
        IDArrayExpNode idArrayExpNode = new IDArrayExpNode();
        idArrayExpNode.set_lineNumber(ctx.start.getLine());
        idArrayExpNode.set_lineEndNumber(ctx.getStop().getLine());

        // Add the ID
        BaseNode tempIdNode = visit(ctx.id());
        if (tempIdNode instanceof IDExpNode) {
            idArrayExpNode.set_idNode((IDExpNode) tempIdNode);
        }
        else {
            addProblem(ctx, "");
        }

        // Visit and add the expression node
        BaseNode tempAccessorNode = visit(ctx.expression());
        if (tempAccessorNode instanceof ExpNode) {
            idArrayExpNode.set_accessor((ExpNode) tempAccessorNode);
        }
        else {
            addProblem(ctx, "");
        }

        return idArrayExpNode;
    }

    @Override public BaseNode visitIdVar(mctlParser.IdVarContext ctx) {
        // Create an ID node and add its id
        ActualIDExpNode actualIDExpNode = new ActualIDExpNode();
        actualIDExpNode.set_id(ctx.getText());
        actualIDExpNode.set_lineNumber(ctx.start.getLine());
        actualIDExpNode.set_lineEndNumber(ctx.getStop().getLine());

        return actualIDExpNode;
    }

    @Override public BaseNode visitReturn(mctlParser.ReturnContext ctx) {
        ReturnNode returnNode = new ReturnNode();
        returnNode.set_lineNumber(ctx.start.getLine());
        returnNode.set_lineEndNumber(ctx.getStop().getLine());

        BaseNode expNode = visit(ctx.expression());

        //Set the return expression
        if (expNode instanceof ExpNode) {
            returnNode.set_returnExp((ExpNode) expNode);
        } else{
            addProblem(ctx, "");
        }

        return returnNode;
    }

    @Override public BaseNode visitFunctionDeclaration(mctlParser.FunctionDeclarationContext ctx) {
        FuncDecNode funcDecNode = new FuncDecNode();
        funcDecNode.set_lineNumber(ctx.start.getLine());
        funcDecNode.set_lineEndNumber(ctx.getStop().getLine());

        funcDecNode.set_id(ctx.ID().getText());

        // Visit and add all parameter nodes
        if (ctx.formalParameters() != null) {
            for (ParseTree child : ctx.formalParameters().formalParameter()) {
                BaseNode parameter = visit(child);

                if (parameter instanceof FormalParamNode) {
                    funcDecNode.add_param((FormalParamNode) parameter);
                }
                else {
                    addProblem(ctx, "");
                }
            }
        }

        // Visit the return type node
        BaseNode tempReturnTypeNode = visit(ctx.returnType());
        if (tempReturnTypeNode instanceof TypeNode) {
            funcDecNode.set_returnType((TypeNode) tempReturnTypeNode);
        }
        else {
            addProblem(ctx, "");
        }

        // Visit the block node
        BaseNode tempBlockNode = visit(ctx.block());
        if (tempBlockNode instanceof BlockNode) {
            funcDecNode.set_funcBlock((BlockNode) tempBlockNode);
        }
        else {
            addProblem(ctx, "");
        }

        return funcDecNode;
    }

    @Override public BaseNode visitIfStatement(mctlParser.IfStatementContext ctx) {
        IfStateNode ifStateNode = new IfStateNode();
        ifStateNode.set_lineNumber(ctx.start.getLine());
        ifStateNode.set_lineEndNumber(ctx.getStop().getLine());

        // Set Expression List
        for (ParseTree child : ctx.if_().ifLiteral()) {
            BaseNode tempExpNode = visit(child);
            if (tempExpNode instanceof ExpNode) {
                ifStateNode.add_expChild((ExpNode) tempExpNode);
            }
            else {
                addProblem(ctx, "");
            }
        }

        // Set Block List
        for (ParseTree child : ctx.if_().block()) {
            BaseNode tempBlockNode = visit(child);
            if (tempBlockNode instanceof BlockNode) {
                ifStateNode.add_blockChild((BlockNode) tempBlockNode);
            }
            else {
                addProblem(ctx, "");
            }
        }

        return ifStateNode;
    }

    @Override public BaseNode visitIfLiteral(mctlParser.IfLiteralContext ctx) {
        return visit(ctx.expression());
    }

    @Override public BaseNode visitRepeatStatement(mctlParser.RepeatStatementContext ctx) {
        RepeatStateNode repeatStateNode = new RepeatStateNode();
        repeatStateNode.set_lineNumber(ctx.start.getLine());
        repeatStateNode.set_lineEndNumber(ctx.getStop().getLine());

        // Visit and add the repeat expression
        BaseNode repeatExpNode = visit(ctx.repeat().expression());
        if (repeatExpNode instanceof ExpNode) {
            repeatStateNode.set_repeatExp((ExpNode) repeatExpNode);
        } else{
            addProblem(ctx, "");
        }

        // Visit and add the block to the repeat node
        BaseNode repeatBlock = visit(ctx.repeat().block());
        if (repeatBlock instanceof BlockNode) {
            repeatStateNode.set_expBlock((BlockNode) repeatBlock);
        }else{
            addProblem(ctx, "");
        }

        return repeatStateNode;
    }

    @Override public BaseNode visitAssignmentStatement(mctlParser.AssignmentStatementContext ctx) {
        return visit(ctx.assignment());
    }

    @Override public BaseNode visitInvokeStatement(mctlParser.InvokeStatementContext ctx) {
        return visit(ctx.invoke());
    }

    @Override public BaseNode visitStopStatement(mctlParser.StopStatementContext ctx) {
        StopNode stopNode = new StopNode();
        stopNode.set_lineNumber(ctx.start.getLine());
        stopNode.set_lineEndNumber(ctx.getStop().getLine());

        return stopNode;
    }

    @Override public BaseNode visitReturnStatement(mctlParser.ReturnStatementContext ctx) {
        return visitReturn(ctx.return_());
    }

    @Override public BaseNode visitExprAss(mctlParser.ExprAssContext ctx) {
        AssStateNode assStateNode = new AssStateNode();
        assStateNode.set_lineNumber(ctx.start.getLine());
        assStateNode.set_lineEndNumber(ctx.getStop().getLine());

        // Visit and add the id node
        BaseNode tempIdNode = visit(ctx.id());
        if (tempIdNode instanceof IDExpNode) {
            assStateNode.set_assignId((IDExpNode) tempIdNode);
        }
        else {
            addProblem(ctx, "");
        }

        // Visit and add the expression node
        BaseNode tempExprNode = visit(ctx.expression());
        if (tempExprNode instanceof ExpNode) {
            assStateNode.set_assignExp((ExpNode) tempExprNode);
        }
        else {
            addProblem(ctx, "");
        }

        return assStateNode;
    }

    @Override public BaseNode visitIncrAss(mctlParser.IncrAssContext ctx) {
        AssStateNode assStateNode = new AssStateNode();
        assStateNode.set_lineNumber(ctx.start.getLine());
        assStateNode.set_lineEndNumber(ctx.getStop().getLine());

        // Visit and add the id node
        BaseNode tempIdNode = visit(ctx.id());
        if (tempIdNode instanceof IDExpNode) {
            IDExpNode idExpNode = (IDExpNode) tempIdNode;
            assStateNode.set_assignId(idExpNode);

            // Add operator to node
            AddExpNode addExpNode = new AddExpNode();
            addExpNode.set_lineNumber(ctx.start.getLine());
            addExpNode.set_lineEndNumber(ctx.getStop().getLine());
            if (ctx.op.getType() == mctlParser.INCREMENT) {
                assStateNode.set_literalIncrement("++");
                addExpNode.set_operator(mctlParser.PLUS);
                addExpNode.set_operatorLiteral("+");
            }
            else if (ctx.op.getType() == mctlParser.DECREMENT) {
                assStateNode.set_literalIncrement("--");
                addExpNode.set_operator(mctlParser.MINUS);
                addExpNode.set_operatorLiteral("-");
            }
            else {
                addProblem(ctx, "");
            }

            // Set expression to id plus number node with value 1
            NumExpNode numExpNode = new NumExpNode();
            numExpNode.set_lineNumber(ctx.start.getLine());
            numExpNode.set_lineEndNumber(ctx.getStop().getLine());
            numExpNode.set_result(1);

            addExpNode.add_child(idExpNode);
            addExpNode.add_child(numExpNode);

            assStateNode.set_assignExp(addExpNode);
        }
        else {
            addProblem(ctx, "");
        }

        return assStateNode;
    }

    @Override public BaseNode visitFunctionInvoke(mctlParser.FunctionInvokeContext ctx) {
        FuncInvokeNode funcInvokeNode = new FuncInvokeNode();
        funcInvokeNode.set_lineNumber(ctx.start.getLine());
        funcInvokeNode.set_lineEndNumber(ctx.getStop().getLine());

        ActualIDExpNode actualIDExpNode = new ActualIDExpNode();
        actualIDExpNode.set_lineNumber(ctx.start.getLine());
        actualIDExpNode.set_lineEndNumber(ctx.getStop().getLine());

        actualIDExpNode.set_id(ctx.ID().getText());
        funcInvokeNode.set_id(actualIDExpNode);

        if(ctx.actualParameters() != null) {
            for (ParseTree child : ctx.actualParameters().expression()) {
                BaseNode node = visit(child);
                if (node instanceof ExpNode) {
                    funcInvokeNode.add_paramExp((ExpNode) node);
                } else {
                    addProblem(ctx, "");
                }
            }
        }
        return funcInvokeNode;
    }

    @Override public BaseNode visitVarMethodInvoke(mctlParser.VarMethodInvokeContext ctx) {
        VarMethodInvokeNode varMethodInvokeNode = new VarMethodInvokeNode();
        varMethodInvokeNode.set_lineNumber(ctx.start.getLine());
        varMethodInvokeNode.set_lineEndNumber(ctx.getStop().getLine());

        BaseNode tempVarIdNode = visit(ctx.id());
        if (tempVarIdNode instanceof IDExpNode) {
            varMethodInvokeNode.set_varId((IDExpNode) tempVarIdNode);
        }
        else{
            addProblem(ctx, "");
        }

        ActualIDExpNode methodIDExpNode = new ActualIDExpNode();
        methodIDExpNode.set_lineNumber(ctx.start.getLine());
        methodIDExpNode.set_lineEndNumber(ctx.getStop().getLine());

        methodIDExpNode.set_id(ctx.ID().getText());
        varMethodInvokeNode.set_id(methodIDExpNode);

        if(ctx.actualParameters() != null) {
            for (ParseTree child : ctx.actualParameters().expression()) {
                BaseNode node = visit(child);
                if (node instanceof ExpNode) {
                    varMethodInvokeNode.add_paramExp((ExpNode) node);
                } else {
                    addProblem(ctx, "");
                }
            }
        }
        return varMethodInvokeNode;
    }

    @Override public BaseNode visitStringMethodInvoke(mctlParser.StringMethodInvokeContext ctx) {
        StringMethodInvokeNode stringMethodInvokeNode = new StringMethodInvokeNode();
        stringMethodInvokeNode.set_lineNumber(ctx.start.getLine());
        stringMethodInvokeNode.set_lineEndNumber(ctx.getStop().getLine());

        StringExpNode stringExpNode = new StringExpNode();
        stringExpNode.set_lineNumber(ctx.start.getLine());
        stringExpNode.set_lineEndNumber(ctx.getStop().getLine());

        stringExpNode.set_result(ctx.STRING().getText());
        stringMethodInvokeNode.set_string(stringExpNode);

        ActualIDExpNode methodIDExpNode = new ActualIDExpNode();
        methodIDExpNode.set_lineNumber(ctx.start.getLine());
        methodIDExpNode.set_lineEndNumber(ctx.getStop().getLine());

        methodIDExpNode.set_id(ctx.ID().getText());
        stringMethodInvokeNode.set_id(methodIDExpNode);

        if(ctx.actualParameters() != null) {
            for (ParseTree child : ctx.actualParameters().expression()) {
                BaseNode node = visit(child);
                if (node instanceof ExpNode) {
                    stringMethodInvokeNode.add_paramExp((ExpNode) node);
                } else {
                    addProblem(ctx, "");
                }
            }
        }
        return stringMethodInvokeNode;
    }

    @Override public BaseNode visitFormalParameter(mctlParser.FormalParameterContext ctx) {
        FormalParamNode formalParamNode = new FormalParamNode();
        formalParamNode.set_lineNumber(ctx.start.getLine());
        formalParamNode.set_lineEndNumber(ctx.getStop().getLine());

        formalParamNode.set_id(ctx.ID().getText());

        // Set type
        BaseNode tempTypeNode = visit(ctx.variableType());
        if (tempTypeNode instanceof TypeNode) {
            formalParamNode.set_type((TypeNode) tempTypeNode);
        } else {
            addProblem(ctx, "");
        }

        return formalParamNode;
    }

    @Override public BaseNode visitNumberExpr(mctlParser.NumberExprContext ctx) {
        NumExpNode numExpNode = new NumExpNode();
        numExpNode.set_lineNumber(ctx.start.getLine());
        numExpNode.set_lineEndNumber(ctx.getStop().getLine());

        String stringFormattedNumber = ctx.getText();
        numExpNode.set_result(stringFormattedNumber.contains(".") ? Double.parseDouble(stringFormattedNumber) : Integer.parseInt(stringFormattedNumber));

        return numExpNode;
    }

    @Override public BaseNode visitAndExpr(mctlParser.AndExprContext ctx) {
        AndExpNode andExpNode = new AndExpNode();
        andExpNode.set_lineNumber(ctx.start.getLine());
        andExpNode.set_lineEndNumber(ctx.getStop().getLine());

        andExpNode.set_operatorLiteral("and");

        // Iterate over the two individual expressions of the and expression
        for (ParseTree child : ctx.expression()) {
            BaseNode tempExprNode = visit(child);
            if (tempExprNode instanceof ExpNode) {
                andExpNode.add_child((ExpNode) tempExprNode);
            } else {
                addProblem(ctx, "");
            }
        }

        return andExpNode;
    }

    @Override public BaseNode visitCompExpr(mctlParser.CompExprContext ctx) {
        CompExpNode compExpNode = new CompExpNode();
        compExpNode.set_lineNumber(ctx.start.getLine());
        compExpNode.set_lineEndNumber(ctx.getStop().getLine());

        // Add the comparison operator
        compExpNode.set_operator(ctx.op.getType());
        compExpNode.set_operatorLiteral(ctx.op.getText());

        // Iterate over the two individual expressions of the comparison expression
        for (ParseTree child : ctx.expression()) {
            BaseNode tempExprNode = visit(child);
            if (tempExprNode instanceof ExpNode) {
                compExpNode.add_child((ExpNode) tempExprNode);
            } else {
                addProblem(ctx, "");
            }
        }

        return compExpNode;
    }

    @Override public BaseNode visitBoolExpr(mctlParser.BoolExprContext ctx) {
        BoolExpNode boolExpNode = new BoolExpNode();
        boolExpNode.set_lineNumber(ctx.start.getLine());
        boolExpNode.set_lineEndNumber(ctx.getStop().getLine());
        boolExpNode.set_result(Boolean.parseBoolean(ctx.getText()));

        return boolExpNode;
    }

    @Override public BaseNode visitAddExpr(mctlParser.AddExprContext ctx) {
        AddExpNode addExpNode = new AddExpNode();
        addExpNode.set_lineNumber(ctx.start.getLine());
        addExpNode.set_lineEndNumber(ctx.getStop().getLine());

        // Add operator to node
        addExpNode.set_operator(ctx.op.getType());
        addExpNode.set_operatorLiteral(ctx.op.getText());
        if (!(ctx.op.getType() == mctlParser.PLUS || ctx.op.getType() == mctlParser.MINUS)) {
            addProblem(ctx, "");
        }

        // Iterate over the two individual expressions of the comparison expression
        for (ParseTree child : ctx.expression()) {
            BaseNode tempNode = visit(child);
            if (tempNode instanceof ExpNode) {
                addExpNode.add_child((ExpNode) tempNode);
            }
            else {
                addProblem(ctx, "");
            }
        }

        return addExpNode;
    }

    @Override public BaseNode visitOrExpr(mctlParser.OrExprContext ctx) {
        OrExpNode orExpNode = new OrExpNode();
        orExpNode.set_lineNumber(ctx.start.getLine());
        orExpNode.set_lineEndNumber(ctx.getStop().getLine());

        orExpNode.set_operatorLiteral("or");

        // Iterate over the two individual expressions of the or expression
        for (ParseTree child : ctx.expression()) {
            BaseNode tempExprNode = visit(child);
            if (tempExprNode instanceof ExpNode) {
                orExpNode.add_child((ExpNode) tempExprNode);
            } else {
                addProblem(ctx, "");
            }
        }

        return orExpNode;
    }

    @Override public BaseNode visitTypecast(mctlParser.TypecastContext ctx) {
        TypecastExpNode typecastExpNode = new TypecastExpNode();
        typecastExpNode.set_lineNumber(ctx.start.getLine());
        typecastExpNode.set_lineEndNumber(ctx.getStop().getLine());

        BaseNode tempTypeNode = visit(ctx.variableType());
        BaseNode tempExprNode = visit(ctx.expression());

        if (tempTypeNode instanceof TypeNode && tempExprNode instanceof ExpNode) {
            typecastExpNode.set_typeNode((TypeNode) tempTypeNode);
            typecastExpNode.set_expression_node((ExpNode) tempExprNode);
        }
        else {
            addProblem(ctx, "");
        }

        return typecastExpNode;
    }

    @Override public BaseNode visitEqualExpr(mctlParser.EqualExprContext ctx) {
        EqualExpNode equalExpNode = new EqualExpNode();
        equalExpNode.set_lineNumber(ctx.start.getLine());
        equalExpNode.set_lineEndNumber(ctx.getStop().getLine());

        // Add operator to node
        equalExpNode.set_operator(ctx.op.getType());
        equalExpNode.set_operatorLiteral(ctx.op.getText());
        if (!(ctx.op.getType() == mctlParser.EQUAL || ctx.op.getType() == mctlParser.NOTEQUAL)) {
            addProblem(ctx, "");
        }

        // Iterate over the two individual expressions of the equality expression
        for (ParseTree child : ctx.expression()) {
            BaseNode tempExpr = visit(child);
            if (tempExpr instanceof ExpNode) {
                equalExpNode.add_child((ExpNode) tempExpr);
            }
            else {
                addProblem(ctx, "");
            }
        }

        return equalExpNode;
    }

    @Override public BaseNode visitIdExpr(mctlParser.IdExprContext ctx) {
        return(visit(ctx.id()));
    }

    @Override public BaseNode visitUnaryExpr(mctlParser.UnaryExprContext ctx) {
        UnaryExpNode unaryExpNode = new UnaryExpNode();
        unaryExpNode.set_lineNumber(ctx.start.getLine());
        unaryExpNode.set_lineEndNumber(ctx.getStop().getLine());

        // Add operator to node
        unaryExpNode.set_operator(ctx.op.getType());
        unaryExpNode.set_operatorLiteral(ctx.op.getText());
        if (!(ctx.op.getType() == mctlParser.PLUS || ctx.op.getType() == mctlParser.MINUS || ctx.op.getType() == mctlParser.NOT)) {
            addProblem(ctx, "");
        }

        // control if the individual expressions of the comparison expression
        ParseTree child = ctx.expression();
        BaseNode tempNode = visit(child);
        if (tempNode instanceof ExpNode) {
            unaryExpNode.add_child((ExpNode) tempNode);
        }
        else {
            addProblem(ctx, "");
        }
        return unaryExpNode;
    }

    @Override public BaseNode visitParenExpr(mctlParser.ParenExprContext ctx) { return visit(ctx.expression()); }

    @Override public BaseNode visitMulExpr(mctlParser.MulExprContext ctx) {
        MulExpNode mulExpNode = new MulExpNode();
        mulExpNode.set_lineNumber(ctx.start.getLine());
        mulExpNode.set_lineEndNumber(ctx.getStop().getLine());

        // Add operator to node
        mulExpNode.set_operator(ctx.op.getType());
        mulExpNode.set_operatorLiteral(ctx.op.getText());
        if (!(ctx.op.getType() == mctlParser.MULTIPLY || ctx.op.getType() == mctlParser.DIVIDE || ctx.op.getType() == mctlParser.MODULO)) {
            addProblem(ctx, "");
        }

        for (ParseTree child : ctx.expression()) {
            BaseNode tempExpr = visit(child);
            if (tempExpr instanceof ExpNode) {
                mulExpNode.add_child((ExpNode) tempExpr);
            }
            else {
                addProblem(ctx, "");
            }
        }

        return mulExpNode;
    }

    @Override public BaseNode visitStringExpr(mctlParser.StringExprContext ctx) {
        StringExpNode stringExpNode = new StringExpNode();
        stringExpNode.set_lineNumber(ctx.start.getLine());
        stringExpNode.set_lineEndNumber(ctx.getStop().getLine());
        stringExpNode.set_result(ctx.getText());

        return stringExpNode;
    }

    @Override public BaseNode visitInvExpr(mctlParser.InvExprContext ctx) { return visit(ctx.invoke()); }
    @Override public BaseNode visitReturnTypeVariable(mctlParser.ReturnTypeVariableContext ctx) { return visit(ctx.variableType()); }

    @Override public BaseNode visitReturnTypeNothing(mctlParser.ReturnTypeNothingContext ctx) {
        NothingTypeNode nothingTypeNode = new NothingTypeNode();

        // Set the line number
        nothingTypeNode.set_lineNumber(ctx.start.getLine());
        nothingTypeNode.set_lineEndNumber(ctx.getStop().getLine());

        return nothingTypeNode;
    }

    @Override public BaseNode visitVariableType(mctlParser.VariableTypeContext ctx) {
        TypeNode typeNode;

        // Determine the type
        switch (ctx.baseVariableType().getText()) {
            case "NUMBER":
                typeNode = new NumTypeNode();
                break;
            case "STRING":
                typeNode = new StringTypeNode();
                break;
            case "BOOLEAN":
                typeNode = new BoolTypeNode();
                break;
            default:
                typeNode = new IDTypeNode();
                typeNode.set_type(ctx.baseVariableType().getText());
        }

        // Calculate and set the array degree
        typeNode.set_arrayDegree(ctx.LSQR().size());

        // Set the line number
        typeNode.set_lineNumber(ctx.start.getLine());
        typeNode.set_lineEndNumber(ctx.getStop().getLine());

        return typeNode;
    }
}
