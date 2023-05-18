package dk.aau.p4.abaaja.InterpreterTests;

import dk.aau.p4.abaaja.Lib.Interpreter.IGameBridge;
import dk.aau.p4.abaaja.Lib.Interpreter.Interpreter;
import dk.aau.p4.abaaja.Lib.Nodes.FormalParamNode;
import dk.aau.p4.abaaja.Lib.Nodes.MctlNode;
import dk.aau.p4.abaaja.Lib.ProblemHandling.Problem;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.Symbols.FuncSymbol;
import dk.aau.p4.abaaja.Lib.Symbols.Symbol;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import dk.aau.p4.abaaja.Lib.Visitors.AstBuilder;
import dk.aau.p4.abaaja.mctlLexer;
import dk.aau.p4.abaaja.mctlParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class InterpreterTests {

    public MctlNode parseNode(String code) {

        // Build tree from input code:

        mctlLexer lexer = new mctlLexer(CharStreams.fromString(code));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);

        mctlParser parser = new mctlParser(commonTokenStream);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.mctl();

        MctlNode concreteNode = (MctlNode) tree.accept(new AstBuilder(new ProblemCollection()));

        return concreteNode;

    }

    @DataProvider
    public Object[][] varDecTestData() {
        return new Object[][] {
                {"variable test: STRING;", "STRING"},
                {"variable test: BOOLEAN;", "BOOLEAN"},
                {"variable test: NUMBER;", "NUMBER"},
                {"variable test: NUMBER[];", "NUMBER"},
                {"variable test: NUMBER[][];", "NUMBER"},
                {"struct STRUCTURE { variable x: NUMBER }; variable test: STRUCTURE;", "STRUCTURE"},
                {"struct STRUCTURE { variable x: NUMBER }; variable test: STRUCTURE[];", "STRUCTURE"},
                {"struct STRUCTURE { variable x: NUMBER }; variable test: STRUCTURE[][];", "STRUCTURE"},
        };
    }
    @Test(dataProvider = "varDecTestData")
    public void varDec_setsSymbol(String code, String type) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertNotNull(symbol.get_type(), "Should set a type on the symbol");
        Assert.assertEquals(symbol.get_type().get_type_literal(), type, "Should set the appropriate type on the symbol");

    }

    @DataProvider
    public Object[][] funcDecTestData() {
        return new Object[][] {
                {"to test(): STRING {}", List.of(), List.of(), "STRING"},
                {"to test(): NUMBER {}", List.of(), List.of(), "NUMBER"},
                {"to test(): BOOLEAN {}", List.of(), List.of(), "BOOLEAN"},
                {"to test(): NOTHING {}", List.of(), List.of(), "NOTHING"},
                {"to test(input: STRING): NOTHING {}", List.of("input"), List.of("STRING"), "NOTHING"},
                {"to test(input: NUMBER): NOTHING {}", List.of("input"), List.of("NUMBER"), "NOTHING"},
                {"to test(input: BOOLEAN): NOTHING {}", List.of("input"), List.of("BOOLEAN"), "NOTHING"},
                {"to test(left: NUMBER, right: NUMBER): NUMBER {}", List.of("left", "right"), List.of("NUMBER", "NUMBER"), "NUMBER"},
                {"to test(left: NUMBER, right: NUMBER): BOOLEAN {}", List.of("left", "right"), List.of("NUMBER", "NUMBER"), "BOOLEAN"},
                {"to test(name: STRING, surname: STRING, age: NUMBER, address: STRING, bossman: BOOLEAN): STRING {}", List.of("name", "surname", "age", "address", "bossman"), List.of("STRING", "STRING", "NUMBER", "STRING", "BOOLEAN"), "STRING"},
                {"to test(input: NUMBER[]): NUMBER[] {}", List.of("input"), List.of("NUMBER"), "NUMBER"},
                {"struct STRUCTURE { variable x: NUMBER }; to test(input: STRUCTURE): STRUCTURE {}", List.of("input"), List.of("STRUCTURE"), "STRUCTURE"},
        };
    }
    @Test(dataProvider = "funcDecTestData")
    public void funcDec_setsSymbol(String code, List<String> paramNames, List<String> paramTypes, String returnType) {
        Assert.assertEquals(paramNames.size(), paramTypes.size(), "Error in testdata - paramNames and paramTypes should be same length");
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol with the correct function name and add it to the symbol table");
        Assert.assertTrue(symbol instanceof FuncSymbol, "The symbol should be a function symbol");
        FuncSymbol funcSymbol = (FuncSymbol) symbol;
        Assert.assertNotNull(funcSymbol.get_funcBlock(), "The symbol should contain a function block");
        Assert.assertNotNull(funcSymbol.get_type(), "Should set a return type on the symbol");
        Assert.assertEquals(funcSymbol.get_type().get_type_literal(), returnType, "Should set the appropriate return type on the symbol");
        List<FormalParamNode> formalParams = funcSymbol.get_formalParams();
        for(int i = 0; i < paramNames.size(); i++){
            FormalParamNode formalParam = formalParams.get(i);
            Assert.assertEquals(formalParam.get_id(), paramNames.get(i), "Formal parameter " + i + " should be named correctly");
            Assert.assertEquals(formalParam.get_type().get_type(), paramTypes.get(i), "Formal parameter " + i + " should have the correct type set");
        }
    }

    @DataProvider
    public Object[][] varAssTestData_string() {
        return new Object[][] {
                {"variable test: STRING; test = 'test string';", "test string"},
                {"variable test: STRING; test = ' ';", " "},
                {"variable test: STRING; test = '';", ""},
                {"variable test: STRING; test = '\"';", "\""},
                {"variable test: STRING; test = '1';", "1"},
                {"variable test: STRING; test = 'true';", "true"},
        };
    }
    @Test(dataProvider = "varAssTestData_string")
    public void varAss_setsValue_string(String code, String value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");
        Assert.assertNotNull(symbol.get_type(), "Should set a type for the symbol");
        Assert.assertEquals(symbol.get_type().get_type_literal(), "STRING", "Should set the appropriate type on the symbol");

    }

    @DataProvider
    public Object[][] varAssTestData_bool() {
        return new Object[][] {
                {"variable test: BOOLEAN; test = true;", true},
                {"variable test: BOOLEAN; test = false;", false},
        };
    }
    @Test(dataProvider = "varAssTestData_bool")
    public void varAss_setsValue_bool(String code, boolean value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");
        Assert.assertNotNull(symbol.get_type(), "Should set a type for the symbol");
        Assert.assertEquals(symbol.get_type().get_type_literal(), "BOOLEAN", "Should set the appropriate type on the symbol");

    }

    @DataProvider
    public Object[][] varAssTestData_number() {
        return new Object[][] {
                {"variable test: NUMBER; test = 1;", 1.0},
                {"variable test: NUMBER; test = 10.5;", 10.5},
                {"variable test: NUMBER; test = 1.3789;", 1.3789},
                {"variable test: NUMBER; test = 9435;", 9435.0},
                {"variable test: NUMBER; test = 0;", 0.0},
                {"variable test: NUMBER; test = -0;", -0.0},
                {"variable test: NUMBER; test = -8;", -8.0},
                //TODO: When we have better int handling, ensure that test cases like the following works:
                //{"variable test: NUMBER; test = 1045390435;", 1045390435},
        };
    }
    @Test(dataProvider = "varAssTestData_number")
    public void varAss_setsValue_number(String code, Number value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");
        Assert.assertNotNull(symbol.get_type(), "Should set a type for the symbol");
        Assert.assertEquals(symbol.get_type().get_type_literal(), "NUMBER", "Should set the appropriate type on the symbol");

    }

    @DataProvider
    public Object[][] structAssTestData() {
        return new Object[][] {
                {"struct S { variable o: BOOLEAN, variable t: BOOLEAN }; variable test: S; test.o = true; test.t = true;", true, true},
                {"struct S { variable o: BOOLEAN, variable t: BOOLEAN }; variable test: S; test.o = false; test.t = false;", false, false},
                {"struct S { variable o: BOOLEAN, variable t: BOOLEAN }; variable test: S; test.o = true; test.t = false;", true, false},
                {"struct S { variable o: BOOLEAN, variable t: BOOLEAN }; variable test: S; test.o = false; test.t = true;", false, true},
        };
    }
    @Test(dataProvider = "structAssTestData")
    public void structAss_setsValue(String code, boolean value_one, boolean value_two) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertNotNull(symbol.get_type(), "Should set a type for the symbol");
        Assert.assertEquals(symbol.get_type().get_type_literal(), "S", "Should set the appropriate type on the symbol");

        Symbol symbol_one = symbol.get_field("o");
        Assert.assertNotNull(symbol_one, "Should create a symbol for 'o' and add it to the symbol fields");
        Assert.assertEquals(symbol_one.get_value(), value_one, "Should set the appropriate value on symbol 'o'");
        Assert.assertNotNull(symbol_one.get_type(), "Should set a type for symbol 'o'");
        Assert.assertEquals(symbol_one.get_type().get_type_literal(), "BOOLEAN", "Should set the appropriate type on symbol 'o'");

        Symbol symbol_two = symbol.get_field("t");
        Assert.assertNotNull(symbol_two, "Should create a symbol for 't' and add it to the symbol fields");
        Assert.assertEquals(symbol_two.get_value(), value_two, "Should set the appropriate value on symbol 't'");
        Assert.assertNotNull(symbol_two.get_type(), "Should set a type for symbol 't'");
        Assert.assertEquals(symbol_two.get_type().get_type_literal(), "BOOLEAN", "Should set the appropriate type on symbol 't'");

    }

    @DataProvider
    public Object[][] structAssNestedTestData() {
        return new Object[][] {
                {"struct S { variable s: S, variable o: BOOLEAN, variable t: BOOLEAN }; variable test: S; test.s.s.o = true; test.s.s.t = true;", true, true},
                {"struct S { variable s: S, variable o: BOOLEAN, variable t: BOOLEAN }; variable test: S; test.s.s.o = false; test.s.s.t = false;", false, false},
                {"struct S { variable s: S, variable o: BOOLEAN, variable t: BOOLEAN }; variable test: S; test.s.s.o = true; test.s.s.t = false;", true, false},
                {"struct S { variable s: S, variable o: BOOLEAN, variable t: BOOLEAN }; variable test: S; test.s.s.o = false; test.s.s.t = true;", false, true},
        };
    }
    @Test(dataProvider = "structAssNestedTestData")
    public void structAssNested_setsValue(String code, boolean value_one, boolean value_two) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertNotNull(symbol.get_type(), "Should set a type for the symbol");
        Assert.assertEquals(symbol.get_type().get_type_literal(), "S", "Should set the appropriate type on the symbol");

        //TODO: Possibly enforce correct types on nested structs. They are not necessary for the interpreter right now, but consistency is usually good.

        Symbol nested_0s = symbol.get_field("s");
        Assert.assertNotNull(nested_0s, "Should create a nested symbol for 's' and add it to the symbol fields");
        //Assert.assertNotNull(nested_0s.get_type(), "Should set a type for nested symbol 's'");
        //Assert.assertEquals(nested_0s.get_type().get_type_literal(), "S", "Should set the appropriate type on nested symbol 's'");

        Symbol nested_1s = nested_0s.get_field("s");
        Assert.assertNotNull(nested_1s, "Should create a nested symbol for 's' and add it to the nested 's' symbol fields");
        //Assert.assertNotNull(nested_1s.get_type(), "Should set a type for nested symbol 's'");
        //Assert.assertEquals(nested_1s.get_type().get_type_literal(), "S", "Should set the appropriate type on nested symbol 's'");

        Symbol symbol_one = nested_1s.get_field("o");
        Assert.assertNotNull(symbol_one, "Should create a symbol for 'o' and add it to the nested 's' symbol fields");
        Assert.assertEquals(symbol_one.get_value(), value_one, "Should set the appropriate value on symbol 'o'");
        Assert.assertNotNull(symbol_one.get_type(), "Should set a type for symbol 'o'");
        Assert.assertEquals(symbol_one.get_type().get_type_literal(), "BOOLEAN", "Should set the appropriate type on symbol 'o'");

        Symbol symbol_two = nested_1s.get_field("t");
        Assert.assertNotNull(symbol_two, "Should create a symbol for 't' and add it to the nested 's' symbol fields");
        Assert.assertEquals(symbol_two.get_value(), value_two, "Should set the appropriate value on symbol 't'");
        Assert.assertNotNull(symbol_two.get_type(), "Should set a type for symbol 't'");
        Assert.assertEquals(symbol_two.get_type().get_type_literal(), "BOOLEAN", "Should set the appropriate type on symbol 't'");

    }

    @DataProvider
    public Object[][] expTestData_number() {
        return new Object[][] {
                {"variable test: NUMBER; test = +1;", 1.0},
                {"variable test: NUMBER; test = +43;", 43.0},
                {"variable test: NUMBER; test = +0;", 0.0},
                {"variable test: NUMBER; test = +0.3;", 0.3},
                {"variable test: NUMBER; test = -1;", -1.0},
                {"variable test: NUMBER; test = -43;", -43.0},
                {"variable test: NUMBER; test = -0;", -0.0},
                {"variable test: NUMBER; test = -0.3;", -0.3},
                {"variable test: NUMBER; test = (NUMBER) '1';", 1.0},
                {"variable test: NUMBER; test = (NUMBER) '0';", 0.0},
                {"variable test: NUMBER; test = (NUMBER) '1.457';", 1.457},
                {"variable test: NUMBER; test = 0; test++;", 1.0},
                {"variable test: NUMBER; test = 4; test++;", 5.0},
                {"variable test: NUMBER; test = 0; test++; test++;", 2.0},
                {"variable test: NUMBER; test = 1; test--;", 0.0},
                {"variable test: NUMBER; test = 5; test--;", 4.0},
                {"variable test: NUMBER; test = 7; test--; test--;", 5.0},
                {"variable test: NUMBER; test = -4; test++;", -3.0},
                {"variable test: NUMBER; test = -5; test--;", -6.0},
                {"variable test: NUMBER; test = 1 + 1;", 2.0},
                {"variable test: NUMBER; test = 37 + 46;", 83.0},
                {"variable test: NUMBER; test = 0 + 0;", 0.0},
                {"variable test: NUMBER; test = 1 + 0;", 1.0},
                {"variable test: NUMBER; test = 0 + 1;", 1.0},
                {"variable test: NUMBER; test = 0.5 + 0.5;", 1.0},
                {"variable test: NUMBER; test = 0.3 + 1.7;", 2.0},
                {"variable test: NUMBER; test = -5 + -20;", -25.0},
                {"variable test: NUMBER; test = 2 - 1;", 1.0},
                {"variable test: NUMBER; test = 46 - 37;", 9.0},
                {"variable test: NUMBER; test = 1 - 1;", 0.0},
                {"variable test: NUMBER; test = 1 - 0;", 1.0},
                {"variable test: NUMBER; test = 0 - 1;", -1.0},
                {"variable test: NUMBER; test = 0.5 - 0.5;", 0.0},
                {"variable test: NUMBER; test = 0.3 - 1.7;", -1.4},
                {"variable test: NUMBER; test = -8 - -9;", 1.0},
                {"variable test: NUMBER; test = 2 / 2;", 1.0},
                {"variable test: NUMBER; test = 0 / 1;", 0.0},
                {"variable test: NUMBER; test = 1 / 1;", 1.0},
                {"variable test: NUMBER; test = 48 / 12;", 4.0},
                {"variable test: NUMBER; test = -50 / -10;", 5.0},
                {"variable test: NUMBER; test = 1 * 1;", 1.0},
                {"variable test: NUMBER; test = 34 * 27;", 918.0},
                {"variable test: NUMBER; test = 1 * 0;", 0.0},
                {"variable test: NUMBER; test = 0 * 1;", 0.0},
                {"variable test: NUMBER; test = 0 * 0;", 0.0},
                {"variable test: NUMBER; test = -5 * -6;", 30.0},
                {"variable test: NUMBER; test = 4 % 3;", 1.0},
                {"variable test: NUMBER; test = 4 % 4;", 0.0},
                {"variable test: NUMBER; test = 4 % 1;", 0.0},
                {"variable test: NUMBER; test = 0 % 1;", 0.0},
                {"variable test: NUMBER; test = 0 % 5;", 0.0},
                {"variable test: NUMBER; test = 34 % 27;", 7.0},
                {"variable test: NUMBER; test = 34.0 % 27.0;", 7.0},
                {"variable test: NUMBER; test = 27 % 34;", 27.0},
                {"variable test: NUMBER; test = -100 % -9;", -1.0},
                {"variable test: NUMBER; test = -100 % 9;", -1.0},
        };
    }
    @Test(dataProvider = "expTestData_number")
    public void exp_resolvesValue_number(String code, Number value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] expTestData_boolean() {
        return new Object[][] {
                {"variable test: BOOLEAN; test = !false;", true},
                {"variable test: BOOLEAN; test = !true;", false},
                {"variable test: BOOLEAN; test = true and true;", true},
                {"variable test: BOOLEAN; test = true and false;", false},
                {"variable test: BOOLEAN; test = false and true;", false},
                {"variable test: BOOLEAN; test = false and false;", false},
                {"variable test: BOOLEAN; test = true or true;", true},
                {"variable test: BOOLEAN; test = true or false;", true},
                {"variable test: BOOLEAN; test = false or true;", true},
                {"variable test: BOOLEAN; test = false or false;", false},
                {"variable test: BOOLEAN; test = 2 > 0;", true},
                {"variable test: BOOLEAN; test = 4 < 69;", true},
                {"variable test: BOOLEAN; test = 2 > 1;", true},
                {"variable test: BOOLEAN; test = 1 < 2;", true},
                {"variable test: BOOLEAN; test = 1.1 > 1;", true},
                {"variable test: BOOLEAN; test = 1 < 1.1;", true},
                {"variable test: BOOLEAN; test = 1.1 < 1.1;", false},
                {"variable test: BOOLEAN; test = 4 < 4;", false},
                {"variable test: BOOLEAN; test = 0 < 0;", false},
                {"variable test: BOOLEAN; test = 4 > 4;", false},
                {"variable test: BOOLEAN; test = 0 > 0;", false},
                {"variable test: BOOLEAN; test = 2 >= 0;", true},
                {"variable test: BOOLEAN; test = 4 <= 69;", true},
                {"variable test: BOOLEAN; test = 2 >= 1;", true},
                {"variable test: BOOLEAN; test = 1 <= 2;", true},
                {"variable test: BOOLEAN; test = 4 <= 4;", true},
                {"variable test: BOOLEAN; test = 0 <= 0;", true},
                {"variable test: BOOLEAN; test = 4.0 <= 4;", true},
                {"variable test: BOOLEAN; test = 4 <= 4.0;", true},
                {"variable test: BOOLEAN; test = 0.0 <= 0.0;", true},
                {"variable test: BOOLEAN; test = 0 <= 0.0;", true},
                {"variable test: BOOLEAN; test = 0.0 <= 0;", true},
                {"variable test: BOOLEAN; test = 4 >= 4;", true},
                {"variable test: BOOLEAN; test = 0 >= 0;", true},
                {"variable test: BOOLEAN; test = 4 <= 3;", false},
                {"variable test: BOOLEAN; test = 1 <= 0;", false},
                {"variable test: BOOLEAN; test = 3 >= 4;", false},
                {"variable test: BOOLEAN; test = 0 >= 1;", false},
                {"variable test: BOOLEAN; test = true == true;", true},
                {"variable test: BOOLEAN; test = false == false;", true},
                {"variable test: BOOLEAN; test = true == false;", false},
                {"variable test: BOOLEAN; test = false == true;", false},
                {"variable test: BOOLEAN; test = true != true;", false},
                {"variable test: BOOLEAN; test = false != false;", false},
                {"variable test: BOOLEAN; test = true != false;", true},
                {"variable test: BOOLEAN; test = false != true;", true},
                {"variable test: BOOLEAN; test = 'bossman' == 'bossman';", true},
                {"variable test: BOOLEAN; test = 'string' == 'string';", true},
                {"variable test: BOOLEAN; test = '' == '';", true},
                {"variable test: BOOLEAN; test = 'bossman' == 'string';", false},
                {"variable test: BOOLEAN; test = 'string' == 'bossman';", false},
                {"variable test: BOOLEAN; test = 'bossman' == '';", false},
                {"variable test: BOOLEAN; test = '' == 'bossman';", false},
                {"variable test: BOOLEAN; test = 'bossman' != 'bossman';", false},
                {"variable test: BOOLEAN; test = 'string' != 'string';", false},
                {"variable test: BOOLEAN; test = '' != '';", false},
                {"variable test: BOOLEAN; test = 'bossman' != 'string';", true},
                {"variable test: BOOLEAN; test = 'string' != 'bossman';", true},
                {"variable test: BOOLEAN; test = 'bossman' != '';", true},
                {"variable test: BOOLEAN; test = '' != 'bossman';", true},
                {"variable test: BOOLEAN; test = 1 == true;", false},
                {"variable test: BOOLEAN; test = 0 == false;", false},
                {"variable test: BOOLEAN; test = 5 == 5;", true},
                {"variable test: BOOLEAN; test = 420 == 420;", true},
                {"variable test: BOOLEAN; test = 1.378 == 1.378;", true},
                {"variable test: BOOLEAN; test = 5 == 5.0;", true},
                {"variable test: BOOLEAN; test = 5.0 == 5;", true},
                {"variable test: BOOLEAN; test = 5 == 420;", false},
                {"variable test: BOOLEAN; test = 420 == 5;", false},
                {"variable test: BOOLEAN; test = 5.0 == 1.378;", false},
                {"variable test: BOOLEAN; test = 1.379 == 1.378;", false},
                {"variable test: BOOLEAN; test = 420 == 420.1;", false},
                {"variable test: BOOLEAN; test = 0 == 0;", true},
                {"variable test: BOOLEAN; test = 0.0 == 0.0;", true},
                {"variable test: BOOLEAN; test = 0 == 0.0;", true},
                {"variable test: BOOLEAN; test = 0.0 == 0;", true},
                {"variable test: BOOLEAN; test = -0.0 == 0.0;", false},
                {"variable test: BOOLEAN; test = 5 != 5;", false},
                {"variable test: BOOLEAN; test = 420 != 420;", false},
                {"variable test: BOOLEAN; test = 1.378 != 1.378;", false},
                {"variable test: BOOLEAN; test = 5 != 5.0;", false},
                {"variable test: BOOLEAN; test = 5.0 != 5;", false},
                {"variable test: BOOLEAN; test = 0 != 0;", false},
                {"variable test: BOOLEAN; test = 0.0 != 0.0;", false},
                {"variable test: BOOLEAN; test = 0 != 0.0;", false},
                {"variable test: BOOLEAN; test = 0.0 != 0;", false},
                {"variable test: BOOLEAN; test = 5 != 420;", true},
                {"variable test: BOOLEAN; test = 420 != 5;", true},
                {"variable test: BOOLEAN; test = 5.0 != 1.378;", true},
                {"variable test: BOOLEAN; test = 1.379 != 1.378;", true},
                {"variable test: BOOLEAN; test = 420 != 420.1;", true},
        };
    }
    @Test(dataProvider = "expTestData_boolean")
    public void exp_resolvesValue_boolean(String code, boolean value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] expTestData_string() {
        return new Object[][] {
                {"variable test: STRING; test = (STRING) true;", "true"},
                {"variable test: STRING; test = (STRING) false;", "false"},
                {"variable test: STRING; test = (STRING) 1;", "1"},
                {"variable test: STRING; test = (STRING) 0;", "0"},
                {"variable test: STRING; test = (STRING) 1.345;", "1.345"},
                {"variable test: STRING; test = (STRING) 1.345;", "1.345"},
                {"variable test: STRING; test = (STRING) 'ooga booga';", "ooga booga"},
        };
    }
    @Test(dataProvider = "expTestData_string")
    public void exp_resolvesValue_string(String code, String value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] expInvalidationTestData() {
        return new Object[][] {
                {"variable test: NUMBER; test = 0 / 0;", "======== ERROR_INTERPRETER =======\nCannot divide by 0\n---- line ----\nLine: 1\n"},
        };
    }
    @Test(dataProvider = "expInvalidationTestData")
    public void exp_invalidation(String code, String errorMessage) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        Assert.assertEquals(problemCollection.getProblems().size(), 1, "Should create exactly 1 error for this issue");
        for (Problem problem : problemCollection.getProblems()) {
            Assert.assertEquals(problem.getMessage(), errorMessage, "The error should match the expected error");
        }

        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol");
        Assert.assertNull(symbol.get_value(), "Should not initialize the symbol");

    }

    @DataProvider
    public Object[][] invokeTestData_bool() {
        return new Object[][] {
                {"variable test: BOOLEAN; to run(): BOOLEAN { return true; } test = run();", true},
                {"variable test: BOOLEAN; to run(): BOOLEAN { return false; } test = run();", false},
                {"variable test: BOOLEAN; test = run(); to run(): BOOLEAN { return true; }", true},
                {"variable test: BOOLEAN; test = run(); to run(): BOOLEAN { return false; }", false},
                {"variable test: BOOLEAN; struct ON { variable isOn: BOOLEAN }; variable on: ON; variable res: ON; on.isOn = true; to run(): ON { return on; } res = run(); test = res.isOn;", true},
                {"variable test: BOOLEAN; struct ON { variable isOn: BOOLEAN }; variable on: ON; variable res: ON; on.isOn = false; to run(): ON { return on; } res = run(); test = res.isOn;", false},
                {"variable test: BOOLEAN; variable ons: BOOLEAN[]; variable res: BOOLEAN[]; ons[3] = true; to run(): BOOLEAN[] { return ons; } res = run(); test = res[3];", true},
                {"variable test: BOOLEAN; variable ons: BOOLEAN[]; variable res: BOOLEAN[]; ons[3] = false; to run(): BOOLEAN[] { return ons; } res = run(); test = res[3];", false},
                {"variable test: BOOLEAN; to run(condition: BOOLEAN): BOOLEAN { return condition; } test = run(true);", true},
                {"variable test: BOOLEAN; to run(condition: BOOLEAN): BOOLEAN { return condition; } test = run(false);", false},
                {"variable test: BOOLEAN; test = run(true); to run(condition: BOOLEAN): BOOLEAN { return condition; }", true},
                {"variable test: BOOLEAN; test = run(false); to run(condition: BOOLEAN): BOOLEAN { return condition; }", false},
                {"variable test: BOOLEAN; struct ON { variable isOn: BOOLEAN }; variable on: ON; variable res: ON; on.isOn = true; to run(condition: ON): ON { return condition; } res = run(on); test = res.isOn;", true},
                {"variable test: BOOLEAN; struct ON { variable isOn: BOOLEAN }; variable on: ON; variable res: ON; on.isOn = false; to run(condition: ON): ON { return condition; } res = run(on); test = res.isOn;", false},
                {"variable test: BOOLEAN; variable ons: BOOLEAN[]; variable res: BOOLEAN[]; ons[3] = true; to run(condition: BOOLEAN[]): BOOLEAN[] { return condition; } res = run(ons); test = res[3];", true},
                {"variable test: BOOLEAN; variable ons: BOOLEAN[]; variable res: BOOLEAN[]; ons[3] = false; to run(condition: BOOLEAN[]): BOOLEAN[] { return condition; } res = run(ons); test = res[3];", false},
                {"variable test: BOOLEAN; variable start: BOOLEAN; start = true; test = run(start); to run(condition: BOOLEAN): BOOLEAN { return condition; }", true},
                {"variable test: BOOLEAN; variable start: BOOLEAN; start = false; test = run(start); to run(condition: BOOLEAN): BOOLEAN { return condition; }", false},
                {"variable test: BOOLEAN; to run(left: BOOLEAN, right: BOOLEAN): BOOLEAN { return left and right; } test = run(true, true);", true},
                {"variable test: BOOLEAN; to run(left: BOOLEAN, right: BOOLEAN): BOOLEAN { return left and right; } test = run(true, false);", false},
                {"variable test: BOOLEAN; to run(left: BOOLEAN, right: BOOLEAN): BOOLEAN { return left and right; } test = run(false, true);", false},
                {"variable test: BOOLEAN; to run(condition: BOOLEAN): BOOLEAN { variable local: BOOLEAN; local = condition; return local; } test = run(true);", true},
                {"variable test: BOOLEAN; to run(condition: BOOLEAN): BOOLEAN { variable local: BOOLEAN; local = condition; return local; } test = run(false);", false},
                {"variable test: BOOLEAN; struct ON { variable isOn: BOOLEAN }; variable on: ON; variable res: ON; on.isOn = true; to run(condition: ON): ON { variable local: ON; local = condition; return local; } res = run(on); test = res.isOn;", true},
                {"variable test: BOOLEAN; struct ON { variable isOn: BOOLEAN }; variable on: ON; variable res: ON; on.isOn = false; to run(condition: ON): ON { variable local: ON; local = condition; return local; } res = run(on); test = res.isOn;", false},
                {"variable test: BOOLEAN; variable ons: BOOLEAN[]; variable res: BOOLEAN[]; ons[3] = true; to run(condition: BOOLEAN[]): BOOLEAN[] { variable local: BOOLEAN[]; local = condition; return local; } res = run(ons); test = res[3];", true},
                {"variable test: BOOLEAN; variable ons: BOOLEAN[]; variable res: BOOLEAN[]; ons[3] = false; to run(condition: BOOLEAN[]): BOOLEAN[] { variable local: BOOLEAN[]; local = condition; return local; } res = run(ons); test = res[3];", false},
        };
    }
    @Test(dataProvider = "invokeTestData_bool")
    public void invoke_returnsValue_bool(String code, boolean value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] invokeTestData_string() {
        return new Object[][] {
                // TODO: Also make these for numbers
                {"variable test: STRING; to run(): STRING{ return 'data'; } test = run();", "data"},
                {"variable test: STRING; to run(): STRING{ return ''; } test = run();", ""},
                {"variable test: STRING; test = run(); to run(): STRING { return 'data'; }", "data"},
                {"variable test: STRING; test = run(); to run(): STRING { return ''; }", ""},
                {"variable test: STRING; struct ON { variable isOn: STRING }; variable on: ON; variable res: ON; on.isOn = 'data'; to run(): ON { return on; } res = run(); test = res.isOn;", "data"},
                {"variable test: STRING; struct ON { variable isOn: STRING }; variable on: ON; variable res: ON; on.isOn = ''; to run(): ON { return on; } res = run(); test = res.isOn;", ""},
                {"variable test: STRING; variable ons: STRING[]; variable res: STRING[]; ons[3] = 'data'; to run(): STRING[] { return ons; } res = run(); test = res[3];", "data"},
                {"variable test: STRING; variable ons: STRING[]; variable res: STRING[]; ons[3] = ''; to run(): STRING[] { return ons; } res = run(); test = res[3];", ""},
                {"variable test: STRING; to run(condition: STRING): STRING { return condition; } test = run('data');", "data"},
                {"variable test: STRING; to run(condition: STRING): STRING { return condition; } test = run('');", ""},
                {"variable test: STRING; test = run('data'); to run(condition: STRING): STRING { return condition; }", "data"},
                {"variable test: STRING; test = run(''); to run(condition: STRING): STRING { return condition; }", ""},
                {"variable test: STRING; struct ON { variable isOn: STRING }; variable on: ON; variable res: ON; on.isOn = 'data'; to run(condition: ON): ON { return condition; } res = run(on); test = res.isOn;", "data"},
                {"variable test: STRING; struct ON { variable isOn: STRING }; variable on: ON; variable res: ON; on.isOn = ''; to run(condition: ON): ON { return condition; } res = run(on); test = res.isOn;", ""},
                {"variable test: STRING; variable ons: STRING[]; variable res: STRING[]; ons[3] = 'data'; to run(condition: STRING[]): STRING[] { return condition; } res = run(ons); test = res[3];", "data"},
                {"variable test: STRING; variable ons: STRING[]; variable res: STRING[]; ons[3] = ''; to run(condition: STRING[]): STRING[] { return condition; } res = run(ons); test = res[3];", ""},
                {"variable test: STRING; variable start: STRING; start = 'data'; test = run(start); to run(condition: STRING): STRING { return condition; }", "data"},
                {"variable test: STRING; variable start: STRING; start = ''; test = run(start); to run(condition: STRING): STRING { return condition; }", ""},
                {"variable test: STRING; to run(left: STRING, right: STRING): STRING { return left.add(right); } test = run('data', 'data');", "datadata"},
                {"variable test: STRING; to run(left: STRING, right: STRING): STRING { return left.add(right); } test = run('data', '');", "data"},
                {"variable test: STRING; to run(left: STRING, right: STRING): STRING { return left.add(right); } test = run('', 'data');", "data"},
                {"variable test: STRING; to run(condition: STRING): STRING { variable local: STRING; local = condition; return local; } test = run('data');", "data"},
                {"variable test: STRING; to run(condition: STRING): STRING { variable local: STRING; local = condition; return local; } test = run('');", ""},
                {"variable test: STRING; struct ON { variable isOn: STRING }; variable on: ON; variable res: ON; on.isOn = 'data'; to run(condition: ON): ON { variable local: ON; local = condition; return local; } res = run(on); test = res.isOn;", "data"},
                {"variable test: STRING; struct ON { variable isOn: STRING }; variable on: ON; variable res: ON; on.isOn = ''; to run(condition: ON): ON { variable local: ON; local = condition; return local; } res = run(on); test = res.isOn;", ""},
                {"variable test: STRING; variable ons: STRING[]; variable res: STRING[]; ons[3] = 'data'; to run(condition: STRING[]): STRING[] { variable local: STRING[]; local = condition; return local; } res = run(ons); test = res[3];", "data"},
                {"variable test: STRING; variable ons: STRING[]; variable res: STRING[]; ons[3] = ''; to run(condition: STRING[]): STRING[] { variable local: STRING[]; local = condition; return local; } res = run(ons); test = res[3];", ""},
        };
    }
    @Test(dataProvider = "invokeTestData_string")
    public void invoke_returnsValue_string(String code, String value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] invokeParamShadowingTestData() {
        return new Object[][] {
                {"variable test: BOOLEAN; variable shadow: BOOLEAN; to run(shadow: BOOLEAN): BOOLEAN { return shadow; } test = run(true);", true},
                {"variable test: BOOLEAN; variable shadow: BOOLEAN; to run(shadow: BOOLEAN): BOOLEAN { return shadow; } test = run(false);", false},
                {"variable test: BOOLEAN; variable shadow: BOOLEAN; to run(shadow: BOOLEAN): BOOLEAN { return shadow; } shadow = false; test = run(true);", true},
                {"variable test: BOOLEAN; variable shadow: BOOLEAN; to run(shadow: BOOLEAN): BOOLEAN { return shadow; } shadow = true; test = run(false);", false},
                {"variable test: BOOLEAN; variable shadow: BOOLEAN; shadow = false; to run(shadow: BOOLEAN): BOOLEAN { return shadow; } test = run(true);", true},
                {"variable test: BOOLEAN; variable shadow: BOOLEAN; shadow = true; to run(shadow: BOOLEAN): BOOLEAN { return shadow; } test = run(false);", false},
        };
    }
    @Test(dataProvider = "invokeParamShadowingTestData")
    public void invokeParameterShadowing_returnsValue(String code, boolean value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] ifTestData() {
        return new Object[][] {
                {"variable test: BOOLEAN; test = false; if(true){ test = true; }", true},
                {"variable test: BOOLEAN; test = false; if(false){ test = true; }", false},
                {"variable test: BOOLEAN; test = false; if(true){ if(true){ test = true; } }", true},
                {"variable test: BOOLEAN; test = false; if(true){ if(true){ if(true){ if(true){ test = true; } } } }", true},
                {"variable test: BOOLEAN; test = false; if(true){ if(false){ test = true; } }", false},
                {"variable test: BOOLEAN; test = false; if(false){ if(true){ test = true; } }", false},
                {"variable test: BOOLEAN; test = false; if(true){ test = true; }else{ test = false; }", true},
                {"variable test: BOOLEAN; test = false; if(true){ test = true; }else if(true){ test = false; }", true},
                {"variable test: BOOLEAN; test = false; if(true){ test = true; }else if(true){ test = false; }else{ test = false; }", true},
                {"variable test: BOOLEAN; test = false; if(false){ test = false; }else{ test = true; }", true},
                {"variable test: BOOLEAN; test = false; if(false){ test = false; }else if(true){ test = true; }", true},
                {"variable test: BOOLEAN; test = false; if(false){ test = false; }else if(true){ test = true; }else{ test = false; }", true},
                {"variable test: BOOLEAN; test = false; if(false){ test = false; }else if(false){ test = false; }else{ test = true; }", true},
        };
    }
    @Test(dataProvider = "ifTestData")
    public void if_setsValue(String code, boolean value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] repeatTestData() {
        return new Object[][] {
                {"variable test: NUMBER; test = 0; repeat(3){ test++; }", 3.0},
                {"variable test: NUMBER; test = 0; repeat(1){ test++; }", 1.0},
                {"variable test: NUMBER; test = 0; repeat(0){ test++; }", 0.0},
                {"variable test: NUMBER; test = 0; repeat(0){ repeat(3){ test++; } }", 0.0},
                {"variable test: NUMBER; test = 0; repeat(2){ repeat(3){ test++; } }", 6.0},
                {"variable test: NUMBER; test = 0; repeat(2){ repeat(1){ repeat(3){ test++; } } }", 6.0},
                {"variable test: NUMBER; test = 0; repeat(2){ repeat(0){ repeat(3){ test++; } } }", 0.0},
                {"variable test: NUMBER; test = 0; repeat(1){ repeat(1){ repeat(1){ test++; } } }", 1.0},
                {"variable test: NUMBER; variable sentinel: BOOLEAN; test = 0; repeat(false){ test++; }", 0.0},
                {"variable test: NUMBER; variable sentinel: BOOLEAN; test = 0; sentinel = true; repeat(sentinel){ test++; sentinel = false; }", 1.0},
                {"variable test: NUMBER; variable sentinel: BOOLEAN; test = 0; sentinel = true; repeat(sentinel){ sentinel = false; test++; }", 1.0},
                {"variable test: NUMBER; variable sentinel: BOOLEAN; test = 0; sentinel = true; repeat(sentinel){ test++; if(test > 1){ sentinel = false; } }", 2.0},
                {"variable test: NUMBER; test = 0; repeat(true){ test++; stop; }", 1.0},
                {"variable test: NUMBER; test = 0; repeat(true){ stop; test++; }", 0.0},
                {"variable test: NUMBER; test = 0; repeat(true){ test++; if(test > 1){ stop; } }", 2.0},
        };
    }
    @Test(dataProvider = "repeatTestData")
    public void repeat_setsValue(String code, Number value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] stringLengthTestData() {
        return new Object[][] {
                {"variable test: NUMBER; test = 'test string'.length();", 11.0},
                {"variable test: NUMBER; test = 'bossman'.length();", 7.0},
                {"variable test: NUMBER; test = ' '.length();", 1.0},
                {"variable test: NUMBER; test = ''.length();", 0.0},
                {"variable test: NUMBER; test = '\"string\"inside\"string\"'.length();", 22.0},
        };
    }
    @Test(dataProvider = "stringLengthTestData")
    public void stringLength_returnsValue(String code, Number value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] stringAddTestData() {
        return new Object[][] {
                {"variable test: STRING; test = 'test'.add(' string');", "test string"},
                {"variable test: STRING; test = 'test'; test = test.add(test);", "testtest"},
                {"variable test: STRING; test = 'boss'.add('man');", "bossman"},
                {"variable test: STRING; test = 'bb'.add('bb');", "bbbb"},
                {"variable test: STRING; test = ''.add('k');", "k"},
                {"variable test: STRING; test = 'k'.add('');", "k"},
                {"variable test: STRING; test = ' '.add('');", " "},
                {"variable test: STRING; test = ''.add(' ');", " "},
                {"variable test: STRING; test = ' '.add(' ');", "  "},
                {"variable test: STRING; test = ''.add('');", ""},
        };
    }
    @Test(dataProvider = "stringAddTestData")
    public void stringAdd_returnsValue(String code, String value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] stringRemoveTestData() {
        return new Object[][] {
                {"variable test: STRING; test = 'test string'.remove();", "test strin"},
                {"variable test: STRING; test = 'bossman'.remove();", "bossma"},
                {"variable test: STRING; test = 'b'.remove();", ""},
                {"variable test: STRING; test = ' '.remove();", ""},
                {"variable test: STRING; test = ''.remove();", ""},
                {"variable test: STRING; test = 'hej'.remove(1);", "he"},
                {"variable test: STRING; test = 'hej'.remove(true);", "he"},
                {"variable test: STRING; test = 'hej'.remove('yo');", "he"},
        };
    }
    @Test(dataProvider = "stringRemoveTestData")
    public void stringRemove_returnsValue(String code, String value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] stringSubstringTestData() {
        return new Object[][] {
                {"variable test: STRING; test = 'test string'.substring(0, 10);", "test string"},
                {"variable test: STRING; test = 'bossman'.substring(0, 6);", "bossman"},
                {"variable test: STRING; test = 'bossman'.substring(2, 6);", "ssman"},
                {"variable test: STRING; test = 'bossman'.substring(0, 4);", "bossm"},
                {"variable test: STRING; test = 'bossman'.substring(2, 4);", "ssm"},
                {"variable test: STRING; test = 'bossman'.substring(3, 3);", "s"},
                {"variable test: STRING; test = 'bossman'.substring(1, 1);", "o"},
                {"variable test: STRING; test = 'bossman'.substring(0, 0);", "b"},
                {"variable test: STRING; test = 'bossman'.substring(6, 6);", "n"},
                {"variable test: STRING; test = 'k'.substring(0, 0);", "k"},
                {"variable test: STRING; test = ' '.substring(0, 0);", " "},
                {"variable test: STRING; test = ''.substring(0, 0);", ""},
                {"variable test: STRING; test = 'hej'.substring(1);", "ej"},
                {"variable test: STRING; test = 'hej'.substring(1, 2, 500, true, 'yo');", "ej"},
        };
    }
    @Test(dataProvider = "stringSubstringTestData")
    public void stringSubstring_returnsValue(String code, String value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] stringIndexesOfTestData() {
        return new Object[][] {
                {"variable test: NUMBER[]; test = 'test string'.indexesOf('e');", List.of(1.0)},
                {"variable test: NUMBER[]; test = 'test string'.indexesOf('t');", List.of(0.0, 3.0, 6.0)},
                {"variable test: NUMBER[]; test = 'test string'.indexesOf('te');", List.of(0.0)},
                {"variable test: NUMBER[]; test = 'test string'.indexesOf('st');", List.of(2.0, 5.0)},
                {"variable test: NUMBER[]; test = 'test string'.indexesOf('test');", List.of(0.0)},
                {"variable test: NUMBER[]; test = 'test string'.indexesOf('string');", List.of(5.0)},
                {"variable test: NUMBER[]; test = 'test string'.indexesOf('test string');", List.of(0.0)},
                {"variable test: NUMBER[]; test = 'test string'.indexesOf('test string ');", List.of()},
                {"variable test: NUMBER[]; test = 'test string'.indexesOf(' test string');", List.of()},
                {"variable test: NUMBER[]; test = 'test string'.indexesOf('teststring');", List.of()},
                {"variable test: NUMBER[]; test = ''.indexesOf('e');", List.of()},
                {"variable test: NUMBER[]; test = ''.indexesOf('string');", List.of()},
                {"variable test: NUMBER[]; test = 'bossman'.indexesOf('');", List.of()},
                {"variable test: NUMBER[]; test = ' '.indexesOf('');", List.of()},
                {"variable test: NUMBER[]; test = ''.indexesOf('');", List.of()},
        };
    }
    @Test(dataProvider = "stringIndexesOfTestData")
    public void stringIndexesOf_returnsValue(String code, List<Number> indexes) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");

        Assert.assertEquals(symbol.get_listLength(), ((Number) indexes.size()).doubleValue(), "There should be exactly " + indexes.size() + " matches");

        for(int i = 0; i < indexes.size(); i++){
            Symbol match = symbol.get_index(i);
            Assert.assertNotNull(match, "The symbol should contain a symbol with a match value at index " + i);
            Assert.assertEquals(match.get_value(), indexes.get(i), "The match symbol should resolve to the correct value");
        }

    }

    @DataProvider
    public Object[][] listLengthTestData() {
        return new Object[][] {
                // TODO: Expand these tests with more cases and more types
                {"variable test: NUMBER; variable list: BOOLEAN[]; list[0] = true; test = list.length();", 1.0},
                {"variable test: NUMBER; variable list: BOOLEAN[]; list[0] = false; test = list.length();", 1.0},
                {"variable test: NUMBER; variable list: BOOLEAN[]; list[4] = true; test = list.length();", 5.0},
                {"variable test: NUMBER; variable list: BOOLEAN[]; list[4] = false; test = list.length();", 5.0},
                {"variable test: NUMBER; variable list: BOOLEAN[]; test = list.length();", 0.0},
                {"variable test: NUMBER; variable list: BOOLEAN[][]; list[0][8] = true; test = list.length();", 1.0},
                {"variable test: NUMBER; variable list: BOOLEAN[][]; list[0][8] = true; test = list[0].length();", 9.0},
                {"variable test: NUMBER; variable list: NUMBER[]; list[0] = 1; test = list.length();", 1.0},
                {"variable test: NUMBER; variable list: NUMBER[]; list[0] = 1; test = list.length(100);", 1.0},
                {"variable test: NUMBER; variable list: NUMBER[]; list[0] = 1; test = list.length('yes');", 1.0},
                {"variable test: NUMBER; variable list: NUMBER[]; list[0] = 1; test = list.length(true);", 1.0},
                {"variable test: NUMBER; variable list: NUMBER[]; list[0] = 1; test = list.length(false);", 1.0},
                {"variable test: NUMBER; variable list: STRING[]; list[0] = 'string'; test = list.length();", 1.0},
                {"variable test: NUMBER; variable list: STRING[]; list[0] = 'man'; test = list.length();", 1.0},
                {"variable test: NUMBER; variable list: STRING[]; list[0] = ''; test = list.length();", 1.0},
                {"variable test: NUMBER; variable list: STRING[]; list[4] = 'man'; test = list.length();", 5.0},
                {"variable test: NUMBER; variable list: STRING[]; list[4] = ''; test = list.length();", 5.0},
                {"variable test: NUMBER; variable list: STRING[]; test = list.length();", 0.0},
                {"variable test: NUMBER; variable list: NUMBER[]; list[0] = 6; test = list.length();", 1.0},
                {"variable test: NUMBER; variable list: NUMBER[]; list[0] = 0; test = list.length();", 1.0},
                {"variable test: NUMBER; variable list: NUMBER[]; list[4] = 6; test = list.length();", 5.0},
                {"variable test: NUMBER; variable list: NUMBER[]; list[4] = 0; test = list.length();", 5.0},
                {"variable test: NUMBER; variable list: NUMBER[]; test = list.length();", 0.0},
                {"variable test: NUMBER; struct STRUCTURE { variable x: NUMBER }; variable list: STRUCTURE[]; list[0].x = 1; test = list.length();", 1.0},
        };
    }
    @Test(dataProvider = "listLengthTestData")
    public void listLength_returnsValue(String code, Number length) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");

        Assert.assertEquals(symbol.get_value(), length, "The symbol list should be the correct length");

    }

    @DataProvider
    public Object[][] listAddTestData_bool() {
        return new Object[][] {
                // TODO: Expand these tests with more cases
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; test = list.add(true);", List.of(true)},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; test = list.add(false);", List.of(false)},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = true; test = list.add(true);", List.of(true, true)},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = false; test = list.add(false);", List.of(false, false)},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = false; list[1] = true; list[2] = true; test = list.add(false);", List.of(false, true, true, false)},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = false; list[1] = false; list[2] = false; test = list.add(true);", List.of(false, false, false, true)},
                {"variable test: BOOLEAN[]; test = test.add(true);", List.of(true)},
        };
    }
    @Test(dataProvider = "listAddTestData_bool")
    public void listAdd_returnsValue_bool(String code, List<Boolean> indexes) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");

        Assert.assertEquals(symbol.get_listLength(), ((Number) indexes.size()).doubleValue(), "The list should be the correct length");

        for(int i = 0; i < indexes.size(); i++){
            Symbol match = symbol.get_index(i);
            Assert.assertNotNull(match, "The symbol should contain a symbol with a value at index " + i);
            Assert.assertEquals(match.get_value(), indexes.get(i), "The index symbol should resolve to the correct value");
        }

    }

    @DataProvider
    public Object[][] listAddTestData_string() {
        return new Object[][] {
                // TODO: Expand these tests with more cases
                {"variable test: STRING[]; variable list: STRING[]; test = list.add('str');", List.of("str")},
                {"variable test: STRING[]; variable list: STRING[]; list[0] = 'str1'; test = list.add('str2');", List.of("str1", "str2")},
                {"variable test: STRING[]; variable list: STRING[]; list[0] = 'str1'; list[1] = 'str2'; list[2] = 'str3'; test = list.add('str4');", List.of("str1", "str2", "str3", "str4")},
                {"variable test: STRING[]; test = test.add('str');", List.of("str")},
        };
    }
    @Test(dataProvider = "listAddTestData_string")
    public void listAdd_returnsValue_string(String code, List<String> indexes) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");

        Assert.assertEquals(symbol.get_listLength(), ((Number) indexes.size()).doubleValue(), "The list should be the correct length");

        for (int i = 0; i < indexes.size(); i++) {
            Symbol match = symbol.get_index(i);
            Assert.assertNotNull(match, "The symbol should contain a symbol with a value at index " + i);
            Assert.assertEquals(match.get_value(), indexes.get(i), "The index symbol should resolve to the correct value");
        }

    }

    @DataProvider
    public Object[][] listAddTestData_number() {
        return new Object[][] {
                // TODO: Expand these tests with more cases
                {"variable test: NUMBER[]; variable list: NUMBER[]; test = list.add(1);", List.of(1.0)},
                {"variable test: NUMBER[]; variable list: NUMBER[]; list[0] = 1; test = list.add(2);", List.of(1.0, 2.0)},
                {"variable test: NUMBER[]; variable list: NUMBER[]; list[0] = 1; list[1] = 2; list[2] = 3; test = list.add(4);", List.of(1.0, 2.0, 3.0, 4.0)},
                {"variable test: NUMBER[]; test = test.add(1);", List.of(1.0)},
                {"variable test: NUMBER[]; test = test.add(1.0);", List.of(1.0)},
        };
    }
    @Test(dataProvider = "listAddTestData_number")
    public void listAdd_returnsValue_number(String code, List<Number> indexes) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");

        Assert.assertEquals(symbol.get_listLength(), ((Number) indexes.size()).doubleValue(), "The list should be the correct length");

        for (int i = 0; i < indexes.size(); i++) {
            Symbol match = symbol.get_index(i);
            Assert.assertNotNull(match, "The symbol should contain a symbol with a value at index " + i);
            Assert.assertEquals(match.get_value(), indexes.get(i), "The index symbol should resolve to the correct value");
        }

    }

    @DataProvider
    public Object[][] listRemoveTestData_bool() {
        return new Object[][] {
                // TODO: Expand these tests with more cases
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = false; list[1] = true; list[2] = true; test = list.remove();", List.of(false, true)},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = false; list[1] = false; list[2] = false; test = list.remove();", List.of(false, false)},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = true; test = list.remove();", List.of()},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = false; test = list.remove();", List.of()},
        };
    }
    @Test(dataProvider = "listRemoveTestData_bool")
    public void listRemove_returnsValue_bool(String code, List<Boolean> indexes) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");

        Assert.assertEquals(symbol.get_listLength(), ((Number) indexes.size()).doubleValue(), "The list should be the correct length");

        for(int i = 0; i < indexes.size(); i++){
            Symbol match = symbol.get_index(i);
            Assert.assertNotNull(match, "The symbol should contain a symbol with a value at index " + i);
            Assert.assertEquals(match.get_value(), indexes.get(i), "The index symbol should resolve to the correct value");
        }

    }

    @DataProvider
    public Object[][] listRemoveTestData_string() {
        return new Object[][] {
                // TODO: Expand these tests with more cases
                {"variable test: STRING[]; variable list: STRING[]; list[0] = 'str1'; list[1] = 'str2'; list[2] = 'str3'; test = list.remove();", List.of("str1", "str2")},
                {"variable test: STRING[]; variable list: STRING[]; list[0] = 'str1'; test = list.remove();", List.of()},
        };
    }
    @Test(dataProvider = "listRemoveTestData_string")
    public void listRemove_returnsValue_string(String code, List<String> indexes) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");

        Assert.assertEquals(symbol.get_listLength(), ((Number) indexes.size()).doubleValue(), "The list should be the correct length");

        for(int i = 0; i < indexes.size(); i++){
            Symbol match = symbol.get_index(i);
            Assert.assertNotNull(match, "The symbol should contain a symbol with a value at index " + i);
            Assert.assertEquals(match.get_value(), indexes.get(i), "The index symbol should resolve to the correct value");
        }

    }

    @DataProvider
    public Object[][] listRemoveTestData_number() {
        return new Object[][] {
                // TODO: Expand these tests with more cases
                {"variable test: NUMBER[]; variable list: NUMBER[]; list[0] = 1; list[1] = 2; list[2] = 3; test = list.remove();", List.of(1.0, 2.0)},
                {"variable test: NUMBER[]; variable list: NUMBER[]; list[0] = 1; test = list.remove();", List.of()},
        };
    }
    @Test(dataProvider = "listRemoveTestData_number")
    public void listRemove_returnsValue_number(String code, List<Number> indexes) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");

        Assert.assertEquals(symbol.get_listLength(), ((Number) indexes.size()).doubleValue(), "The list should be the correct length");

        for(int i = 0; i < indexes.size(); i++){
            Symbol match = symbol.get_index(i);
            Assert.assertNotNull(match, "The symbol should contain a symbol with a value at index " + i);
            Assert.assertEquals(match.get_value(), indexes.get(i), "The index symbol should resolve to the correct value");
        }

    }

    @DataProvider
    public Object[][] listSublistTestData_bool() {
        return new Object[][] {
                // TODO: Expand these tests with more cases
                {"variable test: BOOLEAN[]; variable l: BOOLEAN[]; l[0] = true; l[1] = false; l[2] = true; test = l.sublist(0, 0);", List.of(true)},
                {"variable test: BOOLEAN[]; variable l: BOOLEAN[]; l[0] = true; l[1] = false; l[2] = true; test = l.sublist(1, 1);", List.of(false)},
                {"variable test: BOOLEAN[]; variable l: BOOLEAN[]; l[0] = true; l[1] = false; l[2] = true; test = l.sublist(2, 2);", List.of(true)},
                {"variable test: BOOLEAN[]; variable l: BOOLEAN[]; l[0] = true; l[1] = false; l[2] = true; test = l.sublist(0, 1);", List.of(true, false)},
                {"variable test: BOOLEAN[]; variable l: BOOLEAN[]; l[0] = true; l[1] = false; l[2] = true; test = l.sublist(1, 2);", List.of(false, true)},
                {"variable test: BOOLEAN[]; variable l: BOOLEAN[]; l[0] = true; l[1] = false; l[2] = true; test = l.sublist(0, 2);", List.of(true, false, true)},
                {"variable test: BOOLEAN[]; variable l: BOOLEAN[]; l[0] = true; l[1] = false; l[2] = true; test = l.sublist(1);", List.of(false, true)},
        };
    }
    @Test(dataProvider = "listSublistTestData_bool")
    public void listSublist_returnsValue_bool(String code, List<Boolean> indexes) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");

        Assert.assertEquals(symbol.get_listLength(), ((Number) indexes.size()).doubleValue(), "There should be exactly " + indexes.size() + " matches");

        for(int i = 0; i < indexes.size(); i++){
            Symbol match = symbol.get_index(i);
            Assert.assertNotNull(match, "The symbol should contain a symbol with a match value at index " + i);
            Assert.assertEquals(match.get_value(), indexes.get(i), "The match symbol should resolve to the correct value");
        }

    }

    @DataProvider
    public Object[][] listSublistTestData_number() {
        return new Object[][] {
                // TODO: Expand these tests with more cases
                {"variable test: NUMBER[]; variable l: NUMBER[]; l[0] = 1; l[1] = 2; l[2] = 3; test = l.sublist(0, 0);", List.of(1.0)},
                {"variable test: NUMBER[]; variable l: NUMBER[]; l[0] = 1; l[1] = 2; l[2] = 3; test = l.sublist(1, 1);", List.of(2.0)},
                {"variable test: NUMBER[]; variable l: NUMBER[]; l[0] = 1; l[1] = 2; l[2] = 3; test = l.sublist(2, 2);", List.of(3.0)},
                {"variable test: NUMBER[]; variable l: NUMBER[]; l[0] = 1; l[1] = 2; l[2] = 3; test = l.sublist(0, 1);", List.of(1.0, 2.0)},
                {"variable test: NUMBER[]; variable l: NUMBER[]; l[0] = 1; l[1] = 2; l[2] = 3; test = l.sublist(1, 2);", List.of(2.0, 3.0)},
                {"variable test: NUMBER[]; variable l: NUMBER[]; l[0] = 1; l[1] = 2; l[2] = 3; test = l.sublist(0, 2);", List.of(1.0, 2.0, 3.0)},
                {"variable test: NUMBER[]; variable l: NUMBER[]; l[0] = 1; l[1] = 2; l[2] = 3; test = l.sublist(1);", List.of(2.0, 3.0)},
        };
    }
    @Test(dataProvider = "listSublistTestData_number")
    public void listSublist_returnsValue_number(String code, List<Number> indexes) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");

        Assert.assertEquals(symbol.get_listLength(), ((Number) indexes.size()).doubleValue(), "There should be exactly " + indexes.size() + " matches");

        for(int i = 0; i < indexes.size(); i++){
            Symbol match = symbol.get_index(i);
            Assert.assertNotNull(match, "The symbol should contain a symbol with a match value at index " + i);
            Assert.assertEquals(match.get_value(), indexes.get(i), "The match symbol should resolve to the correct value");
        }

    }

    @DataProvider
    public Object[][] listSublistTestData_string() {
        return new Object[][] {
                // TODO: Expand these tests with more cases
                {"variable test: STRING[]; variable l: STRING[]; l[0] = 'str1'; l[1] = 'str2'; l[2] = 'str3'; test = l.sublist(0, 0);", List.of("str1")},
                {"variable test: STRING[]; variable l: STRING[]; l[0] = 'str1'; l[1] = 'str2'; l[2] = 'str3'; test = l.sublist(1, 1);", List.of("str2")},
                {"variable test: STRING[]; variable l: STRING[]; l[0] = 'str1'; l[1] = 'str2'; l[2] = 'str3'; test = l.sublist(2, 2);", List.of("str3")},
                {"variable test: STRING[]; variable l: STRING[]; l[0] = 'str1'; l[1] = 'str2'; l[2] = 'str3'; test = l.sublist(0, 1);", List.of("str1", "str2")},
                {"variable test: STRING[]; variable l: STRING[]; l[0] = 'str1'; l[1] = 'str2'; l[2] = 'str3'; test = l.sublist(1, 2);", List.of("str2", "str3")},
                {"variable test: STRING[]; variable l: STRING[]; l[0] = 'str1'; l[1] = 'str2'; l[2] = 'str3'; test = l.sublist(0, 2);", List.of("str1", "str2", "str3")},
                {"variable test: STRING[]; variable l: STRING[]; l[0] = 'str1'; l[1] = 'str2'; l[2] = 'str3'; test = l.sublist(1);", List.of("str2", "str3")},
        };
    }
    @Test(dataProvider = "listSublistTestData_string")
    public void listSublist_returnsValue_string(String code, List<String> indexes) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");

        Assert.assertEquals(symbol.get_listLength(), ((Number) indexes.size()).doubleValue(), "There should be exactly " + indexes.size() + " matches");

        for(int i = 0; i < indexes.size(); i++){
            Symbol match = symbol.get_index(i);
            Assert.assertNotNull(match, "The symbol should contain a symbol with a match value at index " + i);
            Assert.assertEquals(match.get_value(), indexes.get(i), "The match symbol should resolve to the correct value");
        }

    }

    @DataProvider
    public Object[][] listIndexesOfTestData() {
        return new Object[][] {
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = true; test = l.indexesOf(true);", List.of(0.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = true; test = l.indexesOf(false);", List.of()},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = false; test = l.indexesOf(true);", List.of()},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = false; test = l.indexesOf(false);", List.of(0.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = true; l[1] = true; test = l.indexesOf(true);", List.of(0.0, 1.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = true; l[1] = true; test = l.indexesOf(false);", List.of()},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = false; l[1] = false; test = l.indexesOf(true);", List.of()},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = false; l[1] = false; test = l.indexesOf(false);", List.of(0.0, 1.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = true; l[1] = false; test = l.indexesOf(true);", List.of(0.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = true; l[1] = false; test = l.indexesOf(false);", List.of(1.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = false; l[1] = true; test = l.indexesOf(true);", List.of(1.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = false; l[1] = true; test = l.indexesOf(false);", List.of(0.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = true; l[1] = true; test = l.indexesOf(true);", List.of(0.0, 1.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = true; l[1] = true; test = l.indexesOf(false);", List.of()},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = false; l[1] = false; test = l.indexesOf(true);", List.of()},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = false; l[1] = false; test = l.indexesOf(false);", List.of(0.0, 1.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[1] = true; test = l.indexesOf(true);", List.of(1.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[1] = true; test = l.indexesOf(false);", List.of()},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[1] = false; test = l.indexesOf(true);", List.of()},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[1] = false; test = l.indexesOf(false);", List.of(1.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[6] = true; test = l.indexesOf(true);", List.of(6.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[6] = true; test = l.indexesOf(false);", List.of()},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[6] = false; test = l.indexesOf(true);", List.of()},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[6] = false; test = l.indexesOf(false);", List.of(6.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = true; l[1] = true; l[3] = true; l[4] = true; test = l.indexesOf(true);", List.of(0.0, 1.0, 3.0, 4.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; l[0] = false; l[1] = false; l[3] = false; l[4] = false; test = l.indexesOf(false);", List.of(0.0, 1.0, 3.0, 4.0)},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; test = l.indexesOf(true);", List.of()},
                {"variable test: NUMBER[]; variable l: BOOLEAN[]; test = l.indexesOf(false);", List.of()},
                {"variable test: NUMBER[]; variable l: STRING[]; l[0] = 'string'; l[1] = 'bossman'; l[2] = 't'; l[3] = ''; test = l.indexesOf('string');", List.of(0.0)},
                {"variable test: NUMBER[]; variable l: STRING[]; l[0] = 'string'; l[1] = 'bossman'; l[2] = 't'; l[3] = ''; test = l.indexesOf('bossman');", List.of(1.0)},
                {"variable test: NUMBER[]; variable l: STRING[]; l[0] = 'string'; l[1] = 'bossman'; l[2] = 't'; l[3] = ''; test = l.indexesOf('t');", List.of(2.0)},
                {"variable test: NUMBER[]; variable l: STRING[]; l[0] = 'string'; l[1] = 'bossman'; l[2] = 't'; l[3] = ''; test = l.indexesOf('');", List.of(3.0)},
                {"variable test: NUMBER[]; variable l: STRING[]; l[7] = ' '; test = l.indexesOf(' ');", List.of(7.0)},
                {"variable test: NUMBER[]; variable l: STRING[]; l[7] = ''; test = l.indexesOf('');", List.of(7.0)},
                {"variable test: NUMBER[]; variable l: STRING[]; test = l.indexesOf('');", List.of()},
                {"variable test: NUMBER[]; variable l: NUMBER[]; l[0] = 0; l[1] = 1; l[2] = 2; test = l.indexesOf(0);", List.of(0.0)},
                {"variable test: NUMBER[]; variable l: NUMBER[]; l[0] = 0; l[1] = 1; l[2] = 2; test = l.indexesOf(1);", List.of(1.0)},
                {"variable test: NUMBER[]; variable l: NUMBER[]; l[0] = 0; l[1] = 1; l[2] = 2; test = l.indexesOf(2);", List.of(2.0)},
                {"variable test: NUMBER[]; variable l: NUMBER[]; l[7] = 0; test = l.indexesOf(0);", List.of(7.0)},
                {"variable test: NUMBER[]; variable l: NUMBER[]; test = l.indexesOf(0);", List.of()},
        };
    }
    @Test(dataProvider = "listIndexesOfTestData")
    public void listIndexesOf_returnsValue(String code, List<Number> indexes) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");

        Assert.assertEquals(symbol.get_listLength(), ((Number) indexes.size()).doubleValue(), "There should be exactly " + indexes.size() + " matches");

        for(int i = 0; i < indexes.size(); i++){
            Symbol match = symbol.get_index(i);
            Assert.assertNotNull(match, "The symbol should contain a symbol with a match value at index " + i);
            Assert.assertEquals(match.get_value(), indexes.get(i), "The match symbol should resolve to the correct value");
        }

    }

    @DataProvider
    public Object[][] listUseTestData_bool() {
        return new Object[][] {
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list[0] = true; test = list[0];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list[7] = true; test = list[7];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list = list.add(true); test = list[0];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list[7] = true; list = list.add(true); test = list[8];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list[6] = false; list[7] = true; list[8] = false; test = list[7];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list[6] = false; list[7] = true; list[8] = false; test = list[7];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[0][0][0] = true; test = list[0][0][0];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[5][4][17] = true; test = list[5][4][17];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[5][4][18] = false; list[5][4][17] = true; list[5][4][18] = false; test = list[5][4][17];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[5][3][17] = false; list[5][4][17] = true; list[5][5][17] = false; test = list[5][4][17];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[4][4][17] = false; list[5][4][17] = true; list[6][4][17] = false; test = list[5][4][17];", true},
        };
    }
    @Test(dataProvider = "listUseTestData_bool")
    public void listUse_setsValue_bool(String code, boolean value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] listUseTestData_string() {
        return new Object[][] {
                {"variable test: STRING; variable list: STRING[]; list[0] = 'man'; test = list[0];", "man"},
                {"variable test: STRING; variable list: STRING[]; list = list.add('man'); test = list[0];", "man"},
                {"variable test: STRING; variable list: STRING[]; list[7] = 'man'; test = list[7];", "man"},
                {"variable test: STRING; variable list: STRING[]; list[7] = 'man'; list = list.add('man'); test = list[8];", "man"},
                {"variable test: STRING; variable list: STRING[]; list[6] = ''; list[7] = 'man'; list[8] = ''; test = list[7];", "man"},
                {"variable test: STRING; variable list: STRING[]; list[6] = ''; list[7] = 'man'; list[8] = ''; test = list[7];", "man"},
                {"variable test: STRING; variable list: STRING[][][]; list[0][0][0] = 'man'; test = list[0][0][0];", "man"},
                {"variable test: STRING; variable list: STRING[][][]; list[5][4][17] = 'man'; test = list[5][4][17];", "man"},
                {"variable test: STRING; variable list: STRING[][][]; list[5][4][18] = ''; list[5][4][17] = 'man'; list[5][4][18] = ''; test = list[5][4][17];", "man"},
                {"variable test: STRING; variable list: STRING[][][]; list[5][3][17] = ''; list[5][4][17] = 'man'; list[5][5][17] = ''; test = list[5][4][17];", "man"},
                {"variable test: STRING; variable list: STRING[][][]; list[4][4][17] = ''; list[5][4][17] = 'man'; list[6][4][17] = ''; test = list[5][4][17];", "man"},
        };
    }
    @Test(dataProvider = "listUseTestData_string")
    public void listUse_setsValue_string(String code, String value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] listUseTestData_number() {
        return new Object[][] {
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list[0] = 3; test = list[0];", 3.0},
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list[7] = 3; test = list[7];", 3.0},
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list = list.add(3); test = list[0];", 3.0},
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list[7] = 3; list = list.add(3); test = list[8];", 3.0},
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list[6] = 0; list[7] = 3; list[8] = 0; test = list[7];", 3.0},
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list[6] = 0; list[7] = 3; list[8] = 0; test = list[7];", 3.0},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[0][0][0] = 3; test = list[0][0][0];", 3.0},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[5][4][17] = 3; test = list[5][4][17];", 3.0},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[5][4][18] = 0; list[5][4][17] = 3; list[5][4][18] = 0; test = list[5][4][17];", 3.0},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[5][3][17] = 0; list[5][4][17] = 3; list[5][5][17] = 0; test = list[5][4][17];", 3.0},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[4][4][17] = 0; list[5][4][17] = 3; list[6][4][17] = 0; test = list[5][4][17];", 3.0},
        };
    }
    @Test(dataProvider = "listUseTestData_number")
    public void listUse_setsValue_number(String code, Number value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] structUseTestData() {
        return new Object[][] {
                {"variable test: BOOLEAN; struct LIGHT { variable on: BOOLEAN }; variable light: LIGHT; light.on = true; test = light.on;", true},
                {"variable test: BOOLEAN; struct LIGHT { variable on: BOOLEAN, variable off: BOOLEAN }; variable light: LIGHT; light.on = true; light.off = false; test = light.on;", true},
                {"variable test: BOOLEAN; struct VALVE { variable on: BOOLEAN, variable stream: VALVE }; variable valve: VALVE; valve.stream.on = true; test = valve.stream.on;", true},
                {"variable test: BOOLEAN; struct LIGHT { variable on: BOOLEAN }; variable light: LIGHT; light.on = true; test = light.on;", true},
                {"variable test: BOOLEAN; struct LIGHT { variable on: BOOLEAN, variable off: BOOLEAN }; variable light: LIGHT; light.on = true; light.off = false; test = light.on;", true},
                {"variable test: BOOLEAN; struct VALVE { variable on: BOOLEAN, variable stream: VALVE }; variable valve: VALVE; valve.on = false; valve.stream.on = true; valve.stream.stream.on = false; test = valve.stream.on;", true},
        };
    }
    @Test(dataProvider = "structUseTestData")
    public void structUse_setsValue(String code, boolean value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");

    }

    @DataProvider
    public Object[][] structAndListUseTestData() {
        return new Object[][] {
                {"variable test: BOOLEAN; struct VALVE { variable on: BOOLEAN, variable streams: VALVE[] }; variable valve: VALVE; valve.streams[0].on = true; test = valve.streams[0].on;", true},
                {"variable test: BOOLEAN; struct VALVE { variable on: BOOLEAN, variable streams: VALVE[] }; variable valve: VALVE; valve.on = false; valve.streams[0].on = false; valve.streams[1].on = true; valve.streams[1].streams[1].on = false; valve.streams[2].on = false; test = valve.streams[1].on;", true},
                {"variable test: BOOLEAN; struct VALVE { variable on: BOOLEAN, variable streams: VALVE[] }; variable valves: VALVE[]; valves[0].on = false; valves[1].on = true; valves[2].on = false; test = valves[1].on;", true},
                {"variable test: BOOLEAN; struct VALVE { variable on: BOOLEAN, variable streamInception: VALVE[][], variable stream: VALVE }; variable valve: VALVE; valve.stream.stream.streamInception[3][2].on = true; test = valve.stream.stream.streamInception[3][2].on;", true},
                {"variable test: BOOLEAN; struct VALVE { variable on: BOOLEAN, variable streamInception: VALVE[][], variable stream: VALVE }; variable valve: VALVE; valve.streamInception[3][2].stream.streamInception[0][0].stream.stream.on = true; test = valve.streamInception[3][2].stream.streamInception[0][0].stream.stream.on;", true},
                {"variable test: BOOLEAN; struct VALVE { variable on: BOOLEAN, variable streamInception: VALVE[][], variable stream: VALVE }; variable valveInception: VALVE[][][]; valveInception[8][2][0].stream.streamInception[8][1].on = true; test = valveInception[8][2][0].stream.streamInception[8][1].on;", true},
        };
    }
    @Test(dataProvider = "structAndListUseTestData")
    public void structAndListUse_setsValue(String code, boolean value) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), value, "Should set the appropriate value on the symbol");
    }

    @DataProvider
    public Object[][] variableScopeTestData() {
        return new Object[][] {
                {"variable test : NUMBER; variable x: NUMBER; x = 10; to f(): NUMBER { return x; } to g(x: NUMBER): NUMBER { return f(); } test = g(20);", 10.0},
                {"variable test : NUMBER; variable x: NUMBER; x = 10; to f(x: NUMBER): NUMBER { return x; } to g(x: NUMBER): NUMBER { return f(x); } test = g(20);", 20.0},
        };
    }
    @Test(dataProvider = "variableScopeTestData")
    public void variableScopeTest_usesStaticScoping_returnsCorrectValue(String code, Number expectedValue) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), expectedValue, "Should set the appropriate value on the symbol");
    }

    @DataProvider
    public Object[][] lateAssignNoAssignTestData_number() {
        return new Object[][] {
                {"variable test: NUMBER; variable other: NUMBER; other = 4; test = other; other = 2;", 4.0},
                {"variable test: NUMBER; test = 4; to run(other: NUMBER): NOTHING { other++; } run(test);", 4.0},
        };
    }
    @Test(dataProvider = "lateAssignNoAssignTestData_number")
    public void lateVarAssign_doesNotAssign_number(String code, Number expectedValue) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), expectedValue, "Should not change the value after assignment");
    }

    @DataProvider
    public Object[][] expNoAssignTestData_number() {
        return new Object[][] {
                {"variable test: NUMBER; variable sink: NUMBER; test = -4; sink = +test;", -4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = -test;", 4.0},
                {"variable test: NUMBER; variable sink: STRING; test = 4; sink = (STRING) test;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = test + 6;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = 6 + test;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = test + test;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = test - 3;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = 3 - test;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = test - test;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = test / 2;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = 2 / test;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = test / test;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = test * 7;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = 7 * test;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = test * test;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = test % 2;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = 2 % test;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 4; sink = test % test;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = test < 1;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = 1 < test;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = test < test;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = test > 1;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = 1 > test;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = test > test;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = test <= 1;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = 1 <= test;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = test <= test;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = test >= 1;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = 1 >= test;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = test >= test;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = test == 1;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = 1 == test;", 4.0},
                {"variable test: NUMBER; variable sink: BOOLEAN; test = 4; sink = test == test;", 4.0},
                {"variable test: NUMBER; variable sink: NUMBER; test = 1; sink = test * 2 / (test + test) + 4;", 1.0},
                {"variable test: NUMBER; test = 10; if(test * 2 < 8){}", 10.0},
                {"variable test: NUMBER; test = 8; repeat(test / 2){}", 8.0},
                {"variable test: NUMBER; variable list: STRING[]; list[2] = 1; test = 1; list[test+1] = list[test+1];", 1.0},
                {"variable test: NUMBER; to noop(n: NUMBER):NOTHING{} test = 9; noop(test - 3);", 9.0},
        };
    }
    @Test(dataProvider = "expNoAssignTestData_number")
    public void exp_doesNotAssign_number(String code, Number expectedValue) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), expectedValue, "Should not change the value after using it in an expression");
    }

    @DataProvider
    public Object[][] expNoAssignTestData_bool() {
        return new Object[][] {
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = true; sink = !test;", true},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = false; sink = !test;", false},
                {"variable test: BOOLEAN; variable sink: STRING; test = true; sink = (STRING) test;", true},
                {"variable test: BOOLEAN; variable sink: STRING; test = false; sink = (STRING) test;", false},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = true; sink = test == false;", true},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = true; sink = false == test;", true},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = false; sink = test == false;", false},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = false; sink = false == test;", false},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = true; sink = test != false;", true},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = true; sink = false != test;", true},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = false; sink = test != false;", false},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = false; sink = false != test;", false},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = true; sink = test or false;", true},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = true; sink = false or test;", true},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = false; sink = test or false;", false},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = false; sink = false or test;", false},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = true; sink = test and false;", true},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = true; sink = false and test;", true},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = false; sink = test and false;", false},
                {"variable test: BOOLEAN; variable sink: BOOLEAN; test = false; sink = false and test;", false},
        };
    }
    @Test(dataProvider = "expNoAssignTestData_bool")
    public void exp_doesNotAssign_bool(String code, boolean expectedValue) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), expectedValue, "Should not change the value after using it in an expression");
    }

    @DataProvider
    public Object[][] expNoAssignTestData_string() {
        return new Object[][] {
                // TODO: Also make these tests for lists
                {"variable test: STRING; variable sink: STRING; test = 'boss'; sink = test.add('man');", "boss"},
                {"variable test: STRING; variable sink: STRING; test = 'boss'; sink = 'man'.add(test);", "boss"},
                {"variable test: STRING; variable sink: STRING; test = 'boss'; sink = test.add(test);", "boss"},
                {"variable test: STRING; variable sink: STRING; test = 'boss'; sink = test.remove();", "boss"},
                {"variable test: STRING; variable sink: STRING; test = 'boss'; sink = test.substring(0, 2);", "boss"},
                {"variable test: STRING; variable sink: STRING; test = 'boss'; sink = test.substring(2, 2);", "boss"},
                {"variable test: STRING; variable sink: STRING; test = 'boss'; sink = test.substring(0, 3);", "boss"},
                {"variable test: STRING; variable sink: STRING; test = 'boss'; sink = test.substring(0, 0);", "boss"},
                {"variable test: STRING; variable sink: NUMBER; test = 'boss'; sink = test.length();", "boss"},
        };
    }
    @Test(dataProvider = "expNoAssignTestData_string")
    public void exp_doesNotAssign_string(String code, String expectedValue) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        SymbolTable symbolTable = new SymbolTable();
        IGameBridge bridgeMock = Mockito.mock(IGameBridge.class);

        concreteNode.accept(new Interpreter(problemCollection, symbolTable, bridgeMock));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Symbol symbol = symbolTable.searchSymbol("test");
        Assert.assertNotNull(symbol, "Should create a symbol and add it to the symbol table");
        Assert.assertEquals(symbol.get_value(), expectedValue, "Should not change the value after using it in an expression");
    }

}
