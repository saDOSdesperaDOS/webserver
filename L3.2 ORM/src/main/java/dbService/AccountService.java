package dbService;

import entities.UserProfile;

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private DBService loginToProfile;
    private Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new DBService();
    }

    public void addNewUser(UserProfile userProfile) {
        try {
            loginToProfile.addUser(userProfile.getLogin());
        } catch (DBException e) {
            System.out.println("Exception DBService#addNewUser");
            e.printStackTrace();
        }
    }
    //метод который делает запрос в б.д
    /*public UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }*/

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
