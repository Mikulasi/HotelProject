package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.exception.PersistException;

import javax.servlet.http.HttpServletRequest;


public interface Command {

    public String execute(HttpServletRequest request) throws LogicException, PersistException;
}
