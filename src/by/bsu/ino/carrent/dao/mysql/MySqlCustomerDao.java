package by.bsu.ino.carrent.dao.mysql;

import by.bsu.ino.carrent.dao.AbstractJDBCDao;
import by.bsu.ino.carrent.dao.CustomerDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.model.enums.AccessLevel;

import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MySqlCustomerDao extends AbstractJDBCDao implements CustomerDao {

    public static final String DATE_FORMAT = "dd-MM-yyyy";
    DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final String CREATE = "INSERT INTO Customer (first_name, last_name, DOB, address_street," +
            " address_city, address_country, phone, email, login, password, access_level)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String READ = "SELECT * FROM Customer WHERE idCustomer = ?";
    private static final String UPDATE = "UPDATE Customer SET idCustomer = ?, first_name = ?, last_name = ?, DOB = ?," +
            " address_street = ?, address_city = ?, address_country = ?, phone = ?, email = ?, login = ?," +
            " password = ?, access_level = ? WHERE idCustomer = ?";
    private static final String DELETE = "DELETE FROM Customer WHERE idCustomer = ?";
    private static final String GET_ALL = "SELECT * FROM Customer";
    private static final String GET_USER_BY_LOGIN_PASSWORD = "SELECT * FROM Customer WHERE login = ? AND password = ?";
    private static final String UPDATE_LEVEL = "UPDATE Customer SET access_level = ? WHERE idCustomer = ?";

    @Override
    public Customer create() throws PersistException, DaoException {

        Customer customer = new Customer();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setDate(3, (Date) customer.getDob());
            preparedStatement.setString(4, customer.getAddressStreet());
            preparedStatement.setString(5, customer.getAddressCity());
            preparedStatement.setString(6, customer.getAddressCountry());
            preparedStatement.setString(7, customer.getMobilephone());
            preparedStatement.setString(8, customer.getEmail());
            preparedStatement.setString(9, customer.getLogin());
            preparedStatement.setString(10, customer.getPassword());
            preparedStatement.setString(11, String.valueOf(customer.getLevel()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return customer;
    }

    @Override
    public Customer read(Integer key) throws PersistException, DaoException {
        Customer customer = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getInt("idCustomer"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setDob(resultSet.getDate("DOB"));
                customer.setAddressStreet(resultSet.getString("address_street"));
                customer.setAddressCity(resultSet.getString("address_city"));
                customer.setAddressCountry(resultSet.getString("address_country"));
                customer.setMobilephone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setLogin(resultSet.getString("login"));
                customer.setPassword(resultSet.getString("password"));
                customer.setLevel(AccessLevel.valueOf(resultSet.getString("access_level")));
            }

        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }

        return customer;
    }

    @Override
    public void update(Customer customer) throws PersistException, DaoException {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setDate(3, Date.valueOf(dateFormat.format(customer.getDob())));
            preparedStatement.setString(4, customer.getAddressStreet());
            preparedStatement.setString(5, customer.getAddressCity());
            preparedStatement.setString(6, customer.getAddressCountry());
            preparedStatement.setString(7, customer.getMobilephone());
            preparedStatement.setString(8, customer.getEmail());
            preparedStatement.setString(9, customer.getLogin());
            preparedStatement.setString(10, customer.getPassword());
            preparedStatement.setString(11, String.valueOf(customer.getLevel()));
            preparedStatement.setInt(12, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }

    }

    @Override
    public void delete(Customer customer) throws PersistException, DaoException {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }

    }

    @Override
    public List<Customer> getAll() throws PersistException, DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet;
        ArrayList<Customer> list = new ArrayList<>();
        Customer customer = new Customer();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer.setId(resultSet.getInt("idCustomer"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setDob(resultSet.getDate("DOB"));
                customer.setAddressStreet(resultSet.getString("address_street"));
                customer.setAddressCity(resultSet.getString("address_city"));
                customer.setAddressCountry(resultSet.getString("address_country"));
                customer.setMobilephone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setLogin(resultSet.getString("login"));
                customer.setPassword(resultSet.getString("password"));
                customer.setLevel(AccessLevel.valueOf(resultSet.getString("access_level")));
                list.add(customer);
            }
        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return list;
    }

    @Override
    public Customer findByCredentials(String login, String password) throws DaoException {
        Customer customer = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getInt("idCustomer"));
                customer.setLogin(resultSet.getString("login"));
                customer.setPassword(resultSet.getString("password"));
                customer.setAddressCity(resultSet.getString("address_city"));
                customer.setAddressStreet(resultSet.getString("address_street"));
                customer.setAddressCountry(resultSet.getString("address_country"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setDob(resultSet.getDate("DOB"));
                customer.setEmail(resultSet.getString("email"));
                customer.setMobilephone(resultSet.getString("phone"));
                customer.setLevel(AccessLevel.valueOf(resultSet.getString("access_level")));
            }
        } catch (SQLException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return customer;
    }

    @Override
    public List<Customer> findAllCustomer() throws DaoException {
        ArrayList<Customer> customerList = new ArrayList<>();
        Customer customer;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getInt("idCustomer"));
                customer.setLogin(resultSet.getString("login"));
                customer.setPassword(resultSet.getString("password"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setDob(resultSet.getDate("DOB"));
                customer.setAddressStreet(resultSet.getString("address_street"));
                customer.setAddressCity(resultSet.getString("address_city"));
                customer.setAddressCountry(resultSet.getString("address_country"));
                customer.setMobilephone(resultSet.getString("phone"));
                customer.setLevel(AccessLevel.valueOf(resultSet.getString("access_level")));
                customerList.add(customer);
            }

        } catch (SQLException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return customerList;
    }

    @Override
    public void changeAccess(int idCustomer, AccessLevel level) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_LEVEL);
            preparedStatement.setString(1, level.toString());
            preparedStatement.setInt(2, idCustomer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }

    }

    @Override
    public void addCustomer(Customer customer) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setDate(3, (Date) customer.getDob());
            preparedStatement.setString(4, customer.getAddressStreet());
            preparedStatement.setString(5, customer.getAddressCity());
            preparedStatement.setString(6, customer.getAddressCountry());
            preparedStatement.setString(7, customer.getMobilephone());
            preparedStatement.setString(8, customer.getEmail());
            preparedStatement.setString(9, customer.getLogin());
            preparedStatement.setString(10, customer.getPassword());
            preparedStatement.setString(11, String.valueOf(customer.getLevel()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
    }
}
