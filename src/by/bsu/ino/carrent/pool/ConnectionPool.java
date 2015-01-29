package by.bsu.ino.carrent.pool;

import by.bsu.ino.carrent.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;


public final class ConnectionPool {
    public static final Logger LOG = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;
    private static BlockingQueue<Connection> pool;
    private static ReentrantLock lock = new ReentrantLock();
    private static ConfigurationManager config = ConfigurationManager.getInstance();

    public static ConnectionPool getInstance() {
        if (instance == null) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private ConnectionPool() {
        init();
    }

    private static void init() {
        LOG.info("Trying to create pool of connections...");
        String url = config.getProperty(ConfigurationManager.DB_URL);
        String user = config.getProperty(ConfigurationManager.DB_USER);
        String password = config.getProperty(ConfigurationManager.DB_PASSWORD);
        int size = Integer.parseInt(config.getProperty(ConfigurationManager.DB_MAXCONN));
        String driver = config.getProperty(ConfigurationManager.DB_DRIVER);
        try {
            Class.forName(driver);
            pool = new ArrayBlockingQueue<>(size);
            for (int i = 0; i < size; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                pool.offer(connection);
            }
            LOG.info("Connection pool successfully initialized.");
        } catch (SQLException | ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        boolean flag = true;
        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            LOG.error("Free connection waiting interrupted. Returned `null` connection.", e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            if (!connection.isClosed()) {
                if (!pool.offer(connection)) {
                    LOG.warn("Connection not added.");
                }
            } else {
                LOG.warn("Connection already closed.");
            }
        } catch (SQLException e) {
            LOG.warn("Connection not added.", e);
        }
    }

    public static void releaseConnections() {
            if (instance != null) {
                lock.lock();
                try {
                    if (instance != null) {
                        pool.stream().filter((connection) -> (connection != null)).forEach((connection) -> {
                            try {
                                connection.close();
                            } catch (SQLException e) {
                                LOG.error("Can't close connection (" + connection + "): ", e);
                            }
                        });
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
