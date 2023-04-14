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

    public void OpenScope(){

    }

    public void CloseScope(){

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

    public void EnterSymbol(HashMapEntry symbol){

    }

    public boolean DeclaredLocally() {
        return true;
    }
}
