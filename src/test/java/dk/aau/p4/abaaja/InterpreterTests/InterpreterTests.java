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

    }

    @DataProvider
    public Object[][] varAssTestData_number() {
        return new Object[][] {
                {"variable test: NUMBER; test = 1;", 1.0},
                {"variable test: NUMBER; test = 10.5;", 10.5},
                {"variable test: NUMBER; test = 1.3789;", 1.3789},
                {"variable test: NUMBER; test = 0;", 0.0},
                {"variable test: NUMBER; test = 9435;", 9435.0},
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

        Symbol symbol_one = symbol.get_field("o");
        Assert.assertNotNull(symbol_one, "Should create a symbol for 'o' and add it to the symbol fields");
        Assert.assertEquals(symbol_one.get_value(), value_one, "Should set the appropriate value on symbol 'o'");

        Symbol symbol_two = symbol.get_field("t");
        Assert.assertNotNull(symbol_two, "Should create a symbol for 't' and add it to the symbol fields");
        Assert.assertEquals(symbol_two.get_value(), value_two, "Should set the appropriate value on symbol 't'");

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

        Symbol nested_0s = symbol.get_field("s");
        Assert.assertNotNull(nested_0s, "Should create a nested symbol for 's' and add it to the symbol fields");

        Symbol nested_1s = nested_0s.get_field("s");
        Assert.assertNotNull(nested_1s, "Should create a nested symbol for 's' and add it to the nested 's' symbol fields");

        Symbol symbol_one = nested_1s.get_field("o");
        Assert.assertNotNull(symbol_one, "Should create a symbol for 'o' and add it to the nested 's' symbol fields");
        Assert.assertEquals(symbol_one.get_value(), value_one, "Should set the appropriate value on symbol 'o'");

        Symbol symbol_two = nested_1s.get_field("t");
        Assert.assertNotNull(symbol_two, "Should create a symbol for 't' and add it to the nested 's' symbol fields");
        Assert.assertEquals(symbol_two.get_value(), value_two, "Should set the appropriate value on symbol 't'");

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
                {"variable test: NUMBER; test = 1 + 1;", 2.0},
                {"variable test: NUMBER; test = 37 + 46;", 83.0},
                {"variable test: NUMBER; test = 0 + 0;", 0.0},
                {"variable test: NUMBER; test = 1 + 0;", 1.0},
                {"variable test: NUMBER; test = 0 + 1;", 1.0},
                {"variable test: NUMBER; test = 0.5 + 0.5;", 1.0},
                {"variable test: NUMBER; test = 0.3 + 1.7;", 2.0},
                {"variable test: NUMBER; test = 2 - 1;", 1.0},
                {"variable test: NUMBER; test = 46 - 37;", 9.0},
                {"variable test: NUMBER; test = 1 - 1;", 0.0},
                {"variable test: NUMBER; test = 1 - 0;", 1.0},
                {"variable test: NUMBER; test = 0 - 1;", -1.0},
                {"variable test: NUMBER; test = 0.5 - 0.5;", 0.0},
                {"variable test: NUMBER; test = 0.3 - 1.7;", -1.4},
                {"variable test: NUMBER; test = 2 / 2;", 1.0},
                {"variable test: NUMBER; test = 0 / 1;", 0.0},
                {"variable test: NUMBER; test = 1 / 1;", 1.0},
                {"variable test: NUMBER; test = 48 / 12;", 4.0},
                {"variable test: NUMBER; test = 1 * 1;", 1.0},
                {"variable test: NUMBER; test = 34 * 27;", 918.0},
                {"variable test: NUMBER; test = 1 * 0;", 0.0},
                {"variable test: NUMBER; test = 0 * 1;", 0.0},
                {"variable test: NUMBER; test = 0 * 0;", 0.0},
                {"variable test: NUMBER; test = 4 % 3;", 1.0},
                {"variable test: NUMBER; test = 4 % 4;", 0.0},
                {"variable test: NUMBER; test = 4 % 1;", 0.0},
                {"variable test: NUMBER; test = 0 % 1;", 0.0},
                {"variable test: NUMBER; test = 0 % 5;", 0.0},
                {"variable test: NUMBER; test = 34 % 27;", 7.0},
                {"variable test: NUMBER; test = 27 % 34;", 27.0},
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
    public Object[][] invokeTestData() {
        return new Object[][] {
                {"variable test: BOOLEAN; to run(): BOOLEAN { return true; } test = run();", true},
                {"variable test: BOOLEAN; to run(): BOOLEAN { return false; } test = run();", false},
                {"variable test: BOOLEAN; to run(condition: BOOLEAN): BOOLEAN { return condition; } test = run(true);", true},
                {"variable test: BOOLEAN; to run(condition: BOOLEAN): BOOLEAN { return condition; } test = run(false);", false},
                {"variable test: BOOLEAN; to run(left: BOOLEAN, right: BOOLEAN): BOOLEAN { return left and right; } test = run(true, true);", true},
                {"variable test: BOOLEAN; to run(left: BOOLEAN, right: BOOLEAN): BOOLEAN { return left and right; } test = run(true, false);", false},
                {"variable test: BOOLEAN; to run(left: BOOLEAN, right: BOOLEAN): BOOLEAN { return left and right; } test = run(false, true);", false},
        };
    }
    @Test(dataProvider = "invokeTestData")
    public void invoke_returnsValue(String code, boolean value) {
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
    public Object[][] listAddTestData() {
        return new Object[][] {
                // TODO: Expand these tests with more cases and more types
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; test = list.add(true);", List.of(true)},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; test = list.add(false);", List.of(false)},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = true; test = list.add(true);", List.of(true, true)},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = false; test = list.add(false);", List.of(false, false)},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = false; list[1] = true; list[2] = true; test = list.add(false);", List.of(false, true, true, false)},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = false; list[1] = false; list[2] = false; test = list.add(true);", List.of(false, false, false, true)},
        };
    }
    @Test(dataProvider = "listAddTestData")
    public void listAdd_returnsValue(String code, List<Boolean> indexes) {
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
    public Object[][] listRemoveTestData() {
        return new Object[][] {
                // TODO: Expand these tests with more cases and more types
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = false; list[1] = true; list[2] = true; test = list.remove();", List.of(false, true)},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = false; list[1] = false; list[2] = false; test = list.remove();", List.of(false, false)},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = true; test = list.remove();", List.of()},
                {"variable test: BOOLEAN[]; variable list: BOOLEAN[]; list[0] = false; test = list.remove();", List.of()},
        };
    }
    @Test(dataProvider = "listRemoveTestData")
    public void listRemove_returnsValue(String code, List<Boolean> indexes) {
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
                // TODO: Expand these tests with more cases and more types
                {"variable test: BOOLEAN[]; variable l: BOOLEAN[]; l[0] = true; l[1] = true; l[2] = true; test = l.sublist(0, 0);", List.of(true)},
                {"variable test: BOOLEAN[]; variable l: BOOLEAN[]; l[0] = true; l[1] = true; l[2] = true; test = l.sublist(1, 1);", List.of(true)},
                {"variable test: BOOLEAN[]; variable l: BOOLEAN[]; l[0] = true; l[1] = true; l[2] = true; test = l.sublist(2, 2);", List.of(true)},
                {"variable test: BOOLEAN[]; variable l: BOOLEAN[]; l[0] = true; l[1] = true; l[2] = true; test = l.sublist(0, 1);", List.of(true, true)},
                {"variable test: BOOLEAN[]; variable l: BOOLEAN[]; l[0] = true; l[1] = true; l[2] = true; test = l.sublist(1, 2);", List.of(true, true)},
                {"variable test: BOOLEAN[]; variable l: BOOLEAN[]; l[0] = true; l[1] = true; l[2] = true; test = l.sublist(0, 2);", List.of(true, true, true)},
        };
    }
    @Test(dataProvider = "listSublistTestData_bool")
    public void listSublist_returnsValue_bool(String code, List<Number> indexes) {
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
    public Object[][] listUseTestData() {
        return new Object[][] {
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list[0] = true; test = list[0];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list[7] = true; test = list[7];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list[6] = false; list[7] = true; list[8] = false; test = list[7];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[]; list[6] = false; list[7] = true; list[8] = false; test = list[7];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[0][0][0] = true; test = list[0][0][0];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[5][4][17] = true; test = list[5][4][17];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[5][4][18] = false; list[5][4][17] = true; list[5][4][18] = false; test = list[5][4][17];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[5][3][17] = false; list[5][4][17] = true; list[5][5][17] = false; test = list[5][4][17];", true},
                {"variable test: BOOLEAN; variable list: BOOLEAN[][][]; list[4][4][17] = false; list[5][4][17] = true; list[6][4][17] = false; test = list[5][4][17];", true},
        };
    }
    @Test(dataProvider = "listUseTestData")
    public void listUse_setsValue(String code, boolean value) {
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
                {"variable test: BOOLEAN; struct VALVE { variable on: BOOLEAN, variable streamInception: VALVE[][], variable stream: VALVE }; variable valveInception: VALVE[][][]; valveInception[8][2][0].stream.streamInception[8][1] = true; test = valveInception[8][2][0].stream.streamInception[8][1];", true},
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

}
