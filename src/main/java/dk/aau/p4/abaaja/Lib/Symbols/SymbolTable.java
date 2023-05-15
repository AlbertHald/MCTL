package dk.aau.p4.abaaja.Lib.Symbols;

import dk.aau.p4.abaaja.Lib.PredefinedFunction;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SymbolTable {
    private Scope _currentScope;
    private final Scope _globalScope;
    private final ArrayList<Scope> _symboltable = new ArrayList<>();

    // Predefined functions of the programming language
    private List<PredefinedFunction> predefinedFunctions = Arrays.asList(
            new PredefinedFunction("add", Arrays.asList(Arrays.asList(new MctlTypeDescriptor())), new MctlArrayTypeDescriptor(new MctlTypeDescriptor(), 0), true, true, new MctlArrayTypeDescriptor(new MctlTypeDescriptor(), 0)),
            new PredefinedFunction("remove", new ArrayList<>(), new MctlArrayTypeDescriptor(new MctlTypeDescriptor(), 0), true, true, new MctlArrayTypeDescriptor(new MctlTypeDescriptor(), 0)),
            new PredefinedFunction("length", new ArrayList<>(), new MctlNumberDescriptor(), true, true, new MctlArrayTypeDescriptor(new MctlTypeDescriptor(), 0)),
            new PredefinedFunction("indexesOf",
                    Arrays.asList(Arrays.asList(new MctlStringDescriptor(), new MctlBooleanDescriptor(), new MctlNumberDescriptor(), new MctlNothingDescriptor())),
                    new MctlArrayTypeDescriptor(new MctlNumberDescriptor(), 1), true, true, new MctlArrayTypeDescriptor(new MctlTypeDescriptor(), 0)),
            new PredefinedFunction("substring", Arrays.asList(Arrays.asList(new MctlNumberDescriptor()), Arrays.asList(new MctlNumberDescriptor())), new MctlStringDescriptor(), true, true, new MctlStringDescriptor()),
            new PredefinedFunction("sublist", Arrays.asList(Arrays.asList(new MctlNumberDescriptor()), Arrays.asList(new MctlNumberDescriptor())), new MctlArrayTypeDescriptor(new MctlTypeDescriptor(), 0), true, false, new MctlArrayTypeDescriptor(new MctlTypeDescriptor(),0)),
            new PredefinedFunction("read", new ArrayList<>(), new MctlStringDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("print", Arrays.asList(Arrays.asList(new MctlStringDescriptor())), new MctlNothingDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("moveForward", new ArrayList<>(), new MctlBooleanDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("moveUp", new ArrayList<>(), new MctlBooleanDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("moveDown", new ArrayList<>(), new MctlBooleanDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("moveBackward", new ArrayList<>(), new MctlBooleanDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("turnLeft", new ArrayList<>(), new MctlNothingDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("turnRight", new ArrayList<>(), new MctlNothingDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("breakFront", new ArrayList<>(), new MctlNothingDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("breakAbove", new ArrayList<>(), new MctlNothingDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("breakUnder", new ArrayList<>(), new MctlNothingDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("placeFront", Arrays.asList(Arrays.asList(new MctlStringDescriptor())), new MctlNothingDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("placeAbove", Arrays.asList(Arrays.asList(new MctlStringDescriptor())), new MctlNothingDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("placeUnder", Arrays.asList(Arrays.asList(new MctlStringDescriptor())), new MctlNothingDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("blockFront", new ArrayList<>(), new MctlStringDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("blockAbove", new ArrayList<>(), new MctlStringDescriptor(), false, false, new MctlNothingDescriptor()),
            new PredefinedFunction("blockUnder", new ArrayList<>(), new MctlStringDescriptor(), false, false, new MctlNothingDescriptor())
    );

    // Predefined types of the programming language
    private List<MctlTypeDescriptor> predefinedTypeLiterals = Arrays.asList(
            new MctlBooleanDescriptor(),
            new MctlNumberDescriptor(),
            new MctlStringDescriptor(),
            new MctlNothingDescriptor()
    );

    public SymbolTable(){
        _globalScope = new Scope("Global");
        _symboltable.add(_globalScope);
        _currentScope = _globalScope;

        // Add predefined types
        for (MctlTypeDescriptor type : predefinedTypeLiterals) {
            get_currentScope().add_type(type);
        }

        // Adding predefined functions to the symbol table
        for (PredefinedFunction predefinedFunction : predefinedFunctions) {
            FuncSymbol functionSymbol = new FuncSymbol();
            functionSymbol.set_name(predefinedFunction.get_id());
            functionSymbol.set_type(predefinedFunction.get_returnType());
            functionSymbol.set_types(predefinedFunction.get_parameters());
            functionSymbol.setIsStringFunction(predefinedFunction.getIsStringFunction());
            functionSymbol.setIsVarFunction(predefinedFunction.getIsVarFunction());
            functionSymbol.setExpectedVarType(predefinedFunction.getExpectedVarType());

            // Add function
            insertSymbol((Symbol) functionSymbol);
        }
    }

    public void createScope() {
        Scope scope = new Scope();
        scope.set_parent(_currentScope);
        _symboltable.add(scope);

        _currentScope = scope;
    }

    public void createScope(String name) {
        Scope scope = new Scope(name);
        scope.set_parent(_currentScope);
        _symboltable.add(scope);

        _currentScope = scope;
    }

    public void closeScope(){
        if(_currentScope.get_Parent() != null) {
            _symboltable.remove(_currentScope);
            _currentScope = _symboltable.get(_symboltable.size() - 1);
        }
    }

    public Scope get_currentScope() {
        return _currentScope;
    }

    //Search symbol in scope
    public Symbol searchSymbol(String symbol) {
        Scope scope = _currentScope;

        do {
            if(!scope.get_symbols().isEmpty() && scope.get_symbols().containsKey(symbol))
                return scope.get_symbols().get(symbol);
        } while((scope = scope.get_Parent()) != null);

        return null;
    }

    // Search Type method
    public MctlTypeDescriptor searchType(String type) {
        Scope scope = _currentScope;

        do {
            if(!scope.get_types().isEmpty() && scope.get_types().containsKey(type))
                return scope.get_types().get(type);
        } while((scope = scope.get_Parent()) != null);

        return null;
    }

    // Search Scope name method
    public Scope searchScopeName(String type) {
        Scope scope = _currentScope;

        do {
            if(scope.get_Name() != null && scope.get_Name().equals(type))
                return scope;
        } while((scope = scope.get_Parent()) != null);

        return null;
    }

    public void insertSymbol(Symbol symbol) {
        _currentScope.add_symbol(symbol);
    }
    public void insertType(MctlTypeDescriptor type) {
        _currentScope.add_type(type);
    }
}