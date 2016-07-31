package ua.ponikarchuk.controller;

import ua.ponikarchuk.dao.ApplicationDao;
import ua.ponikarchuk.dao.DAOFactory;
import ua.ponikarchuk.dao.RoomDao;
import ua.ponikarchuk.dao.UserDao;
import ua.ponikarchuk.model.Application;
import ua.ponikarchuk.model.Room;
import ua.ponikarchuk.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ControllerApplication extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getServletPath().equals("/viewApplications")) {
            String userLogin = (String) request.getSession().getAttribute("user");
            DAOFactory factory = DAOFactory.getInstance();
            UserDao userDao = factory.getUserDAO();
            User user = userDao.getUserByLogin(userLogin);

            ApplicationDao applicationDao = factory.getApplicationDAO();
            List<Application> applications = applicationDao.getApplicationsByUser(user);

            request.getSession().setAttribute("applications", applications);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewApplications.jsp");
            dispatcher.forward(request, response);
        } else if (request.getServletPath().equals("/viewAllApplications")) {
            String userLogin = (String) request.getSession().getAttribute("user");
            DAOFactory factory = DAOFactory.getInstance();
            UserDao userDao = factory.getUserDAO();
            User user = userDao.getUserByLogin(userLogin);

            if (user != null && user.getIdRole() == 2) {
                ApplicationDao applicationDao = factory.getApplicationDAO();
                List<Application> applications = applicationDao.getAll();

                request.getSession().setAttribute("applications", applications);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/viewApplications.jsp");
                dispatcher.forward(request, response);
            }
        } else if (request.getServletPath().equals("/checkApplication")) {
            String userLogin = (String) request.getSession().getAttribute("user");
            int idApplication = Integer.parseInt(request.getParameter("id_application"));
            DAOFactory factory = DAOFactory.getInstance();
            UserDao userDao = factory.getUserDAO();
            User user = userDao.getUserByLogin(userLogin);

            ApplicationDao applicationDao = factory.getApplicationDAO();
            Application application = applicationDao.getApplicationsById(idApplication);

            if (user != null && user.getIdRole() == 2) {
                RoomDao roomDao = factory.getRoomDAO();
                List<Room> rooms = roomDao.getAll();

                request.getSession().setAttribute("application", application);
                request.getSession().setAttribute("rooms", rooms);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/checkApplication.jsp");
                dispatcher.forward(request, response);
            }
        } else if (request.getServletPath().equals("/chooseRoom")) {
            String userLogin = (String) request.getSession().getAttribute("user");
            int idApplication = Integer.parseInt(request.getParameter("id_application"));
            int idRoom = Integer.parseInt(request.getParameter("id_room"));
            DAOFactory factory = DAOFactory.getInstance();
            UserDao userDao = factory.getUserDAO();
            User user = userDao.getUserByLogin(userLogin);

            ApplicationDao applicationDao = factory.getApplicationDAO();
            Application application = applicationDao.getApplicationsById(idApplication);

            applicationDao.updateIdRoom(application, idRoom);
            applicationDao.updateStatus(application);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/");
            dispatcher.forward(request, response);

        } else if (request.getServletPath().equals("/payApplication")) {
            String userLogin = (String) request.getSession().getAttribute("user");
            int idApplication = Integer.parseInt(request.getParameter("id_application"));
            DAOFactory factory = DAOFactory.getInstance();
            UserDao userDao = factory.getUserDAO();
            User user = userDao.getUserByLogin(userLogin);

            ApplicationDao applicationDao = factory.getApplicationDAO();
            Application application = applicationDao.getApplicationsById(idApplication);

            applicationDao.updateStatus(application);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
