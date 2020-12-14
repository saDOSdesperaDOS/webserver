package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/api/v1/sessions");
        dispatcher.forward(req, resp);
        if (resp.getStatus() == HttpServletResponse.SC_OK) {
            PrintWriter out = resp.getWriter();
            out.println("Authorized: " + login);
        } else {
            PrintWriter out = resp.getWriter();
            out.println("Unauthorized");
        }

    }
}
