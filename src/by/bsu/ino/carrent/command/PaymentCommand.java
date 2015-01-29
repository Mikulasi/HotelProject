package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.logic.PayBillLogic;
import by.bsu.ino.carrent.logic.ReceiveBillLogic;
import by.bsu.ino.carrent.model.Bill;
import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class PaymentCommand implements Command {

    public static final Logger LOG = Logger.getLogger(PaymentCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request) {

        String page;
        String idBill = request.getParameter("idBill");
        try {
            PayBillLogic.payBill(idBill);
            page = refreshWithChanges(request);
            request.setAttribute("actionMessage", ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.PAYMENT_SUCCESS_MESSAGE));
        } catch (LogicException e) {
            LOG.error("Something goes wrong, redirect to error page.", e);
            request.setAttribute("errorMessage", ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;

    }

    private String refreshWithChanges(HttpServletRequest request) {
        ArrayList<Bill> customerBill;
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        try {
            customerBill = ReceiveBillLogic.findClientBill(customer.getId());
            request.setAttribute("customerBill", customerBill);
        } catch (LogicException e) {
            LOG.error("Something goes wrong, redirect to error page.", e);
            request.setAttribute("errorMessage", ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.ERROR_PAGE_PATH);
        }
        return ConfigurationManager.getInstance().getProperty(
                ConfigurationManager.BILL_LIST_PATH);
    }
}
