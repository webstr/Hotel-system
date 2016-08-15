package ua.ponikarchuk.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter check for validate GET request path.
 */
public class BadRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String path = ((HttpServletRequest) servletRequest).getRequestURI();
        if (path.equals("/") ||
                path.equals("/registration.jsp") ||
                path.equals("/login.jsp") ||
                path.equals("/index.jsp") ||
                path.equals("/createRoom.jsp") ||
                path.equals("/createApplication.jsp") ||
                path.equals("/logout") ||
                path.equals("/controllerCreateApplication") ||
                path.equals("/controllerCreateRoom") ||
                path.equals("/viewApplications") ||
                path.equals("/viewAllApplications") ||
                path.equals("/checkApplication") ||
                path.equals("/chooseRoom") ||
                path.equals("/payApplication") ||
                path.equals("/changeLanguage") ||
                path.equals("/css/bootstrap.min.css") ||
                path.equals("/js/bootstrap.min.js") ||
                path.equals("/controllerLogin") ||
                path.equals("/error.jsp") ||
                path.equals("/controllerRegistration")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String contextPath = ((HttpServletRequest) servletRequest).getContextPath();
            ((HttpServletResponse) servletResponse).sendRedirect(contextPath + "/error.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
