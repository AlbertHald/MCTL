package dk.aau.p4.abaaja.EndToEnd;

import dk.aau.p4.abaaja.Lib.Interpreter.Interpreter;
import dk.aau.p4.abaaja.Lib.Interpreter.TextGameBridge;
import dk.aau.p4.abaaja.Lib.Nodes.MctlNode;
import dk.aau.p4.abaaja.Lib.ProblemHandling.Problem;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.Symbols.SymbolTable;
import dk.aau.p4.abaaja.Lib.TextSinks.StringSink;
import dk.aau.p4.abaaja.Lib.Visitors.AstBuilder;
import dk.aau.p4.abaaja.mctlLexer;
import dk.aau.p4.abaaja.mctlParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class EndToEndTests {

    private static final String codeExamplesPath = "src/test/java/dk/aau/p4/abaaja/EndToEnd";

    public MctlNode parseNode(String code) {

        // Build tree from input code:

        mctlLexer lexer = new mctlLexer(CharStreams.fromString(code));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);

        mctlParser parser = new mctlParser(commonTokenStream);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.mctl();

        MctlNode concreteNode = (MctlNode) tree.accept(new AstBuilder(new ProblemCollection()));

        return concreteNode;

    }

    public Iterator<String> readFolder(String localFolderPath, String extensionFilter) {
        File folder = new File(codeExamplesPath + localFolderPath);
        return Stream.of(Objects.requireNonNull(folder.list()))
                .filter(path -> path.toLowerCase().endsWith(extensionFilter))
                .map(path -> {
                    try {
                        return Files.readString(Paths.get(codeExamplesPath + localFolderPath + "/" + path));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .iterator();
    }

    @DataProvider
    public Iterator<Object[]> code() {
        Iterator<String> fileContents = readFolder("/Code", ".mctl");
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return fileContents.hasNext();
            }

            @Override
            public Object[] next() {
                String content = fileContents.next();
                Pattern pattern = Pattern.compile("^--CODE--$\\n(?<code>.*)^--OUTPUT--$\\n(?<output>.*)", Pattern.MULTILINE | Pattern.DOTALL);
                Matcher matcher = pattern.matcher(content);
                matcher.find();

                return new Object[]{matcher.group("code"), matcher.group("output")};
            }
        };
    }
    @Test(dataProvider = "code")
    public void code_interpretedToOutput(String code, String output) {
        MctlNode concreteNode = parseNode(code);

        ProblemCollection problemCollection = new ProblemCollection();
        StringSink bridgeSink = new StringSink();

        concreteNode.accept(new Interpreter(problemCollection, new SymbolTable(), new TextGameBridge(bridgeSink)));

        for (Problem problem : problemCollection.getProblems()) {
            Assert.fail("Should not fail with message: " + problem.getMessage());
        }
        Assert.assertEquals(bridgeSink.get_result(), output, "Should result in the correct GameBridge output");
    }
}
