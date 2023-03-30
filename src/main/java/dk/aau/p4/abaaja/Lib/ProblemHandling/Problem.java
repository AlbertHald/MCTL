package dk.aau.p4.abaaja.Lib.ProblemHandling;

/**
 * Instances of this class corresponds to a single error or warning.
 */
public class Problem {
    ProblemType problemType;
    int line;
    String errorMessage;

    public Problem(ProblemType problemType, int line, String errorMessage) {
        this.problemType = problemType;
        this.line = line;
        this.errorMessage = errorMessage;
    }
}
