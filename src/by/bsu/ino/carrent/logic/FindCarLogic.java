package by.bsu.ino.carrent.logic;

import by.bsu.ino.carrent.dao.CarDao;
import by.bsu.ino.carrent.dao.mysql.MySqlCarDao;
import by.bsu.ino.carrent.exception.DaoException;
import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.model.enums.CarStatus;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Car;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCarLogic {

    public static ArrayList<Car> findFreeCar() throws LogicException, PersistException {
        CarDao carDao = new MySqlCarDao();
        ArrayList<Car> freeCar = new ArrayList<>();
        try {

            freeCar.addAll(carDao.getAll().stream().filter(room -> room.getCarStatus().equals(CarStatus.FREE)).collect(Collectors.toList()));

        } catch (PersistException | DaoException e) {
            throw new LogicException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE), e);
        }
        return freeCar;
    }

    public static ArrayList<Car> findAllCar() throws PersistException {
        CarDao carDao = new MySqlCarDao();
        try {
            return (ArrayList<Car>) carDao.getAll();
        } catch (DaoException e) {
            throw new PersistException(ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE), e);
        }
    }
}
