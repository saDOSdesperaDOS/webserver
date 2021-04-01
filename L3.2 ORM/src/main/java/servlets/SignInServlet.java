package servlets;

import dbService.AccountService;
import dbService.DBException;
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
        UsersDataSet usersDataSet = null;

        try {
            usersDataSet = accountService.getUserByLogin(login);
        } catch (DBException e) {
            System.out.println("Exception AccountService#getUserByLogin(String login)");
            e.printStackTrace();
        }

        if ( !password.equals(usersDataSet.getPassword()) ) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(401);
            Logger.getGlobal().info("SignInServlet unauthorized " +
                    login + " " + resp.getStatus());
            resp.getWriter().println(login + " unauthorized");
            return;
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        Logger.getGlobal().info("SignInServlet authorized " + login + " " + resp.getStatus());
        resp.getWriter().println("Authorized: " + login);
    }
}
