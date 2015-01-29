package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.model.enums.AccessLevel;
import by.bsu.ino.carrent.logic.LoginLogic;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    public static final Logger LOGGER = Logger.getLogger(LoginCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request) throws PersistException {
        HttpSession session = request.getSession(true);
        String page;
        Customer customer;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            if ((customer = LoginLogic.checkLogin(login, password)) != null) {
                LOGGER.info("Add to session customer.");
                session.setAttribute("customer", customer);
                page = checkAccessLevel(customer.getLevel(), request);
            } else {
                request.setAttribute("error", ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
            }
        } catch (LogicException e) {
            LOGGER.error("Something goes wrong, redirect to error page.", e);
            request.setAttribute("errorMessage", ConfigurationManager.getInstance().getProperty
                    (ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }

    private String checkAccessLevel(AccessLevel access, HttpServletRequest request) {
        if (access != null) {
            switch (access) {
                case ADMIN:
                    return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
                case CUSTOMER:
                    return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CUSTOMER_PAGE_PATH);
                default:
                    request.setAttribute("errorMessage", ConfigurationManager.getInstance().getProperty
                            (ConfigurationManager.ACCOUNT_DOESNT_EXIST));
                    return ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
            }
        }
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
    }
}
