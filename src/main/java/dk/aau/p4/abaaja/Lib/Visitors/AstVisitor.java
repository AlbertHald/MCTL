package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.mctlBaseVisitor;
import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.mctlParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class AstVisitor extends mctlBaseVisitor<BaseNode> {

    @Override public BaseNode visitMctl(mctlParser.MctlContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitBlock(mctlParser.BlockContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitLine(mctlParser.LineContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitDeclaration(mctlParser.DeclarationContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitVariableDeclaration(mctlParser.VariableDeclarationContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitStructDeclaration(mctlParser.StructDeclarationContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitStructBlock(mctlParser.StructBlockContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitIdStruct(mctlParser.IdStructContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitIdArray(mctlParser.IdArrayContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitIdVar(mctlParser.IdVarContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitStatement(mctlParser.StatementContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitReturn(mctlParser.ReturnContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitFunction(mctlParser.FunctionContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitIf(mctlParser.IfContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitIfLiteral(mctlParser.IfLiteralContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitRepeat(mctlParser.RepeatContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitExprAss(mctlParser.ExprAssContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitIncrAss(mctlParser.IncrAssContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitFuncInv(mctlParser.FuncInvContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitProdInv(mctlParser.ProdInvContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitFormalParameters(mctlParser.FormalParametersContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitFormalParameter(mctlParser.FormalParameterContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitActualParameters(mctlParser.ActualParametersContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitNumberExp(mctlParser.NumberExpContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitAndExp(mctlParser.AndExpContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitCompExp(mctlParser.CompExpContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitBoolExp(mctlParser.BoolExpContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitAddExp(mctlParser.AddExpContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitOrExp(mctlParser.OrExpContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitTypecast(mctlParser.TypecastContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitEqualExp(mctlParser.EqualExpContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitIdExp(mctlParser.IdExpContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitUnaryExp(mctlParser.UnaryExpContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitParenExp(mctlParser.ParenExpContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitMulExp(mctlParser.MulExpContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitStringExp(mctlParser.StringExpContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitInvExp(mctlParser.InvExpContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitReturnType(mctlParser.ReturnTypeContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitVariableType(mctlParser.VariableTypeContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitBaseVariableType(mctlParser.BaseVariableTypeContext ctx) { return visitChildren(ctx); }

    @Override public BaseNode visitBoolean(mctlParser.BooleanContext ctx) { return visitChildren(ctx); }
}
