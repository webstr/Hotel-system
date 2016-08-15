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
 * ChooseRoomCommand is the pattern Command.
 * This Command set to application selected room.
 */
public class ChooseRoomCommand implements Command {
    @Override
    public String execute(IRequestWrapper wrapper) {
        HttpSession session = wrapper.getSession(true);
        String userLogin = (String) session.getAttribute("user");
        int idApplication = Integer.parseInt(wrapper.getParameter("id_application"));
        int idRoom = Integer.parseInt(wrapper.getParameter("id_room"));

        DAOFactory factory = DAOFactory.getInstance();
        UserDao userDao = factory.getUserDAO();
        User user = userDao.getUserByLogin(userLogin);

        ApplicationDao applicationDao = factory.getApplicationDAO();
        Application application = applicationDao.getApplicationsById(idApplication);

        applicationDao.updateIdRoom(application, idRoom);
        applicationDao.updateStatus(application);

        return "/";
    }
}
