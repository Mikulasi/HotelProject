package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.logic.FindOrderLogic;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Booking;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AdminBookingCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminBookingCommand.class.getName());

    private static final String BOOKING_LIST = "bookingList";
    private static final String ERROR_MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request)  {
        String page;
        ArrayList<Booking> bookingList;
        try {
            bookingList = FindOrderLogic.getAllOrder();
            request.setAttribute(BOOKING_LIST, bookingList);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ORDER_LIST_PATH);
        } catch (LogicException e) {
            LOG.error("Something goes wrong, redirect to error page.", e);
            request.setAttribute(ERROR_MESSAGE,ConfigurationManager.getInstance().getProperty(
                            ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
