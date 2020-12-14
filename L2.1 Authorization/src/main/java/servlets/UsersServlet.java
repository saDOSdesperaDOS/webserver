package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class UsersServlet extends HttpServlet {
    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"}) //todo: remove after module 2 home work
    private final AccountService accountService;

    public UsersServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //get public user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work

    }

    //sign up
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
        Logger.getGlobal().info("The Servlets UserServlet doPost() method has started processing data");
        UserProfile userProfile = new UserProfile(request.getParameter("login"), request.getParameter("password"), request.getParameter("email"));

        if (accountService.getUserByLogin(userProfile.getLogin()) != null) {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Logger.getGlobal().warning("A user named " + userProfile.getLogin() + " is already registered. Come up with a different username." + " " + "response status: " + response.getStatus());
            return;
        }
            accountService.addNewUser(userProfile);
            Logger.getGlobal().info("A user named " + userProfile.getLogin() + " is registered." + " " + "response status: " + response.getStatus());
    }

    //change profile
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }

    //unregister
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }
}
