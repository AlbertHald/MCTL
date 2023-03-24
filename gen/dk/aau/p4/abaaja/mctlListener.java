// Generated from java-escape by ANTLR 4.11.1
package dk.aau.p4.abaaja;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link mctlParser}.
 */
public interface mctlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link mctlParser#mctl}.
	 * @param ctx the parse tree
	 */
	void enterMctl(mctlParser.MctlContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#mctl}.
	 * @param ctx the parse tree
	 */
	void exitMctl(mctlParser.MctlContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(mctlParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(mctlParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(mctlParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(mctlParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(mctlParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(mctlParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(mctlParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(mctlParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclaration(mctlParser.StructDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclaration(mctlParser.StructDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#structBlock}.
	 * @param ctx the parse tree
	 */
	void enterStructBlock(mctlParser.StructBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#structBlock}.
	 * @param ctx the parse tree
	 */
	void exitStructBlock(mctlParser.StructBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(mctlParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(mctlParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(mctlParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(mctlParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#return}.
	 * @param ctx the parse tree
	 */
	void enterReturn(mctlParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#return}.
	 * @param ctx the parse tree
	 */
	void exitReturn(mctlParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(mctlParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(mctlParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#if}.
	 * @param ctx the parse tree
	 */
	void enterIf(mctlParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#if}.
	 * @param ctx the parse tree
	 */
	void exitIf(mctlParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#else}.
	 * @param ctx the parse tree
	 */
	void enterElse(mctlParser.ElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#else}.
	 * @param ctx the parse tree
	 */
	void exitElse(mctlParser.ElseContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#repeat}.
	 * @param ctx the parse tree
	 */
	void enterRepeat(mctlParser.RepeatContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#repeat}.
	 * @param ctx the parse tree
	 */
	void exitRepeat(mctlParser.RepeatContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(mctlParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(mctlParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 */
	void enterInvoke(mctlParser.InvokeContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 */
	void exitInvoke(mctlParser.InvokeContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(mctlParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(mctlParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(mctlParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(mctlParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#actualParameters}.
	 * @param ctx the parse tree
	 */
	void enterActualParameters(mctlParser.ActualParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#actualParameters}.
	 * @param ctx the parse tree
	 */
	void exitActualParameters(mctlParser.ActualParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixExpression(mctlParser.PostfixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixExpression(mctlParser.PostfixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(mctlParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(mctlParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression(mctlParser.CastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression(mctlParser.CastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(mctlParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(mctlParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(mctlParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(mctlParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(mctlParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(mctlParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(mctlParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(mctlParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(mctlParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(mctlParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(mctlParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(mctlParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(mctlParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(mctlParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(mctlParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(mctlParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#variableType}.
	 * @param ctx the parse tree
	 */
	void enterVariableType(mctlParser.VariableTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#variableType}.
	 * @param ctx the parse tree
	 */
	void exitVariableType(mctlParser.VariableTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#baseVariableType}.
	 * @param ctx the parse tree
	 */
	void enterBaseVariableType(mctlParser.BaseVariableTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#baseVariableType}.
	 * @param ctx the parse tree
	 */
	void exitBaseVariableType(mctlParser.BaseVariableTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link mctlParser#boolean}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(mctlParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#boolean}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(mctlParser.BooleanContext ctx);
}