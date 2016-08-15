package ua.ponikarchuk.command;

import ua.ponikarchuk.controller.IRequestWrapper;
import ua.ponikarchuk.dao.ApplicationDao;
import ua.ponikarchuk.dao.DAOFactory;
import ua.ponikarchuk.dao.UserDao;
import ua.ponikarchuk.model.Application;
import ua.ponikarchuk.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ViewApplicationCommand is the pattern Command.
 * This Command create a list of applications for current user.
 */
public class ViewApplicationCommand implements Command {
    @Override
    public String execute(IRequestWrapper wrapper) {
        HttpSession session = wrapper.getSession(true);
        String userLogin = (String) session.getAttribute("user");

        DAOFactory factory = DAOFactory.getInstance();
        UserDao userDao = factory.getUserDAO();
        User user = userDao.getUserByLogin(userLogin);

        ApplicationDao applicationDao = factory.getApplicationDAO();
        List<Application> applications = applicationDao.getApplicationsByUser(user);

        session.setAttribute("applications", applications);
        return "/viewApplications.jsp";
    }
}
