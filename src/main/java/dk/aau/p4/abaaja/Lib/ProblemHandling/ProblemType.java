package dk.aau.p4.abaaja.Lib.ProblemHandling;

/**
 * Enumerator for errors and warnings.
 */
public enum ProblemType {
    /*
    Add errors and warnings here:
     */
    ERROR_PARSER("ERROR_PARSER"),
    ERROR_LEXER("ERROR_LEXER");

    private final String errorType;

    ProblemType(String type) {
        this.errorType = type;
    }

    public String getProblemString() {
        return errorType;
    }
}