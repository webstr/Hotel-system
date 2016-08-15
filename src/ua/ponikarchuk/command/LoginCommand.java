package ua.ponikarchuk.command;

import ua.ponikarchuk.controller.IRequestWrapper;
import ua.ponikarchuk.dao.DAOFactory;
import ua.ponikarchuk.dao.UserDao;
import ua.ponikarchuk.model.User;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * LoginCommand is the pattern Command.
 * This Command check login and password, and then push user into session. if it valid.
 */
public class LoginCommand implements Command {
    @Override
    public String execute(IRequestWrapper wrapper) {
        HttpSession session = wrapper.getSession(true);
        DAOFactory factory = DAOFactory.getInstance();
        UserDao userDao = factory.getUserDAO();

        Boolean isLogin = (Boolean) session.getAttribute("login");
        if (isLogin != null && isLogin) {
            return "/";
        }

        String login = wrapper.getParameter("login");
        String password = wrapper.getParameter("password");

        User user = userDao.getUserByLogin(login);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("login", true);
            session.setAttribute("user", user.getName());
            session.setAttribute("user_role", user.getIdRole());
            return "/";
        } else {
            session.setAttribute("incorrectLogin", true);
            return "/login.jsp";
        }
    }
}
