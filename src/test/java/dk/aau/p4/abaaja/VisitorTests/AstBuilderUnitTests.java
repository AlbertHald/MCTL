package dk.aau.p4.abaaja.VisitorTests;

import dk.aau.p4.abaaja.mctlLexer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// Imports
import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.mctlParser;
import dk.aau.p4.abaaja.Lib.Visitors.AstVisitor;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

// TODO: Test ASTVisitor
@Test()
public class AstBuilderUnitTests {
    private final AstVisitor astVisitor = new AstVisitor(new ProblemCollection());
    private SoftAssert softAssert;

    @BeforeMethod
    public void BeforeMethod() {
        softAssert = new SoftAssert();
    }

    /**
     * createParseTree method to create a parse tree using Antlr and returning it
     * @param code
     * @return Concrete Parse Tree
     */
    private ParseTree createParseTree(String code) {
        // Create token stream based on string of code
        mctlLexer lexer = new mctlLexer(CharStreams.fromString(code));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);

        // Create parse tree based on token stream
        mctlParser parser = new mctlParser(commonTokenStream);
        parser.setBuildParseTree(true);

        return parser.mctl();
    }

    /**
     * Private method createParseTree unit tests
     */
    @DataProvider
    public Object[][] createParseTreeTestData() {
        return new Object[][] {
                {"a = \"a\";"},
                {"a = \"a\"; a = \"a\";"},
                {"a = \"a\"; a = \"a\"; a = \"a\";"},
        };
    }

    @Test(dataProvider = "createParseTreeTestData")
    public void createParseTree_ValidInput_CreatesParseTree(String code) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Assert
        softAssert.assertTrue(parseTree != null);
    }

    /**
     * visitBoolExpr unit test
     */
    @DataProvider
    public Object[][] visitBoolExprTestData() {
        return new Object[][] {
                {"test = true;", true},
                {"test = false;", false},
        };
    }

    @Test(dataProvider = "visitBoolExprTestData")
    public void visitBoolExpr_ValidInput_CreatesCorrectBooleanExpressionNode(String code, boolean booleanValue) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        /* Creates the following tree -> MctlNode - AssStateNode - IDExpNode , BoolExpNode */
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);
        BoolExpNode boolExpNode = (BoolExpNode) assStateNode.get_assignExp();

        // Assert
        softAssert.assertTrue(boolExpNode instanceof BoolExpNode);
        softAssert.assertTrue(boolExpNode.get_result() == booleanValue);
        softAssert.assertAll();
    }


    /**
     * visitBoolExpr unit test
     */
    @DataProvider
    public Object[][] visitStringExprTestData() {
        return new Object[][] {
            {"test = \"This is a String\";", "\"This is a String\""},
            {"test = \"1234 string 8078.,/()&\";", "\"1234 string 8078.,/()&\""},
            {"test = \"\'Double Quoted String\'\";", "\"\'Double Quoted String\'\""},
            {"test = \'Single Quoted String\';", "\'Single Quoted String\'"},
            {"test = \"Newline: \n Carriage return: \r Tab: \t Backslash: \\ \";", "\"Newline: \n Carriage return: \r Tab: \t Backslash: \\ \""},
            {"test = \"Unicode: \u03A9\u03B1\u03C6\u03C9\";", "\"Unicode: \u03A9\u03B1\u03C6\u03C9\""},
        };
    }

    @Test(dataProvider = "visitStringExprTestData")
    public void visitStringExpr_ValidInput_CreatesCorrectStringExpressionNode(String code, String stringValue) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        /* Creates the following tree -> MctlNode - AssStateNode - IDExpNode , StringExpNode */
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);
        StringExpNode stringExpNode = (StringExpNode) assStateNode.get_assignExp();

        // Assert
        softAssert.assertTrue(stringExpNode instanceof StringExpNode);
        softAssert.assertTrue(stringExpNode.get_result().equals(stringValue));
        softAssert.assertAll();
    }

    /**
     * visitIdExpr unit tests
     */
    @DataProvider
    public Object[][] visitIdExprTestData() {
        return new Object[][] {
                {"unitTest = myVar;", "myVar"}, {"unitTest = x;", "x"}, {"unitTest = abc;", "abc"},
                {"unitTest = y;", "y"}, {"unitTest = _foo;", "_foo"}, {"unitTest = i;", "i"},
                {"unitTest = _123;", "_123"}, {"unitTest = j;", "j"}, {"unitTest = Bar;", "Bar"},
        };
    }

    @Test(dataProvider = "visitIdExprTestData")
    public void visitIdExpr_ValidInput_CreatesCorrectIdExpressionNode(String code, String stringValue) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);
        ActualIDExpNode actualIdExpNode = (ActualIDExpNode) assStateNode.get_assignExp();

        // Assert
        softAssert.assertTrue(actualIdExpNode instanceof ActualIDExpNode);
        softAssert.assertTrue(actualIdExpNode.get_id().equals(stringValue));
        softAssert.assertAll();
    }

    /**
     * visitNumberExpr unit tests
     *
     * Only test cases for positive numbers as the NumberExpNode should not contain negative numbers.
     * This information is stored using the unaryExpNode.
     */
    @DataProvider
    public Object[][] visitNumberExprDoubleTestData() {
        return new Object[][]{
                {"test = 0.0;", 0.0},
                {"test = 1.0;", 1.0},
                {"test = 1.0;", 1.0},
                {"test = 1234.56789;", 1234.56789},
                {"test = 9876.54321;", 9876.54321},
        };
    }

    @Test(dataProvider = "visitNumberExprDoubleTestData")
    public void visitNumberExpr_ValidDoubleInput_CreatesCorrectNumberExpressionNode(String code, Double doubleValue) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        /* Creates the following tree -> MctlNode - AssStateNode - IDExpNode , NumberExpNode */
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);
        NumExpNode numExpNode = (NumExpNode) assStateNode.get_assignExp();

        // Assert
        softAssert.assertTrue(numExpNode instanceof NumExpNode);
        softAssert.assertTrue(numExpNode.get_result().equals(doubleValue));
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitNumberExprIntTestData() {
        return new Object[][]{
                {"test = 0;", 0},
                {"test = 1;", 1},
                {"test = 12345;", 12345},
                {"test = 67890;", 67890},
                {"test = 2147483647;", 2147483647},
        };
    }

    @Test(dataProvider = "visitNumberExprIntTestData")
    public void visitNumberExpr_ValidIntegerInput_CreatesCorrectNumberExpressionNode(String code, int intValue) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        /* Creates the following tree -> MctlNode - AssStateNode - IDExpNode , NumberExpNode */
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);
        NumExpNode numExpNode = (NumExpNode) assStateNode.get_assignExp();

        // Assert
        softAssert.assertTrue(numExpNode instanceof NumExpNode);
        softAssert.assertTrue(numExpNode.get_result().equals(intValue));
        softAssert.assertAll();
    }

    /**
     * visitVariableType unit tests
     */
    @DataProvider
    public Object[][] visitVariableTypeTestData() {
        return new Object[][]{
                {"variable test: NUMBER;", 0, "NUMBER"}, {"variable test: NUMBER[];", 1, "NUMBER"}, {"variable test: NUMBER[][];", 2, "NUMBER"},
                {"variable test: STRING;", 0, "STRING"}, {"variable test: STRING[];", 1, "STRING"}, {"variable test: STRING[][];", 2, "STRING"},
                {"variable test: BOOLEAN;", 0, "BOOLEAN"}, {"variable test: BOOLEAN[];", 1, "BOOLEAN"}, {"variable test: BOOLEAN[][];", 2, "BOOLEAN"},
                {"variable test: STRUCTID;", 0, "STRUCTID"}, {"variable test: testStructId[];", 1, "testStructId"}, {"variable test: struct_test_id[][];", 2, "struct_test_id"},
        };
    }

    @Test(dataProvider = "visitVariableTypeTestData")
    public void visitVariableType_ValidTypeInput_CreatesCorrectTypeNode(String code, int arrayDegree, String expectedTypeNode) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        /* Creates the following tree -> MctlNode - VarDecNode - TypeNode */
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        VarDecNode varDecNode = (VarDecNode) mctlNode.get_children().get(0);
        TypeNode typeNode = (TypeNode) varDecNode.get_varDecType();

        // Assert
        softAssert.assertTrue(typeNode.get_type().equals(expectedTypeNode));
        softAssert.assertTrue(typeNode.get_arrayDegree() == arrayDegree);
        softAssert.assertAll();
    }

    /**
     * visitVariableDelcartation unit tests
     */
    @DataProvider
    public Object[][] visitVariableDeclarationTestData() {
        return new Object[][] {
                {"variable identifier1: NUMBER;", "identifier1", "NUMBER", 0},
                {"variable identifier2: STRING[];", "identifier2", "STRING", 1},
                {"variable identifier3: BOOLEAN[][];", "identifier3", "BOOLEAN", 2},
                {"variable identifier4: STRUCTTEST[][][];", "identifier4", "STRUCTTEST", 3}
        };
    }

    @Test(dataProvider = "visitVariableDeclarationTestData")
    public void visitVariableDeclaration_ValidInput_CreatesVariableDeclarationNode(String code, String expectedIdentifier, String expectedType, int arrayDegree) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        /* Creates the following tree -> MctlNode - VarDecNode - TypeNode */
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        VarDecNode varDecNode = (VarDecNode) mctlNode.get_children().get(0);

        // Assert
        softAssert.assertTrue(varDecNode.get_id().equals(expectedIdentifier));
        softAssert.assertTrue(varDecNode.get_varDecType().get_arrayDegree() == arrayDegree);
        softAssert.assertTrue(varDecNode.get_varDecType().get_type().equals(expectedType));
        softAssert.assertAll();
    }

    /**
     * visitFunctionDelcartation unit tests
     */
    @DataProvider
    public Object[][] visitFunctionDeclarationTestData() {
        return new Object[][] {
                {"to _functionId():BOOLEAN { return a; }", "_functionId", "BOOLEAN", 0},
                {"to function1(id1: NUMBER):NUMBER { return a; }", "function1", "NUMBER", 1},
                {"to test_func(id1:NUMBER, id2:NUMBER):STRING { return a; }", "test_func", "STRING", 2},
        };
    }

    @Test(dataProvider = "visitFunctionDeclarationTestData")
    public void visitFunctionDeclaration_ValidInput_CreatesFunctionDeclarationNode(String code, String expectedIdentifier, String expectedReturnType, int expectedFormalParameters) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        FuncDecNode funcDecNode = (FuncDecNode) mctlNode.get_children().get(0);

        // Assert
        softAssert.assertTrue(funcDecNode.get_id().equals(expectedIdentifier));
        softAssert.assertTrue(funcDecNode.get_returnType().get_type().equals(expectedReturnType));
        softAssert.assertTrue(funcDecNode.get_paramList().size() == expectedFormalParameters);
        softAssert.assertAll();
    }

    /**
     * visitStructDeclaration unit tests
     */
    @DataProvider
    public Object[][] visitStructDeclarationTestData() {
        return new Object[][] {
                {"struct STRUCT1 {variable varID : NUMBER,}", "STRUCT1", "varID", "NUMBER"},
                {"struct struct2 {variable variable_ID : STRING,}", "struct2", "variable_ID", "STRING"},
                {"struct coordinate {variable x : NUMBER}", "coordinate", "x", "NUMBER"},
                {"struct x_y_z {variable hasCoordinates : BOOLEAN}", "x_y_z", "hasCoordinates", "BOOLEAN"}
        };
    }

    @Test(dataProvider = "visitStructDeclarationTestData")
    public void visitStructDeclaration_ValidInput_CreatesStructDeclarationNode(String code, String structId, String varId, String varType) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        /* Creates the following tree -> MctlNode - StructDecNode - VarDecNode */
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        StructDecNode structDecNode = (StructDecNode) mctlNode.get_children().get(0);
        VarDecNode varDecNode = structDecNode.get_declarations().get(0);

        // Assert
        softAssert.assertTrue(structDecNode.get_id().equals(structId));
        softAssert.assertTrue(varDecNode.get_varDecType().get_type().equals(varType));
        softAssert.assertTrue(varDecNode.get_id().equals(varId));
        softAssert.assertAll();
    }

    /**
     * visitIfStatement unit tests
     */
    @DataProvider
    public Object[][] visitIfStatementTestData() {
        return new Object[][] {
                // {code, expression list size, block list size}
                {"if (true) { var = a; } else if (true) { var = b; } else { var = c; }", 2, 3},
                {"if (true) { var = a; } else { var = b; }", 1, 2},
                {"if (true) { var = a; }", 1, 1},
                {"if (true) {}", 1, 1},
        };
    }

    @Test(dataProvider = "visitIfStatementTestData")
    public void visitIfStatement_ValidInput_CreatesIfStatementNode(String code, int expressionListSize, int blockListSize) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        IfStateNode ifStateNode = (IfStateNode) mctlNode.get_children().get(0);

        // Assert
        softAssert.assertTrue(ifStateNode.get_expChildren().size() == expressionListSize);
        softAssert.assertTrue(ifStateNode.get_blockChildrenNode().size() == blockListSize);
        softAssert.assertAll();
    }

    /**
     * visitRepeatStatement unit tests
     */
    @DataProvider
    public Object[][] visitRepeatStatementTestData() {
        return new Object[][] {
                // {code, expression type, block list size}
                {"repeat (true and true) { a++; }"},
                {"repeat (1+1 == 2 and 3>2) { a++; }"},
                {"repeat (testID and testID2) { a++; }"},
        };
    }

    @Test(dataProvider = "visitRepeatStatementTestData")
    public void visitRepeatStatement_ValidInput_CreatesRepeatStatementNode(String code) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        RepeatStateNode repeatStateNode = (RepeatStateNode) mctlNode.get_children().get(0);


        // Assert
        softAssert.assertTrue(repeatStateNode.get_repeatExp() instanceof AndExpNode);
        softAssert.assertTrue(repeatStateNode.get_expBlock().get_children().size() == 1);
        softAssert.assertTrue(repeatStateNode.get_expBlock().get_children().get(0) instanceof AssStateNode);
        softAssert.assertAll();
    }

    /**
     * visitAssignmentStatement unit tests
     */
    @DataProvider
    public Object[][] visitExprAssTestData() {
        return new Object[][] {
                // {code, expression type, block list size}
                {"a.b = a + 1;"},
                {"a.b.c = a + a;"},
                {"a[b] = a + 123456789;"},
                {"a[b][c] = a + a;"},
        };
    }

    @Test(dataProvider = "visitExprAssTestData")
    public void visitExprAss_ValidInput_CreatesAssStateNode(String code) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);

        // Assert
        softAssert.assertTrue(assStateNode.get_assignId() instanceof IDExpNode);
        softAssert.assertTrue(assStateNode.get_assignExp() instanceof AddExpNode);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitIncrAssTestData() {
        return new Object[][] {
                // {code, expression type, block list size}
                {"a++;", mctlParser.PLUS}, {"a--;", mctlParser.MINUS},
                {"a.b++;", mctlParser.PLUS}, {"a.b--;", mctlParser.MINUS},
                {"a.b.c++;", mctlParser.PLUS}, {"a.b.c--;", mctlParser.MINUS},
                {"a[b]++;", mctlParser.PLUS}, {"a[b]--;", mctlParser.MINUS},
                {"a[b][c]++;", mctlParser.PLUS}, {"a[b][c]--;", mctlParser.MINUS},
        };
    }

    @Test(dataProvider = "visitIncrAssTestData")
    public void visitIncrAss_ValidInput_CreatesAssStateNode(String code, int operator) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);

        // Assert
        softAssert.assertTrue(assStateNode.get_assignId() instanceof IDExpNode);
        softAssert.assertTrue(assStateNode.get_assignExp() instanceof AddExpNode);
        softAssert.assertTrue(((AddExpNode) assStateNode.get_assignExp()).get_operator() == operator);
        softAssert.assertTrue(((AddExpNode) assStateNode.get_assignExp()).get_children().get(0) instanceof IDExpNode);
        softAssert.assertTrue(((AddExpNode) assStateNode.get_assignExp()).get_children().get(1) instanceof NumExpNode);
        softAssert.assertAll();
    }

    /**
     * visitStopStatement unit tests
     */
    @Test()
    public void visitStopStatement_ValidInput_CreatesStopNode() {
        // Arrange
        ParseTree parseTree = createParseTree("stop;");

        // Act
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        StopNode stopNode = (StopNode) mctlNode.get_children().get(0);

        // Assert
        softAssert.assertTrue(stopNode instanceof StopNode);
        softAssert.assertAll();
    }

    /**
     * visitReturnStatement unit tests
     */
    @DataProvider
    public Object[][] visitReturnStatementTestData() {
        return new Object[][] {
                // {code, expression type, block list size}
                {"return a;"},
                {"return a[0];"},
        };
    }

    @Test(dataProvider = "visitReturnStatementTestData")
    public void visitReturnStatement_ValidInput_CreatesReturnNode(String code) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        ReturnNode returnNode = (ReturnNode) mctlNode.get_children().get(0);

        // Assert
        softAssert.assertTrue(returnNode.get_returnExp() instanceof IDExpNode, "The expresion is not of the expect type.");
        softAssert.assertAll();
    }

    /**
     * visitFormalParameter unit tests
     */
    @DataProvider
    public Object[][] visitFormalParameterTestData() {
        return new Object[][] {
                {"to func(id0: NUMBER):NUMBER {}", "id0", "NUMBER"},
                {"to func(id1: STRING):NUMBER {}", "id1", "STRING"},
                {"to func(id2: BOOLEAN):NUMBER {}", "id2", "BOOLEAN"},
        };
    }

    @Test(dataProvider = "visitFormalParameterTestData")
    public void visitFormalParameter_ValidInput_CreatesFormalParamNode(String code, String expectedId, String expectedType) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        FuncDecNode funcDecNode = (FuncDecNode) mctlNode.get_children().get(0);

        // Assert
        softAssert.assertTrue(funcDecNode.get_paramList().get(0).get_id().equals(expectedId), "The ID's does not match.");
        softAssert.assertTrue(funcDecNode.get_paramList().get(0).get_type().get_type().equals(expectedType), "The types does not match.");
        softAssert.assertAll();
    }


    /**
     * visitIdVar unit tests
     */
    @DataProvider
    public Object[][] visitIdVarTestData() {
        return new Object[][] {
                {"var = 5;", "var"},
                {"alsoVar = 8;", "alsoVar"},
                {"anotherVar = \"Text!\";", "anotherVar"}
        };
    }

    @Test(dataProvider = "visitIdVarTestData")
    public void visitIdVar_ValidInput_CreatesActualIDExpNodeWithCorrectID(String code, String id) {
        ParseTree parseTree = createParseTree(code);

        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);
        ActualIDExpNode actualIDExpNode = (ActualIDExpNode) assStateNode.get_assignId();

        softAssert.assertTrue(actualIDExpNode.get_id().equals(id), "The ID's does not match");
        softAssert.assertAll();
    }

    /**
     * visitReturnTypeNothing unit tests
     */
    @DataProvider
    public Object[][] visitReturnTypeNothingTestData() {
        return new Object[][] {
                {"to test() : NOTHING {}", "NOTHING"},
                {"to test2() : NOTHING {variable x: NUMBER; x = 5;}", "NOTHING"}
        };
    }

    @Test(dataProvider = "visitReturnTypeNothingTestData")
    public void visitReturnTypeNothing_ValidInput_CreatesNothingTypeNode(String code, String type) {
        ParseTree parseTree = createParseTree(code);

        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        FuncDecNode funcDecNode = (FuncDecNode) mctlNode.get_children().get(0);

        softAssert.assertTrue(funcDecNode.get_returnType().get_type().equals(type), "Return Type");
        softAssert.assertAll();
    }

    /**
     * visitMulExpr unit tests
     */
    @DataProvider
    public Object[][] visitMulExprTestData() {
        return new Object[][] {
                {"test = 2 * 3;", "*"},
                {"test = 10 / 3;", "/"},
                {"test = 121 % 11;", "%"}
        };
    }

    @Test(dataProvider = "visitMulExprTestData")
    public void visitMulExpr_ValidInput_CreatesCorrectMultiplicationExpressionNode(String code, String operator) {
        ParseTree parseTree = createParseTree(code);

        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);
        MulExpNode mulExpNode = (MulExpNode) assStateNode.get_assignExp();

        softAssert.assertTrue(mulExpNode.get_operatorLiteral().equals(operator), "Operator");
        softAssert.assertTrue(mulExpNode.get_children().size() == 2, "Children Size");
        softAssert.assertAll();
    }

    /**
     * visitUnaryExpr unit tests
     */
    @DataProvider
    public Object[][] visitUnaryExprTestData() {
        return new Object[][] {
                {"test = +3;", "+"},
                {"test = -8;", "-"},
                {"test = !test;", "!"}
        };
    }

    @Test(dataProvider = "visitUnaryExprTestData")
    public void visitUnaryExpr_ValidInput_CreatesCorrectUnaryExpressionNode(String code, String operator) {
        ParseTree parseTree = createParseTree(code);

        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);
        UnaryExpNode unaryExpNode = (UnaryExpNode) assStateNode.get_assignExp();

        softAssert.assertTrue(unaryExpNode.get_operatorLiteral().equals(operator), "Operator");
        softAssert.assertTrue(unaryExpNode.get_children().size() == 1, "Children Size");
        softAssert.assertAll();
    }

    /**
     * visitEqualExpr unit tests
     */
    @DataProvider
    public Object[][] visitEqualExprTestData() {
        return new Object[][] {
                {"var = (test == 3);", "=="},
                {"var = (test != 8);", "!="},
        };
    }

    @Test(dataProvider = "visitEqualExprTestData")
    public void visitEqualExpr_ValidInput_CreatesCorrectEqualExpressionNode(String code, String operator) {
        ParseTree parseTree = createParseTree(code);

        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);
        EqualExpNode equalExpNode = (EqualExpNode) assStateNode.get_assignExp();

        softAssert.assertTrue(equalExpNode.get_operatorLiteral().equals(operator), "Operator");
        softAssert.assertTrue(equalExpNode.get_children().size() == 2, "Children Size");
        softAssert.assertAll();
    }

    /**
     * visitBlock unit tests
     */
    @DataProvider
    public Object[][] visitBlockTestData() {
        return new Object[][] {
                {"if(true){}", 0},
                {"if(true){id=val;id=val;}", 2},
                {"if(true){id=val;id=val;id=val;}", 3},
                {"if(true){id=val;id=val;id=val;id=val;}", 4},
        };
    }

    @Test(dataProvider = "visitBlockTestData")
    public void visitBlock_ValidInput_CreatesCorrectBlockNode(String code, int lines) {
        ParseTree parseTree = createParseTree(code);

        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        IfStateNode ifStateNode = (IfStateNode) mctlNode.get_children().get(0);

        softAssert.assertTrue(ifStateNode.get_blockChildrenNode().get(0).get_children().size() == lines, "Number of lines in block does not match the expected.");
        softAssert.assertAll();
    }
    
    /**
     * visitTypecast unit tests
     */
    @DataProvider
    public Object[][] visitTypecastTestData() {
        return new Object[][] {
                {"test = (NUMBER) var;", "NUMBER"},
                {"test = (STRING) var;", "STRING"},
                {"test = (BOOLEAN) var;", "BOOLEAN"}
        };
    }

    @Test(dataProvider = "visitTypecastTestData")
    public void visitTypecast_ValidInput_CreatesCorrectTypecastExpressionNode(String code, String type) {
        ParseTree parseTree = createParseTree(code);

        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);
        TypecastExpNode typecastExpNode = (TypecastExpNode) assStateNode.get_assignExp();

        softAssert.assertTrue(typecastExpNode.get_typeNode().get_type().equals(type));
        softAssert.assertAll();
    }
}
