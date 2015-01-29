package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.logic.CreateOrderLogic;
import by.bsu.ino.carrent.logic.FindCarLogic;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Booking;
import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.model.Car;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

public class CreateOrderCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(CreateOrderCommand.class.getName());
    private static final String FREE_CAR = "freeCar";
    private static final String ERROR_MESSAGE = "errorMessage";
    public static final String ACTION_MESSAGE = "actionMessage";
    private static final String FIRST_DATE = "firstDate";
    private static final String LAST_DATE = "lastDate";
    private static final String ID_CAR = "idCar";
    private static final String COMMENTS = "comments";
    private static final String CUSTOMER = "customer";

    @Override
    public String execute(HttpServletRequest request) {
            String page;

            Booking order = new Booking();
            Customer customer = (Customer) request.getSession().getAttribute(CUSTOMER);
            order.setDateBookingMade(new Date());
            order.setComments(request.getParameter(COMMENTS));
            Integer idCar = Integer.valueOf(request.getParameter(ID_CAR));
            String firstDate = request.getParameter(FIRST_DATE);
            String lastDate = request.getParameter(LAST_DATE);
            try {
                CreateOrderLogic.createOrder(order, customer, idCar, firstDate, lastDate);
                ArrayList<Car> freeCar = FindCarLogic.findFreeCar();
                request.setAttribute(FREE_CAR, freeCar);
                page = refreshWithChanges(request);
                request.setAttribute(ACTION_MESSAGE,ConfigurationManager.getInstance().getProperty
                        (ConfigurationManager.CREATE_ORDER_SUCCESS_MESSAGE));
            } catch (LogicException | PersistException e) {
                LOGGER.error("Something goes wrong, redirect to error page.", e);
                request.setAttribute(ERROR_MESSAGE,ConfigurationManager.getInstance().getProperty
                        (ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            }
        return page;
        }

    private String refreshWithChanges(HttpServletRequest request) throws LogicException, PersistException {
        ArrayList<Car> freeCar;
        try {
            freeCar = FindCarLogic.findFreeCar();
            request.setAttribute(FREE_CAR, freeCar);
        } catch (LogicException e) {
            LOGGER.error("Something goes wrong, redirect to error page.", e);
            request.setAttribute(ERROR_MESSAGE, ConfigurationManager.getInstance().getProperty
                    (ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return ConfigurationManager.getInstance().getProperty
                (ConfigurationManager.CREATE_ORDER_PATH);
    }
    }
