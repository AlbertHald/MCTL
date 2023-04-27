package dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors;

public class MctlArrayTypeDescriptor extends MctlTypeDescriptor {

    private int _degree;
    private MctlTypeDescriptor _type;

    public MctlArrayTypeDescriptor(MctlTypeDescriptor type, int degree) {
        this._degree = degree;
        this._type = type;
    }
    @Override
    public String get_type_literal() {
        String result = _type.get_type_literal();
        for (int i = 0; i < _degree; i++) {
            result += "[]";
        }
        return result;
    }
}
