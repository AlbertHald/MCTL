package dk.aau.p4.abaaja.VisitorTests;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
     * visitBoolExpr unit test
     */
    @Test()
    public void visitBoolExpr_ValidInput_CreatesCorrectBooleanExpressionNode() {
        // Arrange
        int lineNumber = 10;
        Boolean booleanValue = true;

        /* Create mocks */
        mctlParser.BoolExprContext boolExprContext = mock(mctlParser.BoolExprContext.class);
        Token start = mock(Token.class);

        when(boolExprContext.getText()).thenReturn(booleanValue.toString());
        when(boolExprContext.getStart()).thenReturn(start);
        when(start.getLine()).thenReturn(lineNumber);


        // Act
        BoolExpNode boolExpNode = (BoolExpNode) astVisitor.visitBoolExpr(boolExprContext);
        boolean result = (boolExpNode.get_result() == booleanValue) && (boolExpNode.get_lineNumber() == lineNumber);

        // Assert
        Assert.assertTrue(result);
    }

    /**
     * visitStringExpr unit tests
     */
    @Test()
    public void visitStringExpr_ValidInput_CreatesCorrectStringExpressionNode() {
        // Arrange
        int lineNumber = 10;
        String stringValue = "unit test";

        /* Create mocks */
        mctlParser.StringExprContext stringExprContext = mock(mctlParser.StringExprContext.class);
        Token start = mock(Token.class);

        when(stringExprContext.getText()).thenReturn(stringValue);
        when(stringExprContext.getStart()).thenReturn(start);
        when(start.getLine()).thenReturn(lineNumber);


        // Act
        StringExpNode stringExpNode = (StringExpNode) astVisitor.visitStringExpr(stringExprContext);
        boolean result = (stringExpNode.get_result() == stringValue) && (stringExpNode.get_lineNumber() == lineNumber);

        // Assert
        Assert.assertTrue(result);
    }

    /**
     * visitIdExpr unit tests
     */
    @DataProvider
    public Object[][] visitIdExprTestData() {
        return new Object[][] {
                {"myVar", 1}, {"x", 20}, {"abc", 3},
                {"y", 4}, {"_foo", 500}, {"i", 60},
                {"_123", 7}, {"j", 8000}, {"Bar", 9}
        };
    }

    @Test(dataProvider = "visitIdExprTestData")
    public void visitIdExpr_ValidInput_CreatesCorrectIdExpressionNode(String idValue, int lineNumber) {
        // Arrange - Create mocks
        mctlParser.IdExprContext idExprContext = mock(mctlParser.IdExprContext.class);
        Token start = mock(Token.class);

        when(idExprContext.getText()).thenReturn(idValue);
        when(idExprContext.getStart()).thenReturn(start);
        when(start.getLine()).thenReturn(lineNumber);

        // Act
        IDExpNode stringExpNode = (IDExpNode) astVisitor.visitIdExpr(idExprContext);
        boolean result = (stringExpNode.get_ID() == idValue) && (stringExpNode.get_lineNumber() == lineNumber);

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
                {Double.parseDouble("1.2"), 1200}, {Double.parseDouble("0.0"), 1200},
                {Double.parseDouble("15.3"), 1200}, {Double.parseDouble("10.7"), 25},
                {Double.parseDouble("175.25"), 500}, {Double.parseDouble("1000.55"), 0},
                {Double.parseDouble("5002.77"), 100}, {Double.parseDouble("10000.53"), 900},
                {Double.parseDouble("12525.35"), 900}, {Double.parseDouble("1508.23"), 700},
                {Double.parseDouble("20003.978"), 1100}
        };
    }

    @Test(dataProvider = "visitNumberExprDoubleTestData")
    public void visitNumberExpr_ValidDoubleInput_CreatesCorrectNumberExpressionNode(Number numberValue, int lineNumber) {
        // Arrange - Create mocks
        mctlParser.NumberExprContext numberExprContext = mock(mctlParser.NumberExprContext.class);
        Token start = mock(Token.class);

        when(numberExprContext.getText()).thenReturn(numberValue.toString());
        when(numberExprContext.getStart()).thenReturn(start);
        when(start.getLine()).thenReturn(lineNumber);

        // Act
        NumExpNode numExpNode = (NumExpNode) astVisitor.visitNumberExpr(numberExprContext);
        boolean result = (numExpNode.get_result().doubleValue() == numberValue.doubleValue()) &&
                (numExpNode.get_lineNumber() == lineNumber);

        // Assert
        Assert.assertTrue(result);
    }

    @DataProvider
    public Object[][] visitNumberExprIntTestData() {
        return new Object[][]{
                {Integer.parseInt("1"), 1200}, {Integer.parseInt("0"), 1200},
                {Integer.parseInt("15"), 1200}, {Integer.parseInt("10"), 25},
                {Integer.parseInt("175"), 500}, {Integer.parseInt("1000"), 0},
                {Integer.parseInt("5002"), 100}, {Integer.parseInt("10000"), 900},
                {Integer.parseInt("12525"), 900}, {Integer.parseInt("1508"), 700},
                {Integer.parseInt("20003"), 1100}
        };
    }

    @Test(dataProvider = "visitNumberExprIntTestData")
    public void visitNumberExpr_ValidIntegerInput_CreatesCorrectNumberExpressionNode(Number numberValue, int lineNumber) {
        // Arrange - Create mocks
        mctlParser.NumberExprContext numberExprContext = mock(mctlParser.NumberExprContext.class);
        Token start = mock(Token.class);

        when(numberExprContext.getText()).thenReturn(numberValue.toString());
        when(numberExprContext.getStart()).thenReturn(start);
        when(start.getLine()).thenReturn(lineNumber);

        // Act
        NumExpNode numExpNode = (NumExpNode) astVisitor.visitNumberExpr(numberExprContext);
        boolean result = (numExpNode.get_result().intValue() == numberValue.intValue()) &&
                (numExpNode.get_lineNumber() == lineNumber);

        // Assert
        Assert.assertTrue(result);
    }

    /**
     * visitVariableType unit tests
     */
    @DataProvider
    public Object[][] visitVariableTypeTestData() {
        return new Object[][]{
                {"NUMBER[]", 0, 0}, {"NUMBER[]", 10, 1}, {"NUMBER[][]", 100, 2},
                {"STRING[]", 10, 0}, {"STRING[]", 110, 1}, {"STRING[][]", 1100, 2},
                {"BOOLEAN[]", 10, 0}, {"BOOLEAN[]", 110, 1}, {"BOOLEAN[][]", 1100, 2},
                {"STRUCTID[]", 10, 0}, {"testStructId[]", 110, 1}, {"struct_test_id[][]", 1100, 2},
        };
    }

    // TODO: Possibly implement object type checking?
    @Test(dataProvider = "visitVariableTypeTestData")
    public void visitVariableType_ValidTypeInput_CreatesCorrectTypeNode(String variableTypeValue, int lineNumber, int arrayDegree) {
        // Arrange - Create mocks
        mctlParser.VariableTypeContext variableTypeContext = mock(mctlParser.VariableTypeContext.class);
        mctlParser.BaseVariableTypeContext baseVariableTypeContext = mock(mctlParser.BaseVariableTypeContext.class);
        Token start = mock(Token.class);

        /* Mock the list og left square terminal nodes */
        List<TerminalNode> terminalNodeList = new ArrayList<TerminalNode>();
        for (int i = 0; i < arrayDegree; i++) {
            terminalNodeList.add(mock(TerminalNode.class));
        }

        /* Mockito when() methods */
        when(baseVariableTypeContext.getText()).thenReturn(variableTypeValue);

        when(variableTypeContext.baseVariableType()).thenReturn(baseVariableTypeContext);
        when(variableTypeContext.getStart()).thenReturn(start);
        when(start.getLine()).thenReturn(lineNumber);

        when(variableTypeContext.LSQR()).thenReturn(terminalNodeList);

        // Act
        TypeNode variableTypeNode = (TypeNode) astVisitor.visitVariableType(variableTypeContext);
        boolean result = (variableTypeNode.get_type() == variableTypeValue) &&
                (variableTypeNode.get_lineNumber() == lineNumber) &&
                (variableTypeNode.get_arrayDegree() == arrayDegree);

        // Assert
        Assert.assertTrue(result);
    }
}
