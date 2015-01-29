package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.logic.GetCustomerLogic;
import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class UserManageCommand implements Command {

    public static final Logger LOG = Logger.getLogger(UserManageCommand.class.getName());

    private static final String PARAM_CUSTOMER_LIST = "customerList";
    private static final String PARAM_ERROR_MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            ArrayList<Customer> customerList = GetCustomerLogic.getAllCustomer();
            request.setAttribute(PARAM_CUSTOMER_LIST, customerList);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.CUSTOMER_ADMINISTRATION_PATH);
        } catch (LogicException e) {
            LOG.error("Something goes wrong, redirect to error page.", e);
            request.setAttribute(PARAM_ERROR_MESSAGE, ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
