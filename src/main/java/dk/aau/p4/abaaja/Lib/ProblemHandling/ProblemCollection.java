package dk.aau.p4.abaaja.Lib.ProblemHandling;

import java.util.ArrayList;
import java.util.List;

public class ProblemCollection {
    public static final String codeDelim = "---- code ----";
    public static final String errorDelim = "---- error ----";
    public static final String lineDelim = "---- line ----";

    public boolean ignoreProblems = false;

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
        if (ignoreProblems) return;

        problems.add(problem);

        if (!hasErrors && problem.getProblemType().getProblemString().startsWith("E")) hasErrors = true;
    }

    public void addProblem(ProblemType type, String message, int line) {
        if (ignoreProblems) return;

        problems.add(new Problem(type, message, line, 0, 0));

        if (!hasErrors && type.getProblemString().startsWith("E")) hasErrors = true;
    }

    public void addProblem(ProblemType type, String message, int line, int charStartIndex, int charStopIndex) {
        if (ignoreProblems) return;

        problems.add(new Problem(type, message, line, charStartIndex, charStopIndex));

        if (!hasErrors && type.getProblemString().startsWith("E")) hasErrors = true;
    }

    /*
     * Method for creatingProblemMessages
     */
    public static final int totalCharacters = 32;
    public static final String startDelimChar = "=";

    public void addFormattedProblem(ProblemType type, String message, int line) {
        if (ignoreProblems) return;

        // Format message string
        String problemMessage = String.format("%s\n%s\n%s\nLine: %d\n", createProblemTitle(type.toString(), totalCharacters), message, lineDelim, line);

        problems.add(new Problem(type, problemMessage, line, 0, 0));

        if (!hasErrors && type.getProblemString().startsWith("E")) hasErrors = true;
    }

    public static String createProblemTitle(String title, int totalCharacters) {
        // Format the padding strings
        int padding = totalCharacters - title.length();
        int leftPaddingNumber = (int) Math.ceil(padding / 2.0);
        int rightPaddingNumber = (int) Math.floor(padding / 2.0);

        String leftPadding = leftPaddingNumber > 0 ? startDelimChar.repeat(leftPaddingNumber) : "===";
        String rightPadding = rightPaddingNumber > 0 ? startDelimChar.repeat(rightPaddingNumber) : "===";

        // Format message string
        return String.format("%s %s %s", leftPadding, title, rightPadding);
    }

    public static String createHighlightedLine(String orgLine, int startChar, int endChar) {
        // Create line with highlighted error
        String tempLine = orgLine.substring(0, startChar);
        tempLine += "  >>" + orgLine.substring(startChar, endChar) + "<<  ";
        tempLine += orgLine.substring(endChar);

        return tempLine;
    }
}
