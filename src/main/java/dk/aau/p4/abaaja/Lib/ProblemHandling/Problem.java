package dk.aau.p4.abaaja.Lib.ProblemHandling;

/**
 * Instances of this class corresponds to a single error or warning.
 */
public class Problem {
    public Problem(ProblemType problemType, int line, String message) {
        this.problemType = problemType;
        this.line = line;
        this.message = message;
    }

    /*
     * Private variables for the problem
     */
    private ProblemType problemType;
    private int line;
    private String message;

    /*
     * Getter / Setters for the private variables
     */
    public ProblemType getProblemType() { return problemType; }
    public void setProblemType(ProblemType problemType) { this.problemType = problemType; }

    public int getLine() { return line; }
    public void setLine(int line) { this.line = line; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
