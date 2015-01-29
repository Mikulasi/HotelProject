package by.bsu.ino.carrent.filter;

import by.bsu.ino.carrent.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.releaseConnections();
        LOG.info("ConnectionPool is released");
    }

}
