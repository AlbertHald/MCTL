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

    public void CreateScope() {
        Scope scope = new Scope();
        scope.set_parent(_currentScope);
        _symboltable.add(scope);

        _currentScope = scope;
    }

    public void CloseScope(){
        if(_currentScope.get_Parent() != null)

            _symboltable.remove(_currentScope);
        _currentScope = _symboltable.get(_symboltable.size() - 1);
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