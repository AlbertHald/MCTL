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
	 * Enter a parse tree produced by the {@code idArray}
	 * labeled alternative in {@link mctlParser#id}.
	 * @param ctx the parse tree
	 */
	void enterIdArray(mctlParser.IdArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idArray}
	 * labeled alternative in {@link mctlParser#id}.
	 * @param ctx the parse tree
	 */
	void exitIdArray(mctlParser.IdArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idStruct}
	 * labeled alternative in {@link mctlParser#id}.
	 * @param ctx the parse tree
	 */
	void enterIdStruct(mctlParser.IdStructContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idStruct}
	 * labeled alternative in {@link mctlParser#id}.
	 * @param ctx the parse tree
	 */
	void exitIdStruct(mctlParser.IdStructContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idVar}
	 * labeled alternative in {@link mctlParser#id}.
	 * @param ctx the parse tree
	 */
	void enterIdVar(mctlParser.IdVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idVar}
	 * labeled alternative in {@link mctlParser#id}.
	 * @param ctx the parse tree
	 */
	void exitIdVar(mctlParser.IdVarContext ctx);
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
	 * Enter a parse tree produced by {@link mctlParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(mctlParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(mctlParser.FunctionDeclarationContext ctx);
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
	 * Enter a parse tree produced by {@link mctlParser#ifLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIfLiteral(mctlParser.IfLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#ifLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIfLiteral(mctlParser.IfLiteralContext ctx);
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
	 * Enter a parse tree produced by the {@code exprAss}
	 * labeled alternative in {@link mctlParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterExprAss(mctlParser.ExprAssContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprAss}
	 * labeled alternative in {@link mctlParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitExprAss(mctlParser.ExprAssContext ctx);
	/**
	 * Enter a parse tree produced by the {@code incrAss}
	 * labeled alternative in {@link mctlParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterIncrAss(mctlParser.IncrAssContext ctx);
	/**
	 * Exit a parse tree produced by the {@code incrAss}
	 * labeled alternative in {@link mctlParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitIncrAss(mctlParser.IncrAssContext ctx);
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
	 * Enter a parse tree produced by the {@code invExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInvExpr(mctlParser.InvExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code invExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInvExpr(mctlParser.InvExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpr(mctlParser.NumberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpr(mctlParser.NumberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(mctlParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(mctlParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(mctlParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(mctlParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code typecast}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTypecast(mctlParser.TypecastContext ctx);
	/**
	 * Exit a parse tree produced by the {@code typecast}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTypecast(mctlParser.TypecastContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStringExpr(mctlParser.StringExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStringExpr(mctlParser.StringExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(mctlParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(mctlParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(mctlParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(mctlParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCompExpr(mctlParser.CompExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCompExpr(mctlParser.CompExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulExpr(mctlParser.MulExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulExpr(mctlParser.MulExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(mctlParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(mctlParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(mctlParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(mctlParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualExpr(mctlParser.EqualExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualExpr(mctlParser.EqualExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(mctlParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(mctlParser.AndExprContext ctx);
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