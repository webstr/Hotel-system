package ua.ponikarchuk.command;

import ua.ponikarchuk.controller.IRequestWrapper;
import ua.ponikarchuk.dao.ApplicationDao;
import ua.ponikarchuk.dao.DAOFactory;
import ua.ponikarchuk.dao.UserDao;
import ua.ponikarchuk.model.Application;
import ua.ponikarchuk.model.User;

import javax.servlet.http.HttpSession;

/**
 * PayApplicationCommand is the pattern Command.
 * This Command update the status of application, in fact pay money for access to room.
 */
public class PayApplicationCommand implements Command {
    @Override
    public String execute(IRequestWrapper wrapper) {
        HttpSession session = wrapper.getSession(true);
        String userLogin = (String) session.getAttribute("user");
        int idApplication = Integer.parseInt(wrapper.getParameter("id_application"));
        DAOFactory factory = DAOFactory.getInstance();
        UserDao userDao = factory.getUserDAO();
        User user = userDao.getUserByLogin(userLogin);

        ApplicationDao applicationDao = factory.getApplicationDAO();
        Application application = applicationDao.getApplicationsById(idApplication);

        applicationDao.updateStatus(application);

        return "/";
    }
}
