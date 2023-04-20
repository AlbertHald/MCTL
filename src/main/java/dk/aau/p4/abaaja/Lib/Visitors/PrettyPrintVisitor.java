package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import java.util.List;

public class PrettyPrintVisitor implements INodeVisitor{

    /**
     * The string that will be used to represent one indentation level.
     */
    static final String indentation = "  ";

    /**
     * When declaring or calling a function with more than this number of parameters,
     * the parameters will be printed on separate lines to make it more readable.
     */
    static final int breakFuncParamLineIfMoreThan = 4;

    /**
     * The line to print when encountering a node that should never be encountered.
     */
    static final String notImplementedError = "ERROR - Unknown statement";

    void print(String out){
        System.out.print(out);
    }
    void printIndented(String out){
        System.out.print(indentation.repeat(indentationLevel) + out);
    }
    void printIndented(){
        printIndented("");
    }
    void printNewline(){
        System.out.println();
    }

    public static int indentationLevel = 0;
    void indentUp(){
        PrettyPrintVisitor.indentationLevel++;
    }
    void indentDown(){
        PrettyPrintVisitor.indentationLevel--;
    }

    public void visitChildren(BaseNode node) {
        for (BaseNode child : node.get_children()) {
            child.accept(this);
        }
    }


    public void visit(MctlNode node){
        this.visitChildren(node);
    }
    public void visit(LineNode node){
        this.visitChildren(node);
    }

    public void visit(BlockNode node){
        print("{");
        printNewline();
        indentUp();
        this.visitChildren(node);
        indentDown();
        printIndented("}");
    }

    public void visit(VarDecNode node){
        printIndented("variable " + node.get_id() + ": ");
        node.get_varDecType().accept(this);
        print(";");
        printNewline();
    }
    public void visit(FuncDecNode node){

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
        printNewline();
    }
    public void visit(StructDecNode node){
        printIndented("struct " + node.get_id() + ": {");
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
        printNewline();
    }

    public void visit(IfStateNode node){
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
        printNewline();
    }
    public void visit(RepeatStateNode node){
        printIndented("repeat(");
        node.get_repeatExp().accept(this);
        print(")");
        node.get_expBlock().accept(this);
        printNewline();
    }
    public void visit(AssStateNode node){
        printIndented();
        node.get_assignId().accept(this);
        print(" = ");
        node.get_assignExp().accept(this);
        print(";");
        printNewline();
    }
    public void visit(InvokeNode node){
        printIndented();
        node.get_invokeId().accept(this);
        print("(");
        List<ExpNode> paramList = node.get_paramExp();
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
        printNewline();
    }
    public void visit(ReturnNode node){
        BaseNode returnExp = node.get_returnExp();
        printIndented("return");
        if(returnExp != null){
            print(" ");
            returnExp.accept(this);
        }
        print(";");
        printNewline();
    }

    public void visit(FormalParamNode node){
        print(node.get_id() + ": ");
        node.get_type().accept(this);
    }
    public void visit(StopNode node){
        printIndented("stop;");
        printNewline();
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
        }
        if(node instanceof IDStructNode){
            this.visit((IDStructNode) node);
            return;
        }
        print(node.get_ID());
    }
    public void visit(IDArrayExpNode node){
        node.get_IDNode().accept(this);
        for (BaseNode child : node.get_children()) {
            print("[");
            child.accept(this);
            print("]");
        }
    }
    public void visit(IDStructNode node){
        List<BaseNode> childList = node.get_children();
        int numChildren = childList.size();

        for (int i = 0; i < numChildren; i++) {
            if(i > 0) print(".");
            childList.get(i).accept(this);
        }
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
     * Useless crap
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
    public void visit(ActualIDExpNode node){
        this.visit((ExpNode) node);
    }
}
