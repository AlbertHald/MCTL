package dk.aau.p4.abaaja.TypeCheckingVisitorTests;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemType;
import dk.aau.p4.abaaja.Lib.Symbols.FuncSymbol;
import dk.aau.p4.abaaja.Lib.Symbols.Symbol;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.*;
import dk.aau.p4.abaaja.Lib.Visitors.TypeCheckingVisitor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
    public Object[][] _getPrimitiveTypeTestData() {
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

    @Test(dataProvider = "_getPrimitiveTypeTestData")
    public void _getPrimitiveType_ValidInput_ReturnsCorrectType(TypeNode node, MctlTypeDescriptor mctlTypeDescriptor, String expected) {

        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, _symbolTable);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor._getPrimitiveType(node, mctlTypeDescriptor);

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

        // Assert
        softAssert.assertEquals(typeDescriptor.get_type_literal(), "NUMBER", "FuncInvokeNode type checking went wrong");
        softAssert.assertAll();
    }

    @Test()
    public void IDTypeNode_Error_ReturnsCorrectType() {

        IDTypeNode idTypeNode = new IDTypeNode();

        idTypeNode.set_type("test");

        when(_symbolTable.searchType("test")).thenReturn(null);
        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, _symbolTable);

        MctlTypeDescriptor result = typeCheckingVisitor.visit(idTypeNode);

        softAssert.assertNull(result, "IDTypeNode Error type checking went wrong");
        softAssert.assertEquals(problemCollection.getProblems().size(), 1, "IDTypeNode Error error collection should be 1 big");
        softAssert.assertEquals(problemCollection.getProblems().get(0).getProblemType(), ProblemType.ERROR_UNKNOWN_TYPE, "IDTypeNode Error type checking went wrong");
        softAssert.assertAll();
    }

    @Test()
    public void IDTypeNode_ValidNode_ReturnsCorrectType() {

        IDTypeNode idTypeNode = new IDTypeNode();

        idTypeNode.set_type("NUMBER");

        when(_symbolTable.searchType("NUMBER")).thenReturn(new MctlNumberDescriptor());
        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, _symbolTable);


        MctlTypeDescriptor result = typeCheckingVisitor.visit(idTypeNode);

        softAssert.assertEquals(result.get_type_literal(), "NUMBER", "IDTypeNode type checking went wrong");
        softAssert.assertAll();
    }

    @Test()
    public void IDTypeNode_ValidArrayNode_ReturnsCorrectType() {

        IDTypeNode idTypeNode = new IDTypeNode();

        idTypeNode.set_arrayDegree(1);
        idTypeNode.set_type("NUMBER");

        when(_symbolTable.searchType("NUMBER")).thenReturn(new MctlNumberDescriptor());
        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, _symbolTable);

        MctlArrayTypeDescriptor result = (MctlArrayTypeDescriptor) typeCheckingVisitor.visit(idTypeNode);

        softAssert.assertEquals(result.get_type_literal(), "NUMBER[]", "IDTypeNode Array type checking went wrong");
        softAssert.assertEquals(result.getDegree(), 1, "IDTypeNode Array type checking went wrong");
        softAssert.assertAll();
    }


    @Test()
    public void _getArrayType_Error_ReturnsCorrectType() {

        MctlArrayTypeDescriptor typeDescriptor = new MctlArrayTypeDescriptor(
                new MctlNumberDescriptor(),-1);

        int degree = -1;
        int lineNumber = 0;


        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, _symbolTable);

        MctlTypeDescriptor result = typeCheckingVisitor._getArrayType(typeDescriptor, degree, lineNumber);

        softAssert.assertEquals(result.getClass(), MctlNothingDescriptor.class,"_getArrayType Error type checking went wrong");
        softAssert.assertEquals(problemCollection.getProblems().size(), 1, "_getArrayType Error error collection should be 1 big");
        softAssert.assertEquals(problemCollection.getProblems().get(0).getProblemType(), ProblemType.ERROR_TYPE_MISMATCH, "_getArrayType Error type checking went wrong");
        softAssert.assertAll();
    }

    @Test()
    public void _getArrayType_ValidNode_ReturnsCorrectType() {

        MctlArrayTypeDescriptor typeDescriptor = new MctlArrayTypeDescriptor(
                new MctlNumberDescriptor(),0);

        int degree = 0;
        int lineNumber = 0;


        when(_symbolTable.searchType("NUMBER")).thenReturn(new MctlNumberDescriptor());
        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, _symbolTable);

        MctlTypeDescriptor result = typeCheckingVisitor._getArrayType(typeDescriptor, degree, lineNumber);

        softAssert.assertEquals(result.getClass(), MctlNumberDescriptor.class,"_getArrayType Error type checking went wrong");
        softAssert.assertEquals(problemCollection.getProblems().size(), 0, "_getArrayType Error error collection should be empty");
        softAssert.assertAll();
    }

    @Test()
    public void _getArrayType_ValidArrayNode_ReturnsCorrectType() {

        MctlArrayTypeDescriptor typeDescriptor = new MctlArrayTypeDescriptor(
                new MctlNumberDescriptor(),1);

        int degree = 1;
        int lineNumber = 0;


        when(_symbolTable.searchType("NUMBER")).thenReturn(new MctlNumberDescriptor());
        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, _symbolTable);

        MctlTypeDescriptor result = typeCheckingVisitor._getArrayType(typeDescriptor, degree, lineNumber);
        MctlArrayTypeDescriptor resultAsArray = (MctlArrayTypeDescriptor) result;

        softAssert.assertEquals(result.get_type_literal(), "NUMBER[]", "IDTypeNode Array type checking went wrong");
        softAssert.assertEquals(resultAsArray.getDegree(), 1, "IDTypeNode Array type checking went wrong");
        softAssert.assertAll();
    }

    @Test()
    public void ActualIDExpNode_ValidInput_ReturnsCorrectType() {

        ActualIDExpNode actualIDExpNode = new ActualIDExpNode();
        Symbol symbol = new Symbol(new MctlNumberDescriptor());
        symbol.set_isInstantiated(true);

        actualIDExpNode.set_id("test");

        when(_symbolTable.searchSymbol(actualIDExpNode.get_id())).thenReturn(symbol);
        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, _symbolTable);

        MctlTypeDescriptor result = typeCheckingVisitor.visit(actualIDExpNode);

        softAssert.assertEquals(result.getClass(), MctlNumberDescriptor.class, "ActualIDExpNode ValidInput type checking went wrong");
        softAssert.assertEquals(problemCollection.getProblems().size(), 0, "ActualIDExpNode ValidInput error collection should be empty");
        softAssert.assertAll();
    }

    @Test()
    public void ActualIDExpNode_Error_ReturnsCorrectType() {

        ActualIDExpNode actualIDExpNode = new ActualIDExpNode();
        Symbol symbol = new Symbol(new MctlNumberDescriptor());

        actualIDExpNode.set_id("test");

        when(_symbolTable.searchSymbol(actualIDExpNode.get_id())).thenReturn(symbol);
        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, _symbolTable);

        MctlTypeDescriptor result = typeCheckingVisitor.visit(actualIDExpNode);

        softAssert.assertEquals(result.getClass(), MctlNothingDescriptor.class, "ActualIDExpNode Error type checking went wrong");
        softAssert.assertEquals(problemCollection.getProblems().size(), 0, "ActualIDExpNode Error error collection should be empty");
        softAssert.assertAll();
    }

    @Test()
    public void _getTypecastResult_ValidInput_ReturnsCorrectType() {
        MctlTypeDescriptor fromType = new MctlBooleanDescriptor();
        MctlTypeDescriptor toType = new MctlStringDescriptor();

        String type = "NUMBER";
        int lineNumber = 0;

        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, _symbolTable);
        MctlTypeDescriptor result = typeCheckingVisitor._getTypecastResult(fromType, toType, type, lineNumber);

        softAssert.assertEquals(result.getClass(), MctlNumberDescriptor.class, "_getTypecastResult ValidInput type checking went wrong");
        softAssert.assertEquals(problemCollection.getProblems().size(), 0, "_getTypecastResult ValidInput error collection should be empty");
    }

    @Test()
    public void _getTypecastResult_Warning_ReturnsCorrectType() {
        MctlTypeDescriptor fromType = new MctlStringDescriptor();
        MctlTypeDescriptor toType = new MctlStringDescriptor();

        String type = "STRING";
        int lineNumber = 0;

        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, _symbolTable);
        MctlTypeDescriptor result = typeCheckingVisitor._getTypecastResult(fromType, toType, type, lineNumber);

        softAssert.assertEquals(result.getClass(), MctlStringDescriptor.class, "_getTypecastResult warn type checking went wrong");
        softAssert.assertEquals(problemCollection.getProblems().size(), 1, "_getTypecastResult warn error collection should be empty");
        softAssert.assertEquals(problemCollection.getProblems().get(0).getProblemType(), ProblemType.WARNING_REDUNDANT_TYPECAST, "_getTypecastResult warn warning wrong problem type");
        softAssert.assertAll();
    }

    @Test()
    public void _getTypecastResult_Error_ReturnsCorrectType() {
        MctlTypeDescriptor fromType = new MctlBooleanDescriptor();
        MctlTypeDescriptor toType = new MctlStringDescriptor();

        String type = "NOTHING";
        int lineNumber = 0;

        typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, _symbolTable);
        MctlTypeDescriptor result = typeCheckingVisitor._getTypecastResult(fromType, toType, type, lineNumber);

        softAssert.assertEquals(result.getClass(), MctlNothingDescriptor.class, "_getTypecastResult Error type checking went wrong");
        softAssert.assertEquals(problemCollection.getProblems().size(), 1, "_getTypecastResult Error error collection should be empty");
        softAssert.assertEquals(problemCollection.getProblems().get(0).getProblemType(), ProblemType.ERROR_TYPE_CANNOT_BE_CAST, "_getTypecastResult Error error wrong problem type");
        softAssert.assertAll();
    }
}


