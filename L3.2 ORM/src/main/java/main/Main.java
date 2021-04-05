package main;


import dbService.AccountService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {
        /*DBService dbService = new DBService();
        dbService.printConnectInfo();

        try {
            long userId = dbService.addUser("tully");
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUserById(userId);
            System.out.println("User data set: " + dataSet);

        } catch (DBException e) {
            e.printStackTrace();
        }*/
        AccountService accountService = new AccountService();
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        contextHandler.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, contextHandler});
        Server server = new Server(8080);
        server.setHandler(handlers);
        try {
            server.start();
            Logger.getGlobal().info("Server started");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
