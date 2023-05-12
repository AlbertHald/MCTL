package dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors;

public class MctlNumberDescriptor extends MctlTypeDescriptor {
    @Override
    public String get_type_literal() {
        return "NUMBER";
    }

    @Override
    public MctlNumberDescriptor clone() {
        return new MctlNumberDescriptor();
    }
}
