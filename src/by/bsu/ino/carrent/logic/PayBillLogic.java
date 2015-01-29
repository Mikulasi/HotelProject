package by.bsu.ino.carrent.logic;

import by.bsu.ino.carrent.dao.mysql.MySqlBillDao;
import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.model.enums.BillState;
import by.bsu.ino.carrent.dao.BillDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Bill;

public class PayBillLogic {

    public static void payBill(String idBill) throws LogicException {
        BillDao billDao = new MySqlBillDao();
        if (idBill != null) {
            try {
                Bill bill = billDao.findBillById(Integer.parseInt(idBill));
                if (bill.getState().equals(BillState.PAID)) {
                    throw new LogicException(ConfigurationManager.getInstance().getProperty(
                            ConfigurationManager.BILL_ALREADY_PAID_MESSAGE));
                }
                billDao.changeBillStatusById(Integer.parseInt(idBill), BillState.PAID);
            } catch (DaoException e) {
                throw new LogicException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE), e);
            }
        } else {
            throw new LogicException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.BILL_PAID_ERROR_MESSAGE));
        }
    }
}
