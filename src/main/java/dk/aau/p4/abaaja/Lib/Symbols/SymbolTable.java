package dk.aau.p4.abaaja.Lib.Symbols;

import dk.aau.p4.abaaja.Lib.PredefinedFunction;
import dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors.TypeDescriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SymbolTable {
    private Scope _currentScope;
    private final Scope _globalScope;
    private final ArrayList<Scope> _symboltable = new ArrayList<>();

    // Predefined functions of the programming language
    private List<PredefinedFunction> predefinedFunctions = Arrays.asList(
            new PredefinedFunction("add", Arrays.asList(null), null),
            new PredefinedFunction("remove", null, null),
            new PredefinedFunction("length", null, "NUMBER"),
            new PredefinedFunction("print", Arrays.asList(Arrays.asList("STRING")), null),
            new PredefinedFunction("read", null, "STRING"),
            new PredefinedFunction("indexesOf", Arrays.asList(Arrays.asList("STRING", "BOOLEAN", "NUMBER", "NOTHING")), "NUMBER[]"),
            new PredefinedFunction("substring", Arrays.asList(Arrays.asList("NUMBER"), Arrays.asList("NUMBER")), "STRING")
    );

    public SymbolTable(){
        _globalScope = new Scope("Global");
        _symboltable.add(_globalScope);
        _currentScope = _globalScope;

        // Adding predefined functions to the symbol table
        for (PredefinedFunction predefinedFunction : predefinedFunctions) {
            Symbol functionSymbol = new Symbol(predefinedFunction.get_id());
            //functionSymbol.set_type(predefinedFunction.get_returnType());
            //functionSymbol.set_types(predefinedFunction.get_parameters());
        }
    }

    public void createScope() {
        Scope scope = new Scope();
        scope.set_parent(_currentScope);
        _symboltable.add(scope);

        _currentScope = scope;
    }

    public void closeScope(){
        if(_currentScope.get_Parent() != null)

            _symboltable.remove(_currentScope);
        _currentScope = _symboltable.get(_symboltable.size() - 1);
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
    public TypeDescriptor searchType(String type) {
        Scope scope = _currentScope;

        do {
            if(!scope.get_types().isEmpty() && scope.get_types().containsKey(type))
                return scope.get_types().get(type);
        } while((scope = scope.get_Parent()) != null);

        return null;
    }

    public void insertSymbol(Symbol symbol) {
        _currentScope.add_symbol(symbol);
    }
    public void insertType(TypeDescriptor type) {
        _currentScope.add_type(type);
    }
}