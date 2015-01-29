package by.bsu.ino.carrent.filter;

import by.bsu.ino.carrent.model.Customer;
import by.bsu.ino.carrent.model.enums.AccessLevel;
import by.bsu.ino.carrent.manager.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/SecurityClient")
public class SecurityClient implements Filter {

	public static final String PARAM_CUSTOMER = "customer";

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		Customer customer = (Customer) session.getAttribute(PARAM_CUSTOMER);
		if (customer != null) {
			AccessLevel userAccess = customer.getLevel();
			if (!userAccess.equals(AccessLevel.CUSTOMER)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(ConfigurationManager.getInstance().getProperty(
										ConfigurationManager.INDEX_PAGE_PATH));
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
