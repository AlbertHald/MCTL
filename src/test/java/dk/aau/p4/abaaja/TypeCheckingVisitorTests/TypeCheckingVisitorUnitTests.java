package dk.aau.p4.abaaja.TypeCheckingVisitorTests;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.Symbols.FuncSymbol;
import dk.aau.p4.abaaja.Lib.Symbols.Symbol;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.*;
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
import java.util.List;

import static org.mockito.Mockito.*;

public class TypeCheckingVisitorUnitTests {
    private ProblemCollection problemCollection = new ProblemCollection();
    private SymbolTable _symbolTable;
    private TypeCheckingVisitor typeCheckingVisitor;
    private SoftAssert softAssert;

    @BeforeMethod
    public void BeforeMethod() {
        softAssert = new SoftAssert();
        _symbolTable = mock();
    }

    @AfterMethod
    public void AfterMethod() {
        typeCheckingVisitor = null;

        problemCollection = new ProblemCollection();
    }

    @DataProvider
    public Object[][] getPrimitiveTypeTestData() {
        return new Object[][] {
            {new BoolTypeNode(), new MctlBooleanDescriptor(), "BOOLEAN"},
            {new BoolTypeNode(1), new MctlBooleanDescriptor(), "BOOLEAN[]"},
            {new BoolTypeNode(2), new MctlBooleanDescriptor(), "BOOLEAN[][]"},
            {new NumTypeNode(), new MctlNumberDescriptor(), "NUMBER"},
            {new NumTypeNode(1), new MctlNumberDescriptor(), "NUMBER[]"},
            {new NumTypeNode(2), new MctlNumberDescriptor(), "NUMBER[][]"},
            {new StringTypeNode(), new MctlStringDescriptor(), "STRING"},
            {new StringTypeNode(1), new MctlStringDescriptor(), "STRING[]"},
            {new StringTypeNode(2), new MctlStringDescriptor(), "STRING[][]"}
        };
    }

    @Test(dataProvider = "getPrimitiveTypeTestData")
    public void getPrimitiveType_ValidInput_ReturnsCorrectType(TypeNode node, MctlTypeDescriptor mctlTypeDescriptor, String expected) {

        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, _symbolTable);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.getPrimitiveType(node, mctlTypeDescriptor);

        softAssert.assertEquals(typeDescriptor.get_type_literal(), expected, "getPrimitiveType type checking went wrong");
        softAssert.assertAll();
    }

    @Test()
    public void FuncInvokeNode_ValidASTNode_ReturnsCorrectType() {

        // Arrange
        TypeCheckingVisitor typeCheckingVisitor2 = mock();

        FuncInvokeNode funcInvokeNode = new FuncInvokeNode();
        ActualIDExpNode actualIDExpNode = new ActualIDExpNode();
        FuncSymbol funcSymbol = new FuncSymbol();
        AddExpNode expNode = new AddExpNode();
        MctlNumberDescriptor mctlNumberDescriptor = new MctlNumberDescriptor();

        actualIDExpNode.set_id("test");
        funcInvokeNode.set_id(actualIDExpNode);
        funcInvokeNode.add_paramExp(expNode);
        funcInvokeNode.set_lineNumber(0);
        funcSymbol.set_type(mctlNumberDescriptor);

        when(_symbolTable.searchSymbol(funcInvokeNode.get_id().get_id())).thenReturn(funcSymbol);
        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, _symbolTable);

        doNothing().when(typeCheckingVisitor2)._checkFunctionParams(funcInvokeNode.get_paramExps(), funcSymbol, funcInvokeNode.get_lineNumber());

        // Act
        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(funcInvokeNode);

        System.out.println("typeDescriptor.get_type_literal() = " + typeDescriptor.get_type_literal());

        // Assert
        softAssert.assertEquals(typeDescriptor.get_type_literal(), "NUMBER", "FuncInvokeNode type checking went wrong");
        softAssert.assertAll();
    }
}


