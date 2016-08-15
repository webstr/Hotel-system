package ua.ponikarchuk.controller;

import ua.ponikarchuk.command.Command;
import ua.ponikarchuk.command.CommandFactory;
import ua.ponikarchuk.command.CommandFactoryApplication;
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

/**
 * Controller that give wrapper in command, and take back path. Only for GET request.
 */
public class ControllerApplication extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IRequestWrapper wrapper = new RequestWrapper(request);
        CommandFactoryApplication factory = CommandFactoryApplication.getInstance();
        Command command = factory.getCommand(wrapper);
        String viewPath = command.execute(wrapper);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
