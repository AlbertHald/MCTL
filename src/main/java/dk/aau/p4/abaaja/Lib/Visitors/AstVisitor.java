package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.mctlBaseVisitor;
import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.mctlParser;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ParseTree;

public class AstVisitor extends mctlBaseVisitor<BaseNode> {
    @Override public BaseNode visitMctl(mctlParser.MctlContext ctx) {
        MctlNode program = new MctlNode();
        program.set_lineNumber(ctx.start.getLine());

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

        for (ParseTree child : ctx.line()) {
            BaseNode tempNode = visit(child);
            if (tempNode instanceof LineNode) {
                blockNode.add_line((LineNode) tempNode);
            } else {
                // TODO: Possibly implement error handling
            }
        }

        return blockNode;
    }

    @Override public BaseNode visitLine(mctlParser.LineContext ctx) { return visitChildren(ctx); }
    @Override public BaseNode visitDeclaration(mctlParser.DeclarationContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitVariableDeclaration(mctlParser.VariableDeclarationContext ctx) {
        // Set id of the variable node
        VarDecNode varDecNode = new VarDecNode();
        varDecNode.set_id(ctx.ID().getText());
        varDecNode.set_lineNumber(ctx.start.getLine());

        // Get variable type
        BaseNode varTypeNode = visit(ctx.variableType());

        if (varTypeNode instanceof TypeNode) {
            varDecNode.set_varDecType((TypeNode) varTypeNode);
        }

        return visitChildren(ctx);
    }

    @Override public BaseNode visitStructDeclaration(mctlParser.StructDeclarationContext ctx) {
        StructDecNode structDecNode = new StructDecNode();
        structDecNode.set_lineNumber(ctx.start.getLine());

        // Set struct ID
        structDecNode.set_id(ctx.ID().getText());

        // Iterate over variable declarations
        for (ParseTree child: ctx.structBlock().variableDeclaration()) {
            BaseNode tempNode = visit(child);

            if (tempNode instanceof VarDecNode) {
                structDecNode.add_declaration((VarDecNode) tempNode);
            }
            else {
                // TODO: Potentially implement error handling?
            }
        }

        return structDecNode;
    }

    @Override public BaseNode visitIdStruct(mctlParser.IdStructContext ctx) {
        IDStructNode idStructNode = new IDStructNode();
        idStructNode.set_lineNumber(ctx.start.getLine());

        // Add the two individual ID's
        for (ParseTree child : ctx.id()) {
            BaseNode tempIdNode = visit(child);
            if (tempIdNode instanceof IDExpNode) {
                idStructNode.add_child((IDExpNode) tempIdNode);
            }
            else {
                // TODO: Potentially implement error handling?
            }
        }

        return idStructNode;
    }

    @Override public BaseNode visitIdArray(mctlParser.IdArrayContext ctx) {
        IDArrayExpNode idArrayExpNode = new IDArrayExpNode();
        idArrayExpNode.set_lineNumber(ctx.start.getLine());

        // Add the ID
        BaseNode tempIdNode = visit(ctx.id());
        if (tempIdNode instanceof IDExpNode) {
            idArrayExpNode.add_child((IDExpNode) tempIdNode);
        }
        else {
            // TODO: Potentially implement error handling?
        }

        // Visit and add all expression nodes
        for (ParseTree child : ctx.expression()) {
            BaseNode tempNode = visit(child);
            if (tempNode instanceof ExpNode) {
                idArrayExpNode.add_child((ExpNode) tempNode);
            }
            else {
                // TODO: Potentially implement error handling?
            }
        }

        return idArrayExpNode;
    }

    @Override public BaseNode visitIdVar(mctlParser.IdVarContext ctx) {
        // Create an ID node and add its id
        ActualIDExpNode actualIDExpNode = new ActualIDExpNode();
        actualIDExpNode.set_ID(ctx.getText());
        actualIDExpNode.set_lineNumber(ctx.start.getLine());

        return actualIDExpNode;
    }

    @Override public BaseNode visitStatement(mctlParser.StatementContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitReturn(mctlParser.ReturnContext ctx) {
        ReturnNode returnNode = new ReturnNode();
        returnNode.set_lineNumber(ctx.start.getLine());

        BaseNode expNode = visit(ctx.expression());

        //Set the return expression
        if (expNode instanceof ExpNode) {
            returnNode.set_returnExp((ExpNode) expNode);
        } else{
            //TODO: ERROR
        }

        return returnNode;
    }

    @Override public BaseNode visitFunctionDeclaration(mctlParser.FunctionDeclarationContext ctx) {
        FuncDecNode funcDecNode = new FuncDecNode();
        funcDecNode.set_lineNumber(ctx.start.getLine());

        funcDecNode.set_id(ctx.ID().getText());

        // Visit and add all parameter nodes
        for (ParseTree child : ctx.formalParameters().formalParameter()) {
            BaseNode parameter = visit(child);

            if (parameter instanceof FormalParamNode) {
                funcDecNode.add_param((FormalParamNode) parameter);
            }
            else {
                // TODO: Potentially implement error handling?
            }
        }

        // Visit the return type node
        BaseNode tempReturnTypeNode = visit(ctx.returnType());
        if (tempReturnTypeNode instanceof TypeNode) {
            funcDecNode.set_returnType((TypeNode) tempReturnTypeNode);
        }
        else {
            // TODO: Potentially implement error handling?
        }

        // Visit the block node
        BaseNode tempBlockNode = visit(ctx.block());
        if (tempBlockNode instanceof BlockNode) {
            funcDecNode.set_funcBlock((BlockNode) tempBlockNode);
        }
        else {
            // TODO: Potentially implement error handling?
        }

        return funcDecNode;
    }

    @Override public BaseNode visitIf(mctlParser.IfContext ctx) {
        IfStateNode ifStateNode = new IfStateNode();
        ifStateNode.set_lineNumber(ctx.start.getLine());

        // Set Expression List
        for (ParseTree child : ctx.ifLiteral()) {
            BaseNode tempExpNode = visit(child);
            if (tempExpNode instanceof ExpNode) {
                ifStateNode.add_expChild((ExpNode) tempExpNode);
            }
            else {
                // TODO: Potentially implement error handling?
            }
        }

        // Set Block List
        for (ParseTree child : ctx.block()) {
            BaseNode tempBlockNode = visit(child);
            if (tempBlockNode instanceof BlockNode) {
                ifStateNode.add_blockChild((BlockNode) tempBlockNode);
            }
            else {
                // TODO: Potentially implement error handling?
            }
        }

        return ifStateNode;
    }

    @Override public BaseNode visitRepeat(mctlParser.RepeatContext ctx) {
        RepeatStateNode repeatStateNode = new RepeatStateNode();
        repeatStateNode.set_lineNumber(ctx.start.getLine());

        // Visit and add the repeat expression
        BaseNode repeatExpNode = visit(ctx.expression());
        if (repeatExpNode instanceof ExpNode) {
            repeatStateNode.set_repeatExp((ExpNode) repeatExpNode);
        } else{
            //TODO: ERROR
        }

        // Visit and add the block to the repeat node
        BaseNode repeatBlock = visit(ctx.block());
        if (repeatBlock instanceof BlockNode) {
            repeatStateNode.set_expBlock((BlockNode) repeatBlock);
        }else{
            //TODO: ERROR
        }

        return repeatStateNode;
    }

    @Override public BaseNode visitExprAss(mctlParser.ExprAssContext ctx) {
        AssStateNode assStateNode = new AssStateNode();
        assStateNode.set_lineNumber(ctx.start.getLine());

        // Visit and add the id node
        BaseNode tempIdNode = visit(ctx.id());
        if (tempIdNode instanceof IDExpNode) {
            assStateNode.set_assignId((IDExpNode) tempIdNode);
        }
        else {
            // TODO: ERROR
        }

        // Visit and add the expression node
        BaseNode tempExprNode = visit(ctx.expression());
        if (tempExprNode instanceof ExpNode) {
            assStateNode.set_assignExp((ExpNode) tempExprNode);
        }
        else {
            // TODO: ERROR
        }

        return assStateNode;
    }

    @Override public BaseNode visitIncrAss(mctlParser.IncrAssContext ctx) {
        AssStateNode assStateNode = new AssStateNode();
        assStateNode.set_lineNumber(ctx.start.getLine());

        // Visit and add the id node
        BaseNode tempIdNode = visit(ctx.id());
        if (tempIdNode instanceof IDExpNode) {
            IDExpNode idExpNode = (IDExpNode) tempIdNode;
            assStateNode.set_assignId((IDExpNode) idExpNode);

            // Add operator to node
            AddExpNode addExpNode = new AddExpNode();
            addExpNode.set_lineNumber(ctx.start.getLine());
            if (ctx.op.getType() == mctlParser.INCREMENT) { addExpNode.set_operator(mctlParser.PLUS); }
            else if (ctx.op.getType() == mctlParser.DECREMENT) { addExpNode.set_operator(mctlParser.MINUS); }
            else {
                // TODO: Potentially implement error handling?
            }

            // Create num node with the integer value 1
            NumExpNode numExpNode = new NumExpNode();
            numExpNode.set_lineNumber(ctx.start.getLine());
            numExpNode.set_result(1);

            addExpNode.add_child(idExpNode);
            addExpNode.add_child(numExpNode);

        }
        else {
            // TODO: ERROR
        }

        return assStateNode;
    }

    @Override public BaseNode visitInvoke(mctlParser.InvokeContext ctx) {
        InvokeNode invokeNode = new InvokeNode();
        invokeNode.set_lineNumber(ctx.start.getLine());

        IDExpNode idExpNode = new IDExpNode();
        idExpNode.set_lineNumber(ctx.start.getLine());

        idExpNode.set_ID(ctx.ID().getText());
        invokeNode.set_invokeId(idExpNode);

        for(ParseTree child: ctx.actualParameters().expression()) {
            BaseNode node = visit(child);
            if(node instanceof ExpNode){
                invokeNode.add_paramExp((ExpNode) node);
            }else{
                // TODO: Potentially implement error handling?
            }
        }
        return invokeNode;
    }

    @Override public BaseNode visitFormalParameter(mctlParser.FormalParameterContext ctx) {
        FormalParamNode formalParamNode = new FormalParamNode();
        formalParamNode.set_lineNumber(ctx.start.getLine());

        IDExpNode idExpNode = new IDExpNode();
        idExpNode.set_lineNumber(ctx.start.getLine());
        idExpNode.set_ID(ctx.ID().getText());
        formalParamNode.set_id(idExpNode);

        // Set type
        BaseNode tempTypeNode = visit(ctx.variableType());
        if (tempTypeNode instanceof TypeNode) {
            formalParamNode.set_type((TypeNode) tempTypeNode);
        } else {
            // TODO: Potentially implement error handling?
        }

        return formalParamNode;
    }

    @Override public BaseNode visitNumberExpr(mctlParser.NumberExprContext ctx) {
        NumExpNode numExpNode = new NumExpNode();
        numExpNode.set_lineNumber(ctx.start.getLine());

        String stringFormattedNumber = ctx.getText();
        numExpNode.set_result(stringFormattedNumber.contains(".") ? Double.parseDouble(stringFormattedNumber) : Integer.parseInt(stringFormattedNumber));

        return numExpNode;
    }

    @Override public BaseNode visitAndExpr(mctlParser.AndExprContext ctx) {
        AndExpNode andExpNode = new AndExpNode();
        andExpNode.set_lineNumber(ctx.start.getLine());

        // Iterate over the two individual expressions of the and expression
        for (ParseTree child : ctx.expression()) {
            BaseNode tempExprNode = visit(child);
            if (tempExprNode instanceof ExpNode) {
                andExpNode.add_child((ExpNode) tempExprNode);
            } else {
                // TODO: Potentially implement error handling?
            }
        }

        return andExpNode;
    }

    @Override public BaseNode visitCompExpr(mctlParser.CompExprContext ctx) {
        CompExpNode compExpNode = new CompExpNode();
        compExpNode.set_lineNumber(ctx.start.getLine());

        // Add the comparison operator
        compExpNode.set_compOperator(ctx.op.getType());

        // Iterate over the two individual expressions of the comparison expression
        for (ParseTree child : ctx.expression()) {
            BaseNode tempExprNode = visit(child);
            if (tempExprNode instanceof ExpNode) {
                compExpNode.add_child((ExpNode) tempExprNode);
            } else {
                // TODO: Potentially implement error handling?
            }
        }

        return compExpNode;
    }

    @Override public BaseNode visitBoolExpr(mctlParser.BoolExprContext ctx) {
        BoolExpNode boolExpNode = new BoolExpNode();
        boolExpNode.set_lineNumber(ctx.start.getLine());
        boolExpNode.set_result(Boolean.parseBoolean(ctx.getText()));

        return boolExpNode;
    }

    @Override public BaseNode visitAddExpr(mctlParser.AddExprContext ctx) {
        AddExpNode addExpNode = new AddExpNode();
        addExpNode.set_lineNumber(ctx.start.getLine());

        // Add operator to node
        if (ctx.op.getType() == mctlParser.PLUS) { addExpNode.set_operator(mctlParser.PLUS); }
        else if (ctx.op.getType() == mctlParser.MINUS) { addExpNode.set_operator(mctlParser.MINUS); }
        else {
            // TODO: Potentially implement error handling?
        }

        // Iterate over the two individual expressions of the comparison expression
        for (ParseTree child : ctx.expression()) {
            BaseNode tempNode = visit(child);
            if (tempNode instanceof ExpNode) {
                addExpNode.add_child((ExpNode) tempNode);
            }
            else {
                // TODO: Potentially implement error handling?
            }
        }

        return addExpNode;
    }

    @Override public BaseNode visitOrExpr(mctlParser.OrExprContext ctx) {
        OrExpNode orExpNode = new OrExpNode();
        orExpNode.set_lineNumber(ctx.start.getLine());

        // Iterate over the two individual expressions of the or expression
        for (ParseTree child : ctx.expression()) {
            BaseNode tempExprNode = visit(child);
            if (tempExprNode instanceof ExpNode) {
                orExpNode.add_child((ExpNode) tempExprNode);
            } else {
                // TODO: Potentially implement error handling?
            }
        }

        return orExpNode;
    }

    @Override public BaseNode visitTypecast(mctlParser.TypecastContext ctx) {
        TypecastExpNode typecastExpNode = new TypecastExpNode();
        typecastExpNode.set_lineNumber(ctx.start.getLine());

        BaseNode tempTypeNode = visit(ctx.variableType());
        BaseNode tempExprNode = visit(ctx.expression());

        if (tempTypeNode instanceof TypeNode && tempExprNode instanceof ExpNode) {
            typecastExpNode.set_typeNode((TypeNode) tempTypeNode);
            typecastExpNode.set_expression_node((ExpNode) tempExprNode);
        }
        else {
            // TODO: Potentially implement error handling?
        }

        return typecastExpNode;
    }

    @Override public BaseNode visitEqualExpr(mctlParser.EqualExprContext ctx) {
        EqualExpNode equalExpNode = new EqualExpNode();
        equalExpNode.set_lineNumber(ctx.start.getLine());

        // Add operator to node
        if (ctx.op.getType() == mctlParser.EQUAL) { equalExpNode.set_operator(mctlParser.EQUAL); }
        else if (ctx.op.getType() == mctlParser.NOTEQUAL) { equalExpNode.set_operator(mctlParser.NOTEQUAL); }
        else {
            // TODO: Potentially implement error handling?
        }

        // Iterate over the two individual expressions of the equality expression
        for (ParseTree child : ctx.expression()) {
            BaseNode tempExpr = visit(child);
            if (tempExpr instanceof ExpNode) {
                equalExpNode.add_child((ExpNode) tempExpr);
            }
            else {
                // TODO: Potentially implement error handling?
            }
        }

        return equalExpNode;
    }

    @Override public BaseNode visitIdExpr(mctlParser.IdExprContext ctx) {
        IDExpNode idExpNode = new IDExpNode();
        idExpNode.set_lineNumber(ctx.start.getLine());
        idExpNode.set_ID(ctx.getText());

        return idExpNode;
    }
    
    @Override public BaseNode visitUnaryExpr(mctlParser.UnaryExprContext ctx) {
        UnaryExpNode unaryExpNode = new UnaryExpNode();
        unaryExpNode.set_lineNumber(ctx.start.getLine());

        // Add operator to node
        if (ctx.op.getType() == mctlParser.PLUS) { unaryExpNode.set_operator(mctlParser.PLUS); }
        else if (ctx.op.getType() == mctlParser.MINUS) { unaryExpNode.set_operator(mctlParser.MINUS); }
        else if (ctx.op.getType() == mctlParser.NOT){ unaryExpNode.set_operator(mctlParser.NOT); }
        else {
            // TODO: Potentially implement error handling?
        }

        // control if the individual expressions of the comparison expression
        ParseTree child = ctx.expression();
        BaseNode tempNode = visit(child);
        if (tempNode instanceof ExpNode) {
            unaryExpNode.add_child((ExpNode) tempNode);
        }
        else {
                // TODO: Potentially implement error handling?
        }
        return unaryExpNode;
    }

    @Override public BaseNode visitParenExpr(mctlParser.ParenExprContext ctx) { return visit(ctx.expression()); }

    @Override public BaseNode visitMulExpr(mctlParser.MulExprContext ctx) {
        MulExpNode mulExpNode = new MulExpNode();
        mulExpNode.set_lineNumber(ctx.start.getLine());

        // Add operator to node
        if (ctx.op.getType() == mctlParser.MULTIPLY) { mulExpNode.set_operator(mctlParser.MULTIPLY); }
        else if (ctx.op.getType() == mctlParser.DIVIDE) { mulExpNode.set_operator(mctlParser.DIVIDE); }
        else if (ctx.op.getType() == mctlParser.MODULO) { mulExpNode.set_operator(mctlParser.MODULO);
        } else {
            // TODO: Potentially implement error handling?
        }

        for (ParseTree child : ctx.expression()) {
            BaseNode tempExpr = visit(child);
            if (tempExpr instanceof ExpNode) {
                mulExpNode.add_child((ExpNode) tempExpr);
            }
            else {
                // TODO: Potentially implement error handling?
            }
        }

        return visitChildren(ctx);
    }

    @Override public BaseNode visitStringExpr(mctlParser.StringExprContext ctx) {
        StringExpNode stringExpNode = new StringExpNode();
        stringExpNode.set_lineNumber(ctx.start.getLine());
        stringExpNode.set_result(ctx.getText());

        return stringExpNode;
    }

    @Override public BaseNode visitInvExpr(mctlParser.InvExprContext ctx) { return visit(ctx.invoke()); }
    @Override public BaseNode visitReturnType(mctlParser.ReturnTypeContext ctx) { return visitChildren(ctx); }

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
            case "NOTHING":
                typeNode = new NothingTypeNode();
                break;
            default:
                typeNode = new IDTypeNode();
                typeNode.set_type(ctx.baseVariableType().getText());
        }

        // Calculate and set the array degree
        typeNode.set_arrayDegree(ctx.LSQR().size());

        // Set the line number
        typeNode.set_lineNumber(ctx.start.getLine());

        return typeNode;
    }
}
