package ua.ponikarchuk.controller;

import javax.servlet.http.HttpSession;

/**
 * Interface IRequsetWrapper for working with request without access to it.
 */
public interface IRequestWrapper {
    String getParameter(String key);

    String getServletPath();

    HttpSession getSession(Boolean flag);
}
