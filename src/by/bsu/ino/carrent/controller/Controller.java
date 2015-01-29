package by.bsu.ino.carrent.controller;

import by.bsu.ino.carrent.command.Command;
import by.bsu.ino.carrent.exception.LogicException;
import by.bsu.ino.carrent.exception.PersistException;
import by.bsu.ino.carrent.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    public static final Logger LOG = Logger.getLogger(Controller.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            processRequest(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String page = null;
    ActionFactory client = new ActionFactory();
    Command command = client.defineCommand(request);

        try {
            page = command.execute(request);
        } catch (LogicException | PersistException e) {
            LOG.error(ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.LOGIC_EXCEPTION_ERROR_MESSAGE),e);
        }
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
            request.getSession().setAttribute("nullPage", ConfigurationManager.getInstance().getProperty(ConfigurationManager.NULL_PAGE_ERROR));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}

