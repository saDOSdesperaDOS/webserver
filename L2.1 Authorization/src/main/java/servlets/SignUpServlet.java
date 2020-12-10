package servlets;


import accounts.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "signupServlet")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*UserProfile userProfile = new UserProfile(req.getParameter("login"), req.getParameter("pass"), req.getParameter("email"));
        AccountService accountService = new AccountService();
        accountService.addNewUser(userProfile);*/
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("doPost() of SignUpServlet");
    }
}
