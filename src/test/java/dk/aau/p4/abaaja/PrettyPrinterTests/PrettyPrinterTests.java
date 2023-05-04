package dk.aau.p4.abaaja.PrettyPrinterTests;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import dk.aau.p4.abaaja.mctlParser;
import dk.aau.p4.abaaja.mctlLexer;
import dk.aau.p4.abaaja.Lib.Nodes.*;
import dk.aau.p4.abaaja.Lib.Visitors.AstBuilder;
import dk.aau.p4.abaaja.Lib.ProblemHandling.ProblemCollection;
import dk.aau.p4.abaaja.Lib.TextSinks.StringSink;
import dk.aau.p4.abaaja.Lib.Visitors.PrettyPrintVisitor;

@Test()
public class PrettyPrinterTests {

    private static final String codeExamplesPath = "src/test/java/dk/aau/p4/abaaja/PrettyPrinterTests";

    public String formatCode(String code) {

        // Build tree from input code:

        mctlLexer lexer = new mctlLexer(CharStreams.fromString(code));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);

        mctlParser parser = new mctlParser(commonTokenStream);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.mctl();

        MctlNode concreteNode = (MctlNode) tree.accept(new AstBuilder(new ProblemCollection()));

        // Create pretty printed code from tree:

        PrettyPrintVisitor prettyPrintVisitor = new PrettyPrintVisitor();
        StringSink stringSink = new StringSink();
        prettyPrintVisitor.set_sink(stringSink);
        concreteNode.accept(prettyPrintVisitor);

        return stringSink.get_result();
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
    public Iterator<Object[]> prettyCode() {
        Iterator<String> fileContents = readFolder("/PrettyCode", ".mctl");
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return fileContents.hasNext();
            }

            @Override
            public Object[] next() {
                Object content = fileContents.next();
                return new Object[]{content};
            }
        };
    }

    @Test(dataProvider = "prettyCode")
    public void printsIdentically(String code) {
        // TODO: When we know exactly how the parser reports errors, we could probably do a better job of reporting parsing errors here.
        String formattedCode = "";
        try {
            formattedCode = formatCode(code);
        } catch (Exception exception) {
            Assert.fail("Was unable to parse the code: " + exception.getMessage());
        }
        Assert.assertEquals(formattedCode, code, "The formatter should not change code that is already correctly formatted.");
    }

}