package main;


import dbService.AccountService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SessionsServlet;
import servlets.SignUpServlet;
import org.eclipse.jetty.server.Server;

import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {
        /*DBService dbService = new DBService();
        dbService.printConnectInfo();

        try {
            long userId = dbService.addUser("tully");
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);

        } catch (DBException e) {
            e.printStackTrace();
        }*/

        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(new AccountService())), "/signup");
        contextHandler.addServlet(new ServletHolder(new SessionsServlet(new AccountService())), "/api/v1/sessions");
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, contextHandler});
        Server server = new Server(8080);
        server.setHandler(handlers);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger.getGlobal().info("Server started");


    }
}
