package dk.aau.p4.abaaja;

// Antlr imports
import org.antlr.v4.runtime.CharStreams;

public class Main {
    public static void main(String[] args) {
        new MCTL(CharStreams.fromString("a[2]=2;"));
    }

}
