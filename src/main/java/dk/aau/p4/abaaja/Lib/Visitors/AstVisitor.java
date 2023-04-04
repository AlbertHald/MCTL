package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.mctlBaseVisitor;
import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.mctlParser;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ParseTree;

public class AstVisitor extends mctlBaseVisitor<BaseNode> {
    @Override public BaseNode visitMctl(mctlParser.MctlContext ctx) {
        MctlNode program = new MctlNode();

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
        System.out.println("Block:   " + ctx.getText());

        return visitChildren(ctx);
    }
    
    @Override public BaseNode visitLine(mctlParser.LineContext ctx) { return visitChildren(ctx); }
    @Override public BaseNode visitDeclaration(mctlParser.DeclarationContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitVariableDeclaration(mctlParser.VariableDeclarationContext ctx) {
        // Set id of the variable node
        VarDecNode varDecNode = new VarDecNode();
        varDecNode.set_varDecId(ctx.ID().getText());

        // Get variable type
        BaseNode varTypeNode = visit(ctx.variableType());

        if (varTypeNode instanceof TypeNode) {
            varDecNode.set_varDecType((TypeNode) varTypeNode);
        }

        return visitChildren(ctx);
    }

    @Override public BaseNode visitStructDeclaration(mctlParser.StructDeclarationContext ctx) {
        StructDecNode structDecNode = new StructDecNode();

        // Set struct ID
        structDecNode.set_structDecId(ctx.ID().getText());

        // Iterate over variable declarations
        for (ParseTree child: ctx.structBlock().variableDeclaration()) {
            BaseNode tempNode = visit(child);

            if (tempNode instanceof VarDecNode) {
                structDecNode.add_declaration((VarDecNode) tempNode);
            }
            else {
                // TODO: Potentially implement error?
            }
        }

        return structDecNode;
    }

    // TODO: Bliver vist ikke brugt
    @Override public BaseNode visitStructBlock(mctlParser.StructBlockContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitIdStruct(mctlParser.IdStructContext ctx) {
        System.out.println("IDStruct:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitIdArray(mctlParser.IdArrayContext ctx) {
        System.out.println("IdArray:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitIdVar(mctlParser.IdVarContext ctx) {
        System.out.println("IdVar:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitStatement(mctlParser.StatementContext ctx) {
        System.out.println("Statement:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitReturn(mctlParser.ReturnContext ctx) {
        System.out.println("Return:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitFunction(mctlParser.FunctionContext ctx) {
        System.out.println("Function:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitIf(mctlParser.IfContext ctx) {
        System.out.println("If:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitIfLiteral(mctlParser.IfLiteralContext ctx) {
        System.out.println("IfLiteral:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitRepeat(mctlParser.RepeatContext ctx) {
        System.out.println("Repeat:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitExprAss(mctlParser.ExprAssContext ctx) {
        System.out.println("ExprAss:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitIncrAss(mctlParser.IncrAssContext ctx) {
        System.out.println("IncrAss:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitFuncInv(mctlParser.FuncInvContext ctx) {
        System.out.println("FuncInv:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitProdInv(mctlParser.ProdInvContext ctx) {
        System.out.println("ProdInv:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitFormalParameters(mctlParser.FormalParametersContext ctx) {
        System.out.println("FormalParameters:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitFormalParameter(mctlParser.FormalParameterContext ctx) {
        System.out.println("FormalParameter:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitActualParameters(mctlParser.ActualParametersContext ctx) {
        System.out.println("ActualParameters:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitNumberExp(mctlParser.NumberExpContext ctx) {
        System.out.println("NumberExp:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitAndExp(mctlParser.AndExpContext ctx) {
        System.out.println("AndExp:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitCompExp(mctlParser.CompExpContext ctx) {
        System.out.println("VisitCompExp:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitBoolExp(mctlParser.BoolExpContext ctx) {
        System.out.println("BoolExp:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitAddExp(mctlParser.AddExpContext ctx) {
        System.out.println("AddExp:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitOrExp(mctlParser.OrExpContext ctx) {
        System.out.println("OrExp:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitTypecast(mctlParser.TypecastContext ctx) {
        System.out.println("Typecast:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitEqualExp(mctlParser.EqualExpContext ctx) {
        System.out.println("EqualExp:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitIdExp(mctlParser.IdExpContext ctx) {
        System.out.println("IdExp:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitUnaryExp(mctlParser.UnaryExpContext ctx) {
        System.out.println("UnaryExp:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitParenExp(mctlParser.ParenExpContext ctx) {
        System.out.println("ParenExp:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitMulExp(mctlParser.MulExpContext ctx) {
        System.out.println("MulExp:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitStringExp(mctlParser.StringExpContext ctx) {
        System.out.println("StringExp:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitInvExp(mctlParser.InvExpContext ctx) {
        System.out.println("InvExp:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitReturnType(mctlParser.ReturnTypeContext ctx) {
        System.out.println("ReturnType:   " + ctx.getText());

        return visitChildren(ctx);
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
            case "NOTHING":
                typeNode = new NothingTypeNode();
                break;
            default:
                typeNode = new IDTypeNode();
                typeNode.set_type(ctx.baseVariableType().getText());
        }

        // Calculate and set the array degree
        typeNode.set_arrayDegree(ctx.LSQR().size());

        return typeNode;
    }

    @Override public BaseNode visitBaseVariableType(mctlParser.BaseVariableTypeContext ctx) {
        System.out.println("BaseVariableType:   " + ctx.getText());

        return visitChildren(ctx);
    }

    @Override public BaseNode visitBoolean(mctlParser.BooleanContext ctx) {
        System.out.println("Boolean:   " + ctx.getText());

        return visitChildren(ctx);
    }
}
