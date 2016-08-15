package ua.ponikarchuk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * RequsetWrapper is implementation of IRequestWrapper that helps to work request without using it.
 */
public class RequestWrapper implements IRequestWrapper {
    private HttpServletRequest request;

    public RequestWrapper(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String getParameter(String key) {
        return request.getParameter(key);
    }

    @Override
    public String getServletPath() {
        return request.getServletPath();
    }

    @Override
    public HttpSession getSession(Boolean flag) {
        return request.getSession(flag);
    }

}
