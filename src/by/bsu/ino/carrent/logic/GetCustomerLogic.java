package by.bsu.ino.carrent.logic;

import by.bsu.ino.carrent.dao.CustomerDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.dao.mysql.MySqlCustomerDao;
import by.bsu.ino.carrent.manager.ConfigurationManager;

import java.util.ArrayList;

public class GetCustomerLogic {

    public static ArrayList<Customer> getAllCustomer() throws LogicException {
        ArrayList<Customer> customerList;
        CustomerDao customerDao = new MySqlCustomerDao();
        try {
            customerList = (ArrayList<Customer>) customerDao.findAllCustomer();
        } catch (DaoException e) {
            throw new LogicException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE), e);
        }
        return customerList;
    }
}
