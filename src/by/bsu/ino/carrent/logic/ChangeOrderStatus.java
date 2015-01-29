package by.bsu.ino.carrent.logic;

import by.bsu.ino.carrent.dao.BookingDao;
import by.bsu.ino.carrent.dao.mysql.MySqlBookingDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.model.enums.BookingState;
import by.bsu.ino.carrent.manager.ConfigurationManager;

public class ChangeOrderStatus {

    public static final String PARAM_STATUS_EMPTY = "empty";

    public static void changeStatusOrder(Integer idBooking, String bookingStatus) throws LogicException {
        BookingDao bookingDao = new MySqlBookingDao();
        if (idBooking != null) {
            if (PARAM_STATUS_EMPTY.equals(bookingStatus)) {
                throw new LogicException(ConfigurationManager.getInstance().getProperty(
                        ConfigurationManager.CHOOSE_ACTION_ERROR_MESSAGE));
            }
            try {
                bookingDao.changeBookingState(idBooking, BookingState.valueOf(bookingStatus));
                if (BookingState.valueOf(bookingStatus).equals(BookingState.CONFIRMED)) {
                    BillLogic.createBill(idBooking);
                }
            } catch (DaoException e) {
                throw new LogicException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE), e);
            }
        } else {
            throw new LogicException(ConfigurationManager.getInstance().getProperty(
                                    ConfigurationManager.CHANGE_STATUS_ORDER_EXCEPTION_MESSAGE));
        }
    }
}
