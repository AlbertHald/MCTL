// Generated from dk/aau/p4/abaaja/mctl.g4 by ANTLR 4.12.0
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
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		EQUAL=1, NOTEQUAL=2, LESS=3, LESSEQUAL=4, GREATER=5, GREATEREQUAL=6, DOT=7, 
		COMMA=8, MULTIPLY=9, DIVIDE=10, MODULO=11, DECREMENT=12, INCREMENT=13, 
		MINUS=14, PLUS=15, ASSIGN=16, LPAR=17, RPAR=18, LSQR=19, RSQR=20, LCURL=21, 
		RCURL=22, STRING=23, COLON=24, SEMI=25, NOT=26, Nothing=27, String=28, 
		Number=29, Boolean=30, Struct=31, True=32, False=33, And=34, Or=35, Stop=36, 
		Return=37, To=38, Variable=39, If=40, Else=41, Repeat=42, ID=43, NUMBER=44, 
		COMMENT=45, WS=46;
	public static final int
		RULE_mctl = 0, RULE_block = 1, RULE_comment = 2, RULE_declaration = 3, 
		RULE_variableDeclaration = 4, RULE_id = 5, RULE_statement = 6, RULE_return = 7, 
		RULE_if = 8, RULE_ifLiteral = 9, RULE_repeat = 10, RULE_assignment = 11, 
		RULE_invoke = 12, RULE_formalParameters = 13, RULE_formalParameter = 14, 
		RULE_actualParameters = 15, RULE_expression = 16, RULE_returnType = 17, 
		RULE_variableType = 18, RULE_baseVariableType = 19, RULE_boolean = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"mctl", "block", "comment", "declaration", "variableDeclaration", "id", 
			"statement", "return", "if", "ifLiteral", "repeat", "assignment", "invoke", 
			"formalParameters", "formalParameter", "actualParameters", "expression", 
			"returnType", "variableType", "baseVariableType", "boolean"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", "'.'", "','", "'*'", 
			"'/'", "'%'", "'--'", "'++'", "'-'", "'+'", "'='", "'('", "')'", "'['", 
			"']'", "'{'", "'}'", null, "':'", "';'", "'!'", "'NOTHING'", "'STRING'", 
			"'NUMBER'", "'BOOLEAN'", "'struct'", "'true'", "'false'", "'and'", "'or'", 
			"'stop'", "'return'", "'to'", "'variable'", "'if'", "'else'", "'repeat'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "EQUAL", "NOTEQUAL", "LESS", "LESSEQUAL", "GREATER", "GREATEREQUAL", 
			"DOT", "COMMA", "MULTIPLY", "DIVIDE", "MODULO", "DECREMENT", "INCREMENT", 
			"MINUS", "PLUS", "ASSIGN", "LPAR", "RPAR", "LSQR", "RSQR", "LCURL", "RCURL", 
			"STRING", "COLON", "SEMI", "NOT", "Nothing", "String", "Number", "Boolean", 
			"Struct", "True", "False", "And", "Or", "Stop", "Return", "To", "Variable", 
			"If", "Else", "Repeat", "ID", "NUMBER", "COMMENT", "WS"
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
	public String getGrammarFileName() { return "mctl.g4"; }

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
		public TerminalNode EOF() { return getToken(mctlParser.EOF, 0); }
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(mctlParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(mctlParser.SEMI, i);
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
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 50511004827648L) != 0)) {
				{
				setState(46);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Struct:
				case To:
				case Variable:
					{
					setState(42);
					declaration();
					}
					break;
				case STRING:
				case Stop:
				case Return:
				case If:
				case Repeat:
				case ID:
					{
					setState(43);
					statement();
					}
					break;
				case COMMENT:
					{
					setState(44);
					comment();
					}
					break;
				case SEMI:
					{
					setState(45);
					match(SEMI);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51);
			match(EOF);
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
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(mctlParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(mctlParser.SEMI, i);
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
			setState(53);
			match(LCURL);
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 50511004827648L) != 0)) {
				{
				setState(58);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Struct:
				case To:
				case Variable:
					{
					setState(54);
					declaration();
					}
					break;
				case STRING:
				case Stop:
				case Return:
				case If:
				case Repeat:
				case ID:
					{
					setState(55);
					statement();
					}
					break;
				case COMMENT:
					{
					setState(56);
					comment();
					}
					break;
				case SEMI:
					{
					setState(57);
					match(SEMI);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63);
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
	public static class CommentContext extends ParserRuleContext {
		public TerminalNode COMMENT() { return getToken(mctlParser.COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitComment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(COMMENT);
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
	public static class StructDeclarationContext extends DeclarationContext {
		public TerminalNode Struct() { return getToken(mctlParser.Struct, 0); }
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
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
		public StructDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
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
	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclContext extends DeclarationContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(mctlParser.SEMI, 0); }
		public VarDeclContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDeclarationContext extends DeclarationContext {
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
		public FunctionDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaration);
		int _la;
		try {
			int _alt;
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Variable:
				_localctx = new VarDeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				variableDeclaration();
				setState(68);
				match(SEMI);
				}
				break;
			case To:
				_localctx = new FunctionDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				match(To);
				setState(71);
				match(ID);
				setState(72);
				match(LPAR);
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(73);
					formalParameters();
					}
				}

				setState(76);
				match(RPAR);
				setState(77);
				match(COLON);
				setState(78);
				returnType();
				setState(79);
				block();
				}
				break;
			case Struct:
				_localctx = new StructDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				match(Struct);
				setState(82);
				match(ID);
				setState(83);
				match(LCURL);
				setState(84);
				variableDeclaration();
				setState(89);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(85);
						match(COMMA);
						setState(86);
						variableDeclaration();
						}
						} 
					}
					setState(91);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(92);
					match(COMMA);
					}
				}

				setState(95);
				match(RCURL);
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
			setState(99);
			match(Variable);
			setState(100);
			match(ID);
			setState(101);
			match(COLON);
			setState(102);
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
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode LSQR() { return getToken(mctlParser.LSQR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RSQR() { return getToken(mctlParser.RSQR, 0); }
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
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public TerminalNode DOT() { return getToken(mctlParser.DOT, 0); }
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
		return id(0);
	}

	private IdContext id(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		IdContext _localctx = new IdContext(_ctx, _parentState);
		IdContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_id, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new IdVarContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(105);
			match(ID);
			}
			_ctx.stop = _input.LT(-1);
			setState(117);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(115);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new IdStructContext(new IdContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_id);
						setState(107);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(108);
						match(DOT);
						setState(109);
						id(3);
						}
						break;
					case 2:
						{
						_localctx = new IdArrayContext(new IdContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_id);
						setState(110);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(111);
						match(LSQR);
						setState(112);
						expression(0);
						setState(113);
						match(RSQR);
						}
						break;
					}
					} 
				}
				setState(119);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
	public static class StopStatementContext extends StatementContext {
		public TerminalNode Stop() { return getToken(mctlParser.Stop, 0); }
		public TerminalNode SEMI() { return getToken(mctlParser.SEMI, 0); }
		public StopStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterStopStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitStopStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitStopStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RepeatStatementContext extends StatementContext {
		public RepeatContext repeat() {
			return getRuleContext(RepeatContext.class,0);
		}
		public RepeatStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterRepeatStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitRepeatStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitRepeatStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentStatementContext extends StatementContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(mctlParser.SEMI, 0); }
		public AssignmentStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterAssignmentStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitAssignmentStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InvokeStatementContext extends StatementContext {
		public InvokeContext invoke() {
			return getRuleContext(InvokeContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(mctlParser.SEMI, 0); }
		public InvokeStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterInvokeStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitInvokeStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitInvokeStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends StatementContext {
		public IfContext if_() {
			return getRuleContext(IfContext.class,0);
		}
		public IfStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStatementContext extends StatementContext {
		public ReturnContext return_() {
			return getRuleContext(ReturnContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(mctlParser.SEMI, 0); }
		public ReturnStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		try {
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new IfStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				if_();
				}
				break;
			case 2:
				_localctx = new RepeatStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				repeat();
				}
				break;
			case 3:
				_localctx = new AssignmentStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(122);
				assignment();
				setState(123);
				match(SEMI);
				}
				break;
			case 4:
				_localctx = new InvokeStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(125);
				invoke();
				setState(126);
				match(SEMI);
				}
				break;
			case 5:
				_localctx = new StopStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(128);
				match(Stop);
				setState(129);
				match(SEMI);
				}
				break;
			case 6:
				_localctx = new ReturnStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(130);
				return_();
				setState(131);
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
		enterRule(_localctx, 14, RULE_return);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(Return);
			setState(136);
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
		enterRule(_localctx, 16, RULE_if);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			ifLiteral();
			setState(139);
			block();
			setState(146);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(140);
					match(Else);
					setState(141);
					ifLiteral();
					setState(142);
					block();
					}
					} 
				}
				setState(148);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Else) {
				{
				setState(149);
				match(Else);
				setState(150);
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
		enterRule(_localctx, 18, RULE_ifLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(If);
			setState(154);
			match(LPAR);
			setState(155);
			expression(0);
			setState(156);
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
		enterRule(_localctx, 20, RULE_repeat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(Repeat);
			setState(159);
			match(LPAR);
			setState(160);
			expression(0);
			setState(161);
			match(RPAR);
			setState(162);
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
		public Token op;
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(mctlParser.ASSIGN, 0); }
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
		public Token op;
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode INCREMENT() { return getToken(mctlParser.INCREMENT, 0); }
		public TerminalNode DECREMENT() { return getToken(mctlParser.DECREMENT, 0); }
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
		enterRule(_localctx, 22, RULE_assignment);
		int _la;
		try {
			setState(171);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new ExprAssContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				id(0);
				setState(165);
				((ExprAssContext)_localctx).op = match(ASSIGN);
				setState(166);
				expression(0);
				}
				break;
			case 2:
				_localctx = new IncrAssContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				id(0);
				setState(169);
				((IncrAssContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==DECREMENT || _la==INCREMENT) ) {
					((IncrAssContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
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
	public static class StringMethodInvokeContext extends InvokeContext {
		public TerminalNode STRING() { return getToken(mctlParser.STRING, 0); }
		public TerminalNode DOT() { return getToken(mctlParser.DOT, 0); }
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
		public TerminalNode LPAR() { return getToken(mctlParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(mctlParser.RPAR, 0); }
		public ActualParametersContext actualParameters() {
			return getRuleContext(ActualParametersContext.class,0);
		}
		public StringMethodInvokeContext(InvokeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterStringMethodInvoke(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitStringMethodInvoke(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitStringMethodInvoke(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionInvokeContext extends InvokeContext {
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
		public TerminalNode LPAR() { return getToken(mctlParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(mctlParser.RPAR, 0); }
		public ActualParametersContext actualParameters() {
			return getRuleContext(ActualParametersContext.class,0);
		}
		public FunctionInvokeContext(InvokeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterFunctionInvoke(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitFunctionInvoke(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitFunctionInvoke(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarMethodInvokeContext extends InvokeContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode DOT() { return getToken(mctlParser.DOT, 0); }
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
		public TerminalNode LPAR() { return getToken(mctlParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(mctlParser.RPAR, 0); }
		public ActualParametersContext actualParameters() {
			return getRuleContext(ActualParametersContext.class,0);
		}
		public VarMethodInvokeContext(InvokeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterVarMethodInvoke(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitVarMethodInvoke(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitVarMethodInvoke(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InvokeContext invoke() throws RecognitionException {
		InvokeContext _localctx = new InvokeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_invoke);
		int _la;
		try {
			setState(196);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new FunctionInvokeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(173);
				match(ID);
				setState(174);
				match(LPAR);
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 26401239646208L) != 0)) {
					{
					setState(175);
					actualParameters();
					}
				}

				setState(178);
				match(RPAR);
				}
				break;
			case 2:
				_localctx = new VarMethodInvokeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(179);
				id(0);
				setState(180);
				match(DOT);
				setState(181);
				match(ID);
				setState(182);
				match(LPAR);
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 26401239646208L) != 0)) {
					{
					setState(183);
					actualParameters();
					}
				}

				setState(186);
				match(RPAR);
				}
				break;
			case 3:
				_localctx = new StringMethodInvokeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(188);
				match(STRING);
				setState(189);
				match(DOT);
				setState(190);
				match(ID);
				setState(191);
				match(LPAR);
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 26401239646208L) != 0)) {
					{
					setState(192);
					actualParameters();
					}
				}

				setState(195);
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
		enterRule(_localctx, 26, RULE_formalParameters);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(198);
					formalParameter();
					setState(199);
					match(COMMA);
					}
					} 
				}
				setState(205);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			setState(206);
			formalParameter();
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(207);
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
		enterRule(_localctx, 28, RULE_formalParameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(ID);
			setState(211);
			match(COLON);
			setState(212);
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
		enterRule(_localctx, 30, RULE_actualParameters);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(214);
					expression(0);
					setState(215);
					match(COMMA);
					}
					} 
				}
				setState(221);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			setState(222);
			expression(0);
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(223);
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
	public static class InvExprContext extends ExpressionContext {
		public InvokeContext invoke() {
			return getRuleContext(InvokeContext.class,0);
		}
		public InvExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterInvExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitInvExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitInvExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberExprContext extends ExpressionContext {
		public TerminalNode NUMBER() { return getToken(mctlParser.NUMBER, 0); }
		public NumberExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterNumberExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitNumberExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitNumberExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Or() { return getToken(mctlParser.Or, 0); }
		public OrExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExprContext extends ExpressionContext {
		public TerminalNode LPAR() { return getToken(mctlParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(mctlParser.RPAR, 0); }
		public ParenExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitParenExpr(this);
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
	public static class StringExprContext extends ExpressionContext {
		public TerminalNode STRING() { return getToken(mctlParser.STRING, 0); }
		public StringExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterStringExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitStringExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitStringExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(mctlParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(mctlParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(mctlParser.PLUS, 0); }
		public UnaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(mctlParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(mctlParser.MINUS, 0); }
		public AddExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitAddExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitAddExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CompExprContext extends ExpressionContext {
		public Token op;
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
		public CompExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterCompExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitCompExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitCompExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MULTIPLY() { return getToken(mctlParser.MULTIPLY, 0); }
		public TerminalNode DIVIDE() { return getToken(mctlParser.DIVIDE, 0); }
		public TerminalNode MODULO() { return getToken(mctlParser.MODULO, 0); }
		public MulExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterMulExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitMulExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitMulExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoolExprContext extends ExpressionContext {
		public BooleanContext boolean_() {
			return getRuleContext(BooleanContext.class,0);
		}
		public BoolExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterBoolExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitBoolExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitBoolExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdExprContext extends ExpressionContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public IdExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterIdExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitIdExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitIdExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(mctlParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(mctlParser.NOTEQUAL, 0); }
		public EqualExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterEqualExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitEqualExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitEqualExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode And() { return getToken(mctlParser.And, 0); }
		public AndExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitAndExpr(this);
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
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				_localctx = new InvExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(227);
				invoke();
				}
				break;
			case 2:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(228);
				match(LPAR);
				setState(229);
				expression(0);
				setState(230);
				match(RPAR);
				}
				break;
			case 3:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(232);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 67158016L) != 0)) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(233);
				expression(12);
				}
				break;
			case 4:
				{
				_localctx = new TypecastContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(234);
				match(LPAR);
				setState(235);
				variableType();
				setState(236);
				match(RPAR);
				setState(237);
				expression(11);
				}
				break;
			case 5:
				{
				_localctx = new BoolExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(239);
				boolean_();
				}
				break;
			case 6:
				{
				_localctx = new NumberExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(240);
				match(NUMBER);
				}
				break;
			case 7:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(241);
				id(0);
				}
				break;
			case 8:
				{
				_localctx = new StringExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(242);
				match(STRING);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(265);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(263);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						_localctx = new MulExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(245);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(246);
						((MulExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3584L) != 0)) ) {
							((MulExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(247);
						expression(7);
						}
						break;
					case 2:
						{
						_localctx = new AddExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(248);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(249);
						((AddExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MINUS || _la==PLUS) ) {
							((AddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(250);
						expression(6);
						}
						break;
					case 3:
						{
						_localctx = new CompExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(251);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(252);
						((CompExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 120L) != 0)) ) {
							((CompExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(253);
						expression(5);
						}
						break;
					case 4:
						{
						_localctx = new EqualExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(254);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(255);
						((EqualExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
							((EqualExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(256);
						expression(4);
						}
						break;
					case 5:
						{
						_localctx = new AndExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(257);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(258);
						match(And);
						setState(259);
						expression(3);
						}
						break;
					case 6:
						{
						_localctx = new OrExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(260);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(261);
						match(Or);
						setState(262);
						expression(2);
						}
						break;
					}
					} 
				}
				setState(267);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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
	public static class ReturnTypeNothingContext extends ReturnTypeContext {
		public TerminalNode Nothing() { return getToken(mctlParser.Nothing, 0); }
		public ReturnTypeNothingContext(ReturnTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterReturnTypeNothing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitReturnTypeNothing(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitReturnTypeNothing(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReturnTypeVariableContext extends ReturnTypeContext {
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public ReturnTypeVariableContext(ReturnTypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterReturnTypeVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitReturnTypeVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitReturnTypeVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_returnType);
		try {
			setState(270);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String:
			case Number:
			case Boolean:
			case ID:
				_localctx = new ReturnTypeVariableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(268);
				variableType();
				}
				break;
			case Nothing:
				_localctx = new ReturnTypeNothingContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
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
		enterRule(_localctx, 36, RULE_variableType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			baseVariableType();
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LSQR) {
				{
				{
				setState(273);
				match(LSQR);
				setState(274);
				match(RSQR);
				}
				}
				setState(279);
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
		public TerminalNode Boolean() { return getToken(mctlParser.Boolean, 0); }
		public TerminalNode String() { return getToken(mctlParser.String, 0); }
		public TerminalNode Number() { return getToken(mctlParser.Number, 0); }
		public TerminalNode ID() { return getToken(mctlParser.ID, 0); }
		public BaseVariableTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseVariableType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).enterBaseVariableType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof mctlListener ) ((mctlListener)listener).exitBaseVariableType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof mctlVisitor ) return ((mctlVisitor<? extends T>)visitor).visitBaseVariableType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseVariableTypeContext baseVariableType() throws RecognitionException {
		BaseVariableTypeContext _localctx = new BaseVariableTypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_baseVariableType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8797972070400L) != 0)) ) {
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
		enterRule(_localctx, 40, RULE_boolean);
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
		case 5:
			return id_sempred((IdContext)_localctx, predIndex);
		case 16:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean id_sempred(IdContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 2);
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001.\u011d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0005\u0000/\b\u0000\n\u0000\f\u00002\t\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001;\b\u0001\n\u0001\f\u0001>\t\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003K\b\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003"+
		"X\b\u0003\n\u0003\f\u0003[\t\u0003\u0001\u0003\u0003\u0003^\b\u0003\u0001"+
		"\u0003\u0001\u0003\u0003\u0003b\b\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0005\u0005t\b\u0005\n\u0005\f\u0005w\t\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006\u0086\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u0091\b\b\n\b\f\b\u0094"+
		"\t\b\u0001\b\u0001\b\u0003\b\u0098\b\b\u0001\t\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u00ac\b\u000b\u0001\f\u0001\f\u0001\f\u0003\f\u00b1\b\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00b9\b\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00c2\b\f\u0001\f\u0003"+
		"\f\u00c5\b\f\u0001\r\u0001\r\u0001\r\u0005\r\u00ca\b\r\n\r\f\r\u00cd\t"+
		"\r\u0001\r\u0001\r\u0003\r\u00d1\b\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u00da\b\u000f"+
		"\n\u000f\f\u000f\u00dd\t\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00e1"+
		"\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003"+
		"\u0010\u00f4\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0005\u0010\u0108\b\u0010\n\u0010\f\u0010\u010b\t\u0010"+
		"\u0001\u0011\u0001\u0011\u0003\u0011\u010f\b\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0005\u0012\u0114\b\u0012\n\u0012\f\u0012\u0117\t\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0000\u0002\n "+
		"\u0015\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&(\u0000\b\u0001\u0000\f\r\u0002\u0000\u000e\u000f"+
		"\u001a\u001a\u0001\u0000\t\u000b\u0001\u0000\u000e\u000f\u0001\u0000\u0003"+
		"\u0006\u0001\u0000\u0001\u0002\u0002\u0000\u001c\u001e++\u0001\u0000 "+
		"!\u0136\u00000\u0001\u0000\u0000\u0000\u00025\u0001\u0000\u0000\u0000"+
		"\u0004A\u0001\u0000\u0000\u0000\u0006a\u0001\u0000\u0000\u0000\bc\u0001"+
		"\u0000\u0000\u0000\nh\u0001\u0000\u0000\u0000\f\u0085\u0001\u0000\u0000"+
		"\u0000\u000e\u0087\u0001\u0000\u0000\u0000\u0010\u008a\u0001\u0000\u0000"+
		"\u0000\u0012\u0099\u0001\u0000\u0000\u0000\u0014\u009e\u0001\u0000\u0000"+
		"\u0000\u0016\u00ab\u0001\u0000\u0000\u0000\u0018\u00c4\u0001\u0000\u0000"+
		"\u0000\u001a\u00cb\u0001\u0000\u0000\u0000\u001c\u00d2\u0001\u0000\u0000"+
		"\u0000\u001e\u00db\u0001\u0000\u0000\u0000 \u00f3\u0001\u0000\u0000\u0000"+
		"\"\u010e\u0001\u0000\u0000\u0000$\u0110\u0001\u0000\u0000\u0000&\u0118"+
		"\u0001\u0000\u0000\u0000(\u011a\u0001\u0000\u0000\u0000*/\u0003\u0006"+
		"\u0003\u0000+/\u0003\f\u0006\u0000,/\u0003\u0004\u0002\u0000-/\u0005\u0019"+
		"\u0000\u0000.*\u0001\u0000\u0000\u0000.+\u0001\u0000\u0000\u0000.,\u0001"+
		"\u0000\u0000\u0000.-\u0001\u0000\u0000\u0000/2\u0001\u0000\u0000\u0000"+
		"0.\u0001\u0000\u0000\u000001\u0001\u0000\u0000\u000013\u0001\u0000\u0000"+
		"\u000020\u0001\u0000\u0000\u000034\u0005\u0000\u0000\u00014\u0001\u0001"+
		"\u0000\u0000\u00005<\u0005\u0015\u0000\u00006;\u0003\u0006\u0003\u0000"+
		"7;\u0003\f\u0006\u00008;\u0003\u0004\u0002\u00009;\u0005\u0019\u0000\u0000"+
		":6\u0001\u0000\u0000\u0000:7\u0001\u0000\u0000\u0000:8\u0001\u0000\u0000"+
		"\u0000:9\u0001\u0000\u0000\u0000;>\u0001\u0000\u0000\u0000<:\u0001\u0000"+
		"\u0000\u0000<=\u0001\u0000\u0000\u0000=?\u0001\u0000\u0000\u0000><\u0001"+
		"\u0000\u0000\u0000?@\u0005\u0016\u0000\u0000@\u0003\u0001\u0000\u0000"+
		"\u0000AB\u0005-\u0000\u0000B\u0005\u0001\u0000\u0000\u0000CD\u0003\b\u0004"+
		"\u0000DE\u0005\u0019\u0000\u0000Eb\u0001\u0000\u0000\u0000FG\u0005&\u0000"+
		"\u0000GH\u0005+\u0000\u0000HJ\u0005\u0011\u0000\u0000IK\u0003\u001a\r"+
		"\u0000JI\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000KL\u0001\u0000"+
		"\u0000\u0000LM\u0005\u0012\u0000\u0000MN\u0005\u0018\u0000\u0000NO\u0003"+
		"\"\u0011\u0000OP\u0003\u0002\u0001\u0000Pb\u0001\u0000\u0000\u0000QR\u0005"+
		"\u001f\u0000\u0000RS\u0005+\u0000\u0000ST\u0005\u0015\u0000\u0000TY\u0003"+
		"\b\u0004\u0000UV\u0005\b\u0000\u0000VX\u0003\b\u0004\u0000WU\u0001\u0000"+
		"\u0000\u0000X[\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000\u0000YZ\u0001"+
		"\u0000\u0000\u0000Z]\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000"+
		"\\^\u0005\b\u0000\u0000]\\\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000"+
		"\u0000^_\u0001\u0000\u0000\u0000_`\u0005\u0016\u0000\u0000`b\u0001\u0000"+
		"\u0000\u0000aC\u0001\u0000\u0000\u0000aF\u0001\u0000\u0000\u0000aQ\u0001"+
		"\u0000\u0000\u0000b\u0007\u0001\u0000\u0000\u0000cd\u0005\'\u0000\u0000"+
		"de\u0005+\u0000\u0000ef\u0005\u0018\u0000\u0000fg\u0003$\u0012\u0000g"+
		"\t\u0001\u0000\u0000\u0000hi\u0006\u0005\uffff\uffff\u0000ij\u0005+\u0000"+
		"\u0000ju\u0001\u0000\u0000\u0000kl\n\u0002\u0000\u0000lm\u0005\u0007\u0000"+
		"\u0000mt\u0003\n\u0005\u0003no\n\u0001\u0000\u0000op\u0005\u0013\u0000"+
		"\u0000pq\u0003 \u0010\u0000qr\u0005\u0014\u0000\u0000rt\u0001\u0000\u0000"+
		"\u0000sk\u0001\u0000\u0000\u0000sn\u0001\u0000\u0000\u0000tw\u0001\u0000"+
		"\u0000\u0000us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000v\u000b"+
		"\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000x\u0086\u0003\u0010"+
		"\b\u0000y\u0086\u0003\u0014\n\u0000z{\u0003\u0016\u000b\u0000{|\u0005"+
		"\u0019\u0000\u0000|\u0086\u0001\u0000\u0000\u0000}~\u0003\u0018\f\u0000"+
		"~\u007f\u0005\u0019\u0000\u0000\u007f\u0086\u0001\u0000\u0000\u0000\u0080"+
		"\u0081\u0005$\u0000\u0000\u0081\u0086\u0005\u0019\u0000\u0000\u0082\u0083"+
		"\u0003\u000e\u0007\u0000\u0083\u0084\u0005\u0019\u0000\u0000\u0084\u0086"+
		"\u0001\u0000\u0000\u0000\u0085x\u0001\u0000\u0000\u0000\u0085y\u0001\u0000"+
		"\u0000\u0000\u0085z\u0001\u0000\u0000\u0000\u0085}\u0001\u0000\u0000\u0000"+
		"\u0085\u0080\u0001\u0000\u0000\u0000\u0085\u0082\u0001\u0000\u0000\u0000"+
		"\u0086\r\u0001\u0000\u0000\u0000\u0087\u0088\u0005%\u0000\u0000\u0088"+
		"\u0089\u0003 \u0010\u0000\u0089\u000f\u0001\u0000\u0000\u0000\u008a\u008b"+
		"\u0003\u0012\t\u0000\u008b\u0092\u0003\u0002\u0001\u0000\u008c\u008d\u0005"+
		")\u0000\u0000\u008d\u008e\u0003\u0012\t\u0000\u008e\u008f\u0003\u0002"+
		"\u0001\u0000\u008f\u0091\u0001\u0000\u0000\u0000\u0090\u008c\u0001\u0000"+
		"\u0000\u0000\u0091\u0094\u0001\u0000\u0000\u0000\u0092\u0090\u0001\u0000"+
		"\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0097\u0001\u0000"+
		"\u0000\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0095\u0096\u0005)\u0000"+
		"\u0000\u0096\u0098\u0003\u0002\u0001\u0000\u0097\u0095\u0001\u0000\u0000"+
		"\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u0011\u0001\u0000\u0000"+
		"\u0000\u0099\u009a\u0005(\u0000\u0000\u009a\u009b\u0005\u0011\u0000\u0000"+
		"\u009b\u009c\u0003 \u0010\u0000\u009c\u009d\u0005\u0012\u0000\u0000\u009d"+
		"\u0013\u0001\u0000\u0000\u0000\u009e\u009f\u0005*\u0000\u0000\u009f\u00a0"+
		"\u0005\u0011\u0000\u0000\u00a0\u00a1\u0003 \u0010\u0000\u00a1\u00a2\u0005"+
		"\u0012\u0000\u0000\u00a2\u00a3\u0003\u0002\u0001\u0000\u00a3\u0015\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a5\u0003\n\u0005\u0000\u00a5\u00a6\u0005\u0010"+
		"\u0000\u0000\u00a6\u00a7\u0003 \u0010\u0000\u00a7\u00ac\u0001\u0000\u0000"+
		"\u0000\u00a8\u00a9\u0003\n\u0005\u0000\u00a9\u00aa\u0007\u0000\u0000\u0000"+
		"\u00aa\u00ac\u0001\u0000\u0000\u0000\u00ab\u00a4\u0001\u0000\u0000\u0000"+
		"\u00ab\u00a8\u0001\u0000\u0000\u0000\u00ac\u0017\u0001\u0000\u0000\u0000"+
		"\u00ad\u00ae\u0005+\u0000\u0000\u00ae\u00b0\u0005\u0011\u0000\u0000\u00af"+
		"\u00b1\u0003\u001e\u000f\u0000\u00b0\u00af\u0001\u0000\u0000\u0000\u00b0"+
		"\u00b1\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2"+
		"\u00c5\u0005\u0012\u0000\u0000\u00b3\u00b4\u0003\n\u0005\u0000\u00b4\u00b5"+
		"\u0005\u0007\u0000\u0000\u00b5\u00b6\u0005+\u0000\u0000\u00b6\u00b8\u0005"+
		"\u0011\u0000\u0000\u00b7\u00b9\u0003\u001e\u000f\u0000\u00b8\u00b7\u0001"+
		"\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001"+
		"\u0000\u0000\u0000\u00ba\u00bb\u0005\u0012\u0000\u0000\u00bb\u00c5\u0001"+
		"\u0000\u0000\u0000\u00bc\u00bd\u0005\u0017\u0000\u0000\u00bd\u00be\u0005"+
		"\u0007\u0000\u0000\u00be\u00bf\u0005+\u0000\u0000\u00bf\u00c1\u0005\u0011"+
		"\u0000\u0000\u00c0\u00c2\u0003\u001e\u000f\u0000\u00c1\u00c0\u0001\u0000"+
		"\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000"+
		"\u0000\u0000\u00c3\u00c5\u0005\u0012\u0000\u0000\u00c4\u00ad\u0001\u0000"+
		"\u0000\u0000\u00c4\u00b3\u0001\u0000\u0000\u0000\u00c4\u00bc\u0001\u0000"+
		"\u0000\u0000\u00c5\u0019\u0001\u0000\u0000\u0000\u00c6\u00c7\u0003\u001c"+
		"\u000e\u0000\u00c7\u00c8\u0005\b\u0000\u0000\u00c8\u00ca\u0001\u0000\u0000"+
		"\u0000\u00c9\u00c6\u0001\u0000\u0000\u0000\u00ca\u00cd\u0001\u0000\u0000"+
		"\u0000\u00cb\u00c9\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000"+
		"\u0000\u00cc\u00ce\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001\u0000\u0000"+
		"\u0000\u00ce\u00d0\u0003\u001c\u000e\u0000\u00cf\u00d1\u0005\b\u0000\u0000"+
		"\u00d0\u00cf\u0001\u0000\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000"+
		"\u00d1\u001b\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005+\u0000\u0000\u00d3"+
		"\u00d4\u0005\u0018\u0000\u0000\u00d4\u00d5\u0003$\u0012\u0000\u00d5\u001d"+
		"\u0001\u0000\u0000\u0000\u00d6\u00d7\u0003 \u0010\u0000\u00d7\u00d8\u0005"+
		"\b\u0000\u0000\u00d8\u00da\u0001\u0000\u0000\u0000\u00d9\u00d6\u0001\u0000"+
		"\u0000\u0000\u00da\u00dd\u0001\u0000\u0000\u0000\u00db\u00d9\u0001\u0000"+
		"\u0000\u0000\u00db\u00dc\u0001\u0000\u0000\u0000\u00dc\u00de\u0001\u0000"+
		"\u0000\u0000\u00dd\u00db\u0001\u0000\u0000\u0000\u00de\u00e0\u0003 \u0010"+
		"\u0000\u00df\u00e1\u0005\b\u0000\u0000\u00e0\u00df\u0001\u0000\u0000\u0000"+
		"\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1\u001f\u0001\u0000\u0000\u0000"+
		"\u00e2\u00e3\u0006\u0010\uffff\uffff\u0000\u00e3\u00f4\u0003\u0018\f\u0000"+
		"\u00e4\u00e5\u0005\u0011\u0000\u0000\u00e5\u00e6\u0003 \u0010\u0000\u00e6"+
		"\u00e7\u0005\u0012\u0000\u0000\u00e7\u00f4\u0001\u0000\u0000\u0000\u00e8"+
		"\u00e9\u0007\u0001\u0000\u0000\u00e9\u00f4\u0003 \u0010\f\u00ea\u00eb"+
		"\u0005\u0011\u0000\u0000\u00eb\u00ec\u0003$\u0012\u0000\u00ec\u00ed\u0005"+
		"\u0012\u0000\u0000\u00ed\u00ee\u0003 \u0010\u000b\u00ee\u00f4\u0001\u0000"+
		"\u0000\u0000\u00ef\u00f4\u0003(\u0014\u0000\u00f0\u00f4\u0005,\u0000\u0000"+
		"\u00f1\u00f4\u0003\n\u0005\u0000\u00f2\u00f4\u0005\u0017\u0000\u0000\u00f3"+
		"\u00e2\u0001\u0000\u0000\u0000\u00f3\u00e4\u0001\u0000\u0000\u0000\u00f3"+
		"\u00e8\u0001\u0000\u0000\u0000\u00f3\u00ea\u0001\u0000\u0000\u0000\u00f3"+
		"\u00ef\u0001\u0000\u0000\u0000\u00f3\u00f0\u0001\u0000\u0000\u0000\u00f3"+
		"\u00f1\u0001\u0000\u0000\u0000\u00f3\u00f2\u0001\u0000\u0000\u0000\u00f4"+
		"\u0109\u0001\u0000\u0000\u0000\u00f5\u00f6\n\u0006\u0000\u0000\u00f6\u00f7"+
		"\u0007\u0002\u0000\u0000\u00f7\u0108\u0003 \u0010\u0007\u00f8\u00f9\n"+
		"\u0005\u0000\u0000\u00f9\u00fa\u0007\u0003\u0000\u0000\u00fa\u0108\u0003"+
		" \u0010\u0006\u00fb\u00fc\n\u0004\u0000\u0000\u00fc\u00fd\u0007\u0004"+
		"\u0000\u0000\u00fd\u0108\u0003 \u0010\u0005\u00fe\u00ff\n\u0003\u0000"+
		"\u0000\u00ff\u0100\u0007\u0005\u0000\u0000\u0100\u0108\u0003 \u0010\u0004"+
		"\u0101\u0102\n\u0002\u0000\u0000\u0102\u0103\u0005\"\u0000\u0000\u0103"+
		"\u0108\u0003 \u0010\u0003\u0104\u0105\n\u0001\u0000\u0000\u0105\u0106"+
		"\u0005#\u0000\u0000\u0106\u0108\u0003 \u0010\u0002\u0107\u00f5\u0001\u0000"+
		"\u0000\u0000\u0107\u00f8\u0001\u0000\u0000\u0000\u0107\u00fb\u0001\u0000"+
		"\u0000\u0000\u0107\u00fe\u0001\u0000\u0000\u0000\u0107\u0101\u0001\u0000"+
		"\u0000\u0000\u0107\u0104\u0001\u0000\u0000\u0000\u0108\u010b\u0001\u0000"+
		"\u0000\u0000\u0109\u0107\u0001\u0000\u0000\u0000\u0109\u010a\u0001\u0000"+
		"\u0000\u0000\u010a!\u0001\u0000\u0000\u0000\u010b\u0109\u0001\u0000\u0000"+
		"\u0000\u010c\u010f\u0003$\u0012\u0000\u010d\u010f\u0005\u001b\u0000\u0000"+
		"\u010e\u010c\u0001\u0000\u0000\u0000\u010e\u010d\u0001\u0000\u0000\u0000"+
		"\u010f#\u0001\u0000\u0000\u0000\u0110\u0115\u0003&\u0013\u0000\u0111\u0112"+
		"\u0005\u0013\u0000\u0000\u0112\u0114\u0005\u0014\u0000\u0000\u0113\u0111"+
		"\u0001\u0000\u0000\u0000\u0114\u0117\u0001\u0000\u0000\u0000\u0115\u0113"+
		"\u0001\u0000\u0000\u0000\u0115\u0116\u0001\u0000\u0000\u0000\u0116%\u0001"+
		"\u0000\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u0118\u0119\u0007"+
		"\u0006\u0000\u0000\u0119\'\u0001\u0000\u0000\u0000\u011a\u011b\u0007\u0007"+
		"\u0000\u0000\u011b)\u0001\u0000\u0000\u0000\u001b.0:<JY]asu\u0085\u0092"+
		"\u0097\u00ab\u00b0\u00b8\u00c1\u00c4\u00cb\u00d0\u00db\u00e0\u00f3\u0107"+
		"\u0109\u010e\u0115";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}