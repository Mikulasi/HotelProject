package by.bsu.ino.carrent.command;

import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.model.enums.AccessLevel;
import by.bsu.ino.carrent.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class PageCommand implements Command {

    private static final String PARAM_PAGE = "page";
    private static final String PARAM_LOGIN_PAGE = "login";
    private static final String PARAM_REGISTRATION_PAGE = "registration";
    private static final String PARAM_CUSTOMER_PAGE = "customerpage";
    private static final String PARAM_ADMIN_PAGE = "adminpage";
    private static final String PARAM_CABINET_PAGE = "cabinet";

    @Override
    public String execute(HttpServletRequest request)  {
        return checkPage(request);
    }

    private String checkPage(HttpServletRequest request) {
        String pageParam = request.getParameter(PARAM_PAGE);
        switch (pageParam) {
            case PARAM_LOGIN_PAGE:
                return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
            case PARAM_REGISTRATION_PAGE:
                return ConfigurationManager.getInstance().getProperty(ConfigurationManager.REGISTRATION_PAGE_PATH);
            case PARAM_CUSTOMER_PAGE:
                return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CUSTOMER_PAGE_PATH);
            case PARAM_ADMIN_PAGE:
                return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
            case PARAM_CABINET_PAGE:
                return checkRole(request);
            default:
                return ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
        }
    }

    private String checkRole(HttpServletRequest request) {
        AccessLevel check = ((Customer) request.getSession().getAttribute("customer")).getLevel();
        if (check != null) {
            switch (check) {
                case ADMIN:
                    return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
                case CUSTOMER:
                    return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CUSTOMER_PAGE_PATH);
                default:
                    request.setAttribute("errorMessage", ConfigurationManager.getInstance().getProperty(
                                    ConfigurationManager.BANNED_MESSAGE));
                    return ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
            }
        }
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
    }
}