package dbService;

import dbService.dataSets.UsersDataSet;

public class AccountService {
    DBService dbService;

    public AccountService() {
        dbService = new DBService();
    }

    public void addNewUser(UsersDataSet usersDataSet) {
        try {
            dbService.addUser(usersDataSet.getLogin());
        } catch (DBException e) {
            System.out.println("Exception DBService#addNewUser");
            e.printStackTrace();
        }
    }
    //метод который делает запрос в б.д
    public UsersDataSet getUserByLogin(String login) throws DBException {
           return dbService.getUserByLogin(login);
    }

}
