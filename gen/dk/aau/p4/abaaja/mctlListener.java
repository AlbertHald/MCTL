// Generated from dk/aau/p4/abaaja/mctl.g4 by ANTLR 4.12.0
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
	 * Enter a parse tree produced by {@link mctlParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(mctlParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link mctlParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(mctlParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDecl}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(mctlParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDecl}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(mctlParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionDeclaration}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(mctlParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionDeclaration}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(mctlParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code structDeclaration}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclaration(mctlParser.StructDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code structDeclaration}
	 * labeled alternative in {@link mctlParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclaration(mctlParser.StructDeclarationContext ctx);
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
	 * Enter a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(mctlParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(mctlParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code repeatStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterRepeatStatement(mctlParser.RepeatStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code repeatStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitRepeatStatement(mctlParser.RepeatStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignmentStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(mctlParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignmentStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(mctlParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code invokeStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterInvokeStatement(mctlParser.InvokeStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code invokeStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitInvokeStatement(mctlParser.InvokeStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stopStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStopStatement(mctlParser.StopStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stopStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStopStatement(mctlParser.StopStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(mctlParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link mctlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(mctlParser.ReturnStatementContext ctx);
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
	 * Enter a parse tree produced by the {@code functionInvoke}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 */
	void enterFunctionInvoke(mctlParser.FunctionInvokeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionInvoke}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 */
	void exitFunctionInvoke(mctlParser.FunctionInvokeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varMethodInvoke}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 */
	void enterVarMethodInvoke(mctlParser.VarMethodInvokeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varMethodInvoke}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 */
	void exitVarMethodInvoke(mctlParser.VarMethodInvokeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringMethodInvoke}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 */
	void enterStringMethodInvoke(mctlParser.StringMethodInvokeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringMethodInvoke}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 */
	void exitStringMethodInvoke(mctlParser.StringMethodInvokeContext ctx);
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
	 * Enter a parse tree produced by the {@code returnTypeVariable}
	 * labeled alternative in {@link mctlParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnTypeVariable(mctlParser.ReturnTypeVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnTypeVariable}
	 * labeled alternative in {@link mctlParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnTypeVariable(mctlParser.ReturnTypeVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnTypeNothing}
	 * labeled alternative in {@link mctlParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnTypeNothing(mctlParser.ReturnTypeNothingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnTypeNothing}
	 * labeled alternative in {@link mctlParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnTypeNothing(mctlParser.ReturnTypeNothingContext ctx);
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