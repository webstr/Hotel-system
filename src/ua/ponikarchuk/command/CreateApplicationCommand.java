package ua.ponikarchuk.command;

import ua.ponikarchuk.controller.IRequestWrapper;
import ua.ponikarchuk.dao.ApplicationDao;
import ua.ponikarchuk.dao.DAOFactory;
import ua.ponikarchuk.dao.UserDao;
import ua.ponikarchuk.model.Application;
import ua.ponikarchuk.model.User;

import javax.servlet.http.HttpSession;

public class CreateApplicationCommand implements Command {
    @Override
    public String execute(IRequestWrapper wrapper) {
        int size = Integer.parseInt(wrapper.getParameter("size"));
        String type = wrapper.getParameter("type");
        int duration = Integer.parseInt(wrapper.getParameter("duration"));

        HttpSession session = wrapper.getSession(true);
        DAOFactory factory = DAOFactory.getInstance();
        UserDao userDao = factory.getUserDAO();

        Boolean isLogin = (Boolean) session.getAttribute("login");
        if (isLogin != null && !isLogin) {
            return "/";
        }

        User user = userDao.getUserByLogin((String) session.getAttribute("user"));

        ApplicationDao applicationDao = factory.getApplicationDAO();
        if (user != null) {
            Application application = new Application();
            application.setSize(size);
            application.setType(type);
            application.setDuration(duration);
            application.setStatus(Application.Status.OPEN);
            application.setIdBill(null);
            application.setIdUser(user.getId());
            application.setIdRoom(null);
            applicationDao.saveApplication(application);

            return "/succesCreatedApplication.jsp";
        } else {
            session.setAttribute("incorrect", true);
            return "/createApplication.jsp";
        }
    }
}
