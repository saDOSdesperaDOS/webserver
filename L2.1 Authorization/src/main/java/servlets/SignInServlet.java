package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (re) {
            resp.setContentType("text/html");
            resp.setStatus(200);
            PrintWriter out = resp.getWriter();
            out.println("Authorized: login");
        } else {
            resp.setContentType("text/html");
            resp.setStatus(401);
            PrintWriter out = resp.getWriter();
            out.println("Unauthorized");
        }
    }
}
