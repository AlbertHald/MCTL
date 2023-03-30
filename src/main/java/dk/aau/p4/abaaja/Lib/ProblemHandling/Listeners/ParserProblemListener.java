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

        problemCollection.addProblem(ProblemType.ERROR_PARSER, "The following token did not parse: "+ offendingToken.getText(), line);
    }

}
