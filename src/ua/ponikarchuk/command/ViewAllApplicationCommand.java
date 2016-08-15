package ua.ponikarchuk.command;

import ua.ponikarchuk.controller.IRequestWrapper;
import ua.ponikarchuk.dao.ApplicationDao;
import ua.ponikarchuk.dao.DAOFactory;
import ua.ponikarchuk.dao.UserDao;
import ua.ponikarchuk.model.Application;
import ua.ponikarchuk.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ViewAllApplicationCommand is the pattern Command.
 * This Command create a list of all applications, only for admin.
 */
public class ViewAllApplicationCommand implements Command {
    @Override
    public String execute(IRequestWrapper wrapper) {
        HttpSession session = wrapper.getSession(true);
        String userLogin = (String) session.getAttribute("user");

        DAOFactory factory = DAOFactory.getInstance();
        UserDao userDao = factory.getUserDAO();
        User user = userDao.getUserByLogin(userLogin);

        if (user != null && user.getIdRole() == 2) {
            ApplicationDao applicationDao = factory.getApplicationDAO();
            List<Application> applications = applicationDao.getAll();

            session.setAttribute("applications", applications);
            return "/viewApplications.jsp";
        }
        return "/";
    }
}
