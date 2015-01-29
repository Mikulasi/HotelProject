package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.logic.ReceiveBillLogic;
import by.bsu.ino.carrent.model.Bill;
import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ClientBillCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ClientBillCommand.class.getName());

    private static final String CUSTOMER_BILL = "customerBill";
    private static final String CUSTOMER = "customer";
    private static final String ERROR_MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        ArrayList<Bill> customerBill;
        Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER);
        try {
            customerBill = ReceiveBillLogic.findClientBill(customer.getId());
            if (customerBill.size() != 0) {
                request.setAttribute(CUSTOMER_BILL, customerBill);
            } else {
                request.setAttribute(ERROR_MESSAGE,ConfigurationManager.getInstance().getProperty(
                        ConfigurationManager.DOES_NOT_HAVE_BILL_MESSAGE));
            }
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.BILL_LIST_PATH);
        } catch (LogicException e) {
            LOG.error("Error, redirect to error page.", e);
            request.setAttribute(ERROR_MESSAGE,ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
