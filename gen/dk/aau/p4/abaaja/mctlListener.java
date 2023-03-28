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
	 * Enter a parse tree produced by the {@code dec}
	 * labeled alternative in {@link mctlParser#line}.
	 * @param ctx the parse tree
	 */
	void enterDec(mctlParser.DecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dec}
	 * labeled alternative in {@link mctlParser#line}.
	 * @param ctx the parse tree
	 */
	void exitDec(mctlParser.DecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code state}
	 * labeled alternative in {@link mctlParser#line}.
	 * @param ctx the parse tree
	 */
	void enterState(mctlParser.StateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code state}
	 * labeled alternative in {@link mctlParser#line}.
	 * @param ctx the parse tree
	 */
	void exitState(mctlParser.StateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blank}
	 * labeled alternative in {@link mctlParser#line}.
	 * @param ctx the parse tree
	 */
	void enterBlank(mctlParser.BlankContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blank}
	 * labeled alternative in {@link mctlParser#line}.
	 * @param ctx the parse tree
	 */
	void exitBlank(mctlParser.BlankContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(mctlParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDec}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(mctlParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcDec}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterFuncDec(mctlParser.FuncDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcDec}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitFuncDec(mctlParser.FuncDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code structDec}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterStructDec(mctlParser.StructDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code structDec}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitStructDec(mctlParser.StructDecContext ctx);
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
<<<<<<< Updated upstream
=======
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
>>>>>>> Stashed changes
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
	 * Enter a parse tree produced by the {@code ifState}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfState(mctlParser.IfStateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifState}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfState(mctlParser.IfStateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code repeatState}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterRepeatState(mctlParser.RepeatStateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code repeatState}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitRepeatState(mctlParser.RepeatStateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assState}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssState(mctlParser.AssStateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assState}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssState(mctlParser.AssStateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code invState}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterInvState(mctlParser.InvStateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code invState}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitInvState(mctlParser.InvStateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stop}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStop(mctlParser.StopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stop}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStop(mctlParser.StopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnState}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnState(mctlParser.ReturnStateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnState}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnState(mctlParser.ReturnStateContext ctx);
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
	 * Enter a parse tree produced by the {@code plusMinusExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPlusMinusExp(mctlParser.PlusMinusExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusMinusExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPlusMinusExp(mctlParser.PlusMinusExpContext ctx);
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
	 * Enter a parse tree produced by the {@code lessGreatExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLessGreatExp(mctlParser.LessGreatExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lessGreatExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLessGreatExp(mctlParser.LessGreatExpContext ctx);
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
	 * Enter a parse tree produced by the {@code mulDivModExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulDivModExp(mctlParser.MulDivModExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivModExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulDivModExp(mctlParser.MulDivModExpContext ctx);
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
<<<<<<< Updated upstream
=======
	/**
	 * Enter a parse tree produced by the {@code equalNotExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualNotExp(mctlParser.EqualNotExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalNotExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualNotExp(mctlParser.EqualNotExpContext ctx);
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
>>>>>>> Stashed changes
	/**
	 * Enter a parse tree produced by the {@code equalNotExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualNotExp(mctlParser.EqualNotExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalNotExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualNotExp(mctlParser.EqualNotExpContext ctx);
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
	 * Enter a parse tree produced by the {@code varReturn}
	 * labeled alternative in {@link mctlParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterVarReturn(mctlParser.VarReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varReturn}
	 * labeled alternative in {@link mctlParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitVarReturn(mctlParser.VarReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nothing}
	 * labeled alternative in {@link mctlParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterNothing(mctlParser.NothingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nothing}
	 * labeled alternative in {@link mctlParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitNothing(mctlParser.NothingContext ctx);
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
	 * Enter a parse tree produced by the {@code boolBase}
	 * labeled alternative in {@link mctlParser#baseVariableType}.
	 * @param ctx the parse tree
	 */
	void enterBoolBase(mctlParser.BoolBaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolBase}
	 * labeled alternative in {@link mctlParser#baseVariableType}.
	 * @param ctx the parse tree
	 */
	void exitBoolBase(mctlParser.BoolBaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringBase}
	 * labeled alternative in {@link mctlParser#baseVariableType}.
	 * @param ctx the parse tree
	 */
	void enterStringBase(mctlParser.StringBaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringBase}
	 * labeled alternative in {@link mctlParser#baseVariableType}.
	 * @param ctx the parse tree
	 */
	void exitStringBase(mctlParser.StringBaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numBase}
	 * labeled alternative in {@link mctlParser#baseVariableType}.
	 * @param ctx the parse tree
	 */
	void enterNumBase(mctlParser.NumBaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numBase}
	 * labeled alternative in {@link mctlParser#baseVariableType}.
	 * @param ctx the parse tree
	 */
	void exitNumBase(mctlParser.NumBaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idBase}
	 * labeled alternative in {@link mctlParser#baseVariableType}.
	 * @param ctx the parse tree
	 */
	void enterIdBase(mctlParser.IdBaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idBase}
	 * labeled alternative in {@link mctlParser#baseVariableType}.
	 * @param ctx the parse tree
	 */
	void exitIdBase(mctlParser.IdBaseContext ctx);
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