package dk.aau.p4.abaaja.ProblemCollection;

import dk.aau.p4.abaaja.Lib.ProblemHandling.Problem;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test()
public class ProblemCollectionTest {
    ProblemCollection problemCollection;

    /**
     * Creates and resets problemcollection before and after each test
     */
    @BeforeMethod
    public void beforeMethod() { problemCollection = new ProblemCollection(); }

    @AfterMethod
    public void afterMethod() { problemCollection = null; }

    /**
     * ProblemCollection tests
     */
    @Test
    public void getProblems_ContainsProblems_ReturnsErrorsCorrectly() {
        // Arrange
        ProblemType type1 = ProblemType.ERROR_PARSER;
        String message = "";
        int lineNumber = 0;
        int charIndex = 0;

        Problem problem = new Problem(type1, message, lineNumber, charIndex, charIndex);

        // Act
        problemCollection.addProblem(problem);
        boolean result = problem  == problemCollection.getProblems().get(0);

        // Assert
        assert(result);
    }

    @DataProvider
    public Object[][] problemsAndExpectedSize() {
        return new Object[][] {
                new Object[] {
                        new Problem[] {
                                new Problem(ProblemType.ERROR_PARSER, "", 0, 0, 0),
                                new Problem(ProblemType.ERROR_PARSER, "", 0, 0, 0)
                        }, 2
                },
                new Object[] {
                        new Problem[] {
                                new Problem(ProblemType.ERROR_PARSER, "", 0, 0, 0),
                                new Problem(ProblemType.ERROR_PARSER, "", 0, 0, 0),
                                new Problem(ProblemType.ERROR_PARSER, "", 0, 0, 0)
                        }, 3
                }
        };
    }

    @Test(dataProvider = "problemsAndExpectedSize")
    public void addProblem_HasProblemsAlready_ListSizeIncreasesByOne(Problem[] problems, int size) {
        // Arrange
        for (Problem problem : problems) {
            problemCollection.addProblem(problem);
        }

        // Act
        boolean result = problemCollection.getProblems().size() == size;

        // Assert
        assert(result);
    }

    @Test
    public void getHasErrors_ContainsNoErrors_ReturnsFalse() {
        // Act
        boolean result = problemCollection.getHasErrors();

        // Assert
        assert(!result);
    }

    @Test
    public void getHasErrors_ContainsErrors_ReturnsTrue() {
        // Arrange
        ProblemType type = ProblemType.ERROR_PARSER;
        String message = "";
        int lineNumber = 0;

        problemCollection.addProblem(type, message, lineNumber);

        // Act
        boolean result = problemCollection.getHasErrors();

        // Assert
        assert(result);
    }
}
