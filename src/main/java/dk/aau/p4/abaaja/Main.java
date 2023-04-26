package dk.aau.p4.abaaja;

// Antlr imports
import dk.aau.p4.abaaja.Lib.Nodes.MctlNode;
import dk.aau.p4.abaaja.Lib.TextSinks.*;
import dk.aau.p4.abaaja.Lib.Visitors.AstVisitor;
import dk.aau.p4.abaaja.Lib.Visitors.PrettyPrintVisitor;
import dk.aau.p4.abaaja.Lib.Visitors.SymbolTableVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

// Error handling imports
import dk.aau.p4.abaaja.Lib.ProblemHandling.Problem;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.ProblemHandling.Listeners.LexerProblemListener;
import dk.aau.p4.abaaja.Lib.ProblemHandling.Listeners.ParserProblemListener;

public class Main {
    public static void main(String[] args) {
        // Initialize the problem collection
        ProblemCollection problemCollection = new ProblemCollection();

        // Parse test CharStream
        ParseTree tree = syntaxPhase( CharStreams.fromString("a[2]=2;"), problemCollection);

        if (!problemCollection.getHasErrors()) {
            // Continue parsing here
            MctlNode concreteNode = (MctlNode) tree.accept(new AstVisitor(problemCollection));

            PrettyPrintVisitor prettyPrintVisitor = new PrettyPrintVisitor();
            prettyPrintVisitor.set_sink(new ConsoleSink());
            concreteNode.accept(prettyPrintVisitor);

            concreteNode.accept(new SymbolTableVisitor(problemCollection));

            for (Problem problem : problemCollection.getProblems()) {
                System.out.println("Problem type: " + problem.getProblemType() + ". Message: " + problem.getMessage());
            }
        }
        else {
            // Prints parse errors
            for (Problem problem : problemCollection.getProblems()) {
                System.out.println(problem.getMessage());
            }
        }
    }

    /**
     * Method for parsing a given CharStream
     * @param inputStream
     * @param problemCollection
     * @return ParseTree
     */
    private static ParseTree syntaxPhase(CharStream inputStream, ProblemCollection problemCollection) {
        //Scans the source code
        mctlLexer lexer = new mctlLexer(inputStream);
        lexer.removeErrorListeners();
        // Remove existing listeners and add the custom LexerProblemListener
        lexer.addErrorListener(new LexerProblemListener(problemCollection));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);

        //Parse the sourcecode
        mctlParser parser = new mctlParser(commonTokenStream);
        parser.setBuildParseTree(true);
        // Remove existing listeners and add the custom ParserProblemListener
        parser.removeErrorListeners();
        parser.addErrorListener(new ParserProblemListener(problemCollection));

        return parser.mctl();
    }
}

