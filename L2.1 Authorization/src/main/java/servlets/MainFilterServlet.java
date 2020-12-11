package servlets;

import accounts.AccountService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

@WebFilter
public class MainFilterServlet implements Filter {
    private ServletContext context;
    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
        this.context.log("MainFilterServlet initialized");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Enumeration<String> params = req.getParameterNames();
        AccountService accountService = (AccountService) context.getAttribute("accountService");
        if (accountService.getUserBySessionId(req.getSession().getId())) {

        }
    }
    @Override
    public void destroy() {

    }
}
