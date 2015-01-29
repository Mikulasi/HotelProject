package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.logic.FindCarLogic;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Car;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AllCarCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AllCarCommand.class.getName());

    private static final String LIST_CAR = "carList";
    private static final String ERROR_MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request) throws LogicException, PersistException {
        String page;
        ArrayList<Car> listCar;
        try {
            listCar = FindCarLogic.findAllCar();
            request.setAttribute(LIST_CAR, listCar);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.CAR_ADMINISTRATION_PATH);
        } catch (PersistException e) {
            LOG.error("Something goes wrong, redirect to error page.", e);
            request.setAttribute(ERROR_MESSAGE,ConfigurationManager.getInstance().getProperty(
                            ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
