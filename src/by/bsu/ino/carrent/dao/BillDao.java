package by.bsu.ino.carrent.dao;

import by.bsu.ino.carrent.model.enums.BillState;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.model.Bill;

import java.util.List;

public interface BillDao {

    public Bill create() throws PersistException, DaoException;

    public Bill read(Integer key) throws PersistException, DaoException;

    public void update(Bill bill) throws PersistException, DaoException;

    public void delete(Bill bill) throws PersistException, DaoException;

    public List<Bill> getAll() throws PersistException, DaoException;

    public List<Bill> findUserBills(Integer idCustomer) throws DaoException;

    public Bill findBillById(Integer idBill) throws DaoException;

    public void changeBillStatusById(Integer idBill, BillState state) throws DaoException;


}
