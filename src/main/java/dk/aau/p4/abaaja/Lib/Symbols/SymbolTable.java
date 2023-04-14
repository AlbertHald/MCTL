package dk.aau.p4.abaaja.Lib.Symbols;

import java.util.HashMap;

public class SymbolTable {


    public void OpenScope(){

    }

    public void CloseScope(){

    }

    public HashMapEntry RetrieveScope() {

        return new HashMapEntry();
    }

    public void EnterSymbol(HashMapEntry symbol){

    }

    public boolean DeclaredLocally() {
        return true;
    }
}
