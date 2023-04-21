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

import java.util.ArrayList;
import java.util.List;

// TODO: Test ASTVisitor
@Test()
public class AstBuilderUnitTests {
    private final AstVisitor astVisitor = new AstVisitor(new ProblemCollection());

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

        // Act
        boolean result = (parseTree != null);

        // Assert
        Assert.assertTrue(result);
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

        boolean result =
                (boolExpNode instanceof BoolExpNode) &&
                (boolExpNode.get_result() == booleanValue);

        // Assert
        Assert.assertTrue(result);
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

        boolean result = (stringExpNode instanceof StringExpNode) && (stringExpNode.get_result().equals(stringValue));

        // Assert
        Assert.assertTrue(result);
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
        /* Creates the following tree -> MctlNode - AssStateNode - IDExpNode , IDExpNode */
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);
        IDExpNode idExpNode = (IDExpNode) assStateNode.get_assignExp();

        boolean result = (idExpNode instanceof IDExpNode) && (idExpNode.get_ID().equals(stringValue));

        // Assert
        Assert.assertTrue(result);
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

        boolean result = (numExpNode instanceof NumExpNode) && (numExpNode.get_result().equals(doubleValue));

        // Assert
        Assert.assertTrue(result);
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

        boolean result = (numExpNode instanceof NumExpNode) && (numExpNode.get_result().equals(intValue));

        // Assert
        Assert.assertTrue(result);
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

        boolean result = (typeNode.get_type().equals(expectedTypeNode)) && (typeNode.get_arrayDegree() == arrayDegree);

        // Assert
        Assert.assertTrue(result);
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

        boolean result = (varDecNode.get_id().equals(expectedIdentifier)) &&
                (varDecNode.get_varDecType().get_arrayDegree() == arrayDegree) &&
                (varDecNode.get_varDecType().get_type().equals(expectedType));

        // Assert
        Assert.assertTrue(result);
    }

    /**
     * visitStructDeclaration unit tests
     */
    @DataProvider
    public Object[][] visitStructDeclarationIntegerTestData() {
        return new Object[][] {
                {"struct STRUCT1 {variable varID : NUMBER,}", "STRUCT1", "varID", "NUMBER"},
                {"struct struct2 {variable variable_ID : STRING,}", "struct2", "variable_ID", "STRING"},
                {"struct coordinate {variable x : NUMBER}", "coordinate", "x", "NUMBER"},
                {"struct x_y_z {variable hasCoordinates : BOOLEAN}", "x_y_z", "hasCoordinates", "BOOLEAN"}
        };
    }

    @Test(dataProvider = "visitStructDeclarationIntegerTestData")
    public void visitStructDeclaration_ValidInput_CreatesStructDeclarationNode(String code, String structId, String varId, String varType) {
        // Arrange
        ParseTree parseTree = createParseTree(code);

        // Act
        /* Creates the following tree -> MctlNode - StructDecNode - VarDecNode */
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        StructDecNode structDecNode = (StructDecNode) mctlNode.get_children().get(0);
        VarDecNode varDecNode = structDecNode.get_declarations().get(0);

        boolean result = (structDecNode.get_id().equals(structId)) &&
                (varDecNode.get_varDecType().get_type().equals(varType)) &&
                (varDecNode.get_id().equals(varId));

        // Assert
        Assert.assertTrue(result);
    }

}
