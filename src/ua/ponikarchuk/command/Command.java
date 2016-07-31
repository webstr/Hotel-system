package ua.ponikarchuk.command;


import ua.ponikarchuk.controller.IRequestWrapper;

public interface Command {
    String execute(IRequestWrapper wrapper);
}
