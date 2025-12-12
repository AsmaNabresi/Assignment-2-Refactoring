public class Dashboard implements IDashboard {
    @Override
    public void update(Event e) {
        System.out.println("[Dashboard] metrics updated: " + e.getPayload());
    }
}
