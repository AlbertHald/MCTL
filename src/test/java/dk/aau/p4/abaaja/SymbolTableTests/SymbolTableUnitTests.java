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
            testSymbolTable.CreateScope("testScope");
        } catch (Exception e) {
            assert(false);
        }
        boolean result = testSymbolTable.get_currentScope().get_Name() == expectedScopeName;

        //Assert
        assert (result);
    }

    @Test()
    public void CloseScope_WhenClosingScope_ThenCurrentScopeSetToPreviousInList() {
        //Arrange
        //Expected Name of scope:
        String expectedScopeName = "Global";

        try {
            testSymbolTable.CreateScope("testScope");
        } catch (Exception e) {
            assert(false);
        }
        testSymbolTable.CloseScope();

        //Act
        boolean result = testSymbolTable.get_currentScope().get_Name() == expectedScopeName;

        //Assert
        assert(result);
    }

    @Test()
    public void SearchScope_WhenSearching_ThenReturnSearchedScope() {
        //Arrange

        //Act

        //Assert
    }

    @Test()
    public void SearchSymbol_WhenSearching_ThenReturnSearchedSymbol() {
        //Arrange
        String expectedSymbolName = "expectedName";
        Symbol testSymbol = new Symbol(expectedSymbolName);
        try{
            testSymbolTable.CreateScope("testScope");
        } catch (Exception e) {
            assert(false);
        }
        testSymbolTable.InsertSymbol(testSymbol);


        //Act
        boolean result = testSymbolTable.SearchSymbol("expectedName").get_name() == expectedSymbolName;

        //Assert
        assert(result);
    }

    @Test()
    public void InsertSymbol_WhenInserted_ThenCurrentScopeContainsSymbol() {

        String expectedSymbolName = "expectedName";
        Symbol testSymbol = new Symbol(expectedSymbolName);

        testSymbolTable.InsertSymbol(testSymbol);

        boolean result = testSymbolTable.get_currentScope().get_symbols().containsKey(expectedSymbolName);

        //Assert
        assert(result);
    }
}
