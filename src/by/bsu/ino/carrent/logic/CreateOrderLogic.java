package by.bsu.ino.carrent.logic;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.dao.BookingDao;
import by.bsu.ino.carrent.dao.CarDao;
import by.bsu.ino.carrent.dao.mysql.MySqlBookingDao;
import by.bsu.ino.carrent.dao.mysql.MySqlCarDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Booking;
import by.bsu.ino.carrent.model.Customer;

import java.util.Date;

public class CreateOrderLogic {

    public static void createOrder(Booking order, Customer idCustomer, Integer idCar,String firstDate,
                                   String  lastDate) throws LogicException {
        BookingDao orderDao = new MySqlBookingDao();
        CarDao carDao = new MySqlCarDao();
        if (idCar != null && !firstDate.equals("") && !lastDate.equals("")) {
            order.setFirstDate(java.sql.Date.valueOf(firstDate));
            order.setLastDate(java.sql.Date.valueOf(lastDate));
            if (CreateOrderLogic.checkDate(order.getFirstDate(), order.getLastDate())) {
                order.setCustomer(idCustomer);
                try {
                    order.setCar(carDao.read(idCar));
                    orderDao.createOrder(order);
                } catch (DaoException | PersistException e) {
                    throw new LogicException(ConfigurationManager.getInstance().getProperty(
                            ConfigurationManager.WRONG_DATE_EXCEPTION_MESSAGE), e);
                }
            }
        }
    }

	private static boolean checkDate(Date checkInDate, Date checkOutDate) throws LogicException {
        return checkOutDate.getTime() - checkInDate.getTime() > 0;
    }
}
