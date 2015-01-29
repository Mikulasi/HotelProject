package by.bsu.ino.carrent.dao.mysql;

import by.bsu.ino.carrent.model.enums.BillState;
import by.bsu.ino.carrent.dao.AbstractJDBCDao;
import by.bsu.ino.carrent.dao.BillDao;
import by.bsu.ino.carrent.dao.BookingDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Bill;

import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MySqlBillDao extends AbstractJDBCDao implements BillDao {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    public static final String CREATE = "INSERT INTO Bill ( idBill, idBooking, bill_date, bill_cost, bill_state ) VALUES ( ?, ?, ?, ?, ? )";
    public static final String READ = "SELECT * FROM Bill WHERE idBill = ?";
    public static final String UPDATE = "UPDATE Bill SET idBill = ?, idBooking = ?, bill_date = ?, bill_cost = ?, bill_state = ? WHERE idBill = ?";
    public static final String DELETE = "DELETE FROM Bill WHERE idBill = ?";
    public static final String GET_ALL = "SELECT * FROM Bill";
    public static final String FIND_USER_BILL = "SELECT * FROM Bill INNER JOIN Booking ON Bill.idBooking" +
            " = Booking.idBooking WHERE idCustomer = ?";
    private static final String CHANGE_BILL_STATUS_BY_ID = "UPDATE Bill SET bill_state = ? WHERE idBill = ? ";

    @Override
    public Bill create() throws PersistException, DaoException {

        Bill bill = new Bill();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setInt(1, bill.getBooking().getId());
            preparedStatement.setString(2, dateFormat.format(bill.getBillDate()));
            preparedStatement.setBigDecimal(3, bill.getCost());
            preparedStatement.setString(4, String.valueOf(bill.getState()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return bill;
    }

    @Override
    public Bill read(Integer key) throws PersistException, DaoException {
        Bill bill = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                bill = new Bill();
                BookingDao bookingDao = new MySqlBookingDao();
                bill.setId(resultSet.getInt("idBill"));
                bill.setBooking(bookingDao.read(resultSet.getInt("idBooking")));
                bill.setBillDate(Date.valueOf(dateFormat.format(resultSet.getDate("bill_date"))));
                bill.setCost(resultSet.getBigDecimal("bill_cost"));
                bill.setState(BillState.valueOf(resultSet.getString("bill_state")));
            }

        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }

        return bill;
    }

    @Override
    public void update(Bill bill) throws PersistException, DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, bill.getBooking().getId());
            preparedStatement.setString(2, dateFormat.format(bill.getBillDate()));
            preparedStatement.setBigDecimal(3, bill.getCost());
            preparedStatement.setString(4, String.valueOf(bill.getState()));
            preparedStatement.setInt(5, bill.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        }finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
    }

    @Override
    public void delete(Bill bill) throws PersistException, DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,bill.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        }finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
    }

    @Override
    public List<Bill> getAll() throws PersistException, DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet;
        ArrayList<Bill> list = new ArrayList<>();
        Bill bill = new Bill();
        BookingDao bookingDao = new MySqlBookingDao();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                bill.setId(resultSet.getInt("idBill"));
                bill.setBooking(bookingDao.read(resultSet.getInt("idBooking")));
                bill.setBillDate(Date.valueOf(dateFormat.format(resultSet.getDate("bill_date"))));
                bill.setCost(resultSet.getBigDecimal("bill_cost"));
                bill.setState(BillState.valueOf(resultSet.getString("bill_state")));
                list.add(bill);
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
    public List<Bill> findUserBills(Integer idCustomer) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        Bill bill = new Bill();
        ArrayList<Bill> list = new ArrayList<>();
        BookingDao bookingDao = new MySqlBookingDao();
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_USER_BILL);
            preparedStatement.setInt(1,idCustomer);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                bill.setId(resultSet.getInt("idBill"));
                bill.setBooking(bookingDao.read(resultSet.getInt("idBooking")));
                bill.setBillDate(Date.valueOf(dateFormat.format(resultSet.getDate("bill_date"))));
                bill.setCost(resultSet.getBigDecimal("bill_cost"));
                bill.setState(BillState.valueOf(resultSet.getString("bill_state")));
                list.add(bill);

            }
        } catch (SQLException | PersistException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        }finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return list;
    }

    @Override
    public Bill findBillById(Integer idBill) throws DaoException {
        Bill bill = new Bill();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet rs;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setInt(1,idBill);
            rs = preparedStatement.executeQuery();
            if (rs.next()){
                BookingDao bookingDao = new MySqlBookingDao();
                bill.setId(rs.getInt("idBill"));
                bill.setBooking(bookingDao.read(rs.getInt("idBooking")));
                bill.setBillDate(Date.valueOf(dateFormat.format(rs.getDate("bill_date"))));
                bill.setCost(rs.getBigDecimal("bill_cost"));
                bill.setState(BillState.valueOf(rs.getString("bill_state")));
            }
        } catch (SQLException | PersistException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        }finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return bill;
    }

    @Override
    public void changeBillStatusById(Integer idBill, BillState state) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(CHANGE_BILL_STATUS_BY_ID);
            preparedStatement.setString(1, String.valueOf(state));
            preparedStatement.setInt(2, idBill);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
    }

}
