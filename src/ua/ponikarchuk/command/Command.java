package ua.ponikarchuk.command;

import ua.ponikarchuk.controller.IRequestWrapper;

/**
 * This interface is used to create different type of commands, that work with controllers
 */
public interface Command {
    String execute(IRequestWrapper wrapper);
}
