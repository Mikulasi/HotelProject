package by.bsu.ino.carrent.dao.mysql;

import by.bsu.ino.carrent.dao.AbstractJDBCDao;
import by.bsu.ino.carrent.dao.CarTypeDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.CarType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCarTypeDao extends AbstractJDBCDao implements CarTypeDao {
    private static final String CREATE = "INSERT INTO VehicleType (idVehicleType, vehicle_type, vehicle_description, vehicle_price) VALUES (?, ?, ?, ?)";
    private static final String READ = "SELECT * FROM VehicleType WHERE idVehicleType = ?";
    private static final String UPDATE = "UPDATE VehicleType SET idVehicleType = ?, vehicle_type = ?, vehicle_description = ?, vehicle_price = ?";
    private static final String DELETE = "DELETE FROM VehicleType WHERE idVehicleType = ?";
    private static final String GET_ALL = "SELECT * FROM VehicleType";

    @Override
    public CarType create() throws PersistException, DaoException {
        CarType carType = new CarType();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setString(1, carType.getCarType());
            preparedStatement.setString(2, carType.getCarDescription());
            preparedStatement.setBigDecimal(3, carType.getCarPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return carType;
    }

    @Override
    public CarType read(Integer key) throws PersistException, DaoException {
        CarType carType = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                carType = new CarType();
                carType.setId(resultSet.getInt("idVehicleType"));
                carType.setCarType(resultSet.getString("vehicle_type"));
                carType.setCarDescription(resultSet.getString("vehicle_description"));
                carType.setCarPrice(resultSet.getBigDecimal("vehicle_price"));
            }

        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        } finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return carType;
    }

    @Override
    public void update(CarType carType) throws PersistException, DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, carType.getId());
            preparedStatement.setString(2, carType.getCarType());
            preparedStatement.setString(3, carType.getCarDescription());
            preparedStatement.setBigDecimal(4, carType.getCarPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        }finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
    }

    @Override
    public void delete(CarType carType) throws PersistException, DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, carType.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        }finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }

    }

    @Override
    public List<CarType> getAll() throws PersistException, DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet;
        ArrayList<CarType> list = new ArrayList<>();
        CarType carType = new CarType();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                carType.setId(resultSet.getInt("idVehicleType"));
                carType.setCarType(resultSet.getString("vehicle_type"));
                carType.setCarDescription(resultSet.getString("vehicle_description"));
                carType.setCarPrice(resultSet.getBigDecimal("vehicle_price"));
                list.add(carType);
            }
        } catch (SQLException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
        }finally {
            closePreparedStatement(preparedStatement);
            releaseConnection(connection);
        }
        return list;
    }
}
