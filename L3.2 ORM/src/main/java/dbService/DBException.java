package dbService;

public class DBException extends Exception {
    public DBException(Throwable e) {
        System.out.println("Unathorized");
    }
}
