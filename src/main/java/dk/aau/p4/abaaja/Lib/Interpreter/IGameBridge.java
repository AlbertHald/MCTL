package dk.aau.p4.abaaja.Lib.Interpreter;

public interface IGameBridge {

    void print(String text);

    // move ----
    boolean moveForward();
    boolean moveUp();
    boolean moveDown();
    boolean moveBackward();
    void turnLeft();
    void turnRight();

    // break ----
    void breakFront();
    void breakAbove();
    void breakUnder();

    // place ----
    void placeFront(String blockId);
    void placeAbove(String blockId);
    void placeUnder(String blockId);

    // read ----
    String blockFront();
    String blockAbove();
    String blockUnder();

}
