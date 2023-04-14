package dk.aau.p4.abaaja.VisitorTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// Imports
import org.antlr.v4.runtime.ParserRuleContext;
import dk.aau.p4.abaaja.mctlParser;

@Test()
public class AstBuilderUnitTests {
    @DataProvider
    public Object[][] data() {
        return new String[][] {new String[] {"data1"}, new String[] {"data2"}};
    }

    @Test(dataProvider = "data")
    public void test(String d) {
        Assert.assertEquals("First Line\nSecond Line", "First Line\nSecond Line");
    }

    // TODO: Test Antlr Lexer
    // TODO: Test Antlr Parser
    // TODO: Test ASTVisitor

    /**
     * visitBoolExpr unit tests
     */
    @Test()
    public void visitBoolExpr_ValidInput_CreatesCorrectBooleanExpressionNode() {
        mctlParser.BoolExprContext boolExprContext = new mctlParser.BoolExprContext(new mctlParser.ExpressionContext());
    }
}
