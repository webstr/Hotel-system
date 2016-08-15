package ua.ponikarchuk.controller;

import ua.ponikarchuk.command.Command;
import ua.ponikarchuk.command.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller that give wrapper in command, and take back path. Only for POST request.
 */
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IRequestWrapper wrapper = new RequestWrapper(request);
        CommandFactory factory = CommandFactory.getInstance();
        Command command = factory.getCommand(wrapper);
        String viewPath = command.execute(wrapper);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
