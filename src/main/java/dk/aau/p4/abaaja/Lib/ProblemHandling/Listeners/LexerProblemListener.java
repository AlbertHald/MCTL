package dk.aau.p4.abaaja.Lib.ProblemHandling.Listeners;

// Antlr imports
import org.antlr.v4.runtime.*;

// Problem collection imports
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemType;

public class LexerProblemListener extends BaseErrorListener {
    ProblemCollection problemCollection;

    public LexerProblemListener(ProblemCollection problemCollection) {
        this.problemCollection = problemCollection;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {

        CharStream charStream = (CharStream) recognizer.getInputStream();

        // Get input stream and split at newline character
        String totalTokenStream = charStream.toString();
        String[] lines = totalTokenStream.split("\n");

        // Get problematic line
        String problematicLine = lines[line - 1];

        // Create line with highlighted error
        String tempLine = problematicLine.substring(0, charPositionInLine);
        tempLine += "  >>" + problematicLine.charAt(charPositionInLine) + "<<  ";
        tempLine += problematicLine.substring(charPositionInLine + 1);

        String title = problemCollection.createProblemTitle(ProblemType.ERROR_LEXER.toString(), problemCollection.totalCharacters);
        String codeDelim = "---- code ----";
        String errorDelim = "---- error ----";

        String problemString = String.format("%s\nUnrecognizable token at Line %d, Character %d:\n%s\n%s\n%s\n%s", title, line, charPositionInLine, codeDelim, tempLine, errorDelim, msg);

        problemCollection.addProblem(ProblemType.ERROR_LEXER, problemString, line);
    }
}
