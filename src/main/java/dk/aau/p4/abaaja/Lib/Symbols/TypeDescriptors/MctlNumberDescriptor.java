package dk.aau.p4.abaaja.Lib.Symbols.TypeDescriptors;

public class MctlNumberDescriptor extends MctlTypeDescriptor {
    private final String _typeDescriptor;

    @Override
    public String get_type_literal() {
        return "yes";
    }

    @Override
    public void set_type_literal(String typeLiteral) {

    }

}
