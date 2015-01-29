package by.bsu.ino.carrent.logic;

import by.bsu.ino.carrent.dao.CustomerDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.dao.mysql.MySqlCustomerDao;
import by.bsu.ino.carrent.manager.ConfigurationManager;

public class LoginLogic {

    public static Customer checkLogin(String login, String password) throws LogicException, PersistException {
        CustomerDao customerDao = new MySqlCustomerDao();
        Customer customer;
        try {
            customer = customerDao.findByCredentials(login, password);
        } catch (DaoException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE), e);
        }
        return customer;
    }
}
