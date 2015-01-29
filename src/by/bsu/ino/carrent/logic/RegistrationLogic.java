package by.bsu.ino.carrent.logic;

import by.bsu.ino.carrent.dao.CustomerDao;
import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.dao.mysql.MySqlCustomerDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.manager.ConfigurationManager;

import java.util.regex.Pattern;

public class RegistrationLogic {

    private static final String EMAIL_VALIDATION = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*" +
            "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String PASSWORD_VALIDATION = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";

    private static final String LOGIN_VALIDATION = "^[a-zA-Z0-9_-]{3,15}$";


    public static void registration(Customer customer, String password, String passwordAgain) throws LogicException {
        CustomerDao customerDao = new MySqlCustomerDao();
        try {
            validation(customer, password, passwordAgain);
            System.out.println(customerDao.findByCredentials(customer.getLogin(), customer.getPassword()));
            if (customerDao.findByCredentials(customer.getLogin(), customer.getPassword()) == null) {
                customerDao.addCustomer(customer);
            } else {
                throw new LogicException(ConfigurationManager.getInstance().getProperty(
                        ConfigurationManager.REGISTRATION_INTERRUPT_MESSAGE));
            }
        } catch (NumberFormatException | DaoException e) {
            throw new LogicException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE), e);
        }
    }

    private static void validation(Customer customer, String password, String passwordAgain) throws LogicException {
        validationField(LOGIN_VALIDATION, ConfigurationManager.LOGIN_IS_NOT_VALID, customer.getLogin());
        validationPassword(password, passwordAgain);
        validationField(EMAIL_VALIDATION, ConfigurationManager.EMAIL_IS_NOT_VALID, customer.getEmail());
        validationField(LOGIN_VALIDATION, ConfigurationManager.FIRST_NAME_IS_NOT_VALID, customer.getFirstName());
        validationField(LOGIN_VALIDATION, ConfigurationManager.LAST_NAME_IS_NOT_VALID, customer.getLastName());
    }


    private static void validationPassword(String password, String passwordAgain) throws LogicException {
        if (!password.equals(passwordAgain)&& Pattern.matches(PASSWORD_VALIDATION, password)) {
            throw new LogicException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.PASSWORD_IS_NOT_VALID));
        }
    }


    private static void validationField(String pattern, String errorMessage, String field) throws LogicException {
        if (!Pattern.matches(pattern, field)) {
            throw new LogicException(ConfigurationManager.getInstance().getProperty(errorMessage));
        }
    }
}
