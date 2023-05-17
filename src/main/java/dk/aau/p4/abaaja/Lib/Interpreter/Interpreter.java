package dk.aau.p4.abaaja.Lib.Interpreter;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemType;
import dk.aau.p4.abaaja.Lib.Symbols.*;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.*;
import dk.aau.p4.abaaja.Lib.Visitors.INodeVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Interpreter implements INodeVisitor {
    public Interpreter(ProblemCollection problemCollection, SymbolTable symbolTable, IGameBridge gameBridge) {
        this.problemCollection = problemCollection;
        this.symbolTable = symbolTable;
        this.gameBridge = gameBridge;
    }

    /**
     * If a boolean repeat runs for more than this number, the interpreter will stop the loop.
     * This is similar to a stack overflow error, but simpler.
     */
    private final int MAX_REPEAT_ITERATIONS = 100000;

    private ProblemCollection problemCollection;
    private SymbolTable symbolTable;
    private IGameBridge gameBridge;

    private void visitChildren(List<BaseNode> nodes) {
        visitChildrenStoppable(nodes);
    }
    private BaseNode visitChildrenStoppable(List<BaseNode> nodes) {
        for (BaseNode child : nodes) {
            if (child instanceof FuncDecNode) {
                child.accept(this);
            }
        }
        for (BaseNode child : nodes) {
            if (!(child instanceof FuncDecNode)) {
                BaseNode stopper = visitStoppable(child);
                if(stopper != null) return stopper;
            }
        }
        return null;
    }

    public void visit(MctlNode node) {
        visitChildren(node.get_children());
    }

    public void visit(LineNode node) {
        visitChildren(node.get_children());
    }

    public void visit(BlockNode node) {
        visitStoppable(node);
    }
    public BaseNode visitStoppable(BlockNode node) {
        symbolTable.createScope();
        BaseNode stopper = visitChildrenStoppable(node.get_children());
        symbolTable.closeScope();
        return stopper;
    }

    public BaseNode visitStoppable(BaseNode node) {
        if(node instanceof StopNode) {
            return visitStoppable((StopNode) node);
        }if(node instanceof ReturnNode){
            return visitStoppable((ReturnNode) node);
        }else if(node instanceof IfStateNode){
            return visitStoppable((IfStateNode) node);
        }else if(node instanceof RepeatStateNode) {
            return visitStoppable((RepeatStateNode) node);
        }else{
            node.accept(this);
            return null;
        }
    }

    public void visit(CommentNode node) {
        //Intentionally left blank. Comments do not influence code.
    }

    public void visit(DecNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unknown declaration node", node.get_lineNumber());
    }

    public void visit(StateNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unknown control structure", node.get_lineNumber());
    }

    public void visit(VarDecNode node) {
        Symbol symbol = resolve(node.get_varDecType());
        symbol.set_name(node.get_id());
        symbolTable.insertSymbol(symbol);
    }

    public void visit(FuncDecNode node) {
        FuncSymbol symbol = new FuncSymbol();
        symbol.set_name(node.get_id());

        Symbol type = resolve(node.get_returnType());
        symbol.set_type(type.get_type().clone());

        symbol.set_originScope(symbolTable.get_currentScope());

        symbol.set_funcBlock(node.get_funcBlock());
        symbol.set_formalParams(node.get_paramList());
        symbolTable.insertSymbol(symbol);
    }

    public void visit(StructDecNode node) {
        //Intentionally left blank. Struct definitions are only used for type checking.
    }

    public void visit(IfStateNode node) {
        visitStoppable(node);
    }
    public BaseNode visitStoppable(IfStateNode node) {
        List<ExpNode> expList = node.get_expChildren();
        List<BlockNode> blockList = node.get_blockChildrenNode();
        int numExps = expList.size();
        int numBlocks = blockList.size();

        iterateBlocks: for (int i = 0; i < numBlocks; i++) {
            testExp: if(numExps > i){
                Symbol expSymbol = resolve(expList.get(i));
                if(expSymbol == null || expSymbol.get_value() == null){
                    problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Ignoring if statement with no expression", node.get_lineNumber());
                    break testExp;
                }
                if(!((boolean) expSymbol.get_value())){
                    continue iterateBlocks;
                }
            }
            return visitStoppable(blockList.get(i));
        }
        return null;
    }

    public void visit(RepeatStateNode node) {
        visitStoppable(node);
    }
    public BaseNode visitStoppable(RepeatStateNode node) {
        Symbol symbol = resolve(node.get_repeatExp());
        if(symbol == null || symbol.get_value() == null){
            problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Ignoring repeat with no sentinel expression", node.get_lineNumber());
            return null;
        }
        BaseNode stopper = null;

        // If exp is boolean, run until the user code changes the boolean to false
        if(symbol.get_type() instanceof MctlBooleanDescriptor){
            int iterations = 0;
            while(stopper == null && (boolean) resolve(node.get_repeatExp()).get_value()) {
                stopper = visitStoppable(node.get_expBlock());
                iterations++;
                if(iterations > MAX_REPEAT_ITERATIONS){
                    problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Stopping infinite repeat", node.get_lineNumber());
                    return null;
                }
            }

        // If exp is numeric, run for the set amount passed
        }else if(symbol.get_type() instanceof MctlNumberDescriptor){
            int repeats = ((Number) symbol.get_value()).intValue();
            for(int i = repeats; i > 0; i--){
                if(stopper != null){
                    if(stopper instanceof StopNode){
                        return null;
                    }else{
                        return stopper;
                    }
                }
                stopper = visitStoppable(node.get_expBlock());
            }
        }else{
            problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unsupported repeat expression type: " + symbol.get_type().get_type_literal(), node.get_lineNumber());
            return null;
        }

        if(stopper instanceof StopNode){
            return null;
        }else{
            return stopper;
        }
    }

    public void visit(AssStateNode node) {
        Symbol symbol = resolve(node.get_assignId());
        if(symbol == null){
            problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered assignment to uninitialized variable", node.get_lineNumber());
            return;
        }

        Symbol result = resolve(node.get_assignExp()).clone();

        symbol.set_value(result.get_value());
        symbol.set_indexes(result.get_indexes());
        symbol.set_fields(result.get_fields());
        symbol.set_type(result.get_type());
    }

    public void visit(InvokeNode node) {
        if(node instanceof FuncInvokeNode){
            visit((FuncInvokeNode) node);
        }else if(node instanceof VarMethodInvokeNode){
            visit((VarMethodInvokeNode) node);
        }else if(node instanceof StringMethodInvokeNode){
            visit((StringMethodInvokeNode) node);
        }else{
            problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unknown invocation structure", node.get_lineNumber());
        }
    }
    public Symbol resolve(InvokeNode node) {
        if(node instanceof FuncInvokeNode){
            return resolve((FuncInvokeNode) node);
        }else if(node instanceof VarMethodInvokeNode){
            return resolve((VarMethodInvokeNode) node);
        }else if(node instanceof StringMethodInvokeNode){
            return resolve((StringMethodInvokeNode) node);
        }
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unknown invocation structure", node.get_lineNumber());
        return new Symbol(new MctlNothingDescriptor(), null);
    }

    public void visit(FuncInvokeNode node) {
        resolve(node);
    }
    public Symbol resolve(FuncInvokeNode node) {
        Symbol result = new Symbol(new MctlNothingDescriptor(), null);
        switch(resolve(node.get_id()).get_name()){
            case "print" -> {
                String text = (String) resolve(node.get_paramExps().get(0)).get_value();
                if(text == null) text = "";
                gameBridge.print(text);
            }
            case "read" -> {
                String bridgeResult = gameBridge.read();
                result.set_type(new MctlStringDescriptor());
                result.set_value(bridgeResult);
                return result;
            }
            case "setDelay" -> {
                Symbol delay = resolve(node.get_paramExps().get(0));
                gameBridge.setDelay(((Number) delay.get_value()).intValue());
            }
            case "moveForward" -> {
                boolean bridgeResult = gameBridge.moveForward();
                result.set_type(new MctlBooleanDescriptor());
                result.set_value(bridgeResult);
                return result;
            }
            case "moveUp" -> {
                boolean bridgeResult = gameBridge.moveUp();
                result.set_type(new MctlBooleanDescriptor());
                result.set_value(bridgeResult);
                return result;
            }
            case "moveDown" -> {
                boolean bridgeResult = gameBridge.moveDown();
                result.set_type(new MctlBooleanDescriptor());
                result.set_value(bridgeResult);
                return result;
            }
            case "moveBackward" -> {
                boolean bridgeResult = gameBridge.moveBackward();
                result.set_type(new MctlBooleanDescriptor());
                result.set_value(bridgeResult);
                return result;
            }
            case "turnLeft" -> {
                gameBridge.turnLeft();
            }
            case "turnRight" -> {
                gameBridge.turnRight();
            }
            case "breakFront" -> {
                gameBridge.breakFront();
            }
            case "breakAbove" -> {
                gameBridge.breakAbove();
            }
            case "breakUnder" -> {
                gameBridge.breakUnder();
            }
            case "placeFront" -> {
                Symbol blockId = resolve(node.get_paramExps().get(0));
                gameBridge.placeFront((String) blockId.get_value());
            }
            case "placeAbove" -> {
                Symbol blockId = resolve(node.get_paramExps().get(0));
                gameBridge.placeAbove((String) blockId.get_value());
            }
            case "placeUnder" -> {
                Symbol blockId = resolve(node.get_paramExps().get(0));
                gameBridge.placeUnder((String) blockId.get_value());
            }
            case "blockFront" -> {
                String bridgeResult = gameBridge.blockFront();
                result.set_type(new MctlStringDescriptor());
                result.set_value(bridgeResult);
                return result;
            }
            case "blockAbove" -> {
                String bridgeResult = gameBridge.blockAbove();
                result.set_type(new MctlStringDescriptor());
                result.set_value(bridgeResult);
                return result;
            }
            case "blockUnder" -> {
                String bridgeResult = gameBridge.blockUnder();
                result.set_type(new MctlStringDescriptor());
                result.set_value(bridgeResult);
                return result;
            }
            default -> {
                FuncSymbol symbol = (FuncSymbol) resolve(node.get_id());

                // Resolve the values of all actual parameters at calltime
                List<Symbol> parameters = new ArrayList<>();
                int i = 0;
                for(ExpNode actualParam : node.get_paramExps()){
                    Symbol paramSymbol = resolve(actualParam).clone();
                    paramSymbol.set_name(symbol.get_formalParams().get(i).get_id());
                    parameters.add(paramSymbol);
                    i++;
                }

                // Variables are statically scoped. We open the function block in the scope the function was declared in.
                Scope currentScope = symbolTable.get_currentScope();
                symbolTable.set_currentScope(symbol.get_originScope());
                symbolTable.createScope();

                // Insert the values of the actual parameters in the function block scope
                for(Symbol parameter : parameters){
                    symbolTable.insertSymbol(parameter);
                }

                // Run the block
                BaseNode stopper = visitChildrenStoppable(symbol.get_funcBlock().get_children());
                if(stopper instanceof ReturnNode returnNode){
                    if(returnNode.get_returnExp() != null) result = resolve(returnNode.get_returnExp());
                }

                // Close the static function scope and return to the current scope
                symbolTable.closeScope();
                symbolTable.set_currentScope(currentScope);

                return result;
            }
        }
        return result;
    }

    public void visit(VarMethodInvokeNode node) {
        resolve(node);
    }
    public Symbol resolve(VarMethodInvokeNode node) {
        Symbol subject = resolve(node.get_varId());

        // If string, convert to a string invoke and call that instead.
        if (subject.get_type() instanceof MctlStringDescriptor && subject.get_indexes().size() == 0) {
            StringMethodInvokeNode stringNode = new StringMethodInvokeNode();
            stringNode.set_lineNumber(node.get_lineNumber());
            stringNode.set_lineEndNumber(node.get_lineEndNumber());
            stringNode.set_id(node.get_id());
            stringNode.set_paramExps(node.get_paramExps());
            StringExpNode expNode = new StringExpNode();
            expNode.set_lineNumber(node.get_lineNumber());
            expNode.set_lineEndNumber(node.get_lineEndNumber());
            expNode.set_result((String) subject.get_value());
            stringNode.set_string(expNode);
            return resolve(stringNode);
        }

        String methodName = resolve(node.get_id()).get_name();
        Symbol result = new Symbol(new MctlNothingDescriptor(), null);
        switch (methodName) {
            case "length" -> {
                result.set_type(new MctlNumberDescriptor());
                result.set_value( ((Number) subject.get_listLength()).doubleValue() );
            }
            case "add" -> {
                result = subject.clone();

                result.add_index(resolve(node.get_paramExps().get(0)));
            }
            case "remove" -> {
                result = subject.clone();

                result.remove_index();
            }
            case "sublist" -> {
                result.set_type(subject.get_type().clone());

                int start = ((Number) resolve(node.get_paramExps().get(0)).get_value()).intValue();
                int end = ((Number) resolve(node.get_paramExps().get(1)).get_value()).intValue();
                if(subject.get_listLength() == 0){
                    problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Trying to call sublist on an empty list.", node.get_lineNumber());
                }else if(start < 0 || subject.get_listLength() <= start){
                    problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Trying to access list at index " + start + " when only index 0-" + (subject.get_listLength()-1) + " is valid.", node.get_lineNumber());
                }else if(end < start || subject.get_listLength() <= end){
                    problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Trying to access end of list at index " + end + " when only index " + start + "-" + (subject.get_listLength()-1) + " is valid.", node.get_lineNumber());
                }else{
                    for(int i = start; i <= end; i++){
                        result.add_index(subject.get_index(start));
                    }
                }
            }
            case "indexesOf" -> {
                result.set_type(new MctlArrayTypeDescriptor(new MctlNumberDescriptor(), 1));
                List<Symbol> subjectList = subject.get_indexes();
                Symbol query = resolve(node.get_paramExps().get(0));

                for(int i = 0; i < subjectList.size(); i++){
                    Symbol subjectIndex = subjectList.get(i);
                    if(subjectIndex == null) continue;
                    if(subjectIndex.get_value().equals(query.get_value())){
                        Symbol match = new Symbol(new MctlNumberDescriptor(), ((Number) i).doubleValue());
                        result.add_index(match);
                    }
                }
            }
            default -> {
                problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unsupported method invocation on " + subject.get_type().get_type_literal() + ": " + methodName, node.get_lineNumber());
            }
        }
        return result;
    }

    public void visit(StringMethodInvokeNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected method invocation on STRING", node.get_lineNumber());
    }
    public Symbol resolve(StringMethodInvokeNode node) {
        String methodName = resolve(node.get_id()).get_name();
        String subject = (String) resolve(node.get_string()).get_value();
        Symbol result = new Symbol(new MctlNothingDescriptor(), null);
        switch(methodName){
            case "length" -> {
                result.set_type(new MctlNumberDescriptor());
                result.set_value(((Number) subject.length()).doubleValue());
            }
            case "add" -> {
                result.set_type(new MctlStringDescriptor());
                String end = (String) resolve(node.get_paramExps().get(0)).get_value();
                result.set_value(subject + end);
            }
            case "remove" -> {
                result.set_type(new MctlStringDescriptor());
                result.set_value(subject.substring(0, subject.length() - 1));
            }
            case "substring" -> {
                result.set_type(new MctlStringDescriptor());
                int start = ((Number) resolve(node.get_paramExps().get(0)).get_value()).intValue();
                int end = ((Number) resolve(node.get_paramExps().get(1)).get_value()).intValue();
                if(subject.length() == 0){
                    problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Trying to call substring on an empty STRING.", node.get_lineNumber());
                    result.set_value("");
                }else if(start < 0 || subject.length() <= start){
                    problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Trying to access STRING at index " + start + " when only index 0-" + (subject.length()-1) + " is valid.", node.get_lineNumber());
                    result.set_value("");
                }else if(end < start || subject.length() <= end){
                    problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Trying to access end of STRING at index " + end + " when only index " + start + "-" + (subject.length()-1) + " is valid.", node.get_lineNumber());
                    result.set_value("");
                }else{
                    result.set_value(subject.substring(start, end+1));
                }
            }
            case "indexesOf" -> {
                result.set_type(new MctlArrayTypeDescriptor(new MctlNumberDescriptor(), 1));
                String query = (String) resolve(node.get_paramExps().get(0)).get_value();
                if(query.length() == 0) break;
                int safeLastIndex = (subject.length() - query.length());

                for(int i = 0; i <= safeLastIndex; i++){
                    if(subject.substring(i, i+query.length()).equals(query)){
                        Symbol match = new Symbol(new MctlNumberDescriptor(), ((Number) i).doubleValue());
                        result.add_index(match);
                    }
                }
            }
            default -> {
                problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unsupported method invocation on STRING: " + methodName, node.get_lineNumber());
            }
        }
        return result;
    }

    public void visit(ReturnNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered return outside of returnable structure", node.get_lineNumber());
    }
    public BaseNode visitStoppable(ReturnNode node) {
        return node;
    }

    public void visit(FormalParamNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected formal parameter: " + node.get_id(), node.get_lineNumber());
    }
    public void visit(StopNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered stop outside of stoppable structure", node.get_lineNumber());
    }
    public BaseNode visitStoppable(StopNode node){
        return node;
    }

    public void visit(TypeNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected type definition: " + node.get_type(), node.get_lineNumber());
    }
    public Symbol resolve(TypeNode node){
        if(node instanceof BoolTypeNode){
            return resolve((BoolTypeNode) node);
        }else if(node instanceof NumTypeNode){
            return resolve((NumTypeNode) node);
        }else if(node instanceof StringTypeNode){
            return resolve((StringTypeNode) node);
        }else if(node instanceof NothingTypeNode){
            return resolve((NothingTypeNode) node);
        }else if(node instanceof IDTypeNode){
            return resolve((IDTypeNode) node);
        }
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unsupported type definition: " + node.get_type(), node.get_lineNumber());
        return null;
    }

    public void visit(BoolTypeNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected type definition: " + node.get_type(), node.get_lineNumber());
    }
    public Symbol resolve(BoolTypeNode node) {
        MctlTypeDescriptor type = new MctlBooleanDescriptor();
        return new Symbol(type);
    }

    public void visit(NumTypeNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected type definition: " + node.get_type(), node.get_lineNumber());
    }
    public Symbol resolve(NumTypeNode node) {
        MctlTypeDescriptor type = new MctlNumberDescriptor();
        return new Symbol(type);
    }

    public void visit(StringTypeNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected type definition: " + node.get_type(), node.get_lineNumber());
    }
    public Symbol resolve(StringTypeNode node) {
        MctlTypeDescriptor type = new MctlStringDescriptor();
        return new Symbol(type);
    }

    public void visit(NothingTypeNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected type definition: " + node.get_type(), node.get_lineNumber());

    }
    public Symbol resolve(NothingTypeNode node) {
        MctlTypeDescriptor type = new MctlNothingDescriptor();
        return new Symbol(type);
    }

    public void visit(IDTypeNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected type definition: " + node.get_type(), node.get_lineNumber());
    }
    public Symbol resolve(IDTypeNode node) {
        MctlTypeDescriptor type = new MctlStructDescriptor(node.get_type());
        return new Symbol(type);
    }

    public void visit(ExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected expression", node.get_lineNumber());
    }
    public Symbol resolve(ExpNode node){
        if(node instanceof InvokeExpNode) {
            return resolve((InvokeExpNode) node);
        }else if(node instanceof UnaryExpNode){
            return resolve((UnaryExpNode) node);
        }else if(node instanceof TypecastExpNode){
            return resolve((TypecastExpNode) node);
        }else if(node instanceof BinaryExpNode){
            return resolve((BinaryExpNode) node);
        }else if(node instanceof IDExpNode){
            return resolve((IDExpNode) node);
        }else if(node instanceof BoolExpNode){
            return resolve((BoolExpNode) node);
        }else if(node instanceof NumExpNode){
            return resolve((NumExpNode) node);
        }else if(node instanceof StringExpNode){
            return resolve((StringExpNode) node);
        }

        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unknown expression", node.get_lineNumber());
        return null;
    }

    @Override
    public void visit(InvokeExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected invocation expression", node.get_lineNumber());
    }
    public Symbol resolve(InvokeExpNode node) {
        return resolve(node.getInvokeNode());
    }

    public void visit(UnaryExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected unary expression", node.get_lineNumber());
    }
    public Symbol resolve(UnaryExpNode node) {
        Symbol result = resolve(node.get_unaryExp()).clone();
        switch (node.get_operatorLiteral()) {
            case "!" -> {
                result.set_value(!(boolean) result.get_value());
            }
            case "+" -> {
                double initial = ((Number) result.get_value()).doubleValue();
                result.set_value(initial < 0 ? -initial : initial);
            }
            case "-" -> {
                double initial = ((Number) result.get_value()).doubleValue();
                result.set_value(initial >= 0 ? -initial : initial);
            }
            default -> {
                problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unknown unary expression", node.get_lineNumber());
            }
        }
        return result;
    }

    public void visit(TypecastExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected typecast expression", node.get_lineNumber());
    }
    public Symbol resolve(TypecastExpNode node) {
        Symbol original = resolve(node.get_expression_node()).clone();
        Symbol cast = resolve(node.get_typeNode()).clone();
        MctlTypeDescriptor originalType = original.get_type();
        MctlTypeDescriptor castType = cast.get_type();

        if(Objects.equals(originalType.get_type_literal(), castType.get_type_literal())) {
            cast.set_value(original.get_value());
            cast.set_type(original.get_type().clone());
        }else if(originalType instanceof MctlBooleanDescriptor && castType instanceof MctlStringDescriptor){
            Boolean value = (Boolean) original.get_value();
            if(value == null){
                cast.set_value(null);
            }else {
                cast.set_value(value ? "true" : "false");
            }
            cast.set_type(new MctlStringDescriptor());
        }else if(originalType instanceof MctlNumberDescriptor && castType instanceof MctlStringDescriptor){
            Number value = (Number) original.get_value();
            if(value == null){
                cast.set_value(null);
            }else{
                // The `replaceAll` removes unnecessary trailing zeroes from output
                cast.set_value(value.toString().replaceAll("\\.0*(?=$)", ""));
            }
            cast.set_type(new MctlStringDescriptor());
        }else if(originalType instanceof MctlStringDescriptor && castType instanceof MctlNumberDescriptor){
            String value = (String) original.get_value();
            if(value == null){
                cast.set_value(null);
                cast.set_type(new MctlNumberDescriptor());
            }else{
                try {
                    cast.set_value(Double.parseDouble((String) original.get_value()));
                    cast.set_type(new MctlNumberDescriptor());
                } catch (Exception exception) {
                    problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Unable to cast STRING \"" + original.get_value() + "\" to NUMBER", node.get_lineNumber());
                    cast.set_value(null);
                }
            }
        }else{
            problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Illegal cast from type " + originalType.get_type_literal() + " to " + castType.get_type_literal(), node.get_lineNumber());
        }

        return cast;
    }

    public void visit(BinaryExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected binary expression", node.get_lineNumber());
    }
    public Symbol resolve(BinaryExpNode node) {
        if(node instanceof MulExpNode){
            return resolve((MulExpNode) node);
        }else if(node instanceof AddExpNode){
            return resolve((AddExpNode) node);
        }else if(node instanceof AndExpNode){
            return resolve((AndExpNode) node);
        }else if(node instanceof OrExpNode){
            return resolve((OrExpNode) node);
        }else if(node instanceof CompExpNode){
            return resolve((CompExpNode) node);
        }else if(node instanceof EqualExpNode){
            return resolve((EqualExpNode) node);
        }
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unknown binary expression", node.get_lineNumber());
        return null;
    }

    public void visit(MulExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected arithmetic expression", node.get_lineNumber());
    }
    public Symbol resolve(MulExpNode node) {
        Symbol leftSymbol = resolve((ExpNode) node.get_children().get(0)).clone();
        Symbol rightSymbol = resolve((ExpNode) node.get_children().get(1)).clone();
        double leftInit = ((Number) leftSymbol.get_value()).doubleValue();
        double rightInit = ((Number) rightSymbol.get_value()).doubleValue();
        switch(node.get_operatorLiteral()) {
            case "*" -> {
                leftSymbol.set_value(leftInit * rightInit);
                return leftSymbol;
            }
            case "/" -> {
                leftSymbol.set_value(leftInit / rightInit);
                return leftSymbol;
            }
            case "%" -> {
                leftSymbol.set_value(leftInit % rightInit);
                return leftSymbol;
            }
            default -> {
                problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unknown arithmetic expression", node.get_lineNumber());
            }
        }
        return null;
    }

    public void visit(AddExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected arithmetic expression", node.get_lineNumber());
    }
    public Symbol resolve(AddExpNode node) {
        Symbol leftSymbol = resolve((ExpNode) node.get_children().get(0)).clone();
        Symbol rightSymbol = resolve((ExpNode) node.get_children().get(1)).clone();
        double leftInit = ((Number) leftSymbol.get_value()).doubleValue();
        double rightInit = ((Number) rightSymbol.get_value()).doubleValue();
        switch(node.get_operatorLiteral()) {
            case "+" -> {
                leftSymbol.set_value(leftInit + rightInit);
                return leftSymbol;
            }
            case "-" -> {
                leftSymbol.set_value(leftInit - rightInit);
                return leftSymbol;
            }
            default -> {
                problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unknown arithmetic expression", node.get_lineNumber());
            }
        }
        return null;
    }

    public void visit(AndExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected logical expression", node.get_lineNumber());
    }
    public Symbol resolve(AndExpNode node) {
        Symbol leftSymbol = resolve((ExpNode) node.get_children().get(0)).clone();
        Symbol rightSymbol = resolve((ExpNode) node.get_children().get(1)).clone();
        boolean leftInit = (boolean) leftSymbol.get_value();
        boolean rightInit = (boolean) rightSymbol.get_value();
        leftSymbol.set_value(leftInit && rightInit);
        return leftSymbol;
    }

    public void visit(OrExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected logical expression", node.get_lineNumber());
    }
    public Symbol resolve(OrExpNode node) {
        Symbol leftSymbol = resolve((ExpNode) node.get_children().get(0)).clone();
        Symbol rightSymbol = resolve((ExpNode) node.get_children().get(1)).clone();
        boolean leftInit = (boolean) leftSymbol.get_value();
        boolean rightInit = (boolean) rightSymbol.get_value();
        leftSymbol.set_value(leftInit || rightInit);
        return leftSymbol;
    }

    public void visit(CompExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected comparison expression", node.get_lineNumber());
    }
    public Symbol resolve(CompExpNode node) {
        Symbol leftSymbol = resolve((ExpNode) node.get_children().get(0)).clone();
        Symbol rightSymbol = resolve((ExpNode) node.get_children().get(1)).clone();
        double leftInit = ((Number) leftSymbol.get_value()).doubleValue();
        double rightInit = ((Number) rightSymbol.get_value()).doubleValue();
        Symbol resultSymbol = new Symbol(new MctlBooleanDescriptor());
        switch(node.get_operatorLiteral()) {
            case "<=" -> {
                resultSymbol.set_value(leftInit <= rightInit);
                return resultSymbol;
            }
            case ">=" -> {
                resultSymbol.set_value(leftInit >= rightInit);
                return resultSymbol;
            }
            case "<" -> {
                resultSymbol.set_value(leftInit < rightInit);
                return resultSymbol;
            }
            case ">" -> {
                resultSymbol.set_value(leftInit > rightInit);
                return resultSymbol;
            }
            default -> {
                problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unknown comparison expression", node.get_lineNumber());
            }
        }
        return null;
    }

    public void visit(EqualExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected comparison expression", node.get_lineNumber());
    }
    public Symbol resolve(EqualExpNode node) {
        Symbol leftSymbol = resolve((ExpNode) node.get_children().get(0)).clone();
        Symbol rightSymbol = resolve((ExpNode) node.get_children().get(1)).clone();
        boolean comparison = leftSymbol.get_value().equals(rightSymbol.get_value());
        Symbol resultSymbol = new Symbol(new MctlBooleanDescriptor());
        switch(node.get_operatorLiteral()) {
            case "==" -> {
                resultSymbol.set_value(comparison);
                return resultSymbol;
            }
            case "!=" -> {
                resultSymbol.set_value(!comparison);
                return resultSymbol;
            }
            default -> {
                problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unknown comparison expression", node.get_lineNumber());
            }
        }
        return null;
    }

    public void visit(IDExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected variable expression", node.get_lineNumber());
    }
    public Symbol resolve(IDExpNode node) {
        if(node instanceof ActualIDExpNode){
            return resolve((ActualIDExpNode) node);
        }else if(node instanceof IDArrayExpNode){
            return resolve((IDArrayExpNode) node);
        }else if(node instanceof IDStructNode){
            return resolve((IDStructNode) node);
        }
        return resolve(node.get_idNode());
    }

    public void visit(ActualIDExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected variable expression", node.get_lineNumber());
    }
    public Symbol resolve(ActualIDExpNode node) {
        Symbol symbol = symbolTable.searchSymbol(node.get_id());
        if(symbol == null){
            symbol = new Symbol(node.get_id());
        }
        return symbol;
    }

    public void visit(IDArrayExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected variable expression", node.get_lineNumber());
    }
    public Symbol resolve(IDArrayExpNode node) {
        Symbol base = resolve(node.get_idNode());

        int accessor = ((Number) resolve(node.get_accessor()).get_value()).intValue();

        Symbol indexSymbol = base.get_index(accessor);
        if(indexSymbol == null){
            indexSymbol = new Symbol();
            base.set_index(accessor, indexSymbol);
        }

        return indexSymbol;
    }

    public void visit(IDStructNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected variable expression", node.get_lineNumber());
    }
    public Symbol resolve(IDStructNode node) {
        Symbol base = resolve(node.get_idNode());

        String accessor = resolve(node.get_accessor()).get_name();

        Symbol fieldSymbol = base.get_field(accessor);
        if(fieldSymbol == null){
            fieldSymbol = new Symbol(accessor);
            base.set_field(fieldSymbol);
        }

        return fieldSymbol;
    }

    public void visit(BoolExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected boolean literal expression", node.get_lineNumber());
    }
    public Symbol resolve(BoolExpNode node) {
        return new Symbol(new MctlBooleanDescriptor(), node.get_result());
    }

    public void visit(NumExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected numerical literal expression", node.get_lineNumber());
    }
    public Symbol resolve(NumExpNode node) {
        // The cast to double ensures that all values are floats.
        // TODO: This is a hacky solution to the problem of using ints and floats together, we should probably allow ints more broadly
        return new Symbol(new MctlNumberDescriptor(), (Number) node.get_result().doubleValue());
    }

    public void visit(StringExpNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unexpected string literal expression", node.get_lineNumber());
    }
    public Symbol resolve(StringExpNode node) {
        return new Symbol(new MctlStringDescriptor(), node.get_result());
    }

    public Symbol resolve(BaseNode node) {
        problemCollection.addProblem(ProblemType.ERROR_INTERPRETER, "Encountered unknown node", node.get_lineNumber());
        //System.out.println(node);
        return new Symbol(new MctlNothingDescriptor(), null);
    }

}
