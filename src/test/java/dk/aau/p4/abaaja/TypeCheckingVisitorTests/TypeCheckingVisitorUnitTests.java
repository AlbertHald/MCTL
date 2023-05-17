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
        softAssert.assertEquals(typeDescriptor.get_type_literal(), "NUMBER", "AddExpNode type checking went wrong.");
        softAssert.assertAll();
    }
}
