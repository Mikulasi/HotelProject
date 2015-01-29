package by.bsu.ino.carrent.dao.mysql;

import by.bsu.ino.carrent.dao.CarDao;
import by.bsu.ino.carrent.dao.CustomerDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.model.enums.BookingState;
import by.bsu.ino.carrent.dao.AbstractJDBCDao;
import by.bsu.ino.carrent.dao.BookingDao;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MySqlBookingDao extends AbstractJDBCDao implements BookingDao {

    private static final String DATE_FORMAT = "yyyy-mm-dd";
    DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final String CREATE = "INSERT INTO Booking (idBooking, idCustomer,idVehicle, date_booking_made, " +
            "first_date, last_date, total_cost, comments, booking_state) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String READ = "SELECT * FROM Booking WHERE idBooking = ?";
    private static final String UPDATE = "UPDATE Booking SET idBooking = ?, idCustomer = ?, idVehicle = ?, " +
            "date_booking_made = ?, first_date = ?, last_date = ?, total_cost = ?, comments = ?, " +
            "booking_state = ? WHERE idBooking = ?";
    private static final String DELETE = "DELETE FROM Booking WHERE idBooking = ?";
    private static final String GET_ALL = "SELECT * FROM Booking";
    private static final String GET_CUSTOMER_ORDER = "SELECT * FROM Booking WHERE idCustomer = ?";
    private static final String UPDATE_BOOKING_STATE = "UPDATE Booking SET booking_state = ? WHERE idBooking = ?";

    @Override
    public Booking create() throws PersistException, DaoException {

        Booking booking = new Booking();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setInt(1, booking.getCar().getId());
            preparedStatement.setString(2, dateFormat.format(booking.getDateBookingMade()));
            preparedStatement.setString(3, dateFormat.format(booking.getFirstDate()));
            preparedStatement.setString(4, dateFormat.format(booking.getLastDate()));
            preparedStatement.setBigDecimal(5, booking.getTotalCost());
            preparedStatement.setString(6, booking.getComments());
            preparedStatement.setInt(7, booking.getCustomer().getId());
            preparedStatement.setString(8, booking.getState().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return booking;
    }

    @Override
    public Booking read(Integer key) throws PersistException, DaoException {
        Booking booking = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                booking = new Booking();
                CarDao carDao = new MySqlCarDao();
                CustomerDao customerDao = new MySqlCustomerDao();
                booking.setId(resultSet.getInt("idBooking"));
                booking.setCustomer(customerDao.read(resultSet.getInt("idCustomer")));
                booking.setCar(carDao.read(resultSet.getInt("idVehicle")));
                booking.setDateBookingMade(resultSet.getDate("date_booking_made"));
                booking.setFirstDate(resultSet.getDate("first_date"));
                booking.setLastDate(resultSet.getDate("last_date"));
                booking.setTotalCost(resultSet.getBigDecimal("total_cost"));
                booking.setComments(resultSet.getString("comments"));
                booking.setState(BookingState.valueOf(resultSet.getString("booking_state")));
            }

        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }

        return booking;
    }

    @Override
    public void update(Booking booking) throws PersistException, DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, booking.getCustomer().getId());
            preparedStatement.setInt(2, booking.getCar().getId());
            preparedStatement.setString(3, dateFormat.format(booking.getDateBookingMade()));
            preparedStatement.setString(4, dateFormat.format(booking.getFirstDate()));
            preparedStatement.setString(5, dateFormat.format(booking.getLastDate()));
            preparedStatement.setBigDecimal(6, booking.getTotalCost());
            preparedStatement.setString(7, booking.getComments());
            preparedStatement.setString(8, booking.getState().toString());
            preparedStatement.setInt(9, booking.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        }finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
    }

    @Override
    public void delete(Booking booking) throws PersistException, DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,booking.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        }finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
    }

    @Override
    public List<Booking> getAll() throws PersistException, DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet;
        ArrayList<Booking> list = new ArrayList<>();
        Booking booking = new Booking();
        CarDao carDao = new MySqlCarDao();
        CustomerDao customerDao = new MySqlCustomerDao();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                booking.setId(resultSet.getInt("idBooking"));
                booking.setCustomer(customerDao.read(resultSet.getInt("idCustomer")));
                booking.setCar(carDao.read(resultSet.getInt("idVehicle")));
                booking.setDateBookingMade(resultSet.getDate("date_booking_made"));
                booking.setFirstDate(resultSet.getDate("first_date"));
                booking.setLastDate(resultSet.getDate("last_date"));
                booking.setTotalCost(resultSet.getBigDecimal("total_cost"));
                booking.setComments(resultSet.getString("comments"));
                booking.setState(BookingState.valueOf(resultSet.getString("booking_state")));
                list.add(booking);
            }
        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        }finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return list;
    }

    @Override
    public List<Booking> receiveCustomerOrder(Integer idCustomer) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        CustomerDao customerDao = new MySqlCustomerDao();
        CarDao carDao = new MySqlCarDao();
        ArrayList<Booking> bookingList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_CUSTOMER_ORDER);
            preparedStatement.setInt(1, idCustomer);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setId(resultSet.getInt("idBooking"));
                booking.setCustomer(customerDao.read(resultSet.getInt("idCustomer")));
                booking.setCar(carDao.read(resultSet.getInt("idVehicle")));
                booking.setComments(resultSet.getString("comments"));
                booking.setDateBookingMade(resultSet.getDate("date_booking_made"));
                booking.setFirstDate(resultSet.getDate("first_date"));
                booking.setLastDate(resultSet.getDate("last_date"));
                booking.setTotalCost(resultSet.getBigDecimal("total_cost"));
                booking.setState(BookingState.valueOf(resultSet.getString("booking_state")));
                bookingList.add(booking);
            }
        } catch (SQLException | PersistException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }

        return bookingList;
    }

    @Override
    public void changeBookingState(Integer idBooking, BookingState state) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_BOOKING_STATE);
            preparedStatement.setString(1, state.toString());
            preparedStatement.setInt(2, idBooking);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
    }

    @Override
    public void createOrder(Booking order) throws DaoException {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setInt(1, order.getCar().getId());
            preparedStatement.setString(2, dateFormat.format(order.getDateBookingMade()));
            preparedStatement.setString(3, dateFormat.format(order.getFirstDate()));
            preparedStatement.setString(4, dateFormat.format(order.getLastDate()));
            preparedStatement.setBigDecimal(5, order.getTotalCost());
            preparedStatement.setString(6, order.getComments());
            preparedStatement.setInt(7, order.getCustomer().getId());
            preparedStatement.setString(8, order.getState().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }

    }
}
