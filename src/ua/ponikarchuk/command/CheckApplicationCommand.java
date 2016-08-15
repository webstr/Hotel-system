package ua.ponikarchuk.command;

import ua.ponikarchuk.controller.IRequestWrapper;
import ua.ponikarchuk.dao.ApplicationDao;
import ua.ponikarchuk.dao.DAOFactory;
import ua.ponikarchuk.dao.RoomDao;
import ua.ponikarchuk.dao.UserDao;
import ua.ponikarchuk.model.Application;
import ua.ponikarchuk.model.Room;
import ua.ponikarchuk.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * CheckApplicationCommand is the pattern Command.
 * This Command gives a list of free rooms, that can be used to servise the application.
 */
public class CheckApplicationCommand implements Command {
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

        if (user != null && user.getIdRole() == 2) {
            RoomDao roomDao = factory.getRoomDAO();
            List<Room> rooms = roomDao.getAll();

            session.setAttribute("application", application);
            session.setAttribute("rooms", rooms);
            return "/checkApplication.jsp";
        }
        return "/";
    }
}
