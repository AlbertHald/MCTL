package dk.aau.p4.abaaja.Lib.Visitors;

import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemType;
import dk.aau.p4.abaaja.Lib.Symbols.Symbol;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.*;

import java.util.ArrayList;
import java.util.List;

public class TypeCheckingVisitor {
    private final ProblemCollection _problemCollection;
    private final SymbolTable _symbolTable;

    public TypeCheckingVisitor(ProblemCollection problemCollection, SymbolTable symbolTable) {
        this._problemCollection = problemCollection;
        this._symbolTable = symbolTable;
    }

    public MctlTypeDescriptor visit(DecNode node) {
        MctlTypeDescriptor typeDescriptor = null;

        if (node instanceof VarDecNode) { typeDescriptor = visit((VarDecNode) node); }
        else if (node instanceof FuncDecNode) { typeDescriptor = visit((FuncDecNode) node); }
        else if (node instanceof StructDecNode) { typeDescriptor = visit((StructDecNode) node); }

        return typeDescriptor;
    }

    public MctlTypeDescriptor visit(StateNode node) {
        MctlTypeDescriptor typeDescriptor = null;

        if (node instanceof IfStateNode) { typeDescriptor = visit((IfStateNode) node); }
        else if (node instanceof AssStateNode) { typeDescriptor = visit((AssStateNode) node); }
        else if (node instanceof FuncInvokeNode) { typeDescriptor = visit((FuncInvokeNode) node); }
        else if (node instanceof InvokeNode) { typeDescriptor = visit((InvokeNode) node); }
        else if (node instanceof RepeatStateNode) { typeDescriptor = visit((RepeatStateNode) node); }
        else if (node instanceof ReturnNode) { typeDescriptor = visit((ReturnNode) node); }
        else if (node instanceof StopNode) { typeDescriptor = visit((StopNode) node); }

        return typeDescriptor;
    }

    public MctlTypeDescriptor visit(IfStateNode node) {
        MctlTypeDescriptor typeDescriptor = _symbolTable.searchType("BOOLEAN");

        for (ExpNode expNode : node.get_expChildren()) {
            MctlTypeDescriptor tempTypeDescriptor = visit(expNode);
            if (!tempTypeDescriptor.get_type_literal().equals("BOOLEAN")) {
                _problemCollection.addProblem(
                        ProblemType.ERROR_TYPE_MISMATCH,
                        "Expected type BOOLEAN but got: " + tempTypeDescriptor.get_type_literal(),
                        expNode.get_lineNumber()
                );

                typeDescriptor = null;
            }
        }

        return typeDescriptor;
    }

    public MctlTypeDescriptor visit(RepeatStateNode node) {
        String typeLiteral = visit(node.get_repeatExp()).get_type_literal();

        if (!(typeLiteral.equals("BOOLEAN") || typeLiteral.equals("NUMBER"))) {
            _problemCollection.addProblem(
                    ProblemType.ERROR_TYPE_MISMATCH,
                    "Expected type BOOLEAN or NUMBER but got: " + typeLiteral,
                    node.get_lineNumber()
            );
        }

        return _symbolTable.searchType(typeLiteral);
    }

    public MctlTypeDescriptor visit(InvokeExpNode node) { return visit(node.getInvokeNode()); }

    public MctlTypeDescriptor visit(InvokeNode node) {
        MctlTypeDescriptor typeDescriptor = null;

        if (node instanceof StringMethodInvokeNode) { typeDescriptor = visit((StringMethodInvokeNode) node); }
        else if (node instanceof VarMethodInvokeNode) { typeDescriptor = visit((VarMethodInvokeNode) node); }
        else if (node instanceof FuncInvokeNode) { typeDescriptor = visit((FuncInvokeNode) node); }


        return typeDescriptor;
    }

    public MctlTypeDescriptor visit(TypeNode node) {
        MctlTypeDescriptor typeDescriptor = null;

        if (node instanceof BoolTypeNode) { typeDescriptor = visit((BoolTypeNode) node); }
        else if (node instanceof NumTypeNode) { typeDescriptor = visit((NumTypeNode) node); }
        else if (node instanceof StringTypeNode) { typeDescriptor = visit((StringTypeNode) node); }
        else if (node instanceof NothingTypeNode) { typeDescriptor = visit((NothingTypeNode) node); }
        else if (node instanceof IDTypeNode) { typeDescriptor = visit((IDTypeNode) node); }
        return typeDescriptor;
    }

    public MctlTypeDescriptor visit(IDTypeNode node) {
        MctlTypeDescriptor typeDescriptor = _symbolTable.searchType(node.get_type());

        if (typeDescriptor == null) {
            _problemCollection.addProblem(
                    ProblemType.ERROR_UNKNOWN_TYPE,
                    "The type: " + node.get_type() + " has not been declared",
                    node.get_lineNumber()
            );
        }

        return typeDescriptor;
    }

    public MctlTypeDescriptor visit(BinaryExpNode node) {
        MctlTypeDescriptor typeDescriptor = null;

        if (node instanceof AddExpNode) { typeDescriptor = visit((AddExpNode) node); }
        else if (node instanceof AndExpNode) { typeDescriptor = visit((AndExpNode) node); }
        else if (node instanceof CompExpNode) { typeDescriptor = visit((CompExpNode) node); }
        else if (node instanceof EqualExpNode) { typeDescriptor = visit((EqualExpNode) node); }
        else if (node instanceof MulExpNode) { typeDescriptor = visit((MulExpNode) node); }
        else if (node instanceof OrExpNode) { typeDescriptor = visit((OrExpNode) node); }

        return typeDescriptor;
    }

    public MctlTypeDescriptor visit(EqualExpNode node) {
        String type1 = visit((ExpNode) node.get_children().get(0)).get_type_literal();
        String type2 = visit((ExpNode) node.get_children().get(1)).get_type_literal();

        if (!type1.equals(type2)) {
            _problemCollection.addProblem(
                    ProblemType.ERROR_TYPE_MISMATCH,
                    "Expected both expressions to be of the same type, but got type: " + type1 + " and type: " + type2,
                    node.get_lineNumber()
            );

            return null;
        }

        return _symbolTable.searchType(type1);
    }

    public MctlTypeDescriptor visit(IDExpNode node) {
        MctlTypeDescriptor typeDescriptor = null;

        if (node instanceof IDArrayExpNode) { typeDescriptor = visit((IDArrayExpNode) node); }
        else if (node instanceof IDStructNode) { typeDescriptor = visit((IDStructNode) node); }
        else if (node instanceof ActualIDExpNode) { typeDescriptor = visit((ActualIDExpNode) node); }

        return typeDescriptor;
    }

    public MctlTypeDescriptor visit(IDArrayExpNode node) {
        Symbol symbol = _symbolTable.searchSymbol(node.get_contained_id());
        MctlTypeDescriptor accessorArrayTypeDescriptor = _symbolTable.searchType("NOTHING");

        if (symbol != null && !symbol.get_isInstantiated()) {
            return _symbolTable.searchType("NOTHING");
        } else if (symbol == null) {
            return null;
        }

        // Counting array nodes
        int arrayDegree = 0;
        IDExpNode tempIdNode = node;
        while (tempIdNode instanceof IDArrayExpNode) {
            arrayDegree++;
            tempIdNode = tempIdNode.get_idNode();
        }

        if (symbol.get_type() instanceof MctlArrayTypeDescriptor arrayTypeDescriptor) {
            int accessorDegree = arrayTypeDescriptor.getDegree() - arrayDegree;

            // The type referred to is not an array
            if (accessorDegree == 0) {
                accessorArrayTypeDescriptor = arrayTypeDescriptor.getType();
            } else if (accessorDegree < 0) {
                // TODO: User trying to access degree larger than the defined
            } else {
                accessorArrayTypeDescriptor = new MctlArrayTypeDescriptor(arrayTypeDescriptor.getType(), accessorDegree);
            }
        }
        else if (symbol.get_type() instanceof MctlStructDescriptor structTypeDescriptor) {
            // Remember to add degree
            MctlTypeDescriptor derivedType = getStructDerivedType(structTypeDescriptor, (IDStructNode) node.get_idNode());

            if (derivedType instanceof MctlArrayTypeDescriptor derivedArrayType) {
                // Calculating the accessory array degree
                int accessorDegree = derivedArrayType.getDegree() - arrayDegree;

                // The type referred to is not an array
                if (accessorDegree == 0) {
                    accessorArrayTypeDescriptor = derivedArrayType.getType();
                } else if (accessorDegree < 0) {
                    // TODO: User trying to access degree larger than the defined
                } else {
                    accessorArrayTypeDescriptor = new MctlArrayTypeDescriptor(derivedArrayType.getType(), accessorDegree);
                }
            }
            else {
                accessorArrayTypeDescriptor = derivedType;
            }
        }
        else {
            _problemCollection.addProblem(
                    ProblemType.ERROR_TYPE_MISMATCH,
                    "The variable " + symbol.get_name() + " is not of the correct type",
                    node.get_lineNumber()
            );
        }

        System.out.println(accessorArrayTypeDescriptor);
        return accessorArrayTypeDescriptor;
    }

    private MctlTypeDescriptor getArrayType(MctlTypeDescriptor descriptor, int degree) {
        MctlTypeDescriptor accessorType = _symbolTable.searchType("NOTHING");

        // The type referred to is not an array
        if (degree == 0) {
            accessorType = descriptor;
        } else if (degree < 0) {
            // TODO: User trying to access degree larger than the defined
        } else {
            accessorType = new MctlArrayTypeDescriptor(descriptor, degree);
        }

        return accessorType;
    }

    private MctlTypeDescriptor getStructDerivedType(MctlStructDescriptor parsedStructDescriptor, IDStructNode idStructNode) {
        IDExpNode idExpNode = idStructNode;
        MctlTypeDescriptor accessorType = parsedStructDescriptor;

        List<IDExpNode> nodeList = new ArrayList<>();

        // Add all IdExpNodes to a list in bottom up order
        while(!(idExpNode instanceof ActualIDExpNode)) {
            nodeList.add(0, idExpNode);
            idExpNode = idExpNode.get_idNode();
        }

        // Iterate over each element in the IdExpNodeElement
        for (IDExpNode idExp : nodeList) {
            if (idExp instanceof IDStructNode tempIDStructNode) {
                if (accessorType instanceof MctlStructDescriptor accessorDescriptor) {
                    accessorType = accessorDescriptor.get_structsymbol(tempIDStructNode.get_accessor().get_contained_id());
                } else {
                    // TODO: Implement error here. Happens if user is trying to access a struct on a type that is not a struct and so on
                }
            } else if (idExp instanceof IDArrayExpNode tempIDArrayExpNode) {
                if (accessorType instanceof MctlArrayTypeDescriptor accessorDescriptor) {
                    int accessorDegree = accessorDescriptor.getDegree() - tempIDArrayExpNode.get_degree();
                    accessorType = getArrayType(accessorDescriptor.getType(), accessorDegree);
                }
                else {
                    // TODO: Implement error here. Happens if user is trying to access an array type on a type that is not an array
                }
            }
        }

        return accessorType;
    }

    public MctlTypeDescriptor visit(ActualIDExpNode node) {
        Symbol symbol = _symbolTable.searchSymbol(node.get_id());

        if (symbol != null && !symbol.get_isInstantiated()) {
            return _symbolTable.searchType("NOTHING");
        } else if (symbol == null) {
            return null;
        }

        return symbol.get_type();
    }

    public MctlTypeDescriptor visit(IDStructNode node) {
        boolean foundPrimitiveType = false;

        IDStructNode currNode = node;
        MctlTypeDescriptor accessorType;

        MctlStructDescriptor mctlTypeDescriptor;
        MctlTypeDescriptor descriptor = visit(currNode.get_idNode());

        if (descriptor instanceof MctlArrayTypeDescriptor) {
            String no_brackets = ((MctlArrayTypeDescriptor) descriptor).get_contained_type_literal();
            MctlStructDescriptor another_descriptor = (MctlStructDescriptor) _symbolTable.searchType(no_brackets);
            ActualIDExpNode another_node = (ActualIDExpNode) node.get_accessor();

            return another_descriptor.get_structsymbol(another_node.get_id());
        } else {
            mctlTypeDescriptor = (MctlStructDescriptor) descriptor;
        }

        if (mctlTypeDescriptor.get_type_literal().equals("NOTHING")) {
            return mctlTypeDescriptor;
        }

        do {
            // Get id of accessor
            IDExpNode accessor = currNode.get_accessor();
            while (!(accessor instanceof ActualIDExpNode actualIDExpNode)) {
                accessor = accessor.get_idNode();
            }

            // Get type of accessor
            String accessorId = actualIDExpNode.get_id();

            if (mctlTypeDescriptor.get_structVariables().containsKey(accessorId)) {
                accessorType = mctlTypeDescriptor.get_structsymbol(accessorId);
            } else {
                _problemCollection.addProblem(
                        ProblemType.ERROR_UNKNOWN_TYPE,
                        "The struct " + mctlTypeDescriptor.get_type_literal() + " does not contain a member with ID: " + accessorId,
                        node.get_lineNumber()
                );

                return null;
            }

            // Check if accessor type is a primitive type
            if ((currNode.get_accessor() instanceof IDStructNode idStructNode)) {
                mctlTypeDescriptor = (MctlStructDescriptor) accessorType;
                currNode = idStructNode;
            } else {
                foundPrimitiveType = true;
            }
        } while(!foundPrimitiveType);

        return accessorType;
    }

    public MctlTypeDescriptor visit(ExpNode node) {
        MctlTypeDescriptor typeDescriptor = null;

        if (node instanceof IDExpNode) { typeDescriptor = visit((IDExpNode) node); }
        else if (node instanceof BinaryExpNode) { typeDescriptor = visit((BinaryExpNode) node); }
        else if (node instanceof BoolExpNode) { typeDescriptor = visit((BoolExpNode) node); }
        else if (node instanceof InvokeExpNode) { typeDescriptor = visit((InvokeExpNode) node); }
        else if (node instanceof NumExpNode) { typeDescriptor = visit((NumExpNode) node); }
        else if (node instanceof StringExpNode) { typeDescriptor = visit((StringExpNode) node); }
        else if (node instanceof TypecastExpNode) { typeDescriptor = visit((TypecastExpNode) node); }
        else if (node instanceof UnaryExpNode) { typeDescriptor = visit((UnaryExpNode) node); }

        return typeDescriptor;
    }

    public MctlTypeDescriptor visit(MulExpNode node) { return expectsType(node, "NUMBER"); }
    public MctlTypeDescriptor visit(AddExpNode node) { return expectsType(node, "NUMBER"); }
    public MctlTypeDescriptor visit(AndExpNode node) { return expectsType(node, "BOOLEAN"); }
    public MctlTypeDescriptor visit(OrExpNode node) { return expectsType(node, "BOOLEAN"); }
    public MctlTypeDescriptor visit(CompExpNode node) { return expectsType(node, "NUMBER"); }

    public MctlTypeDescriptor visit(BoolTypeNode node) { return _symbolTable.searchType("BOOLEAN"); }
    public MctlTypeDescriptor visit(NumTypeNode node) { return _symbolTable.searchType("NUMBER"); }
    public MctlTypeDescriptor visit(StringTypeNode node) { return _symbolTable.searchType("STRING"); }
    public MctlTypeDescriptor visit(NothingTypeNode node) { return _symbolTable.searchType("NOTHING"); }
    public MctlTypeDescriptor visit(BoolExpNode node) { return _symbolTable.searchType("BOOLEAN"); }
    public MctlTypeDescriptor visit(NumExpNode node) { return _symbolTable.searchType("NUMBER"); }
    public MctlTypeDescriptor visit(StringExpNode node) { return _symbolTable.searchType("STRING"); }
    public MctlTypeDescriptor visit(UnaryExpNode node) { return visit(node.get_unaryExp()); }
    public MctlTypeDescriptor visit(TypecastExpNode node) { return visit(node.get_expression_node()); }
    public MctlTypeDescriptor visit(ReturnNode node) { return visit(node.get_returnExp()); }

    public MctlTypeDescriptor visit(MctlNode node) { return null; }
    public MctlTypeDescriptor visit(LineNode node) { return null; }
    public MctlTypeDescriptor visit(BlockNode node) { return null; }
    public MctlTypeDescriptor visit(FuncInvokeNode node) { return null; }
    public MctlTypeDescriptor visit(VarMethodInvokeNode node) { return null; }
    public MctlTypeDescriptor visit(StringMethodInvokeNode node) { return null; }
    public MctlTypeDescriptor visit(FormalParamNode node) { return null; }
    public MctlTypeDescriptor visit(StopNode node) { return null; }
    public MctlTypeDescriptor visit(CommentNode node) { return null; }
    public MctlTypeDescriptor visit(VarDecNode node) { return null; }
    public MctlTypeDescriptor visit(FuncDecNode node) { return null; }
    public MctlTypeDescriptor visit(StructDecNode node) { return null; }
    public MctlTypeDescriptor visit(AssStateNode node) { return null; }

    public MctlTypeDescriptor expectsType(BaseNode node, String typeLiteral) {
        String typeChildOne = visit((ExpNode) node.get_children().get(0)).get_type_literal();
        String typeChildTwo = visit((ExpNode) node.get_children().get(1)).get_type_literal();

        if (!typeChildOne.equals(typeLiteral)) {
            _problemCollection.addProblem(
                    ProblemType.ERROR_TYPE_MISMATCH,
                    "Expected type " + typeLiteral + " but got " + typeChildOne,
                    node.get_lineNumber()
            );
        }

        if (!typeChildTwo.equals(typeLiteral)) {
            _problemCollection.addProblem(
                    ProblemType.ERROR_TYPE_MISMATCH,
                    "Expected type " + typeLiteral + " but got " + typeChildTwo,
                    node.get_lineNumber()
            );
        }

        if (!(typeChildOne.equals(typeLiteral) && typeChildTwo.equals(typeLiteral))) {
            return null;
        }

        return _symbolTable.searchType(typeLiteral);
    }
}
