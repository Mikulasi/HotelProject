package by.bsu.ino.carrent.dao;

import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.pool.ConnectionPool;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractJDBCDao {

    public static final Logger LOG = Logger.getLogger(AbstractJDBCDao.class);
    protected ConnectionPool pool;

    public AbstractJDBCDao() {
        pool = ConnectionPool.getInstance();
    }

    public Connection getConnection() {
        return pool.getConnection();
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().closeConnection(connection);
        }
    }
    public void closePreparedStatement(PreparedStatement preparedStatement) throws DaoException {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DaoException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.SQL_EXCEPTION_ERROR_MESSAGE), e);
            }
        }
    }
}
