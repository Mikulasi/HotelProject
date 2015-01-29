package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {

    public static final Logger LOGGER = Logger.getLogger(LogOutCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        LOGGER.info("Invalidate session.");
        session.invalidate();
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
    }
}
