package by.bsu.ino.carrent.logic;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.dao.BillDao;
import by.bsu.ino.carrent.dao.mysql.MySqlBillDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Bill;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ReceiveBillLogic {

    public static ArrayList<Bill> findClientBill(Integer idCustomer) throws LogicException {

        BillDao billDao = new MySqlBillDao();
        ArrayList<Bill> billList = new ArrayList<>();
            try {

            billList.addAll(billDao.findUserBills(idCustomer).stream().collect(Collectors.toList()));

        } catch (DaoException e) {
            throw new LogicException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE), e);
        }
        return billList;
    }
}
