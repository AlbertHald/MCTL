package dk.aau.p4.abaaja.Lib;

import java.util.List;

public class PredefinedFunction {
    private String _id;
    private List<List<String>> _parameters;
    private String _returnType;

    public PredefinedFunction(String id, List<List<String>> parameters, String returnType) {
        this._id = id;
        this._parameters = parameters;
        this._returnType = returnType;
    }

    public String get_id() { return _id; }
    public List<List<String>> get_parameters() { return _parameters; }
    public String get_returnType() { return _returnType; }
}
