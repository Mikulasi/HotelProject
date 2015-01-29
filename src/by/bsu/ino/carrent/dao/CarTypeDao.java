package by.bsu.ino.carrent.dao;

import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.model.CarType;

import java.util.List;

public interface CarTypeDao {

    public CarType create() throws PersistException, DaoException;

    public CarType read(Integer key) throws PersistException, DaoException;

    public void update(CarType carType) throws PersistException, DaoException;

    public void delete(CarType carType) throws PersistException, DaoException;

    public List<CarType> getAll() throws PersistException, DaoException;
}
