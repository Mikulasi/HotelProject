package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.logic.FindOrderLogic;
import by.bsu.ino.carrent.logic.ChangeOrderStatus;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Booking;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ChangeBookingStatusCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ChangeBookingStatusCommand.class.getName());

    private static final String BOOKING_LIST = "bookingList";
    private static final String ID_BOOKING = "idBooking";
    private static final String BOOKING_STATE = "bookingState";
    private static final String ERROR_MESSAGE = "errorMessage";
    public static final String ACTION_MESSAGE = "actionMessage";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String idBooking = request.getParameter(ID_BOOKING);
        String bookingStatus = request.getParameter(BOOKING_STATE);

        try {
            ChangeOrderStatus.changeStatusOrder(Integer.valueOf(idBooking), bookingStatus);
            page = refreshWithChanges(request);
            request.setAttribute(ACTION_MESSAGE,ConfigurationManager.getInstance().getProperty(
                            ConfigurationManager.CHANGE_STATUS_SUCCESS_MESSAGE));
        } catch (LogicException e) {
            LOG.error("Something goes wrong, redirect to error page.", e);
            request.setAttribute(ERROR_MESSAGE,ConfigurationManager.getInstance().getProperty(
                            ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }

    private String refreshWithChanges(HttpServletRequest request) {
        ArrayList<Booking> bookingList;
        try {
            bookingList = FindOrderLogic.getAllOrder();
            request.setAttribute(BOOKING_LIST, bookingList);
        } catch (LogicException e) {
            LOG.error("Something goes wrong, redirect to error page.", e);
            request.setAttribute(ERROR_MESSAGE,ConfigurationManager.getInstance().getProperty(
                            ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.ERROR_PAGE_PATH);
        }
        return ConfigurationManager.getInstance().getProperty(
                ConfigurationManager.ORDER_LIST_PATH);
    }

}
