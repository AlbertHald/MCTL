package dk.aau.p4.abaaja;

// Antlr imports
import dk.aau.p4.abaaja.Lib.Interpreter.TextGameBridge;
import org.antlr.v4.runtime.CharStreams;

public class Main {
    public static void main(String[] args) {
        new MCTLInterpreter(new TextGameBridge())
                .run(CharStreams.fromString("struct COORDINATE {\n" +
                        "    variable x: NUMBER,\n" +
                        "    variable y: NUMBER,\n" +
                        "    variable z: NUMBER\n" +
                        "}" +
                        "\n" +
                        "to getCoordinates() : COORDINATE {\n" +
                        "    variable test: COORDINATE;\n" +
                        "    test.x = 0;\n" +
                        "    test.y = 0;\n" +
                        "    test.z = 0;\n" +
                        "    return test;" +
                        "}\n" +
                        "variable str: NUMBER[];\n" +
                        "\n" +
                        "to move(coordinate: COORDINATE) : NOTHING {}\n" +
                        "to select(id: NUMBER) : NOTHING{}\n" +
                        "to place(location: COORDINATE) : NOTHING{}\n" +
                        "to testNumberReturn() : NUMBER { variable testArr: NUMBER[]; testArr.add(3); return testArr[0]; }\n" +
                        "str.add(testNumberReturn());\n" +
                        "\n" +
                        "variable oakInvId: NUMBER;\n" +
                        "variable oakStairInvId: NUMBER;\n" +
                        "variable axeInvId: NUMBER;\n" +
                        "\n" +
                        "oakInvId = 1;\n" +
                        "oakStairInvId = 2;\n" +
                        "axeInvId = 9;\n" +
                        "\n" +
                        "buildStairUpwards(10, axeInvId, oakStairInvId, oakInvId, getCoordinates());\n" +
                        "\n" +
                        "#{ \n" +
                        "    Function for building a stair with a given height\n" +
                        "    PlaceholderInvId must be a solid block (Not sand blocks and so on)\n" +
                        "}\n" +
                        "to buildStairUpwards(height: NUMBER, axeInvId: NUMBER, stairInvId: NUMBER, placeholderInvId: NUMBER, startCoordinate: COORDINATE): NOTHING {\n" +
                        "    variable currCoordinate: COORDINATE;\n" +
                        "    currCoordinate = startCoordinate;\n" +
                        "    \n" +
                        "    move(currCoordinate);\n" +
                        "    moveBackward();\n" +
                        "\n" +
                        "    currCoordinate = getCoordinates();\n" +
                        "\n" +
                        "    #{ Place initial stair }\n" +
                        "    select(stairInvId);\n" +
                        "    place(startCoordinate);\n" +
                        "\n" +
                        "    currCoordinate.x++;\n" +
                        "\n" +
                        "    height--;\n" +
                        "    \n" +
                        "    #{ Iteratively Build Stair }\n" +
                        "    repeat(height) {\n" +
                        "        select(placeholderInvId);\n" +
                        "        place(currCoordinate);\n" +
                        "\n" +
                        "        currCoordinate.x++;\n" +
                        "\n" +
                        "        select(stairInvId);\n" +
                        "        place(currCoordinate);\n" +
                        "\n" +
                        "        #{ Mine placeholder block }\n" +
                        "        select(axeInvId);\n" +
                        "        moveForward();\n" +
                        "        breakUnder();\n" +
                        "        moveBackward();\n" +
                        "\n" +
                        "        #{ Increment coordinates in the stairs direction by one }\n" +
                        "    } \n" +
                        "}"));
    }

}
