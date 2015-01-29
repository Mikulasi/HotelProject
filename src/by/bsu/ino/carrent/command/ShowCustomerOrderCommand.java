package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.logic.FindOrderLogic;
import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Booking;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ShowCustomerOrderCommand implements Command {

    public static final Logger LOG = Logger.getLogger(ShowCustomerOrderCommand.class.getName());

    public static final String CUSTOMER_ORDER = "customerOrder";
    public static final String CUSTOMER = "customer";
    public static final String ERROR_MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        ArrayList<Booking> customerOrder;
        Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER);
        try {
            customerOrder = FindOrderLogic.getCustomerOrder(customer.getId());
            if (customerOrder.size() != 0) {
                request.setAttribute(CUSTOMER_ORDER, customerOrder);
            } else {
                request.setAttribute(ERROR_MESSAGE, ConfigurationManager.getInstance().getProperty(
                        ConfigurationManager.DOES_NOT_HAVE_FREE_CAR_MESSAGE));
            }
            page = ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.CUSTOMER_ORDER_LIST_PATH);
        } catch (LogicException e) {
            LOG.error("Something goes wrong, redirect to error page.", e);
            request.setAttribute(ERROR_MESSAGE, ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
