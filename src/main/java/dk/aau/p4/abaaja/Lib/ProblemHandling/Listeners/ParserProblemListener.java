package dk.aau.p4.abaaja.Lib.ProblemHandling.Listeners;

// Antlr imports
import org.antlr.v4.runtime.*;

// Problem collection imports
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemType;

public class ParserProblemListener extends BaseErrorListener {
    ProblemCollection problemCollection;

    public ParserProblemListener(ProblemCollection problemCollection) {
        this.problemCollection = problemCollection;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {
        // TODO: Implement proper error messages
        CommonTokenStream tokens = (CommonTokenStream)recognizer.getInputStream();
        Token offendingToken = (Token) offendingSymbol;

        // Get input stream and split at newline character
        String totalTokenStream = tokens.getTokenSource().getInputStream().toString();
        String[] lines = totalTokenStream.split("\n");

        // Get problematic line
        int problematicLineIndex = offendingToken.getLine() - 1;
        String problematicLine = lines[problematicLineIndex];

        int tokenLength = offendingToken.getStopIndex() - offendingToken.getStartIndex();
        int tokenEnd = charPositionInLine + tokenLength + 1;

        // Create line with highlighted error
        String tempLine = problematicLine.substring(0, charPositionInLine);
        tempLine += "  >>" + problematicLine.substring(charPositionInLine, tokenEnd) + "<<  ";
        tempLine += problematicLine.substring(tokenEnd, problematicLine.length());

        // Format message string
        String errorMessage = String.format("Parse error at Line %d, Character %d: %s", line, charPositionInLine, tempLine);

        problemCollection.addProblem(ProblemType.ERROR_PARSER, errorMessage, line, charPositionInLine, tokenEnd);
    }

}