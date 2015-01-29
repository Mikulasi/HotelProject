package by.bsu.ino.carrent.filter;

import javax.servlet.*;
import java.io.IOException;

public class FilterEncoding implements Filter {

	private String encoding;

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		String contentType = request.getContentType();
		if (contentType != null&& contentType.startsWith("application/x-www-form-urlencoded")) {
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
		if (encoding == null) {
			encoding = "UTF-8";
		}
	}
}
