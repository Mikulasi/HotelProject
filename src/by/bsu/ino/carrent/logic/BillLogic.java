package by.bsu.ino.carrent.logic;

import by.bsu.ino.carrent.dao.BillDao;
import by.bsu.ino.carrent.dao.mysql.MySqlBillDao;
import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.model.enums.BillState;
import by.bsu.ino.carrent.model.enums.CarStatus;
import by.bsu.ino.carrent.dao.BookingDao;
import by.bsu.ino.carrent.dao.CarDao;
import by.bsu.ino.carrent.dao.mysql.MySqlBookingDao;
import by.bsu.ino.carrent.dao.mysql.MySqlCarDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Bill;
import by.bsu.ino.carrent.model.Booking;

import java.math.BigDecimal;
import java.util.Date;

public class BillLogic {

    public static void createBill(Integer idBooking) throws LogicException {
        try {
            BillDao billDAO = new MySqlBillDao();
            BookingDao bookingDao = new MySqlBookingDao();
            Bill bill = new Bill();
            Booking booking = bookingDao.read(Integer.parseInt(String.valueOf(idBooking)));
            bill.setBillDate(new Date());
            bill.setBooking(booking);
            bill.setState(BillState.UNPAID);
            bill.setCost(calculateTotalSum(booking));
            setBusyCar(booking.getCar().getId());
            billDAO.create();
        } catch (PersistException | DaoException e) {
            throw new LogicException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE), e);
        }
    }

    private static void setBusyCar(int id) throws PersistException, DaoException {
        CarDao carDAO = new MySqlCarDao();
        carDAO.changeCarStatus(id, CarStatus.BUSY);
    }

    private static BigDecimal calculateTotalSum(Booking booking) {
        BigDecimal paymentSum = BigDecimal.valueOf((int) ((booking.getLastDate().getTime() -
                booking.getLastDate().getTime()) / (24 * 60 * 60 * 1000)));
        return paymentSum.multiply(booking.getTotalCost());
    }
}
