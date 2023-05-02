package dk.aau.p4.abaaja.TypeCheckingVisitorTests;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;
import dk.aau.p4.abaaja.Lib.Visitors.AstVisitor;
import dk.aau.p4.abaaja.Lib.Visitors.SymbolTableVisitor;
import dk.aau.p4.abaaja.Lib.Visitors.TypeCheckingVisitor;
import dk.aau.p4.abaaja.mctlLexer;
import dk.aau.p4.abaaja.mctlParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TypeCheckingVisitorUnitTests {
    private final ProblemCollection problemCollection = new ProblemCollection();
    private final AstVisitor astVisitor = new AstVisitor(problemCollection);
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
    }

    private ParseTree createParseTree(String code) {
        mctlLexer lexer = new mctlLexer(CharStreams.fromString(code));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);

        mctlParser parser = new mctlParser(commonTokenStream);
        parser.setBuildParseTree(true);

        return parser.mctl();
    }

    @DataProvider
    public Object[][] createParseTreeTestData() {
        return new Object[][] {
                {"a = \"a\";"},
                {"a = \"a\"; a = \"a\";"},
                {"a = \"a\"; a = \"a\"; a = \"a\";"}
        };
    }

    // TODO: Condition 'parseTree != null' is always 'true'
    @Test(dataProvider = "createParseTreeTestData")
    public void createParseTree_ValidInput_CreatesParseTree(String code) {
        ParseTree parseTree = createParseTree(code);

        softAssert.assertTrue(parseTree != null, "Create Parse Tree: " + code);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] expectsTypeTestData() {
        return new Object[][] {
                {"test = 1 * 2;", "NUMBER"},
                {"test = 2 / 1;", "NUMBER"},
                {"test = 10 % 3;", "NUMBER"},
                {"test = 1 + 2;", "NUMBER"},
                {"test = 2 - 1;", "NUMBER"},
                {"test = true or false;", "BOOLEAN"},
                {"test = false and false;", "BOOLEAN"},
                {"test = 1 > 2;", "NUMBER"},
                {"test = 1 < 2;", "NUMBER"},
                {"test = 1 >= 2;", "NUMBER"},
                {"test = 1 <= 2;", "NUMBER"}
        };
    }

    @Test(dataProvider = "expectsTypeTestData")
    public void expectsType_ValidInput_ReturnsCorrectType(String code, String type) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        symbolTableVisitor.visit(mctlNode);

        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);
        ExpNode expNode = assStateNode.get_assignExp();

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.expectsType(expNode, type);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals(type), "ExpectsType: Expected type " + type + " for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitStructDecTestData() {
        return new Object[][] {
                {"struct STRUCT1 {variable varID: NUMBER,}"},
                {"struct struct2 {variable variable_ID: STRING,}"},
                {"struct coordinate {variable x: NUMBER}"},
                {"struct x_y_z {variable hasCoordinates: BOOLEAN}"}
        };
    }

    @DataProvider
    public Object[][] visitAssStateTestData() {
        return new Object[][] {
                {"variable a: NUMBER; a = a + 1;"},
                {"variable a: NUMBER; a = a + a;"},
                {"variable a: NUMBER; a = a + 123456789;"},
                {"variable a: STRING; a = \"hello\";"}
        };
    }

    @Test(dataProvider = "visitAssStateTestData")
    public void visitAssState_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        symbolTableVisitor.visit(mctlNode);

        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(1);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(assStateNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit AssState: " + code);
        softAssert.assertAll();
    }

    @Test(dataProvider = "visitStructDecTestData")
    public void visitStructDec_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        symbolTableVisitor.visit(mctlNode);

        StructDecNode structDecNode = (StructDecNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(structDecNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit StructDec: " + code);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitFunctionDecTestData() {
        return new Object[][] {
                {"to _functionId():BOOLEAN { return a; }"},
                {"to function1(id1: NUMBER):NUMBER { return a; }"},
                {"to test_func(id1:NUMBER, id2:NUMBER):STRING { return a; }"}
        };
    }

    @Test(dataProvider = "visitFunctionDecTestData")
    public void visitFunctionDec_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        symbolTableVisitor.visit(mctlNode);

        FuncDecNode funcDecNode = (FuncDecNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(funcDecNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit FuncDec: " + code);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitVarDecTestData() {
        return new Object[][] {
                {"variable identifier1: NUMBER;"},
                {"variable identifier2: STRING[];"},
                {"variable identifier3: BOOLEAN[][];"},
                {"variable identifier4: STRUCTURE[][][];"}
        };
    }

    @Test(dataProvider = "visitVarDecTestData")
    public void visitVarDec_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        symbolTableVisitor.visit(mctlNode);

        VarDecNode varDecNode = (VarDecNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(varDecNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit VarDec: " + code);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitCommentTestData() {
        return new Object[][] {
                {"#{ comment }"},
                {"#{comment}"}
        };
    }

    @Test(dataProvider = "visitCommentTestData")
    public void visitComment_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        symbolTableVisitor.visit(mctlNode);

        CommentNode commentNode = (CommentNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(commentNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit Comment: " + code);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitStopTestData() {
        return new Object[][] {
                {"stop;"}
        };
    }

    @Test(dataProvider = "visitStopTestData")
    public void visitStop_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        symbolTableVisitor.visit(mctlNode);

        StopNode stopNode = (StopNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(stopNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit Stop: " + code);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitFormalParamTestData() {
        return new Object[][] {
                {"to func(id0: NUMBER):NUMBER {}"},
                {"to func(id1: STRING):NUMBER {}"},
                {"to func(id2: BOOLEAN):NUMBER {}"}
        };
    }

    @Test(dataProvider = "visitFormalParamTestData")
    public void visitFormalParam_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        symbolTableVisitor.visit(mctlNode);

        FuncDecNode funcDecNode = (FuncDecNode) mctlNode.get_children().get(0);
        FormalParamNode formalParamNode = funcDecNode.get_paramList().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(formalParamNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit FormalParam: " + code);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitStringMethodInvokeTestData() {
        return new Object[][] {
                {"\"var\".accessor(bing, bong);"},
                {"\"vroom vroom\".go();"},
                {"\"1\".toString(2);"}
        };
    }

    @Test(dataProvider = "visitStringMethodInvokeTestData")
    public void visitStringMethodInvoke_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        symbolTableVisitor.visit(mctlNode);

        StringMethodInvokeNode stringMethodInvokeNode = (StringMethodInvokeNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(stringMethodInvokeNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit StringMethodInvoke: " + code);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitVarMethodInvokeTestData() {
        return new Object[][] {
                {"var.accessor(bing, bong);"},
                {"vroom.go();"},
                {"_1.toString(2);"}
        };
    }

    @Test(dataProvider = "visitVarMethodInvokeTestData")
    public void visitVarMethodInvoke_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        symbolTableVisitor.visit(mctlNode);

        VarMethodInvokeNode varMethodInvokeNode = (VarMethodInvokeNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(varMethodInvokeNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit VarMethodInvoke: " + code);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitFuncInvokeTestData() {
        return new Object[][] {
                {"var(bing, bong);"},
                {"vroom();"},
                {"_1(2);"}
        };
    }

    @Test(dataProvider = "visitFuncInvokeTestData")
    public void visitFuncInvoke_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        symbolTableVisitor.visit(mctlNode);

        FuncInvokeNode funcInvokeNode = (FuncInvokeNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(funcInvokeNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit FuncInvoke: " + code);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitBlockTestData() {
        return new Object[][] {
                {"if(true){}"},
                {"if(true){id=val;id=val;}"},
                {"if(true){id=val;id=val;id=val;}"},
                {"if(true){id=val;id=val;id=val;id=val;}"}
        };
    }

    @Test(dataProvider = "visitBlockTestData")
    public void visitBlock_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        symbolTableVisitor.visit(mctlNode);

        IfStateNode ifStateNode = (IfStateNode) mctlNode.get_children().get(0);
        BlockNode blockNode = ifStateNode.get_blockChildrenNode().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(blockNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit Block: " + code);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitLineTestData() {
        return new Object[][] {
                {"if(true){id=val;id=val;}"},
                {"if(true){id=val;id=val;id=val;}"},
                {"if(true){id=val;id=val;id=val;id=val;}"}
        };
    }

    @Test(dataProvider = "visitLineTestData")
    public void visitLine_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        symbolTableVisitor.visit(mctlNode);

        IfStateNode ifStateNode = (IfStateNode) mctlNode.get_children().get(0);
        BlockNode blockNode = ifStateNode.get_blockChildrenNode().get(0);
        LineNode lineNode = (LineNode) blockNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(lineNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit Line: " + code);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitMctlTestData() {
        return new Object[][] {
                {"test = 0.0;"},
                {"variable var1: NUMBER; variable var2: NUMBER; test = var1 + var2;"},
                {"struct x_y_z {variable hasCoordinates : BOOLEAN}"},
                {"if (true) { var = a; } else if (true) { var = b; } else { var = c; }"},
                {"if (a == b) { var = 1; } methodMan(); arr[y] = arr[i + 2]; repeat(10) { y++; }"},
                {"var1 = 1; var2 = 2; var3 = 3; var4 = 4; var5 = 5;"}
        };
    }

    @Test(dataProvider = "visitMctlTestData")
    public void visitMctl_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);
        symbolTableVisitor.visit(mctlNode);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(mctlNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit Mctl: " + code);
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitReturnTestData() {
        return new Object[][] {
                {"variable var: NUMBER; return 4;", "NUMBER"},
                {"variable var: NUMBER; return \"yo\";", "STRING"},
                {"variable var: NUMBER; return true;", "BOOLEAN"},
                {"variable var: NUMBER; return var;", "NUMBER"},
                {"variable var: STRING; return var;", "STRING"},
                {"variable var: BOOLEAN; return var;", "BOOLEAN"},
                {"variable var: NUMBER[]; return var[0];", "NUMBER[]"},
                {"variable var: STRING[]; return var[0];", "STRING[]"},
                {"variable var: BOOLEAN[]; return var[0];", "BOOLEAN[]"},
                {"variable var: NUMBER[][]; return var[0];", "NUMBER[][]"},
                {"variable var: STRING[][]; return var[0];", "STRING[][]"},
                {"variable var: BOOLEAN[][]; return var[0];", "BOOLEAN[][]"}
        };
    }

    @Test(dataProvider = "visitReturnTestData")
    public void visitReturn_ValidInput_ReturnsCorrectType(String code, String type) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        symbolTableVisitor.visit(mctlNode);

        ReturnNode returnNode = (ReturnNode) mctlNode.get_children().get(1);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(returnNode);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals(type), "Visit Return: Expected type " + type + " for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitTypecastTestData() {
        return new Object[][] {
                {"variable var: NUMBER; test = (NUMBER) var;", "NUMBER"},
                {"variable var: STRING; test = (STRING) var;", "STRING"},
                {"variable var: BOOLEAN; test = (BOOLEAN) var;", "BOOLEAN"}
        };
    }

    @Test(dataProvider = "visitTypecastTestData")
    public void visitTypecast_ValidInput_ReturnsCorrectType(String code, String type) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        symbolTableVisitor.visit(mctlNode);

        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(1);
        TypecastExpNode typecastExpNode = (TypecastExpNode) assStateNode.get_assignExp();

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(typecastExpNode);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals(type), "Visit Typecast: Expected type " + type + " for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitUnaryExpTestData() {
        return new Object[][] {
                {"variable var: NUMBER; test = +var;", "NUMBER"},
                {"variable var: STRING; test = -8;", "NUMBER"},
                {"variable test: BOOLEAN; test = !test;", "BOOLEAN"}
        };
    }

    @Test(dataProvider = "visitUnaryExpTestData")
    public void visitUnaryExp_ValidInput_ReturnsCorrectType(String code, String type) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        symbolTableVisitor.visit(mctlNode);

        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(1);
        UnaryExpNode unaryExpNode = (UnaryExpNode) assStateNode.get_assignExp();

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(unaryExpNode);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals(type), "Visit UnaryExp: Expected type " + type + " for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitIDArrayExpTestData() {
        return new Object[][] {
                {"variable c: STRING; variable b: STRING; variable a: NUMBER[]; a[0] = 1;", "NUMBER[]"},
                {"variable c: STRING; variable b: STRING; variable a: NUMBER[][]; a[0][1] = 2;", "NUMBER[][]"},
                {"variable c: STRING; variable b: STRING; variable a: NUMBER[][][]; a[0][1][3][4][5] = 5;", "NUMBER[][][]"},
                {"variable c: STRING; variable b: STRING; variable a: STRING[]; a[0] = \"1\";", "STRING[]"},
                {"variable c: STRING; variable b: STRING; variable a: STRING[][]; a[0][1] = \"2\";", "STRING[][]"},
                {"variable c: STRING; variable b: STRING; variable a: STRING[][][]; a[0][1][3][4][5] = \"5\";", "STRING[][][]"},
                {"variable c: STRING; variable b: STRING; variable a: BOOLEAN[]; a[0] = true;", "BOOLEAN[]"},
                {"variable c: STRING; variable b: STRING; variable a: BOOLEAN[][]; a[0][1] = true;", "BOOLEAN[][]"},
                {"variable c: STRING; variable b: STRING; variable a: BOOLEAN[][][]; a[0][1][3][4][5] = false;", "BOOLEAN[][][]"},
                {"variable c: STRING; struct test { variable b: NUMBER[] }; variable a: test; a.b[0] = 1;", "NUMBER[]"},
                {"variable c: STRING; struct test { variable b: NUMBER[] }; variable a: test[]; a[0].b[0] = 1;", "NUMBER[]"},
                {"variable c: STRING; struct test { variable b: NUMBER[][] }; variable a: test[]; a[0].b[0][0] = 1;", "NUMBER[][]"},
                {"variable c: STRING; struct test { variable b: NUMBER[][] }; variable a: test[][]; a[0][0].b[0][0] = 1;", "NUMBER[][]"},
                {"variable c: STRING; struct test { variable b: NUMBER[][][] }; variable a: test[][][]; a[0][0][0].b[0][0][0] = 1;", "NUMBER[][][]"},
                {"struct test2 { variable b: test[][][] }; struct test { variable c: NUMBER[][][] }; variable a: test2[][][]; a[0][0][0].b[0][0][0].c[0][0][0] = 1;", "NUMBER[][][]"}
        };
    }

    @Test(dataProvider = "visitIDArrayExpTestData")
    public void visitIDArrayExp_ValidInput_ReturnsCorrectType(String code, String type) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        symbolTableVisitor.visit(mctlNode);

        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(3);
        IDArrayExpNode idArrayExpNode = (IDArrayExpNode) assStateNode.get_assignId();

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(idArrayExpNode);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals(type), "Visit IDArrayExp: Expected type " + type + " for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitStringExpTestData() {
        return new Object[][] {
                {"test = \"This is a String\";"},
                {"test = \"1234 string 8078.,/()&\";"},
                {"test = \"'Double Quoted String'\";"},
                {"test = 'Single Quoted String';"},
                {"test = \"Newline: \n Carriage return: \r Tab: \t Backslash: \\ \";"},
                {"test = \"Unicode: Ωαφω\";"}
        };
    }

    @Test(dataProvider = "visitStringExpTestData")
    public void visitStringExp_ValidInput_ReturnsCorrectType(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        symbolTableVisitor.visit(mctlNode);

        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);
        StringExpNode stringExpNode = (StringExpNode) assStateNode.get_assignExp();

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(stringExpNode);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals("STRING"), "Visit StringExp: Expected type STRING for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitNumExpTestData() {
        return new Object[][]{
                {"test = 0.0;"},
                {"test = 1.0;"},
                {"test = 1.0;"},
                {"test = 1234.56789;"},
                {"test = 9876.54321;"},
                {"test = 0;"},
                {"test = 1;"},
                {"test = 12345;"},
                {"test = 67890;"},
                {"test = 2147483647;"}
        };
    }

    @Test(dataProvider = "visitNumExpTestData")
    public void visitNumExp_ValidInput_ReturnsCorrectType(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        symbolTableVisitor.visit(mctlNode);

        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(0);
        NumExpNode numExpNode = (NumExpNode) assStateNode.get_assignExp();

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(numExpNode);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals("NUMBER"), "Visit NumExp: Expected type NUMBER for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitNothingTypeTestData() {
        return new Object[][] {
                {"to test(): NOTHING {}"},
                {"to test(): NOTHING { variable x: NUMBER; x = 5; }"},
                {"to test(): NOTHING { test(); }"}
        };
    }

    @Test(dataProvider = "visitNothingTypeTestData")
    public void visitNothingType_ValidInput_ReturnsCorrectType(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        symbolTableVisitor.visit(mctlNode);

        FuncDecNode funcDecNode = (FuncDecNode) mctlNode.get_children().get(0);
        NothingTypeNode nothingTypeNode = (NothingTypeNode) funcDecNode.get_returnType();

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(nothingTypeNode);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals("NOTHING"), "Visit Nothing: Expected type NOTHING for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitTypeTestData() {
        return new Object[][] {
                {"variable x: STRING; variable test: STRING;", "STRING"},
                {"variable x: STRING; variable test: STRING[];", "STRING"},
                {"variable x: STRING; variable test: STRING[][];", "STRING"},
                {"variable x: STRING; variable test: NUMBER;", "NUMBER"},
                {"variable x: STRING; variable test: NUMBER[];", "NUMBER"},
                {"variable x: STRING; variable test: NUMBER[][];", "NUMBER"},
                {"variable x: STRING; variable test: BOOLEAN;", "BOOLEAN"},
                {"variable x: STRING; variable test: BOOLEAN[];", "BOOLEAN"},
                {"variable x: STRING; variable test: BOOLEAN[][];", "BOOLEAN"},
                {"struct STRUCTURE { variable x: NUMBER }; variable test: STRUCTURE;", "STRUCTURE"},
                {"struct testStructId { variable x: NUMBER }; variable test: testStructId[];", "testStructId"},
                {"struct struct_test_id { variable x: NUMBER }; variable test: struct_test_id[][];", "struct_test_id"}
        };
    }

    @Test(dataProvider = "visitTypeTestData")
    public void visitType_ValidInput_ReturnsCorrectType(String code, String type) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        symbolTableVisitor.visit(mctlNode);

        VarDecNode varDecNode = (VarDecNode) mctlNode.get_children().get(1);
        TypeNode typeNode = varDecNode.get_varDecType();

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(typeNode);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals(type), "Visit Type: Expected type " + type + " for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitCompExpTestData() {
        return new Object[][] {
                {"variable var1: NUMBER; variable var2: NUMBER; test = -1 > -2;"},
                {"variable var1: NUMBER; variable var2: NUMBER; test = 1 > 2;"},
                {"variable var1: NUMBER; variable var2: NUMBER; test = var1 > var2;"},
                {"variable var1: NUMBER; variable var2: NUMBER; test = 1 < 2;"},
                {"variable var1: NUMBER; variable var2: NUMBER; test = var1 < var2;"},
                {"variable var1: NUMBER; variable var2: NUMBER; test = 1 >= 2;"},
                {"variable var1: NUMBER; variable var2: NUMBER; test = var1 >= var2;"},
                {"variable var1: NUMBER; variable var2: NUMBER; test = 1 <= 2;"},
                {"variable var1: NUMBER; variable var2: NUMBER; test = var1 <= var2;"}
        };
    }

    @Test(dataProvider = "visitCompExpTestData")
    public void visitCompExp_ValidInput_ReturnsCorrectType(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        symbolTableVisitor.visit(mctlNode);

        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(2);
        CompExpNode compExpNode = (CompExpNode) assStateNode.get_assignExp();

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(compExpNode);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals("NUMBER"), "Visit CompExp: Expected type NUMBER for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitOrExpTestData() {
        return new Object[][] {
                {"variable var1: BOOLEAN;" +
                        "variable var2: BOOLEAN;" +
                        "variable var3: BOOLEAN;" +
                        "variable var4: BOOLEAN;" +
                        "variable var5: BOOLEAN;" +
                        "test = var1 or var2;"},
                {"variable var1: BOOLEAN;" +
                        "variable var2: BOOLEAN;" +
                        "variable var3: BOOLEAN;" +
                        "variable var4: BOOLEAN;" +
                        "variable var5: BOOLEAN;" +
                        "test = var1 or var2 or var3 or var4 or var5;"}
        };
    }

    @Test(dataProvider = "visitOrExpTestData")
    public void visitOrExp_ValidInput_ReturnsCorrectType(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        symbolTableVisitor.visit(mctlNode);

        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(5);
        OrExpNode orExpNode = (OrExpNode) assStateNode.get_assignExp();

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(orExpNode);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals("BOOLEAN"), "Visit OrExp: Expected type BOOLEAN for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitAndExpTestData() {
        return new Object[][] {
                {"variable var1: BOOLEAN;" +
                        "variable var2: BOOLEAN;" +
                        "variable var3: BOOLEAN;" +
                        "variable var4: BOOLEAN;" +
                        "variable var5: BOOLEAN;" +
                        "test = var1 and var2;"},
                {"variable var1: BOOLEAN;" +
                        "variable var2: BOOLEAN;" +
                        "variable var3: BOOLEAN;" +
                        "variable var4: BOOLEAN;" +
                        "variable var5: BOOLEAN;" +
                        "test = var1 and var2 and var3 and var4 and var5;"}
        };
    }

    @Test(dataProvider = "visitAndExpTestData")
    public void visitAndExp_ValidInput_ReturnsCorrectType(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        symbolTableVisitor.visit(mctlNode);

        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(5);
        AndExpNode andExpNode = (AndExpNode) assStateNode.get_assignExp();

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(andExpNode);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals("BOOLEAN"), "Visit AndExp: Expected type BOOLEAN for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitAddExpTestData() {
        return new Object[][] {
                {"variable var1: NUMBER;" +
                        "variable var2: NUMBER;" +
                        "variable var3: NUMBER;" +
                        "variable var4: NUMBER;" +
                        "variable var5: NUMBER;" +
                        "test = var1 + var2;"},
                {"variable var1: NUMBER;" +
                        "variable var2: NUMBER;" +
                        "variable var3: NUMBER;" +
                        "variable var4: NUMBER;" +
                        "variable var5: NUMBER;" +
                        "test = var1 + var2 + var3 + var4 + var5;"},
                {"variable var1: NUMBER;" +
                        "variable var2: NUMBER;" +
                        "variable var3: NUMBER;" +
                        "variable var4: NUMBER;" +
                        "variable var5: NUMBER;" +
                        "test = var1 - var2;"},
                {"variable var1: NUMBER;" +
                        "variable var2: NUMBER;" +
                        "variable var3: NUMBER;" +
                        "variable var4: NUMBER;" +
                        "variable var5: NUMBER;" +
                        "test = var1 - var2 - var3 - var4 - var5;"}
        };
    }

    @Test(dataProvider = "visitAddExpTestData")
    public void visitAddExp_ValidInput_ReturnsCorrectType(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        symbolTableVisitor.visit(mctlNode);

        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(5);
        AddExpNode addExpNode = (AddExpNode) assStateNode.get_assignExp();

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(addExpNode);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals("NUMBER"), "Visit AddExp: Expected type NUMBER for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitMulExpTestData() {
        return new Object[][] {
                {"variable var1: NUMBER;" +
                        "variable var2: NUMBER;" +
                        "variable var3: NUMBER;" +
                        "variable var4: NUMBER;" +
                        "variable var5: NUMBER;" +
                        "test = var1 * var2;"},
                {"variable var1: NUMBER;" +
                        "variable var2: NUMBER;" +
                        "variable var3: NUMBER;" +
                        "variable var4: NUMBER;" +
                        "variable var5: NUMBER;" +
                        "test = var1 * var2 * var3 * var4 * var5;"},
                {"variable var1: NUMBER;" +
                        "variable var2: NUMBER;" +
                        "variable var3: NUMBER;" +
                        "variable var4: NUMBER;" +
                        "variable var5: NUMBER;" +
                        "test = var1 / var2;"},
                {"variable var1: NUMBER;" +
                        "variable var2: NUMBER;" +
                        "variable var3: NUMBER;" +
                        "variable var4: NUMBER;" +
                        "variable var5: NUMBER;" +
                        "test = var1 / var2 / var3 / var4 / var5;"},
                {"variable var1: NUMBER;" +
                        "variable var2: NUMBER;" +
                        "variable var3: NUMBER;" +
                        "variable var4: NUMBER;" +
                        "variable var5: NUMBER;" +
                        "test = var1 % var2;"},
                {"variable var1: NUMBER;" +
                        "variable var2: NUMBER;" +
                        "variable var3: NUMBER;" +
                        "variable var4: NUMBER;" +
                        "variable var5: NUMBER;" +
                        "test = var1 % var2 % var3 % var4 % var5;"}
        };
    }

    @Test(dataProvider = "visitMulExpTestData")
    public void visitMulExp_ValidInput_ReturnsCorrectType(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        symbolTableVisitor.visit(mctlNode);

        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(5);
        MulExpNode mulExpNode = (MulExpNode) assStateNode.get_assignExp();

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(mulExpNode);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals("NUMBER"), "Visit MulExp: Expected type NUMBER for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitIDStructTestData() {
        return new Object[][] {
                {"variable empty: STRING; struct STRUCTURE { variable num: NUMBER }; variable var: STRUCTURE; var.num = 1;", "NUMBER"},
                {"variable empty: STRING; struct STRUCTURE { variable num: NUMBER }; variable var: STRUCTURE[]; var[0].num = 3;", "NUMBER"},
                {"variable empty: STRING; struct STRUCTURE { variable str: STRING }; variable var: STRUCTURE; var.str = \"1\";", "STRING"},
                {"variable empty: STRING; struct STRUCTURE { variable str: STRING }; variable var: STRUCTURE[]; var[0].str = \"3\";", "STRING"},
                {"variable empty: STRING; struct STRUCTURE { variable bool: BOOLEAN }; variable var: STRUCTURE; var.bool = true;", "BOOLEAN"},
                {"variable empty: STRING; struct STRUCTURE { variable bool: BOOLEAN }; variable var: STRUCTURE[]; var[0].bool = true;", "BOOLEAN"},
                {"variable empty: STRING; struct STRUCTURE { variable bool: BOOLEAN }; variable var: STRUCTURE[][]; var[0][0].bool = true;", "BOOLEAN"},
                {"struct STRUCTURE1 { variable bool: BOOLEAN }; struct STRUCTURE2 { variable inner: STRUCTURE1[] }; variable var: STRUCTURE2[][]; var[0][0].inner.bool = true;", "BOOLEAN"}
        };
    }

    @Test(dataProvider = "visitIDStructTestData")
    public void visitIDStruct_ValidInput_ReturnsCorrectType(String code, String type) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        symbolTableVisitor.visit(mctlNode);

        AssStateNode assStateNode = (AssStateNode) mctlNode.get_children().get(3);
        IDStructNode idStructNode = (IDStructNode) assStateNode.get_assignId();

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(idStructNode);

        softAssert.assertTrue(typeDescriptor.get_type_literal().equals(type), "Visit IDStruct: Expected type " + type + " for '" + code + "' but got: " + typeDescriptor.get_type_literal());
        softAssert.assertAll();
    }
}
