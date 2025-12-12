public class Database implements IDatabase {
    @Override
    public void save(String id, String data) {
        System.out.println("[DB] Saved " + id + ": " + data);
    }
}
