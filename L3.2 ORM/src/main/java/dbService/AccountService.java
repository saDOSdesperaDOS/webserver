package dbService;

import dbService.dataSets.UsersDataSet;

public class AccountService {
    DBService dbService;

    public AccountService() {
        dbService = new DBService();
    }

    public long saveUser(UsersDataSet usersDataSet) {
            return dbService.addUser(usersDataSet.getLogin(), usersDataSet.getPassword());
    }
    public long getId(String login) {
         return  dbService.getUserId(login);
    }

}
