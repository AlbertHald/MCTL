package dk.aau.p4.abaaja.Lib.Symbols;

import java.util.ArrayList;

public class SymbolTable {

    private Scope _currentScope;
    private final Scope _globalScope;
    private final ArrayList<Scope> _symboltable = new ArrayList<>();


    public SymbolTable(){
        _globalScope = new Scope("Global");
        _symboltable.add(_globalScope);
        _currentScope = _globalScope;
    }

    public void CreateScope(String scopeName) throws Exception {
        if(SearchScope(scopeName) != null) {
            throw new Exception("Scope already exists: " + scopeName);
        }
        Scope scope = new Scope(scopeName);
        scope.set_parent(_currentScope);
        _symboltable.add(scope);

        _currentScope = scope;
    }

    public void CloseScope(){
        if(_currentScope.get_Parent() != null)

            _symboltable.remove(_currentScope);
            _currentScope = _symboltable.get(_symboltable.size() - 1);
    }

    public void EnterScope(String scopeName) throws Exception {
        Scope scope = SearchScope(scopeName);
        if(scope == null)
            throw new Exception("Scope does not exist: " + scopeName);

        _currentScope = scope;
    }

    //A function that finds a scope (can return null if not found)
    public Scope SearchScope(String scopeName){

        for (Scope scope : _symboltable) {
            if(scope.get_Name().equals(scopeName)) {
                return scope;
            }
        }
        return null;
    }

    public Scope get_currentScope() {
        return _currentScope;
    }

    //Search symbol in scope
    public Symbol SearchSymbol(String symbol) {
        Scope scope = _currentScope;

        do {
            if(!scope.get_symbols().isEmpty() && scope.get_symbols().containsKey(symbol))
                return scope.get_symbols().get(symbol);
        } while((scope = scope.get_Parent()) != null);

        return null;
    }

    public void InsertSymbol(Symbol symbol) {
        _currentScope.set_symbols(symbol);
    }
}
