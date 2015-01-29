package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.logic.ChangeLevelLogic;
import by.bsu.ino.carrent.logic.GetCustomerLogic;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ChangeLevelCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ChangeLevelCommand.class.getName());
    private static final String CUSTOMER_LIST = "customerList";
    private static final String ID_CUSTOMER = "idCustomer";
    private static final String ACCESS_LEVEL = "level";
    private static String ERROR_MESSAGE = "errorMessage";
    private static final String ACTION_MESSAGE = "actionMessage";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String idCustomer = request.getParameter(ID_CUSTOMER);
        String level = request.getParameter(ACCESS_LEVEL);
        try {
            ChangeLevelLogic.changeLevel(idCustomer, level);
            page = refreshWithChanges(request);
            request.setAttribute(ACTION_MESSAGE, ConfigurationManager.getInstance().getProperty(
                            ConfigurationManager.CHANGE_STATUS_SUCCESS_MESSAGE));
        } catch (LogicException e) {
            LOG.error("Something goes wrong, redirect to error page.", e);
            request.setAttribute(ERROR_MESSAGE, ConfigurationManager.getInstance().getProperty(
                            ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }

    private String refreshWithChanges(HttpServletRequest request) {
        ArrayList<Customer> customerList;
        try {
            customerList = GetCustomerLogic.getAllCustomer();
            request.setAttribute(CUSTOMER_LIST, customerList);
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.CUSTOMER_ADMINISTRATION_PATH);
        } catch (LogicException e) {
            LOG.error("Something goes wrong, redirect to error page.", e);
            request.setAttribute(ERROR_MESSAGE, ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
    }
}
