package dk.aau.p4.abaaja.Lib.ProblemHandling;

import java.util.ArrayList;
import java.util.List;

public class ProblemCollection {
    /*
     * Get / Set methods for the problem collection variables
     */
    public List<Problem> getProblems() {
        return problems;
    }
    public boolean getHasErrors() {
        return hasErrors;
    }

    /*
     * Private variables for the problem collection
     */
    final private List<Problem> problems = new ArrayList<>();
    private boolean hasErrors = false;

    /*
     * Add method for adding problems to the collection
     */
    public void addProblem(ProblemType type, String message, int line) {
        problems.add(new Problem(type, line, message));

        if (type.getProblemString().startsWith("E") && !hasErrors) hasErrors = true;
    }
}
