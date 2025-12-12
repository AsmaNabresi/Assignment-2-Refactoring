public class EncryptDecorator implements ITransformation {
    private final ITransformation wrappee;

    public EncryptDecorator(ITransformation wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public String apply(String input) {
        return "ENC(" + wrappee.apply(input) + ")";
    }
}
