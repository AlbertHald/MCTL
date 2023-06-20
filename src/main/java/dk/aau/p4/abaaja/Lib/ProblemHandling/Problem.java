package dk.aau.p4.abaaja.Lib.ProblemHandling;

/**
 * Instances of this class corresponds to a single error or warning.
 */
public class Problem {
    public Problem(ProblemType problemType, String message, int line, int charStartIndex, int charStopIndex) {
        this.problemType = problemType;
        this.message = message;
        this.line = line;
    }

    /*
     * Private variables for the problem
     */
    private ProblemType problemType;
    private String message;
    private int line;
    private int charStopIndex;
    private int charStartIndex;

    /*
     * Getter / Setters for the private variables
     */
    public ProblemType getProblemType() { return problemType; }
    public void setProblemType(ProblemType problemType) { this.problemType = problemType; }

    public int getLine() { return line; }
    public void setLine(int line) { this.line = line; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getCharStopIndex() { return charStopIndex; }
    public void setCharStopIndex(int charStopIndex) { this.charStopIndex = charStopIndex; }

    public int getCharStartIndex() { return charStartIndex; }
    public void setCharStartIndex(int charStartIndex) { this.charStartIndex = charStartIndex; }
}
