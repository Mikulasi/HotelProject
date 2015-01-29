package by.bsu.ino.carrent.dao;

import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.model.enums.CarStatus;
import by.bsu.ino.carrent.model.Car;

import java.util.List;

public interface CarDao {

    public Car create() throws PersistException, DaoException;

    public Car read(Integer key) throws PersistException, DaoException;

    public void update(Car car) throws PersistException, DaoException;

    public void delete(Car car) throws PersistException, DaoException;

    public List<Car> getAll() throws PersistException, DaoException;

    public void changeCarStatus(Integer id, CarStatus status) throws DaoException;
}
