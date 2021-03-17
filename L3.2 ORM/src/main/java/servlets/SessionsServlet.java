package servlets;

import com.google.gson.Gson;
import dbService.AccountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class SessionsServlet extends HttpServlet {
    private final AccountService accountService;

    public SessionsServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //get logged user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        entities.UserProfile profile = accountService.getUserBySessionId(sessionId);
        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(profile);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    //sign in
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(401);
            Logger.getGlobal().info("SessionServlet bad request " +
                    login + " " + response.getStatus());
            response.getWriter().println("Unauthorized");
            return;
        }

        entities.UserProfile profile = accountService.getUserByLogin(login);
        if (!profile.getPass().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(401);
            Logger.getGlobal().info("SessionServlet unauthorized " +
                    login + " " + response.getStatus());
            response.getWriter().println("Unauthorized");
            return;
        }

        if (profile == null) {
                request.getRequestDispatcher("/signup").forward(request, response);
            return;
        }

        accountService.addSession(request.getSession().getId(), profile);
        /*Gson gson = new Gson();
        String json = gson.toJson(profile);*/
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        Logger.getGlobal().info("SessionServlet authorized " + login + " " + response.getStatus());
        response.getWriter().println("Authorized: " + login);
    }

    //sign out
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        entities.UserProfile profile = accountService.getUserBySessionId(sessionId);
        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            accountService.deleteSession(sessionId);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Goodbye!");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }
}
