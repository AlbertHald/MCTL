package dk.aau.p4.abaaja.Lib.Symbols;

import java.util.HashMap;
import java.util.Stack;

public class SymbolTable {
    private Scope _currentScope;
    private final Scope _globalScope;


    public SymbolTable(){
        _globalScope = new Scope("Global");
        _currentScope = _globalScope;
    }

    public void createScope(String scopeName) throws Exception{
        if(GlobalSearchScope(scopeName) != null)
            throw new Exception("Scope already exists: " + scopeName);

        Scope scope = new Scope(scopeName);
        scope.set_parent(_currentScope);
        _currentScope.add_child(scope);


        _currentScope = scope;
    }

    public void CloseScope(){
        if(_currentScope.get_Parent() != null)
            _currentScope = _currentScope.get_Parent();
    }

    public void EnterScope(String scopeName) throws Exception{
        Scope scope = GlobalSearchScope(scopeName);
        if(scope == null)
            throw new Exception("Scope does not exist: " + scopeName);

        _currentScope = scope;
    }

    public Scope SearchScope(String scopeName, Scope scope){
        if (scope.get_Name().equals(scopeName))
            return scope;

        for ( Scope child : scope.get_children() ) {
            Scope result = SearchScope(scopeName, child);
            if (result != null)
                return result;
        }

        return null;
    }

    //A function that finds a scope (can return null if not found)
    public Scope GlobalSearchScope(String scope) {
        return this.SearchScope(scope, _globalScope);
    }

    public Scope get_currentScope() {
        return _currentScope;
    }

    //Search symbol in scope
    public HashMapEntry SearchSymbol(String symbol) {
        Scope scope = _currentScope;

        do {
            if(!scope.get_Symbols().isEmpty() && scope.get_Symbols().containsKey(symbol))
                return scope.get_Symbols().get(symbol);
        } while((scope = scope.get_Parent()) != null);

        return null;
    }

    public void InsertSymbol(String symbol, HashMapEntry Attribute) {
        _currentScope.set_symbols(symbol, Attribute);
    }
}
