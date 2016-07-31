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

public class CreateRoomCommand implements Command {
    @Override
    public String execute(IRequestWrapper wrapper) {
        int roomNumber = Integer.parseInt(wrapper.getParameter("room_number"));
        int floor = Integer.parseInt(wrapper.getParameter("floor"));
        int status = Integer.parseInt(wrapper.getParameter("status"));
        String type = wrapper.getParameter("type");
        int size = Integer.parseInt(wrapper.getParameter("size"));

        HttpSession session = wrapper.getSession(true);
        DAOFactory factory = DAOFactory.getInstance();
        UserDao userDao = factory.getUserDAO();

        Boolean isLogin = (Boolean) session.getAttribute("login");
        if (isLogin != null && !isLogin) {
            return "/";
        }

        User user = userDao.getUserByLogin((String) session.getAttribute("user"));

        RoomDao roomDao = factory.getRoomDAO();
        if (user != null && user.getIdRole() == 2) {
            Room room = new Room();
            room.setRoomNumber(roomNumber);
            room.setFloor(floor);
            room.setStatus(status);
            room.setType(type);
            room.setSize(size);
            roomDao.saveRoom(room);

            return "/succesCreatedRoom.jsp";
        } else {
            session.setAttribute("incorrect", true);
            return "/createRoom.jsp";
        }
    }
}
