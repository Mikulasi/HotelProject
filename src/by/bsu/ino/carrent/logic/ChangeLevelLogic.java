package by.bsu.ino.carrent.logic;

import by.bsu.ino.carrent.dao.CustomerDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.model.enums.AccessLevel;
import by.bsu.ino.carrent.dao.mysql.MySqlCustomerDao;
import by.bsu.ino.carrent.manager.ConfigurationManager;

public class ChangeLevelLogic {

    private static final String PARAM_EMPTY_STATUS = "empty";

    public static void changeLevel(String idCustomer, String level) throws LogicException {

        CustomerDao customerDao = new MySqlCustomerDao();
        if (idCustomer != null) {
            if (PARAM_EMPTY_STATUS.equals(level)) {
                throw new LogicException(ConfigurationManager.getInstance().getProperty(
                        ConfigurationManager.CHOOSE_ACTION_ERROR_MESSAGE));
            }
            try {
                customerDao.changeAccess(Integer.parseInt(idCustomer), AccessLevel.valueOf(level));
            } catch (NumberFormatException | DaoException e) {
                throw new LogicException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE), e);
            }
        } else {
            throw new LogicException(ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.CHANGE_ACCESS_LEVEL_EXCEPTION_MESSAGE));
        }
    }
}
