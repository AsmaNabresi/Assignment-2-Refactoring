public class EventProcessor {

    private final IDatabase db;
    private final IDashboard dashboard;
    private final ILogger logger;
    private final Map<String, IEventHandler> handlers = new HashMap<>();

    public EventProcessor(IDatabase db, IDashboard dashboard, ILogger logger) {
        this.db = db;
        this.dashboard = dashboard;
        this.logger = logger;

        handlers.put("USER", new UserEventHandler());
        handlers.put("SECURITY", new SecurityEventHandler());
        handlers.put("SYSTEM", new SystemEventHandler());
    }

    public void process(Event e) {

        if (e.getPayload() == null || e.getPayload().isEmpty()) {
            logger.log("Invalid event");
            return;
        }

        e.setId(System.currentTimeMillis() + "-" + Math.abs(e.getPayload().hashCode()));

        dashboard.update(e);
        logger.log("Processing event " + e.getId());

        ITransformation transform = new BasePayload(e.getPayload());

        if (e.isEncrypt()) transform = new EncryptDecorator(transform);
        if (e.isCompress()) transform = new CompressDecorator(transform);
        if (e.isAddMetadata()) transform = new MetadataDecorator(transform, e.getMetadata());

        String finalData = transform.apply(e.getPayload());

        db.save(e.getId(), finalData);

        if (handlers.containsKey(e.getType())) {
            handlers.get(e.getType()).handle(e);
        }
    }
}
