package dk.aau.p4.abaaja.Lib.Errors;

import java.util.ArrayList;
import java.util.List;

public class ProblemCollection {
    /*
     * Get / Set methods for the error collection variables
     */
    public List<Problem> getErrors() {
        return problems;
    }
    public boolean getHasProblems() {
        return hasProblems;
    }

    /*
     * Private variables for the error collection
     */
    final private List<Problem> problems = new ArrayList<>();
    private boolean hasProblems = false;

    /*
     * Add method for adding errors, warnings, etc. to the collection
     */
    public void addProblem(ProblemType type, String message, int line) {
        problems.add(new Problem(type, line, message));
    }
}
