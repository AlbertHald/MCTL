package dk.aau.p4.abaaja.Lib.ProblemHandling;

/**
 * Enumerator for errors and warnings.
 */
public enum ProblemType {
    /*
    E: Error, W: Warning
    Add errors and warnings here:
     */
    ERROR_1("E1"),
    ERROR_2("E2");

    private final String errorType;

    ProblemType(String type) {
        this.errorType = type;
    }

    public String getError() {
        return errorType;
    }
}