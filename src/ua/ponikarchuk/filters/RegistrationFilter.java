package ua.ponikarchuk.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter check for validate filled of registration form.
 */
public class RegistrationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String name = servletRequest.getParameter("name");
        String surname = servletRequest.getParameter("surname");
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");

        if (name == null || surname == null || login == null || password == null) {
            servletRequest.setAttribute("incorrect", true);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
