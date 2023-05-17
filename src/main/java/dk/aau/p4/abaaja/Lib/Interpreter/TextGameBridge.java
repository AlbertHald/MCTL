package dk.aau.p4.abaaja.Lib.Interpreter;

import dk.aau.p4.abaaja.Lib.TextSinks.ConsoleSink;
import dk.aau.p4.abaaja.Lib.TextSinks.ITextSink;

/**
 * This is used for easy testing of the Interpreter.
 * It will pretend to be the game bridge, but will simply print the commands to the console.
 */
public class TextGameBridge implements IGameBridge {

    /**
     * Can output to different sinks depending on what is passed to `set_sink()`.
     * Can output to console by passing `System.out`, can output to String by passing `StringSink`.
     */
    private final ITextSink _sink;

    public TextGameBridge(){
        this._sink = new ConsoleSink();
    }
    public TextGameBridge(ITextSink sink){
        this._sink = sink;
    }

    private void printFunc(String functionDescription, String outputs){
        _sink.print(functionDescription + " [->" + outputs + "]");
        _sink.println();
    }
    private void printFunc(String functionDescription){
        printFunc(functionDescription, "NOTHING");
    }

    @Override
    public void print(String text) {
        printFunc("Print: \"" + text + "\"");
    }

    @Override
    public String read() {
        printFunc("Read", "\"Dummy read string\"");
        return "Dummy read string";
    }

    @Override
    public boolean moveForward(){
        printFunc("Move 1 forward", "true");
        return true;
    }

    @Override
    public boolean moveUp() {
        printFunc("Move 1 up", "true");
        return true;
    }

    @Override
    public boolean moveDown() {
        printFunc("Move 1 down", "true");
        return true;
    }

    @Override
    public boolean moveBackward() {
        printFunc("Move 1 back", "true");
        return true;
    }

    @Override
    public void turnLeft() {
        printFunc("Turn 90 deg left");
    }

    @Override
    public void turnRight() {
        printFunc("Turn 90 deg right");
    }

    @Override
    public void breakFront() {
        printFunc("Break block in front of");
    }

    @Override
    public void breakAbove() {
        printFunc("Break block above");
    }

    @Override
    public void breakUnder() {
        printFunc("Break block under");
    }

    @Override
    public void placeFront(String blockId) {
        printFunc("Place block in front of: " + blockId);
    }

    @Override
    public void placeAbove(String blockId) {
        printFunc("Place block above: " + blockId);
    }

    @Override
    public void placeUnder(String blockId) {
        printFunc("Place block under: " + blockId);
    }

    @Override
    public String blockFront() {
        printFunc("Get block info in front of", "\"dummy\"");
        return "dummy";
    }

    @Override
    public String blockAbove() {
        printFunc("Get block info above", "\"dummy\"");
        return "dummy";
    }

    @Override
    public String blockUnder() {
        printFunc("Get block info under", "\"dummy\"");
        return "dummy";
    }

    @Override
    public void internal_terminate() {
        printFunc("Terminate the program");
    }


}
