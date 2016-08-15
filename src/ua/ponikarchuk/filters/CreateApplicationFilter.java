package ua.ponikarchuk.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter check for validate filled of Application form.
 */
public class CreateApplicationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String size = servletRequest.getParameter("size");
        String type = servletRequest.getParameter("type");
        String duration = servletRequest.getParameter("duration");

        if (size == null || "".equals(size) || type == null || "".equals(type) || duration == null || "".equals(duration)) {
            String contextPath = ((HttpServletRequest) servletRequest).getContextPath();
            ((HttpServletResponse) servletResponse).sendRedirect(contextPath + "/error.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
