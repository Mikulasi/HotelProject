package by.bsu.ino.carrent.controller;

import by.bsu.ino.carrent.command.Command;
import by.bsu.ino.carrent.command.NoCommand;
import by.bsu.ino.carrent.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    public Command defineCommand(HttpServletRequest request) {
        Command current = new NoCommand();

        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + ConfigurationManager.getInstance().getProperty(ConfigurationManager.WRONG_ACTION_MESSAGE));
        }
        return current;
    }
}
