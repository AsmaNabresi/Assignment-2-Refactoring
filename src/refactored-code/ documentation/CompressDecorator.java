public class CompressDecorator implements ITransformation {
    private final ITransformation wrappee;

    public CompressDecorator(ITransformation wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public String apply(String input) {
        return "CMP(" + wrappee.apply(input) + ")";
    }
}
