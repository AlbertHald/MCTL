package dk.aau.p4.abaaja.Lib.TextSinks;

public class ConsoleSink implements ITextSink {
    public void print(String out){
        System.out.print(out);
    }
    public void println(){
        System.out.println();
    }
}
