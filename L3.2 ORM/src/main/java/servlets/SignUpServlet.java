package servlets;

import dbService.AccountService;
import dbService.dataSets.UsersDataSet;
import org.h2.jdbc.JdbcSQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (accountService.getId(login) != -1 || login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(401);
            Logger.getGlobal().info("SignUpServlet bad request:  illegal login or password or this login: " +
                    login + " is registered " + response.getStatus());
            response.getWriter().println("SignUpServlet bad request:  illegal login or password or this login: " +
                    login + " - is registered " + response.getStatus());
            return;
        }
        accountService.saveUser(new UsersDataSet(login, password));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        Logger.getGlobal().info("SignUpServlet registered " + login + " " + response.getStatus());
        response.getWriter().println("Registered: " + login);
    }
}
