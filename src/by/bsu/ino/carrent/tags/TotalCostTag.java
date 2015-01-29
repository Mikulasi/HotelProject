package by.bsu.ino.carrent.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class TotalCostTag extends TagSupport {

    public static final String PARAM_TOTAL_SUM = "totalSum";
	private String differenceDay;
	private String carPrice;

	public void setDifferenceDay(String differenceDay) {
		this.differenceDay = differenceDay;
	}

	public void setCarPrice(String carPrice) {
		this.carPrice = carPrice;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			int totalSum = Integer.parseInt(differenceDay)* Integer.parseInt(carPrice);
			JspWriter out = pageContext.getOut();
			pageContext.getRequest().setAttribute(PARAM_TOTAL_SUM, totalSum);
			out.print(totalSum);
		} catch (IOException e) {
			throw new JspException("Error: " + e.getMessage());
		}
		return SKIP_BODY;
	}

}
