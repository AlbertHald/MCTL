package dk.aau.p4.abaaja.TypeCheckingVisitorTests;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;
import dk.aau.p4.abaaja.Lib.Visitors.AstBuilder;
import dk.aau.p4.abaaja.Lib.Visitors.SymbolTableVisitor;
import dk.aau.p4.abaaja.Lib.Visitors.TypeCheckingVisitor;
import dk.aau.p4.abaaja.mctlParser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class TypeCheckingVisitorUnitTests {
    private ProblemCollection problemCollection = new ProblemCollection();
    private final AstBuilder astBuilder = new AstBuilder(problemCollection);
    private SymbolTableVisitor symbolTableVisitor;
    private TypeCheckingVisitor typeCheckingVisitor;
    private SoftAssert softAssert;

    @BeforeMethod
    public void BeforeMethod() {
        softAssert = new SoftAssert();
        symbolTableVisitor = new SymbolTableVisitor(problemCollection);
        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, symbolTableVisitor.getSymbolTable());
    }

    @AfterMethod
    public void AfterMethod() {
        symbolTableVisitor = null;
        typeCheckingVisitor = null;

        problemCollection = new ProblemCollection();
    }

    @Test()
    public void addExpNode_ValidASTNode_ReturnsTypeNUMBER() {
        // Arrange
        AddExpNode addExpNode = new AddExpNode();
        addExpNode.set_operator(mctlParser.PLUS);
        addExpNode.set_operatorLiteral("+");

        // Number 1 and 2
        NumExpNode numExpNode1 = new NumExpNode();
        NumExpNode numExpNode2 = new NumExpNode();

        addExpNode.set_children(Arrays.asList(numExpNode1, numExpNode2));

        // Act
        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(addExpNode);

        // Assert
        softAssert.assertEquals(typeDescriptor.get_type_literal(), "NUMBER", "AddExpNode type checking went wrong");
        softAssert.assertAll();
    }

    @Test()
    public void numExpNode_ValidASTNode_ReturnsTypeNUMBER() {
        // Arrange
        NumExpNode numExpNode = new NumExpNode();

        // Act
        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(numExpNode);

        // Assert
        softAssert.assertEquals(typeDescriptor.get_type_literal(), "NUMBER", "NumExpNode type checking went wrong");
        softAssert.assertAll();
    }

    @Test()
    public void boolExpNode_ValidASTNode_ReturnsTypeBOOLEAN() {
        // Arrange
        BoolExpNode boolExpNode = new BoolExpNode();

        // Act
        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(boolExpNode);

        // Assert
        softAssert.assertEquals(typeDescriptor.get_type_literal(), "BOOLEAN", "BoolExpNode type checking went wrong");
        softAssert.assertAll();
    }

    @Test()
    public void stringExpNode_ValidASTNode_ReturnsTypeSTRING() {
        // Arrange
        StringExpNode stringExpNode = new StringExpNode();

        // Act
        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(stringExpNode);

        // Assert
        softAssert.assertEquals(typeDescriptor.get_type_literal(), "STRING", "StringExpNode type checking went wrong");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] numTypeNodeTestData() {
        return new Object[][] {
                {new NumTypeNode(), "NUMBER"},
                {new NumTypeNode(1), "NUMBER[]"},
                {new NumTypeNode(2), "NUMBER[][]"},
        };
    }

    @Test(dataProvider = "numTypeNodeTestData")
    public void numTypeNode_ValidASTNode_ReturnsNumberType(NumTypeNode typeNode, String expectedLiteral) {
        // Act
        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(typeNode);

        // Assert
        softAssert.assertEquals(typeDescriptor.get_type_literal(), expectedLiteral, "NumTypeNode type checking went wrong");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] stringTypeNodeTestData() {
        return new Object[][] {
                {new StringTypeNode(), "STRING"},
                {new StringTypeNode(1), "STRING[]"},
                {new StringTypeNode(2), "STRING[][]"},
        };
    }

    @Test(dataProvider = "stringTypeNodeTestData")
    public void stringTypeNode_ValidASTNode_ReturnsStringType(StringTypeNode typeNode, String expectedLiteral) {
        // Act
        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(typeNode);

        // Assert
        softAssert.assertEquals(typeDescriptor.get_type_literal(), expectedLiteral, "StringTypeNode type checking went wrong");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] boolTypeNodeTestData() {
        return new Object[][] {
                {new BoolTypeNode(), "BOOLEAN"},
                {new BoolTypeNode(1), "BOOLEAN[]"},
                {new BoolTypeNode(2), "BOOLEAN[][]"},
        };
    }

    @Test(dataProvider = "boolTypeNodeTestData")
    public void boolTypeNode_ValidASTNode_ReturnsBoolType(BoolTypeNode typeNode, String expectedLiteral) {

        // Act
        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(typeNode);

        // Assert
        softAssert.assertEquals(typeDescriptor.get_type_literal(), expectedLiteral, "BoolTypeNode type checking went wrong");
        softAssert.assertAll();
    }
}
