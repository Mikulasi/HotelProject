package by.bsu.ino.carrent.dao;

import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.model.enums.AccessLevel;

import java.util.List;

public interface CustomerDao {

    public Customer create() throws PersistException, DaoException;

    public Customer read(Integer key) throws PersistException, DaoException;

    public void update(Customer customer) throws PersistException, DaoException;

    public void delete(Customer customer) throws PersistException, DaoException;

    public List<Customer> getAll() throws PersistException, DaoException;

    public Customer findByCredentials(String login, String password) throws DaoException;

    public List<Customer> findAllCustomer() throws DaoException;

    public void changeAccess(int idCustomer, AccessLevel level) throws DaoException;

    public void addCustomer(Customer customer) throws DaoException;

}
