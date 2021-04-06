import dbService.AccountService;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import java.util.logging.Logger;

public class Test {
    public static void main(String... args) {
        String login = "testlogin";
        String tLogin = "testlogin2";
        String pass = "testpass";
        DBService dbService = new DBService();
        dbService.addUser(login, pass);
        Logger.getGlobal().info("added " + login );
        Logger.getGlobal().info("id = " + dbService.getUserId(login));
        Logger.getGlobal().info("id = " + dbService.getUserId(tLogin));

    }
}
