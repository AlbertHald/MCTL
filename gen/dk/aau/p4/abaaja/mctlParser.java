// Generated from java-escape by ANTLR 4.11.1
package dk.aau.p4.abaaja;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class mctlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		EQUAL=1, NOTEQUAL=2, LESS=3, LESSEQUAL=4, GREATER=5, GREATEREQUAL=6, DOT=7, 
		COMMA=8, MULTIPLY=9, DIVIDE=10, MODULO=11, MINUS=12, PLUS=13, ASSIGN=14, 
		LPAR=15, RPAR=16, LSQR=17, RSQR=18, LCURL=19, RCURL=20, STRING=21, COLON=22, 
		SEMI=23, NOT=24, Nothing=25, String=26, Number=27, Boolean=28, Struct=29, 
		True=30, False=31, Add=32, IndexesOf=33, SubString=34, SubList=35, And=36, 
		Or=37, Stop=38, Return=39, To=40, Variable=41, If=42, Else=43, Repeat=44, 
		ID=45, NUMBER=46, COMMENT=47, WS=48;
	public static final int
		RULE_mctl = 0, RULE_block = 1, RULE_line = 2, RULE_declaration = 3, RULE_variableDeclaration = 4, 
		RULE_structDeclaration = 5, RULE_structBlock = 6, RULE_id = 7, RULE_statement = 8, 
		RULE_return = 9, RULE_function = 10, RULE_if = 11, RULE_ifLiteral = 12, 
		RULE_repeat = 13, RULE_assignment = 14, RULE_invoke = 15, RULE_formalParameters = 16, 
		RULE_formalParameter = 17, RULE_actualParameters = 18, RULE_expression = 19, 
		RULE_returnType = 20, RULE_variableType = 21, RULE_baseVariableType = 22, 
		RULE_boolean = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"mctl", "block", "line", "declaration", "variableDeclaration", "structDeclaration", 
			"structBlock", "id", "statement", "return", "function", "if", "ifLiteral", 
			"repeat", "assignment", "invoke", "formalParameters", "formalParameter", 
			"actualParameters", "expression", "returnType", "variableType", "baseVariableType", 
			"boolean"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", "'.'", "','", "'*'", 
			"'/'", "'%'", "'-'", "'+'", "'='", "'('", "')'", "'['", "']'", "'{'", 
			"'}'", null, "':'", "';'", "'!'", "'NOTHING'", "'STRING'", "'NUMBER'", 
			"'BOOLEAN'", "'struct'", "'true'", "'false'", "'add'", "'indexesOf'", 
			"'subString'", "'subList'", "'and'", "'or'", "'stop'", "'return'", "'to'", 
			"'variable'", "'if'", "'else'", "'repeat'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "EQUAL", "NOTEQUAL", "LESS", "LESSEQUAL", "GREATER", "GREATEREQUAL", 
			"DOT", "COMMA", "MULTIPLY", "DIVIDE", "MODULO", "MINUS", "PLUS", "ASSIGN", 
			"LPAR", "RPAR", "LSQR", "RSQR", "LCURL", "RCURL", "STRING", "COLON", 
			"SEMI", "NOT", "Nothing", "String", "Number", "Boolean", "Struct", "True", 
			"False", "Add", "IndexesOf", "SubString", "SubList", "And", "Or", "Stop", 
			"Return", "To", "Variable", "If", "Else", "Repeat", "ID", "NUMBER", "COMMENT", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public mctlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MctlContext extends ParserRuleContext {
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public MctlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mctl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterMctl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitMctl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitMctl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MctlContext mctl() throws RecognitionException {
		MctlContext _localctx = new MctlContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_mctl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 202035808960512L) != 0) {
				{
				{
				setState(48);
				line();
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LCURL() { return getToken(mctlParser.LCURL, 0); }
		public TerminalNode RCURL() { return getToken(mctlParser.RCURL, 0); }
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(LCURL);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 202035808960512L) != 0) {
				{
				{
				setState(55);
				line();
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(61);
			match(RCURL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LineContext extends ParserRuleContext {
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
	 
		public LineContext() { }
		public void copyFrom(LineContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DecContext extends LineContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public DecContext(LineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitDec(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlankContext extends LineContext {
		public TerminalNode COMMENT() { return getToken(mctlParser.COMMENT, 0); }
		public TerminalNode SEMI() { return getToken(mctlParser.SEMI, 0); }
		public BlankContext(LineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterBlank(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitBlank(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitBlank(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StateContext extends LineContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StateContext(LineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitState(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_line);
		try {
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Struct:
			case To:
			case Variable:
				_localctx = new DecContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				declaration();
				}
				break;
			case STRING:
			case Stop:
			case Return:
			case If:
			case Repeat:
			case ID:
				_localctx = new StateContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				statement();
				}
				break;
			case COMMENT:
				_localctx = new BlankContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(65);
				match(COMMENT);
				}
				break;
			case SEMI:
				_localctx = new BlankContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(66);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	 
		public DeclarationContext() { }
		public void copyFrom(DeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FuncDecContext extends DeclarationContext {
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public FuncDecContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterFuncDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitFuncDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitFuncDec(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StructDecContext extends DeclarationContext {
		public StructDeclarationContext structDeclaration() {
			return getRuleContext(StructDeclarationContext.class,0);
		}
		public StructDecContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterStructDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitStructDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitStructDec(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarDecContext extends DeclarationContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(mctlParser.SEMI, 0); }
		public VarDecContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterVarDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitVarDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitVarDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaration);
		try {
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Variable:
				_localctx = new VarDecContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				variableDeclaration();
				setState(70);
				match(SEMI);
				}
				break;
			case To:
				_localctx = new FuncDecContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				function();
				}
				break;
			case Struct:
				_localctx = new StructDecContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				structDeclaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationContext extends ParserRuleContext {
		public TerminalNode Variable() { return getToken(mctlParser.Variable, 0); }
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
		public TerminalNode COLON() { return getToken(mctlParser.COLON, 0); }
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(Variable);
			setState(77);
			match(ID);
			setState(78);
			match(COLON);
			setState(79);
			variableType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StructDeclarationContext extends ParserRuleContext {
		public TerminalNode Struct() { return getToken(mctlParser.Struct, 0); }
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
		public StructBlockContext structBlock() {
			return getRuleContext(StructBlockContext.class,0);
		}
		public StructDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterStructDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitStructDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitStructDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructDeclarationContext structDeclaration() throws RecognitionException {
		StructDeclarationContext _localctx = new StructDeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_structDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(Struct);
			setState(82);
			match(ID);
			setState(83);
			structBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StructBlockContext extends ParserRuleContext {
		public TerminalNode LCURL() { return getToken(mctlParser.LCURL, 0); }
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public TerminalNode RCURL() { return getToken(mctlParser.RCURL, 0); }
		public List<TerminalNode> COMMA() { return getTokens(mctlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(mctlParser.COMMA, i);
		}
		public StructBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterStructBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitStructBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitStructBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructBlockContext structBlock() throws RecognitionException {
		StructBlockContext _localctx = new StructBlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_structBlock);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(LCURL);
			setState(86);
			variableDeclaration();
			setState(91);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(87);
					match(COMMA);
					setState(88);
					variableDeclaration();
					}
					} 
				}
				setState(93);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(94);
				match(COMMA);
				}
			}

			setState(97);
			match(RCURL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdContext extends ParserRuleContext {
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
	 
		public IdContext() { }
		public void copyFrom(IdContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdArrayContext extends IdContext {
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
		public List<TerminalNode> LSQR() { return getTokens(mctlParser.LSQR); }
		public TerminalNode LSQR(int i) {
			return getToken(mctlParser.LSQR, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RSQR() { return getTokens(mctlParser.RSQR); }
		public TerminalNode RSQR(int i) {
			return getToken(mctlParser.RSQR, i);
		}
		public IdArrayContext(IdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterIdArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitIdArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitIdArray(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdStructContext extends IdContext {
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
		public TerminalNode DOT() { return getToken(mctlParser.DOT, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public IdStructContext(IdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterIdStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitIdStruct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitIdStruct(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdVarContext extends IdContext {
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
		public IdVarContext(IdContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterIdVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitIdVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitIdVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_id);
		try {
			int _alt;
			setState(112);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new IdStructContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				match(ID);
				setState(100);
				match(DOT);
				setState(101);
				id();
				}
				break;
			case 2:
				_localctx = new IdArrayContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				match(ID);
				setState(107); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(103);
						match(LSQR);
						setState(104);
						expression(0);
						setState(105);
						match(RSQR);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(109); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				_localctx = new IdVarContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(111);
				match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfStateContext extends StatementContext {
		public IfContext if_() {
			return getRuleContext(IfContext.class,0);
		}
		public IfStateContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterIfState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitIfState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitIfState(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStateContext extends StatementContext {
		public ReturnContext return_() {
			return getRuleContext(ReturnContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(mctlParser.SEMI, 0); }
		public ReturnStateContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterReturnState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitReturnState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitReturnState(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssStateContext extends StatementContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(mctlParser.SEMI, 0); }
		public AssStateContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterAssState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitAssState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitAssState(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StopContext extends StatementContext {
		public TerminalNode Stop() { return getToken(mctlParser.Stop, 0); }
		public TerminalNode SEMI() { return getToken(mctlParser.SEMI, 0); }
		public StopContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterStop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitStop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitStop(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RepeatStateContext extends StatementContext {
		public RepeatContext repeat() {
			return getRuleContext(RepeatContext.class,0);
		}
		public RepeatStateContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterRepeatState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitRepeatState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitRepeatState(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InvStateContext extends StatementContext {
		public InvokeContext invoke() {
			return getRuleContext(InvokeContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(mctlParser.SEMI, 0); }
		public InvStateContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterInvState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitInvState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitInvState(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statement);
		try {
			setState(127);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new IfStateContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				if_();
				}
				break;
			case 2:
				_localctx = new RepeatStateContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				repeat();
				}
				break;
			case 3:
				_localctx = new AssStateContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
				assignment();
				setState(117);
				match(SEMI);
				}
				break;
			case 4:
				_localctx = new InvStateContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(119);
				invoke();
				setState(120);
				match(SEMI);
				}
				break;
			case 5:
				_localctx = new StopContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(122);
				match(Stop);
				setState(123);
				match(SEMI);
				}
				break;
			case 6:
				_localctx = new ReturnStateContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(124);
				return_();
				setState(125);
				match(SEMI);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(mctlParser.Return, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnContext return_() throws RecognitionException {
		ReturnContext _localctx = new ReturnContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_return);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			match(Return);
			setState(130);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode To() { return getToken(mctlParser.To, 0); }
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
		public TerminalNode LPAR() { return getToken(mctlParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(mctlParser.RPAR, 0); }
		public TerminalNode COLON() { return getToken(mctlParser.COLON, 0); }
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(To);
			setState(133);
			match(ID);
			setState(134);
			match(LPAR);
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(135);
				formalParameters();
				}
			}

			setState(138);
			match(RPAR);
			setState(139);
			match(COLON);
			setState(140);
			returnType();
			setState(141);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfContext extends ParserRuleContext {
		public List<IfLiteralContext> ifLiteral() {
			return getRuleContexts(IfLiteralContext.class);
		}
		public IfLiteralContext ifLiteral(int i) {
			return getRuleContext(IfLiteralContext.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<TerminalNode> Else() { return getTokens(mctlParser.Else); }
		public TerminalNode Else(int i) {
			return getToken(mctlParser.Else, i);
		}
		public IfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfContext if_() throws RecognitionException {
		IfContext _localctx = new IfContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_if);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			ifLiteral();
			setState(144);
			block();
			setState(151);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(145);
					match(Else);
					setState(146);
					ifLiteral();
					setState(147);
					block();
					}
					} 
				}
				setState(153);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Else) {
				{
				setState(154);
				match(Else);
				setState(155);
				block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfLiteralContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(mctlParser.If, 0); }
		public TerminalNode LPAR() { return getToken(mctlParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(mctlParser.RPAR, 0); }
		public IfLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterIfLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitIfLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitIfLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfLiteralContext ifLiteral() throws RecognitionException {
		IfLiteralContext _localctx = new IfLiteralContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_ifLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(If);
			setState(159);
			match(LPAR);
			setState(160);
			expression(0);
			setState(161);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RepeatContext extends ParserRuleContext {
		public TerminalNode Repeat() { return getToken(mctlParser.Repeat, 0); }
		public TerminalNode LPAR() { return getToken(mctlParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(mctlParser.RPAR, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public RepeatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterRepeat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitRepeat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitRepeat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepeatContext repeat() throws RecognitionException {
		RepeatContext _localctx = new RepeatContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_repeat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(Repeat);
			setState(164);
			match(LPAR);
			setState(165);
			expression(0);
			setState(166);
			match(RPAR);
			setState(167);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentContext extends ParserRuleContext {
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
	 
		public AssignmentContext() { }
		public void copyFrom(AssignmentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAssContext extends AssignmentContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(mctlParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprAssContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterExprAss(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitExprAss(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitExprAss(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IncrAssContext extends AssignmentContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<TerminalNode> PLUS() { return getTokens(mctlParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(mctlParser.PLUS, i);
		}
		public IncrAssContext(AssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterIncrAss(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitIncrAss(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitIncrAss(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_assignment);
		try {
			setState(177);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new ExprAssContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(169);
				id();
				setState(170);
				match(ASSIGN);
				setState(171);
				expression(0);
				}
				break;
			case 2:
				_localctx = new IncrAssContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(173);
				id();
				setState(174);
				match(PLUS);
				setState(175);
				match(PLUS);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InvokeContext extends ParserRuleContext {
		public InvokeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invoke; }
	 
		public InvokeContext() { }
		public void copyFrom(InvokeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FuncInvContext extends InvokeContext {
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
		public TerminalNode LPAR() { return getToken(mctlParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(mctlParser.RPAR, 0); }
		public ActualParametersContext actualParameters() {
			return getRuleContext(ActualParametersContext.class,0);
		}
		public FuncInvContext(InvokeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterFuncInv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitFuncInv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitFuncInv(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ProdInvContext extends InvokeContext {
		public TerminalNode DOT() { return getToken(mctlParser.DOT, 0); }
		public TerminalNode LPAR() { return getToken(mctlParser.LPAR, 0); }
		public ActualParametersContext actualParameters() {
			return getRuleContext(ActualParametersContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(mctlParser.RPAR, 0); }
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
		public TerminalNode STRING() { return getToken(mctlParser.STRING, 0); }
		public TerminalNode Add() { return getToken(mctlParser.Add, 0); }
		public TerminalNode IndexesOf() { return getToken(mctlParser.IndexesOf, 0); }
		public TerminalNode SubString() { return getToken(mctlParser.SubString, 0); }
		public TerminalNode SubList() { return getToken(mctlParser.SubList, 0); }
		public ProdInvContext(InvokeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterProdInv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitProdInv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitProdInv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InvokeContext invoke() throws RecognitionException {
		InvokeContext _localctx = new InvokeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_invoke);
		int _la;
		try {
			setState(192);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new FuncInvContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				match(ID);
				setState(180);
				match(LPAR);
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 105556356411392L) != 0) {
					{
					setState(181);
					actualParameters();
					}
				}

				setState(184);
				match(RPAR);
				}
				break;
			case 2:
				_localctx = new ProdInvContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
				_la = _input.LA(1);
				if ( !(_la==STRING || _la==ID) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(186);
				match(DOT);
				setState(187);
				_la = _input.LA(1);
				if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 64424509440L) != 0) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(188);
				match(LPAR);
				setState(189);
				actualParameters();
				setState(190);
				match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormalParametersContext extends ParserRuleContext {
		public List<FormalParameterContext> formalParameter() {
			return getRuleContexts(FormalParameterContext.class);
		}
		public FormalParameterContext formalParameter(int i) {
			return getRuleContext(FormalParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(mctlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(mctlParser.COMMA, i);
		}
		public FormalParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterFormalParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitFormalParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitFormalParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParametersContext formalParameters() throws RecognitionException {
		FormalParametersContext _localctx = new FormalParametersContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_formalParameters);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(194);
					formalParameter();
					setState(195);
					match(COMMA);
					}
					} 
				}
				setState(201);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(202);
			formalParameter();
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(203);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormalParameterContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
		public TerminalNode COLON() { return getToken(mctlParser.COLON, 0); }
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public FormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitFormalParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitFormalParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterContext formalParameter() throws RecognitionException {
		FormalParameterContext _localctx = new FormalParameterContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_formalParameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(ID);
			setState(207);
			match(COLON);
			setState(208);
			variableType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActualParametersContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(mctlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(mctlParser.COMMA, i);
		}
		public ActualParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actualParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterActualParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitActualParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitActualParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActualParametersContext actualParameters() throws RecognitionException {
		ActualParametersContext _localctx = new ActualParametersContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_actualParameters);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(210);
					expression(0);
					setState(211);
					match(COMMA);
					}
					} 
				}
				setState(217);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			setState(218);
			expression(0);
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(219);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PlusMinusExpContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(mctlParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(mctlParser.MINUS, 0); }
		public PlusMinusExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterPlusMinusExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitPlusMinusExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitPlusMinusExp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberExpContext extends ExpressionContext {
		public TerminalNode NUMBER() { return getToken(mctlParser.NUMBER, 0); }
		public NumberExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterNumberExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitNumberExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitNumberExp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndExpContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode And() { return getToken(mctlParser.And, 0); }
		public AndExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterAndExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitAndExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitAndExp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoolExpContext extends ExpressionContext {
		public BooleanContext boolean_() {
			return getRuleContext(BooleanContext.class,0);
		}
		public BoolExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterBoolExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitBoolExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitBoolExp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrExpContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Or() { return getToken(mctlParser.Or, 0); }
		public OrExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterOrExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitOrExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitOrExp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LessGreatExpContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LESS() { return getToken(mctlParser.LESS, 0); }
		public TerminalNode LESSEQUAL() { return getToken(mctlParser.LESSEQUAL, 0); }
		public TerminalNode GREATER() { return getToken(mctlParser.GREATER, 0); }
		public TerminalNode GREATEREQUAL() { return getToken(mctlParser.GREATEREQUAL, 0); }
		public LessGreatExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterLessGreatExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitLessGreatExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitLessGreatExp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypecastContext extends ExpressionContext {
		public TerminalNode LPAR() { return getToken(mctlParser.LPAR, 0); }
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(mctlParser.RPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypecastContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterTypecast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitTypecast(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitTypecast(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdExpContext extends ExpressionContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public IdExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterIdExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitIdExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitIdExp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulDivModExpContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MULTIPLY() { return getToken(mctlParser.MULTIPLY, 0); }
		public TerminalNode DIVIDE() { return getToken(mctlParser.DIVIDE, 0); }
		public TerminalNode MODULO() { return getToken(mctlParser.MODULO, 0); }
		public MulDivModExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterMulDivModExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitMulDivModExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitMulDivModExp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExpContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(mctlParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(mctlParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(mctlParser.PLUS, 0); }
		public UnaryExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterUnaryExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitUnaryExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitUnaryExp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualNotExpContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(mctlParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(mctlParser.NOTEQUAL, 0); }
		public EqualNotExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterEqualNotExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitEqualNotExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitEqualNotExp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExpContext extends ExpressionContext {
		public TerminalNode LPAR() { return getToken(mctlParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(mctlParser.RPAR, 0); }
		public ParenExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterParenExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitParenExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitParenExp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringExpContext extends ExpressionContext {
		public TerminalNode STRING() { return getToken(mctlParser.STRING, 0); }
		public StringExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterStringExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitStringExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitStringExp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InvExpContext extends ExpressionContext {
		public InvokeContext invoke() {
			return getRuleContext(InvokeContext.class,0);
		}
		public InvExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterInvExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitInvExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitInvExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				_localctx = new InvExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(223);
				invoke();
				}
				break;
			case 2:
				{
				_localctx = new ParenExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(224);
				match(LPAR);
				setState(225);
				expression(0);
				setState(226);
				match(RPAR);
				}
				break;
			case 3:
				{
				_localctx = new UnaryExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(228);
				_la = _input.LA(1);
				if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 16789504L) != 0) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(229);
				expression(12);
				}
				break;
			case 4:
				{
				_localctx = new TypecastContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(230);
				match(LPAR);
				setState(231);
				variableType();
				setState(232);
				match(RPAR);
				setState(233);
				expression(11);
				}
				break;
			case 5:
				{
				_localctx = new BoolExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(235);
				boolean_();
				}
				break;
			case 6:
				{
				_localctx = new NumberExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(236);
				match(NUMBER);
				}
				break;
			case 7:
				{
				_localctx = new IdExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(237);
				id();
				}
				break;
			case 8:
				{
				_localctx = new StringExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(238);
				match(STRING);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(261);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(259);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivModExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(241);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(242);
						_la = _input.LA(1);
						if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 3584L) != 0) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(243);
						expression(7);
						}
						break;
					case 2:
						{
						_localctx = new PlusMinusExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(244);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(245);
						_la = _input.LA(1);
						if ( !(_la==MINUS || _la==PLUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(246);
						expression(6);
						}
						break;
					case 3:
						{
						_localctx = new LessGreatExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(247);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(248);
						_la = _input.LA(1);
						if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 120L) != 0) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(249);
						expression(5);
						}
						break;
					case 4:
						{
						_localctx = new EqualNotExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(250);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(251);
						_la = _input.LA(1);
						if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(252);
						expression(4);
						}
						break;
					case 5:
						{
						_localctx = new AndExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(253);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(254);
						match(And);
						setState(255);
						expression(3);
						}
						break;
					case 6:
						{
						_localctx = new OrExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(256);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(257);
						match(Or);
						setState(258);
						expression(2);
						}
						break;
					}
					} 
				}
				setState(263);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnTypeContext extends ParserRuleContext {
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
	 
		public ReturnTypeContext() { }
		public void copyFrom(ReturnTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarReturnContext extends ReturnTypeContext {
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public VarReturnContext(ReturnTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterVarReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitVarReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitVarReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NothingContext extends ReturnTypeContext {
		public TerminalNode Nothing() { return getToken(mctlParser.Nothing, 0); }
		public NothingContext(ReturnTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterNothing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitNothing(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitNothing(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_returnType);
		try {
			setState(266);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String:
			case Number:
			case Boolean:
			case ID:
				_localctx = new VarReturnContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(264);
				variableType();
				}
				break;
			case Nothing:
				_localctx = new NothingContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(265);
				match(Nothing);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableTypeContext extends ParserRuleContext {
		public BaseVariableTypeContext baseVariableType() {
			return getRuleContext(BaseVariableTypeContext.class,0);
		}
		public List<TerminalNode> LSQR() { return getTokens(mctlParser.LSQR); }
		public TerminalNode LSQR(int i) {
			return getToken(mctlParser.LSQR, i);
		}
		public List<TerminalNode> RSQR() { return getTokens(mctlParser.RSQR); }
		public TerminalNode RSQR(int i) {
			return getToken(mctlParser.RSQR, i);
		}
		public VariableTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterVariableType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitVariableType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitVariableType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableTypeContext variableType() throws RecognitionException {
		VariableTypeContext _localctx = new VariableTypeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_variableType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			baseVariableType();
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LSQR) {
				{
				{
				setState(269);
				match(LSQR);
				setState(270);
				match(RSQR);
				}
				}
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BaseVariableTypeContext extends ParserRuleContext {
		public BaseVariableTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseVariableType; }
	 
		public BaseVariableTypeContext() { }
		public void copyFrom(BaseVariableTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdBaseContext extends BaseVariableTypeContext {
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
		public IdBaseContext(BaseVariableTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterIdBase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitIdBase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitIdBase(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumBaseContext extends BaseVariableTypeContext {
		public TerminalNode Number() { return getToken(mctlParser.Number, 0); }
		public NumBaseContext(BaseVariableTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterNumBase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitNumBase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitNumBase(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringBaseContext extends BaseVariableTypeContext {
		public TerminalNode String() { return getToken(mctlParser.String, 0); }
		public StringBaseContext(BaseVariableTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterStringBase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitStringBase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitStringBase(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoolBaseContext extends BaseVariableTypeContext {
		public TerminalNode Boolean() { return getToken(mctlParser.Boolean, 0); }
		public BoolBaseContext(BaseVariableTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterBoolBase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitBoolBase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitBoolBase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseVariableTypeContext baseVariableType() throws RecognitionException {
		BaseVariableTypeContext _localctx = new BaseVariableTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_baseVariableType);
		try {
			setState(280);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Boolean:
				_localctx = new BoolBaseContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(276);
				match(Boolean);
				}
				break;
			case String:
				_localctx = new StringBaseContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(277);
				match(String);
				}
				break;
			case Number:
				_localctx = new NumBaseContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(278);
				match(Number);
				}
				break;
			case ID:
				_localctx = new IdBaseContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(279);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BooleanContext extends ParserRuleContext {
		public TerminalNode True() { return getToken(mctlParser.True, 0); }
		public TerminalNode False() { return getToken(mctlParser.False, 0); }
		public BooleanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanContext boolean_() throws RecognitionException {
		BooleanContext _localctx = new BooleanContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_boolean);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			_la = _input.LA(1);
			if ( !(_la==True || _la==False) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 19:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00010\u011d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0001\u0000\u0005\u0000"+
		"2\b\u0000\n\u0000\f\u00005\t\u0000\u0001\u0001\u0001\u0001\u0005\u0001"+
		"9\b\u0001\n\u0001\f\u0001<\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002D\b\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003K\b\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006Z\b\u0006\n\u0006\f\u0006]\t\u0006\u0001\u0006"+
		"\u0003\u0006`\b\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0004\u0007l\b\u0007\u000b\u0007\f\u0007m\u0001\u0007\u0003\u0007q\b"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u0080\b\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0089\b\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0005\u000b\u0096\b\u000b\n\u000b\f\u000b\u0099"+
		"\t\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u009d\b\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0003\u000e\u00b2\b\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u00b7\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f"+
		"\u00c1\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00c6\b"+
		"\u0010\n\u0010\f\u0010\u00c9\t\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u00cd\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0005\u0012\u00d6\b\u0012\n\u0012\f\u0012\u00d9"+
		"\t\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00dd\b\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u00f0\b\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0005\u0013\u0104\b\u0013\n\u0013\f\u0013\u0107\t\u0013\u0001\u0014\u0001"+
		"\u0014\u0003\u0014\u010b\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0005"+
		"\u0015\u0110\b\u0015\n\u0015\f\u0015\u0113\t\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0003\u0016\u0119\b\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0000\u0001&\u0018\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.\u0000\b\u0002\u0000"+
		"\u0015\u0015--\u0001\u0000 #\u0002\u0000\f\r\u0018\u0018\u0001\u0000\t"+
		"\u000b\u0001\u0000\f\r\u0001\u0000\u0003\u0006\u0001\u0000\u0001\u0002"+
		"\u0001\u0000\u001e\u001f\u0131\u00003\u0001\u0000\u0000\u0000\u00026\u0001"+
		"\u0000\u0000\u0000\u0004C\u0001\u0000\u0000\u0000\u0006J\u0001\u0000\u0000"+
		"\u0000\bL\u0001\u0000\u0000\u0000\nQ\u0001\u0000\u0000\u0000\fU\u0001"+
		"\u0000\u0000\u0000\u000ep\u0001\u0000\u0000\u0000\u0010\u007f\u0001\u0000"+
		"\u0000\u0000\u0012\u0081\u0001\u0000\u0000\u0000\u0014\u0084\u0001\u0000"+
		"\u0000\u0000\u0016\u008f\u0001\u0000\u0000\u0000\u0018\u009e\u0001\u0000"+
		"\u0000\u0000\u001a\u00a3\u0001\u0000\u0000\u0000\u001c\u00b1\u0001\u0000"+
		"\u0000\u0000\u001e\u00c0\u0001\u0000\u0000\u0000 \u00c7\u0001\u0000\u0000"+
		"\u0000\"\u00ce\u0001\u0000\u0000\u0000$\u00d7\u0001\u0000\u0000\u0000"+
		"&\u00ef\u0001\u0000\u0000\u0000(\u010a\u0001\u0000\u0000\u0000*\u010c"+
		"\u0001\u0000\u0000\u0000,\u0118\u0001\u0000\u0000\u0000.\u011a\u0001\u0000"+
		"\u0000\u000002\u0003\u0004\u0002\u000010\u0001\u0000\u0000\u000025\u0001"+
		"\u0000\u0000\u000031\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u0000"+
		"4\u0001\u0001\u0000\u0000\u000053\u0001\u0000\u0000\u00006:\u0005\u0013"+
		"\u0000\u000079\u0003\u0004\u0002\u000087\u0001\u0000\u0000\u00009<\u0001"+
		"\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000"+
		";=\u0001\u0000\u0000\u0000<:\u0001\u0000\u0000\u0000=>\u0005\u0014\u0000"+
		"\u0000>\u0003\u0001\u0000\u0000\u0000?D\u0003\u0006\u0003\u0000@D\u0003"+
		"\u0010\b\u0000AD\u0005/\u0000\u0000BD\u0005\u0017\u0000\u0000C?\u0001"+
		"\u0000\u0000\u0000C@\u0001\u0000\u0000\u0000CA\u0001\u0000\u0000\u0000"+
		"CB\u0001\u0000\u0000\u0000D\u0005\u0001\u0000\u0000\u0000EF\u0003\b\u0004"+
		"\u0000FG\u0005\u0017\u0000\u0000GK\u0001\u0000\u0000\u0000HK\u0003\u0014"+
		"\n\u0000IK\u0003\n\u0005\u0000JE\u0001\u0000\u0000\u0000JH\u0001\u0000"+
		"\u0000\u0000JI\u0001\u0000\u0000\u0000K\u0007\u0001\u0000\u0000\u0000"+
		"LM\u0005)\u0000\u0000MN\u0005-\u0000\u0000NO\u0005\u0016\u0000\u0000O"+
		"P\u0003*\u0015\u0000P\t\u0001\u0000\u0000\u0000QR\u0005\u001d\u0000\u0000"+
		"RS\u0005-\u0000\u0000ST\u0003\f\u0006\u0000T\u000b\u0001\u0000\u0000\u0000"+
		"UV\u0005\u0013\u0000\u0000V[\u0003\b\u0004\u0000WX\u0005\b\u0000\u0000"+
		"XZ\u0003\b\u0004\u0000YW\u0001\u0000\u0000\u0000Z]\u0001\u0000\u0000\u0000"+
		"[Y\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000\\_\u0001\u0000\u0000"+
		"\u0000][\u0001\u0000\u0000\u0000^`\u0005\b\u0000\u0000_^\u0001\u0000\u0000"+
		"\u0000_`\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000ab\u0005\u0014"+
		"\u0000\u0000b\r\u0001\u0000\u0000\u0000cd\u0005-\u0000\u0000de\u0005\u0007"+
		"\u0000\u0000eq\u0003\u000e\u0007\u0000fk\u0005-\u0000\u0000gh\u0005\u0011"+
		"\u0000\u0000hi\u0003&\u0013\u0000ij\u0005\u0012\u0000\u0000jl\u0001\u0000"+
		"\u0000\u0000kg\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mk\u0001"+
		"\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000nq\u0001\u0000\u0000\u0000"+
		"oq\u0005-\u0000\u0000pc\u0001\u0000\u0000\u0000pf\u0001\u0000\u0000\u0000"+
		"po\u0001\u0000\u0000\u0000q\u000f\u0001\u0000\u0000\u0000r\u0080\u0003"+
		"\u0016\u000b\u0000s\u0080\u0003\u001a\r\u0000tu\u0003\u001c\u000e\u0000"+
		"uv\u0005\u0017\u0000\u0000v\u0080\u0001\u0000\u0000\u0000wx\u0003\u001e"+
		"\u000f\u0000xy\u0005\u0017\u0000\u0000y\u0080\u0001\u0000\u0000\u0000"+
		"z{\u0005&\u0000\u0000{\u0080\u0005\u0017\u0000\u0000|}\u0003\u0012\t\u0000"+
		"}~\u0005\u0017\u0000\u0000~\u0080\u0001\u0000\u0000\u0000\u007fr\u0001"+
		"\u0000\u0000\u0000\u007fs\u0001\u0000\u0000\u0000\u007ft\u0001\u0000\u0000"+
		"\u0000\u007fw\u0001\u0000\u0000\u0000\u007fz\u0001\u0000\u0000\u0000\u007f"+
		"|\u0001\u0000\u0000\u0000\u0080\u0011\u0001\u0000\u0000\u0000\u0081\u0082"+
		"\u0005\'\u0000\u0000\u0082\u0083\u0003&\u0013\u0000\u0083\u0013\u0001"+
		"\u0000\u0000\u0000\u0084\u0085\u0005(\u0000\u0000\u0085\u0086\u0005-\u0000"+
		"\u0000\u0086\u0088\u0005\u000f\u0000\u0000\u0087\u0089\u0003 \u0010\u0000"+
		"\u0088\u0087\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000"+
		"\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u008b\u0005\u0010\u0000\u0000"+
		"\u008b\u008c\u0005\u0016\u0000\u0000\u008c\u008d\u0003(\u0014\u0000\u008d"+
		"\u008e\u0003\u0002\u0001\u0000\u008e\u0015\u0001\u0000\u0000\u0000\u008f"+
		"\u0090\u0003\u0018\f\u0000\u0090\u0097\u0003\u0002\u0001\u0000\u0091\u0092"+
		"\u0005+\u0000\u0000\u0092\u0093\u0003\u0018\f\u0000\u0093\u0094\u0003"+
		"\u0002\u0001\u0000\u0094\u0096\u0001\u0000\u0000\u0000\u0095\u0091\u0001"+
		"\u0000\u0000\u0000\u0096\u0099\u0001\u0000\u0000\u0000\u0097\u0095\u0001"+
		"\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u009c\u0001"+
		"\u0000\u0000\u0000\u0099\u0097\u0001\u0000\u0000\u0000\u009a\u009b\u0005"+
		"+\u0000\u0000\u009b\u009d\u0003\u0002\u0001\u0000\u009c\u009a\u0001\u0000"+
		"\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u0017\u0001\u0000"+
		"\u0000\u0000\u009e\u009f\u0005*\u0000\u0000\u009f\u00a0\u0005\u000f\u0000"+
		"\u0000\u00a0\u00a1\u0003&\u0013\u0000\u00a1\u00a2\u0005\u0010\u0000\u0000"+
		"\u00a2\u0019\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005,\u0000\u0000\u00a4"+
		"\u00a5\u0005\u000f\u0000\u0000\u00a5\u00a6\u0003&\u0013\u0000\u00a6\u00a7"+
		"\u0005\u0010\u0000\u0000\u00a7\u00a8\u0003\u0002\u0001\u0000\u00a8\u001b"+
		"\u0001\u0000\u0000\u0000\u00a9\u00aa\u0003\u000e\u0007\u0000\u00aa\u00ab"+
		"\u0005\u000e\u0000\u0000\u00ab\u00ac\u0003&\u0013\u0000\u00ac\u00b2\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ae\u0003\u000e\u0007\u0000\u00ae\u00af\u0005"+
		"\r\u0000\u0000\u00af\u00b0\u0005\r\u0000\u0000\u00b0\u00b2\u0001\u0000"+
		"\u0000\u0000\u00b1\u00a9\u0001\u0000\u0000\u0000\u00b1\u00ad\u0001\u0000"+
		"\u0000\u0000\u00b2\u001d\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005-\u0000"+
		"\u0000\u00b4\u00b6\u0005\u000f\u0000\u0000\u00b5\u00b7\u0003$\u0012\u0000"+
		"\u00b6\u00b5\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00c1\u0005\u0010\u0000\u0000"+
		"\u00b9\u00ba\u0007\u0000\u0000\u0000\u00ba\u00bb\u0005\u0007\u0000\u0000"+
		"\u00bb\u00bc\u0007\u0001\u0000\u0000\u00bc\u00bd\u0005\u000f\u0000\u0000"+
		"\u00bd\u00be\u0003$\u0012\u0000\u00be\u00bf\u0005\u0010\u0000\u0000\u00bf"+
		"\u00c1\u0001\u0000\u0000\u0000\u00c0\u00b3\u0001\u0000\u0000\u0000\u00c0"+
		"\u00b9\u0001\u0000\u0000\u0000\u00c1\u001f\u0001\u0000\u0000\u0000\u00c2"+
		"\u00c3\u0003\"\u0011\u0000\u00c3\u00c4\u0005\b\u0000\u0000\u00c4\u00c6"+
		"\u0001\u0000\u0000\u0000\u00c5\u00c2\u0001\u0000\u0000\u0000\u00c6\u00c9"+
		"\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c7\u00c8"+
		"\u0001\u0000\u0000\u0000\u00c8\u00ca\u0001\u0000\u0000\u0000\u00c9\u00c7"+
		"\u0001\u0000\u0000\u0000\u00ca\u00cc\u0003\"\u0011\u0000\u00cb\u00cd\u0005"+
		"\b\u0000\u0000\u00cc\u00cb\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cd!\u0001\u0000\u0000\u0000\u00ce\u00cf\u0005-\u0000\u0000"+
		"\u00cf\u00d0\u0005\u0016\u0000\u0000\u00d0\u00d1\u0003*\u0015\u0000\u00d1"+
		"#\u0001\u0000\u0000\u0000\u00d2\u00d3\u0003&\u0013\u0000\u00d3\u00d4\u0005"+
		"\b\u0000\u0000\u00d4\u00d6\u0001\u0000\u0000\u0000\u00d5\u00d2\u0001\u0000"+
		"\u0000\u0000\u00d6\u00d9\u0001\u0000\u0000\u0000\u00d7\u00d5\u0001\u0000"+
		"\u0000\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000\u00d8\u00da\u0001\u0000"+
		"\u0000\u0000\u00d9\u00d7\u0001\u0000\u0000\u0000\u00da\u00dc\u0003&\u0013"+
		"\u0000\u00db\u00dd\u0005\b\u0000\u0000\u00dc\u00db\u0001\u0000\u0000\u0000"+
		"\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dd%\u0001\u0000\u0000\u0000\u00de"+
		"\u00df\u0006\u0013\uffff\uffff\u0000\u00df\u00f0\u0003\u001e\u000f\u0000"+
		"\u00e0\u00e1\u0005\u000f\u0000\u0000\u00e1\u00e2\u0003&\u0013\u0000\u00e2"+
		"\u00e3\u0005\u0010\u0000\u0000\u00e3\u00f0\u0001\u0000\u0000\u0000\u00e4"+
		"\u00e5\u0007\u0002\u0000\u0000\u00e5\u00f0\u0003&\u0013\f\u00e6\u00e7"+
		"\u0005\u000f\u0000\u0000\u00e7\u00e8\u0003*\u0015\u0000\u00e8\u00e9\u0005"+
		"\u0010\u0000\u0000\u00e9\u00ea\u0003&\u0013\u000b\u00ea\u00f0\u0001\u0000"+
		"\u0000\u0000\u00eb\u00f0\u0003.\u0017\u0000\u00ec\u00f0\u0005.\u0000\u0000"+
		"\u00ed\u00f0\u0003\u000e\u0007\u0000\u00ee\u00f0\u0005\u0015\u0000\u0000"+
		"\u00ef\u00de\u0001\u0000\u0000\u0000\u00ef\u00e0\u0001\u0000\u0000\u0000"+
		"\u00ef\u00e4\u0001\u0000\u0000\u0000\u00ef\u00e6\u0001\u0000\u0000\u0000"+
		"\u00ef\u00eb\u0001\u0000\u0000\u0000\u00ef\u00ec\u0001\u0000\u0000\u0000"+
		"\u00ef\u00ed\u0001\u0000\u0000\u0000\u00ef\u00ee\u0001\u0000\u0000\u0000"+
		"\u00f0\u0105\u0001\u0000\u0000\u0000\u00f1\u00f2\n\u0006\u0000\u0000\u00f2"+
		"\u00f3\u0007\u0003\u0000\u0000\u00f3\u0104\u0003&\u0013\u0007\u00f4\u00f5"+
		"\n\u0005\u0000\u0000\u00f5\u00f6\u0007\u0004\u0000\u0000\u00f6\u0104\u0003"+
		"&\u0013\u0006\u00f7\u00f8\n\u0004\u0000\u0000\u00f8\u00f9\u0007\u0005"+
		"\u0000\u0000\u00f9\u0104\u0003&\u0013\u0005\u00fa\u00fb\n\u0003\u0000"+
		"\u0000\u00fb\u00fc\u0007\u0006\u0000\u0000\u00fc\u0104\u0003&\u0013\u0004"+
		"\u00fd\u00fe\n\u0002\u0000\u0000\u00fe\u00ff\u0005$\u0000\u0000\u00ff"+
		"\u0104\u0003&\u0013\u0003\u0100\u0101\n\u0001\u0000\u0000\u0101\u0102"+
		"\u0005%\u0000\u0000\u0102\u0104\u0003&\u0013\u0002\u0103\u00f1\u0001\u0000"+
		"\u0000\u0000\u0103\u00f4\u0001\u0000\u0000\u0000\u0103\u00f7\u0001\u0000"+
		"\u0000\u0000\u0103\u00fa\u0001\u0000\u0000\u0000\u0103\u00fd\u0001\u0000"+
		"\u0000\u0000\u0103\u0100\u0001\u0000\u0000\u0000\u0104\u0107\u0001\u0000"+
		"\u0000\u0000\u0105\u0103\u0001\u0000\u0000\u0000\u0105\u0106\u0001\u0000"+
		"\u0000\u0000\u0106\'\u0001\u0000\u0000\u0000\u0107\u0105\u0001\u0000\u0000"+
		"\u0000\u0108\u010b\u0003*\u0015\u0000\u0109\u010b\u0005\u0019\u0000\u0000"+
		"\u010a\u0108\u0001\u0000\u0000\u0000\u010a\u0109\u0001\u0000\u0000\u0000"+
		"\u010b)\u0001\u0000\u0000\u0000\u010c\u0111\u0003,\u0016\u0000\u010d\u010e"+
		"\u0005\u0011\u0000\u0000\u010e\u0110\u0005\u0012\u0000\u0000\u010f\u010d"+
		"\u0001\u0000\u0000\u0000\u0110\u0113\u0001\u0000\u0000\u0000\u0111\u010f"+
		"\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000\u0000\u0112+\u0001"+
		"\u0000\u0000\u0000\u0113\u0111\u0001\u0000\u0000\u0000\u0114\u0119\u0005"+
		"\u001c\u0000\u0000\u0115\u0119\u0005\u001a\u0000\u0000\u0116\u0119\u0005"+
		"\u001b\u0000\u0000\u0117\u0119\u0005-\u0000\u0000\u0118\u0114\u0001\u0000"+
		"\u0000\u0000\u0118\u0115\u0001\u0000\u0000\u0000\u0118\u0116\u0001\u0000"+
		"\u0000\u0000\u0118\u0117\u0001\u0000\u0000\u0000\u0119-\u0001\u0000\u0000"+
		"\u0000\u011a\u011b\u0007\u0007\u0000\u0000\u011b/\u0001\u0000\u0000\u0000"+
		"\u00193:CJ[_mp\u007f\u0088\u0097\u009c\u00b1\u00b6\u00c0\u00c7\u00cc\u00d7"+
		"\u00dc\u00ef\u0103\u0105\u010a\u0111\u0118";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}