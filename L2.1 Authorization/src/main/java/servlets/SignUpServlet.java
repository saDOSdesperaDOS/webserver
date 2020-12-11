package servlets;


import accounts.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AccountService accountService = (AccountService) this.getServletContext().getAttribute("accountService");
        UserProfile userProfile = new UserProfile(req.getParameter("login"), req.getParameter("password"), req.getParameter("email"));
        accountService.addNewUser(userProfile);
        this.getServletContext().log("added userProfile");
        accountService.addSession(req.getSession().getId(), userProfile);
        this.getServletContext().log("added sessionID");
    }
}
