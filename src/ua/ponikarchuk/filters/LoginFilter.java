package ua.ponikarchuk.filters;

import javax.servlet.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Filter check for validate filled of login form.
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");

        if (login == null || "".equals(login) || password == null || "".equals(password)) {
            servletRequest.setAttribute("incorrect", true);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
