// Generated from java-escape by ANTLR 4.11.1
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
	 * Visit a parse tree produced by {@link mctlParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(mctlParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(mctlParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(mctlParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#structDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclaration(mctlParser.StructDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#structBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructBlock(mctlParser.StructBlockContext ctx);
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
	 * Visit a parse tree produced by {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(mctlParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(mctlParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(mctlParser.FunctionDeclarationContext ctx);
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
	 * Visit a parse tree produced by the {@code funcInv}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncInv(mctlParser.FuncInvContext ctx);
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
	 * Visit a parse tree produced by {@link mctlParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(mctlParser.ReturnTypeContext ctx);
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