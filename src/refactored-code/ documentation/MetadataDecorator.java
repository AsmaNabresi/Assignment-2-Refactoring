public class MetadataDecorator implements ITransformation {
    private final ITransformation wrappee;
    private final String metadata;

    public MetadataDecorator(ITransformation wrappee, String metadata) {
        this.wrappee = wrappee;
        this.metadata = metadata;
    }

    @Override
    public String apply(String input) {
        return "META(" + metadata + ")::" + wrappee.apply(input);
    }
}
