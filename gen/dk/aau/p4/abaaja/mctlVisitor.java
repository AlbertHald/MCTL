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
	 * Visit a parse tree produced by {@link mctlParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(mctlParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(mctlParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(mctlParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(mctlParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse(mctlParser.ElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#repeat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeat(mctlParser.RepeatContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(mctlParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvoke(mctlParser.InvokeContext ctx);
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
	 * Visit a parse tree produced by {@link mctlParser#invokeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeExpression(mctlParser.InvokeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(mctlParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#castExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpression(mctlParser.CastExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(mctlParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(mctlParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#relationalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(mctlParser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(mctlParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(mctlParser.LogicalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(mctlParser.LogicalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(mctlParser.ExpressionContext ctx);
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