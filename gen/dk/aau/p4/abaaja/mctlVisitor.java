// Generated from /Users/bastianhansen/Library/CloudStorage/OneDrive-AalborgUniversitet/Semester 4/P4/Minecraft-Turtle-Language/src/main/java/dk/aau/p4/abaaja/mctl.g4 by ANTLR 4.12.0
package dk.aau.p4.abaaja;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link mctlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface mctlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link mctlParser#mctl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMctl(mctlParser.MctlContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(mctlParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(mctlParser.CommentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDecl}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(mctlParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionDeclaration}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(mctlParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code structDeclaration}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclaration(mctlParser.StructDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(mctlParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idArray}
	 * labeled alternative in {@link mctlParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdArray(mctlParser.IdArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idStruct}
	 * labeled alternative in {@link mctlParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdStruct(mctlParser.IdStructContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idVar}
	 * labeled alternative in {@link mctlParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdVar(mctlParser.IdVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(mctlParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code repeatStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatStatement(mctlParser.RepeatStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignmentStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(mctlParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code invokeStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeStatement(mctlParser.InvokeStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stopStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStopStatement(mctlParser.StopStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(mctlParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(mctlParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(mctlParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#ifLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfLiteral(mctlParser.IfLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#repeat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeat(mctlParser.RepeatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAss}
	 * labeled alternative in {@link mctlParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAss(mctlParser.ExprAssContext ctx);
	/**
	 * Visit a parse tree produced by the {@code incrAss}
	 * labeled alternative in {@link mctlParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncrAss(mctlParser.IncrAssContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionInvoke}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionInvoke(mctlParser.FunctionInvokeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varMethodInvoke}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarMethodInvoke(mctlParser.VarMethodInvokeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringMethodInvoke}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringMethodInvoke(mctlParser.StringMethodInvokeContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#formalParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameters(mctlParser.FormalParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#formalParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameter(mctlParser.FormalParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#actualParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualParameters(mctlParser.ActualParametersContext ctx);
	/**
	 * Visit a parse tree produced by the {@code invExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvExpr(mctlParser.InvExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpr(mctlParser.NumberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(mctlParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(mctlParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typecast}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypecast(mctlParser.TypecastContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpr(mctlParser.StringExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(mctlParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(mctlParser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpr(mctlParser.CompExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExpr(mctlParser.MulExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpr(mctlParser.BoolExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(mctlParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualExpr(mctlParser.EqualExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(mctlParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnTypeVariable}
	 * labeled alternative in {@link mctlParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnTypeVariable(mctlParser.ReturnTypeVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnTypeNothing}
	 * labeled alternative in {@link mctlParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnTypeNothing(mctlParser.ReturnTypeNothingContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#variableType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableType(mctlParser.VariableTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#baseVariableType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseVariableType(mctlParser.BaseVariableTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#boolean}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean(mctlParser.BooleanContext ctx);
}