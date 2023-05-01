package dk.aau.p4.abaaja.TypeCheckingVisitorTests;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.MctlTypeDescriptor;
import dk.aau.p4.abaaja.Lib.Visitors.AstVisitor;
import dk.aau.p4.abaaja.Lib.Visitors.TypeCheckingVisitor;
import dk.aau.p4.abaaja.mctlLexer;
import dk.aau.p4.abaaja.mctlParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TypeCheckingVisitorUnitTests {
    private final ProblemCollection problemCollection = new ProblemCollection();
    private final SymbolTable symbolTable = new SymbolTable();
    private final AstVisitor astVisitor = new AstVisitor(problemCollection);
    private final TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor(problemCollection, symbolTable);
    private SoftAssert softAssert;

    @BeforeMethod
    public void BeforeMethod() {
        softAssert = new SoftAssert();
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
                {"a = \"a\"; a = \"a\"; a = \"a\";"},
        };
    }

    // TODO: Condition 'parseTree != null' is always 'true'
    @Test(dataProvider = "createParseTreeTestData")
    public void createParseTree_ValidInput_CreatesParseTree(String code) {
        ParseTree parseTree = createParseTree(code);

        softAssert.assertTrue(parseTree != null, "Create Parse Tree");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] expectsTypeTestData() {
        return new Object[][] {
                {"test = var + rav;", "+"},
                {"test = var1 + var2 + var3 + var4 + var5;", "+"},
                {"test = var - rav;", "-"},
                {"test = var1 - var2 - var3 - var4 - var5;", "-"}
        };
    }

    @Test(dataProvider = "expectsTypeTestData")
    public void expectsType_ValidInput_ReturnsCorrectType(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        StructDecNode structDecNode = (StructDecNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(structDecNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit StructDec");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitStructDecTestData() {
        return new Object[][] {
                {"struct STRUCT1 {variable varID : NUMBER,}"},
                {"struct struct2 {variable variable_ID : STRING,}"},
                {"struct coordinate {variable x : NUMBER}"},
                {"struct x_y_z {variable hasCoordinates : BOOLEAN}"}
        };
    }

    @Test(dataProvider = "visitStructDecTestData")
    public void visitStructDec_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        StructDecNode structDecNode = (StructDecNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(structDecNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit StructDec");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitFunctionDecTestData() {
        return new Object[][] {
                {"to _functionId():BOOLEAN { return a; }"},
                {"to function1(id1: NUMBER):NUMBER { return a; }"},
                {"to test_func(id1:NUMBER, id2:NUMBER):STRING { return a; }"},
        };
    }

    @Test(dataProvider = "visitFunctionDecTestData")
    public void visitFunctionDec_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        FuncDecNode funcDecNode = (FuncDecNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(funcDecNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit FuncDec");
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

        VarDecNode varDecNode = (VarDecNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(varDecNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit VarDec");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitCommentTestData() {
        return new Object[][] {
                {"#{ comment }"},
                {"#{comment}"},
        };
    }

    @Test(dataProvider = "visitCommentTestData")
    public void visitComment_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        CommentNode commentNode = (CommentNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(commentNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit Comment");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitStopTestData() {
        return new Object[][] {
                {"stop;"},
        };
    }

    @Test(dataProvider = "visitStopTestData")
    public void visitStop_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        StopNode stopNode = (StopNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(stopNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit Stop");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitFormalParamTestData() {
        return new Object[][] {
                {"to func(id0: NUMBER):NUMBER {}"},
                {"to func(id1: STRING):NUMBER {}"},
                {"to func(id2: BOOLEAN):NUMBER {}"},
        };
    }

    @Test(dataProvider = "visitFormalParamTestData")
    public void visitFormalParam_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        FuncDecNode funcDecNode = (FuncDecNode) mctlNode.get_children().get(0);
        FormalParamNode formalParamNode = funcDecNode.get_paramList().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(formalParamNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit FormalParam");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitStringMethodInvokeTestData() {
        return new Object[][] {
                {"\"var\".rav(bing, bong);"},
                {"\"vroom vroom\".go();"},
                {"\"1\".toString(2);"},
        };
    }

    @Test(dataProvider = "visitStringMethodInvokeTestData")
    public void visitStringMethodInvoke_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        StringMethodInvokeNode stringMethodInvokeNode = (StringMethodInvokeNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(stringMethodInvokeNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit StringMethodInvoke");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitVarMethodInvokeTestData() {
        return new Object[][] {
                {"var.rav(bing, bong);"},
                {"vroom.go();"},
                {"_1.toString(2);"},
        };
    }

    @Test(dataProvider = "visitVarMethodInvokeTestData")
    public void visitVarMethodInvoke_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        VarMethodInvokeNode varMethodInvokeNode = (VarMethodInvokeNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(varMethodInvokeNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit VarMethodInvoke");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitFuncInvokeTestData() {
        return new Object[][] {
                {"var(bing, bong);"},
                {"vroom();"},
                {"_1(2);"},
        };
    }

    @Test(dataProvider = "visitFuncInvokeTestData")
    public void visitFuncInvoke_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        FuncInvokeNode funcInvokeNode = (FuncInvokeNode) mctlNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(funcInvokeNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit FuncInvoke");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitBlockTestData() {
        return new Object[][] {
                {"if(true){}"},
                {"if(true){id=val;id=val;}"},
                {"if(true){id=val;id=val;id=val;}"},
                {"if(true){id=val;id=val;id=val;id=val;}"},
        };
    }

    @Test(dataProvider = "visitBlockTestData")
    public void visitBlock_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        IfStateNode ifStateNode = (IfStateNode) mctlNode.get_children().get(0);
        BlockNode blockNode = ifStateNode.get_blockChildrenNode().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(blockNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit Block");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitLineTestData() {
        return new Object[][] {
                {"if(true){id=val;id=val;}"},
                {"if(true){id=val;id=val;id=val;}"},
                {"if(true){id=val;id=val;id=val;id=val;}"},
        };
    }

    @Test(dataProvider = "visitLineTestData")
    public void visitLine_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        IfStateNode ifStateNode = (IfStateNode) mctlNode.get_children().get(0);
        BlockNode blockNode = ifStateNode.get_blockChildrenNode().get(0);
        LineNode lineNode = (LineNode) blockNode.get_children().get(0);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(lineNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit Line");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] visitMctlTestData() {
        return new Object[][] {
                {"test = 0.0;", 1},
                {"test = var1 + var2 + var3 + var4 + var5;", 1},
                {"struct x_y_z {variable hasCoordinates : BOOLEAN}", 1},
                {"if (true) { var = a; } else if (true) { var = b; } else { var = c; }", 1},
                {"if (a == b) { var = 1; } methodMan(); arr[y] = arr[i + 2]; repeat(10) { y++; }", 4},
                {"var1 = 1; var2 = 2; var3 = 3; var4 = 4; var5 = 5;", 5},
        };
    }

    @Test(dataProvider = "visitMctlTestData")
    public void visitMctl_ValidInput_ReturnsNull(String code) {
        ParseTree parseTree = createParseTree(code);
        MctlNode mctlNode = (MctlNode) parseTree.accept(astVisitor);

        MctlTypeDescriptor typeDescriptor = typeCheckingVisitor.visit(mctlNode);

        softAssert.assertTrue(typeDescriptor == null, "Visit Mctl");
        softAssert.assertAll();
    }
}
