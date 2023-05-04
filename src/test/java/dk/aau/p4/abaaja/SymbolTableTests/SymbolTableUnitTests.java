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

    //Methods are run before and after each test
    //
    @BeforeMethod()
    public void BeforeMethod() {
        testSymbolTable = new SymbolTable();
    }
    @AfterMethod()
    public void AfterMethod() {testSymbolTable = null;}


    @Test()
    public void GetCurrentScope_WhenGettingScope_ThenReturnsAScopeClass() {

        //Act
        boolean result = testSymbolTable.get_currentScope() instanceof Scope;

        //Assert
        assert(result);
    }

    @Test()
    public void CreateScope_SetsToCurrentScope_CurrentScopeMustBeSameAsCreatedScopeName() {

        //Arrange
        String expectedScopeName = "testScope";

        //Act
        try {
            testSymbolTable.createScope("testScope");
        } catch (Exception e) {
            assert(false);
        }
        boolean result = testSymbolTable.get_currentScope().get_Name().equals(expectedScopeName);

        //Assert
        assert (result);
    }

    @Test()
    public void CloseScope_WhenClosingScope_ThenCurrentScopeSetToPreviousInList() {
        //Arrange
        //Expected Name of scope:
        String expectedScopeName = "Global";

        try {
            testSymbolTable.createScope("testScope");
        } catch (Exception e) {
            assert(false);
        }
        testSymbolTable.closeScope();

        //Act
        boolean result = testSymbolTable.get_currentScope().get_Name().equals(expectedScopeName);

        //Assert
        assert(result);
    }

    @Test()
    public void SearchScope_WhenSearching_ThenReturnSearchedScope() {
        //Arrange
        String expectedScopeName = "testScope2";
        try{
            testSymbolTable.CreateScope("testScope1");
            testSymbolTable.CreateScope("testScope2");
        } catch (Exception e) {
            assert(false);
        }
        //Act
        boolean result = testSymbolTable.SearchScope("testScope2").get_Name().equals(expectedScopeName);

        //Assert
        assert(result);
    }

    @Test()
    public void SearchSymbol_WhenSearching_ThenReturnSearchedSymbol() {
        //Arrange
        String expectedSymbolName = "expectedName";
        Symbol testSymbol = new Symbol(expectedSymbolName);
        try{
            testSymbolTable.createScope("testScope");
        } catch (Exception e) {
            assert(false);
        }
        testSymbolTable.insertSymbol(testSymbol);


        //Act
        boolean result = testSymbolTable.searchSymbol("expectedName").get_name().equals(expectedSymbolName);

        //Assert
        assert(result);
    }

    @Test()
    public void InsertSymbol_WhenInserted_ThenCurrentScopeContainsSymbol() {

        String expectedSymbolName = "expectedName";
        Symbol testSymbol = new Symbol(expectedSymbolName);

        testSymbolTable.insertSymbol(testSymbol);

        boolean result = testSymbolTable.get_currentScope().get_symbols().containsKey(expectedSymbolName);

        //Assert
        assert(result);
    }
}
