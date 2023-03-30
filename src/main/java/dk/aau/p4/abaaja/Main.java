package dk.aau.p4.abaaja;

// Antlr imports
import dk.aau.p4.abaaja.Lib.ProblemHandling.Listeners.ParserProblemListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

// Error handling imports
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.Listeners.LexerProblemListener;

public class Main {
    public static void main(String[] args) {
        // Initialize the problem collection
        ProblemCollection problemCollection = new ProblemCollection();


        ParseTree tree = syntaxPhase( CharStreams.fromString("variable test: NUMBER \n test = 2;"), problemCollection);
        //System.out.println(tree.getChild(0));
    }

    private static ParseTree syntaxPhase(CharStream inputStream, ProblemCollection problemCollection) {
        //Scans the source code
        mctlLexer lexer = new mctlLexer(inputStream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new LexerProblemListener(problemCollection));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);

        //Parses the source code
        mctlParser parser = new mctlParser(commonTokenStream);
        parser.setBuildParseTree(true);
        parser.removeErrorListeners();
        parser.addErrorListener(new ParserProblemListener(problemCollection));

        return parser.mctl();
    }
}

