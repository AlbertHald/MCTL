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
	 * Enter a parse tree produced by the {@code funcInv}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 */
	void enterFuncInv(mctlParser.FuncInvContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcInv}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 */
	void exitFuncInv(mctlParser.FuncInvContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prodInv}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 */
	void enterProdInv(mctlParser.ProdInvContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prodInv}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 */
	void exitProdInv(mctlParser.ProdInvContext ctx);
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
	 * Enter a parse tree produced by the {@code numberExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumberExp(mctlParser.NumberExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumberExp(mctlParser.NumberExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExp(mctlParser.AndExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExp(mctlParser.AndExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCompExp(mctlParser.CompExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCompExp(mctlParser.CompExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolExp(mctlParser.BoolExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolExp(mctlParser.BoolExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddExp(mctlParser.AddExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddExp(mctlParser.AddExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExp(mctlParser.OrExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExp(mctlParser.OrExpContext ctx);
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
	 * Enter a parse tree produced by the {@code equalExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualExp(mctlParser.EqualExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualExp(mctlParser.EqualExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdExp(mctlParser.IdExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdExp(mctlParser.IdExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExp(mctlParser.UnaryExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExp(mctlParser.UnaryExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExp(mctlParser.ParenExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExp(mctlParser.ParenExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulExp(mctlParser.MulExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulExp(mctlParser.MulExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStringExp(mctlParser.StringExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStringExp(mctlParser.StringExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code invExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInvExp(mctlParser.InvExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code invExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInvExp(mctlParser.InvExpContext ctx);
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