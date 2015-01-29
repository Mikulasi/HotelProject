package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {

    private static final Logger LOG = Logger.getLogger(LanguageCommand.class.getName());

    private static final String PARAM_LANG = "lang";

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        String lang = request.getParameter(PARAM_LANG);
        LOG.info("Add to session language parameter.");
        session.setAttribute(PARAM_LANG, lang);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);

    }
}
