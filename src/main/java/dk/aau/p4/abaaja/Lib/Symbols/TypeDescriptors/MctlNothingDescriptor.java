package dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors;

public class MctlNothingDescriptor extends MctlTypeDescriptor {

    @Override
    public String get_type_literal() {
        return "NOTHING";
    }

    @Override
    public MctlNothingDescriptor clone() {
        return new MctlNothingDescriptor();
    }
}
