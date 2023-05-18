package dk.aau.p4.abaaja;

// Antlr imports
import dk.aau.p4.abaaja.Lib.Interpreter.TextGameBridge;
import org.antlr.v4.runtime.CharStreams;

public class Main {
    public static void main(String[] args) {
        String program = "print(\"bang\");";

        System.out.println(new MCTLFormatter(new TextGameBridge()).run(CharStreams.fromString(program)));
        new MCTLInterpreter(new TextGameBridge()).run(CharStreams.fromString(program));
    }

}
