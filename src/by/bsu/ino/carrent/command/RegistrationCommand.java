package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.logic.RegistrationLogic;
import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.model.enums.AccessLevel;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class RegistrationCommand implements Command {

    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class.getName());

    private static final String PARAM_ACTION_MESSAGE = "actionMessage";
    private static final String PARAM_ERROR_MESSAGE = "errorMessage";


    @Override
    public String execute(HttpServletRequest request) {
        String page;
        Customer customer = new Customer();
        String password = request.getParameter("password");
        String passwordAgain = request.getParameter("password_again");
        if (!password.equals(passwordAgain)){
            request.setAttribute("error","1");
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
            return page;
        }
        customer.setLogin(request.getParameter("login"));
        customer.setPassword(request.getParameter("password"));
        customer.setFirstName(request.getParameter("first_name"));
        customer.setLastName(request.getParameter("last_name"));
        customer.setEmail(request.getParameter("email"));
        customer.setAddressStreet(request.getParameter("address_street"));
        customer.setAddressCity(request.getParameter("address_city"));
        customer.setAddressCountry(request.getParameter("address_country"));
        customer.setDob(Date.valueOf(request.getParameter("DOB")));
        customer.setMobilephone(request.getParameter("phone"));
        customer.setLevel(AccessLevel.CUSTOMER);

        try {
            RegistrationLogic.registration(customer, password, passwordAgain);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
            request.setAttribute(PARAM_ACTION_MESSAGE,ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.REGISTRATION_WAS_SUCCESSFUL_MESSAGE));

        } catch (LogicException e) {
            LOG.error("Something goes wrong, redirect to error page.", e);
            request.setAttribute(PARAM_ERROR_MESSAGE,ConfigurationManager.getInstance().getProperty(
                            ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
