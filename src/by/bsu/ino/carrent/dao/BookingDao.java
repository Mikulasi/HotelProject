package by.bsu.ino.carrent.dao;

import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.model.enums.BookingState;
import by.bsu.ino.carrent.model.Booking;

import java.util.List;

public interface BookingDao {

    public Booking create() throws PersistException, DaoException;

    public Booking read(Integer key) throws PersistException, DaoException;

    public void update(Booking booking) throws PersistException, DaoException;

    public void delete(Booking booking) throws PersistException, DaoException;

    public List<Booking> getAll() throws PersistException, DaoException;

    public List<Booking> receiveCustomerOrder(Integer idCustomer) throws DaoException;

    public void changeBookingState(Integer idBooking, BookingState state) throws DaoException;

    public void createOrder(Booking order) throws DaoException;
}
