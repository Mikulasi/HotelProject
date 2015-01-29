package by.bsu.ino.carrent.filter;

import by.bsu.ino.carrent.manager.ConfigurationManager;
import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.model.enums.AccessLevel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityAdmin implements Filter {

	public static final String PARAM_USER = "user";

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		Customer customer = (Customer) session.getAttribute(PARAM_USER);
		if (customer != null) {
			AccessLevel userAccess = customer.getLevel();
			if (!AccessLevel.ADMIN.equals(userAccess)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(ConfigurationManager.getInstance().
                        getProperty(ConfigurationManager.INDEX_PAGE_PATH));
				request.setAttribute("errorMessage",ConfigurationManager.getInstance().getProperty(
										ConfigurationManager.DOES_NOT_HAVE_ACCESS_LEVEL_MESSAGE));
				dispatcher.forward(request, response);
			}
			chain.doFilter(request, response);
		} else {
			request.setAttribute("errorMessage",ConfigurationManager.getInstance().getProperty(
							ConfigurationManager.CUSTOMER_NOT_FOUND_MESSAGE));
			RequestDispatcher dispatcher = request.getRequestDispatcher(ConfigurationManager.getInstance()
							.getProperty(ConfigurationManager.INDEX_PAGE_PATH));
			dispatcher.forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
