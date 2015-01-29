package by.bsu.ino.carrent.logic;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.dao.BookingDao;
import by.bsu.ino.carrent.dao.mysql.MySqlBookingDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Booking;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindOrderLogic {

    public static ArrayList<Booking> getAllOrder() throws LogicException {
        BookingDao bookingDao = new MySqlBookingDao();
        try {
            return (ArrayList<Booking>) bookingDao.getAll();
        } catch (PersistException | DaoException e) {
            throw new LogicException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE), e);
        }
    }

    public static ArrayList<Booking> getCustomerOrder(int idCustomer) throws LogicException {
        BookingDao bookingDao = new MySqlBookingDao();
        ArrayList<Booking> bookingList = new ArrayList<>();
        try {

            bookingList.addAll(bookingDao.receiveCustomerOrder(idCustomer).stream().collect(Collectors.toList()));

        } catch (DaoException e) {
            throw new LogicException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE), e);

        }
        return bookingList;
    }
}
