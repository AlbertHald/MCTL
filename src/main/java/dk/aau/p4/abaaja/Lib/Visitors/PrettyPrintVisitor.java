package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.TextSinks.ITextSink;

import java.util.List;

public class PrettyPrintVisitor implements INodeVisitor{

    /**
     * The string that will be used to represent one indentation level.
     */
    static final String indentation = "  ";

    /**
     * If the code has more than this number of consecutive empty lines,
     * the lines will be trimmed to the max allowed number.
     */
    static final int maxEmptyLines = 3;

    /**
     * When declaring or calling a function with more than this number of parameters,
     * the parameters will be printed on separate lines to make it more readable.
     */
    static final int breakFuncParamLineIfMoreThan = 4;

    /**
     * The line to print when encountering a node that should never be encountered.
     */
    static final String notImplementedError = "ERROR - Unknown statement";

    /**
     * Can output to different sinks depending on what is passed to `set_sink()`.
     * Can output to console by passing `System.out`, can output to String by passing `StringSink`.
     */
    public static ITextSink _sink;
    public void set_sink(ITextSink sink){
        PrettyPrintVisitor._sink = sink;
    }
    void print(String out){
        PrettyPrintVisitor._sink.print(out);
    }
    void printIndented(String out){
        PrettyPrintVisitor._sink.print(indentation.repeat(indentationLevel) + out);
    }
    void printIndented(){
        printIndented("");
    }
    void printNewline(){
        PrettyPrintVisitor.workingLineNumber++;
        PrettyPrintVisitor._sink.println();
    }

    public static int workingLineNumber = 1;
    public static int workingLineNumberOffset = 0;
    void beginLineNode(BaseNode node){
        int addedLines = 0;
        while(
                (node.get_lineNumber() + PrettyPrintVisitor.workingLineNumberOffset > PrettyPrintVisitor.workingLineNumber)
                && (addedLines < maxEmptyLines)
        ){
            addedLines++;
            printNewline();
        }

    }
    void endLineNode(BaseNode node){
        PrettyPrintVisitor.workingLineNumberOffset = workingLineNumber - node.get_lineEndNumber();
        printNewline();
    }

    public static int indentationLevel = 0;
    void indentUp(){
        PrettyPrintVisitor.indentationLevel++;
    }
    void indentDown(){
        PrettyPrintVisitor.indentationLevel--;
    }

    void visitChildren(BaseNode node) {
        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }

    void resetState(){
        PrettyPrintVisitor.indentationLevel = 0;
        PrettyPrintVisitor.workingLineNumber = 1;
        PrettyPrintVisitor.workingLineNumberOffset = 0;
    }


    public void visit(MctlNode node){
        resetState();
        this.visitChildren(node);
    }
    public void visit(LineNode node){
        this.visitChildren(node);
    }

    public void visit(BlockNode node){
        print("{");
        printNewline();
        indentUp();
        if(node.get_children().size() > 0){
            this.visitChildren(node);
        }else{
            printNewline();
        }
        indentDown();
        printIndented("}");
    }

    public void visit(VarDecNode node){
        beginLineNode(node);
        printIndented("variable " + node.get_id() + ": ");
        node.get_varDecType().accept(this);
        print(";");
        endLineNode(node);
    }
    public void visit(FuncDecNode node){
        beginLineNode(node);
        printIndented("to " + node.get_id() + " (");
        List<FormalParamNode> paramList = node.get_paramList();
        int numParams = paramList.size();
        boolean breakParamLines = numParams > breakFuncParamLineIfMoreThan;

        for (int i = 0; i < numParams; i++) {
            boolean isFirstParam = i == 0;
            boolean isLastParam = i == numParams - 1;
            if(breakParamLines) {
                if(isFirstParam) {
                    printNewline();
                    indentUp();
                }
                printIndented();
            }
            paramList.get(i).accept(this);
            if(breakParamLines) {
                if(isLastParam) indentDown();
                print(",");
                printNewline();
            }else if(!isLastParam) {
                print(", ");
            }
        }
        print("): ");
        node.get_returnType().accept(this);
        print(" ");
        node.get_funcBlock().accept(this);
        endLineNode(node);
    }
    public void visit(StructDecNode node){
        beginLineNode(node);
        printIndented("struct " + node.get_id() + " {");
        printNewline();
        indentUp();
        for (VarDecNode declaration : node.get_declarations()) {
            printIndented("variable " + declaration.get_id() + ": ");
            declaration.get_varDecType().accept(this);
            print(",");
            printNewline();
        }
        indentDown();
        printIndented("};");
        endLineNode(node);
    }

    public void visit(IfStateNode node){
        beginLineNode(node);
        List<ExpNode> expList = node.get_expChildren();
        List<BlockNode> blockList = node.get_blockChildrenNode();
        int numExps = expList.size();
        int numBlocks = blockList.size();

        printIndented();
        for (int i = 0; i < numBlocks; i++) {
            if(numExps > i){
                if(i > 0) print("else ");
                print("if(");
                expList.get(i).accept(this);
                print(")");
            }else{
                print("else");
            }
            blockList.get(i).accept(this);
        }
        endLineNode(node);
    }
    public void visit(RepeatStateNode node){
        beginLineNode(node);
        printIndented("repeat(");
        node.get_repeatExp().accept(this);
        print(")");
        node.get_expBlock().accept(this);
        endLineNode(node);
    }
    public void visit(AssStateNode node){
        beginLineNode(node);
        printIndented();
        node.get_assignId().accept(this);
        print(" = ");
        node.get_assignExp().accept(this);
        print(";");
        endLineNode(node);
    }
    public void visit(InvokeNode node){
        beginLineNode(node);
        printIndented();
        if(node instanceof VarMethodInvokeNode){
            ((VarMethodInvokeNode) node).get_varId().accept(this);
            print(".");
        }else if(node instanceof StringMethodInvokeNode){
            ((StringMethodInvokeNode) node).get_string().accept(this);
            print(".");
        }
        node.get_id().accept(this);
        print("(");
        List<ExpNode> paramList = node.get_paramExps();
        int numParams = paramList.size();
        boolean breakParamLines = numParams > breakFuncParamLineIfMoreThan;

        for (int i = 0; i < numParams; i++) {
            boolean isFirstParam = i == 0;
            boolean isLastParam = i == numParams - 1;
            if(breakParamLines) {
                if(isFirstParam) {
                    printNewline();
                    indentUp();
                }
                printIndented();
            }
            paramList.get(i).accept(this);
            if(breakParamLines) {
                if(isLastParam) indentDown();
                print(",");
                printNewline();
            }else if(!isLastParam) {
                print(", ");
            }
        }
        if(breakParamLines) {
            printIndented(");");
        }else {
            print(");");
        }
        endLineNode(node);
    }
    public void visit(ReturnNode node){
        BaseNode returnExp = node.get_returnExp();
        beginLineNode(node);
        printIndented("return");
        if(returnExp != null){
            print(" ");
            returnExp.accept(this);
        }
        print(";");
        endLineNode(node);
    }

    public void visit(FormalParamNode node){
        print(node.get_id() + ": ");
        node.get_type().accept(this);
    }
    public void visit(StopNode node){
        beginLineNode(node);
        printIndented("stop;");
        endLineNode(node);
    }
    public void visit(TypeNode node){
        print(node.get_type());
    }

    public void visit(UnaryExpNode node){
        ExpNode child = (ExpNode) node.get_children().get(0);

        print(node.get_operatorLiteral());
        if(child.get_arity() > 1) print("(");
        child.accept(this);
        if(child.get_arity() > 1) print(")");
    }
    public void visit(TypecastExpNode node){
        ExpNode child = node.get_expression_node();

        print("(");
        node.get_typeNode().accept(this);
        print(") ");
        if(child.get_arity() > 1) print("(");
        child.accept(this);
        if(child.get_arity() > 1) print(")");
    }
    public void visit(BinaryExpNode node){
        List<BaseNode> children = node.get_children();
        ExpNode left = (ExpNode) children.get(0);
        ExpNode right = (ExpNode) children.get(1);

        if(left.get_arity() > 1) print("(");
        left.accept(this);
        if(left.get_arity() > 1) print(")");
        print(" " + node.get_operatorLiteral() + " ");
        if(right.get_arity() > 1) print("(");
        right.accept(this);
        if(right.get_arity() > 1) print(")");
    }
    public void visit(IDExpNode node){
        // TODO: Why are these nodes not automatically visited by the correct visitor?
        if(node instanceof IDArrayExpNode){
            this.visit((IDArrayExpNode) node);
            return;
        }else if(node instanceof IDStructNode){
            this.visit((IDStructNode) node);
            return;
        }else if(node instanceof ActualIDExpNode){
            this.visit((ActualIDExpNode) node);
            return;
        }

        if(node.get_idNode() != null){
            node.get_idNode().accept(this);
        }
    }
    public void visit(IDArrayExpNode node){
        node.get_idNode().accept(this);
        print("[");
        node.get_accessor().accept(this);
        print("]");
    }
    public void visit(IDStructNode node){
        node.get_idNode().accept(this);
        print(".");
        node.get_accessor().accept(this);
    }
    public void visit(ActualIDExpNode node){
        print(node.get_id());
    }
    public void visit(BoolExpNode node){
        print(node.get_result() ? "true" : "false");
    }
    public void visit(NumExpNode node){
        // The `replaceAll` removes unnecessary trailing zeroes from output
        print(node.get_result().toString().replaceAll("\\.?0*(?=$)", ""));
    }
    public void visit(StringExpNode node){
        print(node.get_result());
    }

    /**
     * After a thorough evaluation of our current operations and needs, we have come to the difficult conclusion that the following classes are not necessary.
     * Please know that this decision was not a reflection of their return types or other contributions to the parser.
     * They have been valued members of this scope, and we appreciate all the hard work and bytes they have put in during their time here.
     */
    public void visit(DecNode node){
        printNewline();
        printIndented(notImplementedError);
        printNewline();
    }
    public void visit(StateNode node){
        printNewline();
        printIndented(notImplementedError);
        printNewline();
    }
    public void visit(ExpNode node){
        printNewline();
        printIndented(notImplementedError);
        printNewline();
    }
    public void visit(FuncInvokeNode node){
        this.visit((InvokeNode) node);
    }
    public void visit(VarMethodInvokeNode node){
        this.visit((InvokeNode) node);
    }
    public void visit(StringMethodInvokeNode node){
        this.visit((InvokeNode) node);
    }
    public void visit(BoolTypeNode node){
        this.visit((TypeNode) node);
    }
    public void visit(NumTypeNode node){
        this.visit((TypeNode) node);
    }
    public void visit(StringTypeNode node){
        this.visit((TypeNode) node);
    }
    public void visit(NothingTypeNode node){
        this.visit((TypeNode) node);
    }
    public void visit(IDTypeNode node){
        this.visit((TypeNode) node);
    }
    public void visit(MulExpNode node){
        this.visit((BinaryExpNode) node);
    }
    public void visit(AddExpNode node){
        this.visit((BinaryExpNode) node);
    }
    public void visit(AndExpNode node){
        this.visit((BinaryExpNode) node);
    }
    public void visit(OrExpNode node){
        this.visit((BinaryExpNode) node);
    }
    public void visit(CompExpNode node){
        this.visit((BinaryExpNode) node);
    }
    public void visit(EqualExpNode node){
        this.visit((BinaryExpNode) node);
    }
}
