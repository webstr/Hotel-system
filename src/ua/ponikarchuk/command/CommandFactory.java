package ua.ponikarchuk.command;


import ua.ponikarchuk.controller.IRequestWrapper;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final static CommandFactory instance = new CommandFactory();

    Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("createApplication", new CreateApplicationCommand());
        commands.put("createRoom", new CreateRoomCommand());
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public Command getCommand(IRequestWrapper wrapper) {
        String command = wrapper.getParameter("action");
        return commands.get(command);
    }

}
