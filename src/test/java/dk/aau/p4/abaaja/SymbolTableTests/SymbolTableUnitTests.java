package dk.aau.p4.abaaja.SymbolTableTests;

import dk.aau.p4.abaaja.Lib.Symbols.Symbol;
import dk.aau.p4.abaaja.Lib.Symbols.Scope;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test()
public class SymbolTableUnitTests {
    SymbolTable testSymbolTable;

    @BeforeMethod
    public void BeforeMethod() {testSymbolTable = new SymbolTable();}
    @AfterMethod()
    public void AfterMethod() {testSymbolTable = null;}
    @Test
    public void GetCurrentScope_ScopeClass_ReturnsTheGlobalScope() {

        //Arrange
        testSymbolTable = new SymbolTable();
        Scope testScope = new Scope("Global");
        //Act
        boolean result = testSymbolTable.get_currentScope() == testScope;

        //Assert
        assert(result);
    }

}
