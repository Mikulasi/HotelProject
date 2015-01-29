package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.logic.FindCarLogic;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Car;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class FreeCarCommand implements Command {

    private static final Logger LOG = Logger.getLogger(FreeCarCommand.class.getName());

    private static final String FREE_CAR = "freeCar";
    private static final String PARAM_ERROR_MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request) throws PersistException {
        String page;
        ArrayList<Car> freeCar;
        try {
            freeCar = FindCarLogic.findFreeCar();
            if (freeCar.size() != 0) {
                request.setAttribute(FREE_CAR, freeCar);
            } else {
                request.setAttribute(PARAM_ERROR_MESSAGE,ConfigurationManager.getInstance().getProperty(
                        ConfigurationManager.DOES_NOT_HAVE_FREE_CAR_MESSAGE));
            }
            page = ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.CREATE_ORDER_PATH);
        } catch (LogicException e) {
            LOG.error("Something goes wrong, redirect to error page.", e);
            request.setAttribute(PARAM_ERROR_MESSAGE,ConfigurationManager.getInstance().getProperty(
                            ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}
