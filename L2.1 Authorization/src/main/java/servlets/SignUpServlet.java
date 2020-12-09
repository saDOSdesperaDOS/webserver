package servlets;


import accounts.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*UserProfile userProfile = new UserProfile(req.getParameter("login"), req.getParameter("pass"), req.getParameter("email"));
        AccountService accountService = new AccountService();
        accountService.addNewUser(userProfile);*/
    }
}
