public class BasePayload implements ITransformation {
    private final String value;

    public BasePayload(String value) {
        this.value = value;
    }

    @Override
    public String apply(String input) {
        return value;
    }
}
