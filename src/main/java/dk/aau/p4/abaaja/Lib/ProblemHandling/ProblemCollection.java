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
    public void addProblem(Problem problem) {
        problems.add(problem);

        if (problem.getProblemType().getProblemString().startsWith("E") && !hasErrors) hasErrors = true;
    }

    public void addProblem(ProblemType type, String message, int line) {
        problems.add(new Problem(type, message, line, 0, 0));

        if (type.getProblemString().startsWith("E") && !hasErrors) hasErrors = true;
    }

    public void addProblem(ProblemType type, String message, int line, int charStartIndex, int charStopIndex) {
        problems.add(new Problem(type, message, line, charStartIndex, charStopIndex));

        if (type.getProblemString().startsWith("E") && !hasErrors) hasErrors = true;
    }

    /*
     * Method for creatingProblemMessages
     */
    public final int totalCharacters = 60;
    final String startDelimChar = "=";
    final String lineDelim = "---- line ----";

    public void addFormattedProblem(ProblemType type, String message, int line) {
        // Format message string
        String problemMessage = String.format("%s\n%s\n%s\nLine: %o", createProblemTitle(type.toString(), totalCharacters), message, lineDelim, line);

        problems.add(new Problem(type, problemMessage, line, 0, 0));

        if (type.getProblemString().startsWith("E") && !hasErrors) hasErrors = true;
    }

    public String createProblemTitle(String title, int totalCharacters) {
        // Format the padding strings
        int padding = totalCharacters - title.length();
        int leftPaddingNumber = (int) Math.ceil(padding / 2.0);
        int rightPaddingNumber = (int) Math.floor(padding / 2.0);

        String leftPadding = startDelimChar.repeat(leftPaddingNumber);
        String rightPadding = startDelimChar.repeat(rightPaddingNumber);

        // Format message string
        return String.format("%s %s %s", leftPadding, title, rightPadding);
    }
}
