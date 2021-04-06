package servlets;

import dbService.AccountService;
import dbService.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class SignInServlet extends HttpServlet {
    AccountService accountService;
    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        long id = 0;
        try {
            id = accountService.getId(login);
            UsersDataSet usersDataSet = accountService.getUser(id);
            if (!usersDataSet.getPassword().equals(password)) {
                resp.setContentType("text/html;charset=utf-8");
                resp.setStatus(401);
                Logger.getGlobal().info("Status code " + " " + resp.getStatus());
                resp.getWriter().println("Unauthorized");
                return;
            }
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            Logger.getGlobal().info("Status code " + " " + "(" + resp.getStatus() + ")");
            resp.getWriter().println("Authorized: " + login);
        } catch (NullPointerException e) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(401);
            Logger.getGlobal().info("Status code " + " "  + resp.getStatus());
            resp.getWriter().println("Unauthorized");
        }
    }
}
