public class SecurityEventHandler implements IEventHandler {
    @Override
    public void handle(Event e) {
        System.out.println("[SECURITY] Extra analysis: " + e.getId());
        System.out.println("[SecurityMonitor] Alert triggered");
    }
}
