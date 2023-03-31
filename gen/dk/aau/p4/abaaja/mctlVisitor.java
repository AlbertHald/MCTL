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
	 * Visit a parse tree produced by the {@code idStruct}
	 * labeled alternative in {@link mctlParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdStruct(mctlParser.IdStructContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idArray}
	 * labeled alternative in {@link mctlParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdArray(mctlParser.IdArrayContext ctx);
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
	 * Visit a parse tree produced by the {@code prodInv}
	 * labeled alternative in {@link mctlParser#invoke}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProdInv(mctlParser.ProdInvContext ctx);
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
	 * Visit a parse tree produced by the {@code numberExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExp(mctlParser.NumberExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExp(mctlParser.AndExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExp(mctlParser.CompExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExp(mctlParser.BoolExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExp(mctlParser.AddExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExp(mctlParser.OrExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typecast}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypecast(mctlParser.TypecastContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualExp(mctlParser.EqualExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExp(mctlParser.IdExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExp(mctlParser.UnaryExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExp(mctlParser.ParenExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExp(mctlParser.MulExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExp(mctlParser.StringExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code invExp}
	 * labeled alternative in {@link mctlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvExp(mctlParser.InvExpContext ctx);
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