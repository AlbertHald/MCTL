package dk.aau.p4.abaaja.Lib.TextSinks;

public class StringSink implements ITextSink {
    private String _result = "";
    public String get_result(){
        return this._result;
    }
    public void print(String out){
        this._result += out;
    }
    public void println(){
        this._result += System.lineSeparator();
    }
}
