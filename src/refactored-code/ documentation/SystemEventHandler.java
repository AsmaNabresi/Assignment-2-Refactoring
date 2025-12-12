public class SystemEventHandler implements IEventHandler {
    @Override
    public void handle(Event e) {
        System.out.println("[SYSTEM] System audit log for " + e.getId());
    }
}
