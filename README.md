# Minecraft Turtle Language Interpreter
This is the repository for the Minecraft Turtle Language Interpreter. The actual MCTL implementation can be found at https://github.com/TSKsmiley/MCTL-Interpreter.

The interpreter reads the MCTL code and transforms it into actions that can run inside the Mincecraft runtime.
The actions are sent to the MCTL mod via an `IGameBridge`. Check out the `EndToEnd` tests to see how this is done.
These tests use the `TextGameBridge` which outputs actions as a text file, allowing us to easily inspect the result of running the code.

## Code examples
There are several fully fledged code examples in `test/java/dk/aau/p4/abaaja/EndToEnd/Code`.

Alternatively, you can also check out `test/java/dk/aau/p4/abaaja/PrettyPrinterTests/PrettyCode`.

## Development and testing
The file `src/main/java/dk/aau/p4/abaaja/Main.java` will take any code as text and first format it for you, then run the code.
All output is printed to the console. This is an easy way to run tests while developing.

Most of the interpreter is also unit tested. The tests can be found and run in the `test` folder. They use `TestNG` with `Mockito`.

## How to build for production
1. Ensure you have a Java 17 compiler with Maven installed. 
2. Initialize the Maven project.
3. Run the Maven task `MCTL > Lifecycle > clean`.
4. Run the Maven task `MCTL > Lifecycle > package`.
5. Collect the packaged interpreter at `target/MCTL.jar`.
