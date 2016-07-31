package ua.ponikarchuk.command;

import ua.ponikarchuk.controller.IRequestWrapper;
import ua.ponikarchuk.dao.DAOFactory;
import ua.ponikarchuk.dao.UserDao;
import ua.ponikarchuk.model.User;

import javax.servlet.http.HttpSession;

public class RegistrationCommand implements Command {
    @Override
    public String execute(IRequestWrapper wrapper) {
        String name = wrapper.getParameter("name");
        String surname = wrapper.getParameter("surname");
        String login = wrapper.getParameter("login");
        String password = wrapper.getParameter("password");
        HttpSession session = wrapper.getSession(true);

        DAOFactory factory = DAOFactory.getInstance();
        UserDao userDao = factory.getUserDAO();

        User user = userDao.getUserByLogin(login);
        if (user == null) {
            user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);
            user.setIdRole(1);
            userDao.saveUser(user);
            return "/";
        } else {
            session.setAttribute("incorrect", true);
            return "/registration.jsp";
        }
    }
}
