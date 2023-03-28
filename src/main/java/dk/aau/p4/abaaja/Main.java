package dk.aau.p4.abaaja;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {

    public static void main(String[] args) {
        syntaxPhase( CharStreams.fromString("variable test: NUMBER;"));
    }

    private static ParseTree syntaxPhase(CharStream inputStream) {
        //Scans the source code
        mctlLexer lexer = new mctlLexer(inputStream);
        lexer.removeErrorListeners();
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);

        //Parses the source code
        mctlParser parser = new mctlParser(commonTokenStream);
        parser.setBuildParseTree(true);
        parser.removeErrorListeners();

        return parser.mctl();
    }
}

