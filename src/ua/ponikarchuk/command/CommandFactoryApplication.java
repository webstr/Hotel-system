package ua.ponikarchuk.command;

import ua.ponikarchuk.controller.IRequestWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * CommandFactory is the creation pattern that produce Command's.
 * This factory based on servlet path in GET request.
 */
public class CommandFactoryApplication {
    private final static CommandFactoryApplication instance = new CommandFactoryApplication();

    Map<String, Command> commands = new HashMap<>();

    private CommandFactoryApplication() {
        commands.put("/viewApplications", new ViewApplicationCommand());
        commands.put("/viewAllApplications", new ViewAllApplicationCommand());
        commands.put("/checkApplication", new CheckApplicationCommand());
        commands.put("/chooseRoom", new ChooseRoomCommand());
        commands.put("/payApplication", new PayApplicationCommand());
    }

    public static CommandFactoryApplication getInstance() {
        return instance;
    }

    public Command getCommand(IRequestWrapper wrapper) {
        String command = wrapper.getServletPath();
        return commands.get(command);
    }
}
