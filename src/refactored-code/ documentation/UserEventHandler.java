public class UserEventHandler implements IEventHandler {
    @Override
    public void handle(Event e) {
        System.out.println("[USER] Handling user event: " + e.getId());
    }
}
