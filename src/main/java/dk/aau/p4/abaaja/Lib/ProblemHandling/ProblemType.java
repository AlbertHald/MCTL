package dk.aau.p4.abaaja.Lib.ProblemHandling;

/**
 * Enumerator for errors and warnings.
 */
public enum ProblemType {
    /*
    Add errors and warnings here:
     */
    ERROR_PARSER("ERROR_PARSER"),
    ERROR_LEXER("ERROR_LEXER"),
    ERROR_AST_BUILDER("ERROR_AST_BUILDER"),
    ERROR_IDENTIFIER_CANNOT_BE_REUSED("ERROR_IDENTIFIER_CANNOT_BE_REUSED"),
    ERROR_UNDEFINED_IDENTIFIER("ERROR_UNDEFINED_IDENTIFIER"),
    ERROR_UNKNOWN_TYPE("ERROR_UNKNOWN_TYPE"),
    ERROR_TYPE_MISMATCH("ERROR_TYPE_MISMATCH"),
    ERROR_PARAMETERS_DOES_NOT_MATCH("ERROR_PARAMETERS_DOES_NOT_MATCH"),
    ERROR_ID_NOT_FUNCTION("ERROR_ID_NOT_FUNCTION"),
    ERROR_UTILITY_FUNCTION_INVOKED_IN_WRONG_CONTEXT("ERROR_UTILITY_FUNCTION_INVOKED_IN_WRONG_CONTEXT"),
    ERROR_UNEXPECTED_RETURN("ERROR_UNEXPECTED_RETURN");

    private final String errorType;

    ProblemType(String type) {
        this.errorType = type;
    }

    public String getProblemString() {
        return errorType;
    }
}