package by.bsu.ino.carrent.dao.mysql;

import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.model.enums.CarStatus;
import by.bsu.ino.carrent.dao.AbstractJDBCDao;
import by.bsu.ino.carrent.dao.CarDao;
import by.bsu.ino.carrent.dao.CarTypeDao;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCarDao extends AbstractJDBCDao implements CarDao {

    private static final String CREATE = "INSERT INTO Vehicle (idVehicle, idVehicleType, vehicle_number, comments, status) VALUES (?, ?, ?, ?, ?)";
    private static final String READ = "SELECT * FROM Vehicle WHERE idVehicle = ?";
    private static final String UPDATE = "UPDATE Vehicle SET idVehicle = ?, idVehicleType = ?, vehicle_number = ?, comments = ?, status = ?";
    private static final String DELETE = "DELETE FROM Vehicle WHERE idVehicle = ?";
    private static final String GET_ALL = "SELECT * FROM Vehicle";
    private static final String UPDATE_CAR_STATUS = "UPDATE Vehicle SET status = ? WHERE idVehicle = ?";

    @Override
    public Car create() throws PersistException, DaoException {
        Car car = new Car();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setInt(1, car.getCarType().getId());
            preparedStatement.setString(2, car.getCarNumber());
            preparedStatement.setString(3, car.getComments());
            preparedStatement.setString(4, car.getCarStatus().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return car;
    }

    @Override
    public Car read(Integer key) throws PersistException, DaoException {
        Car car = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                car = new Car();
                CarTypeDao carTypeDao = new MySqlCarTypeDao();
                car.setId(resultSet.getInt("idVehicle"));
                car.setCarType(carTypeDao.read(resultSet.getInt("idVehicleType")));
                car.setCarNumber(resultSet.getString("vehicle_number"));
                car.setComments(resultSet.getString("comments"));
                car.setCarStatus(CarStatus.valueOf(resultSet.getString("status")));
            }

        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return car;
    }

    @Override
    public void update(Car car) throws PersistException, DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, car.getCarType().getId());
            preparedStatement.setString(2, car.getCarNumber());
            preparedStatement.setString(3, car.getComments());
            preparedStatement.setString(4, car.getCarStatus().toString());
            preparedStatement.setInt(5, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        }finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
    }

    @Override
    public void delete(Car car) throws PersistException, DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        }finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
    }

    @Override
    public List<Car> getAll() throws PersistException, DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet;
        ArrayList<Car> list = new ArrayList<>();
        Car car = new Car();
        CarTypeDao carTypeDao = new MySqlCarTypeDao();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                car.setId(resultSet.getInt("idVehicle"));
                car.setCarNumber(resultSet.getString("vehicle_number"));
                car.setComments(resultSet.getString("comments"));
                car.setCarStatus(CarStatus.valueOf(resultSet.getString("status")));
                car.setCarType(carTypeDao.read(resultSet.getInt("idVehicleType")));
                list.add(car);
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
    public void changeCarStatus(Integer id, CarStatus status) throws DaoException {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CAR_STATUS);
            preparedStatement.setString(1, status.toString());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
    }
}
