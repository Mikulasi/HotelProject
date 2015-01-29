package by.bsu.ino.carrent.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DifferenceDateTag extends TagSupport {

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DIFFERENCE_DAY = "diffDay";
	private String firstDate;
	private String lastDate;

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			long difference;
			difference = (getTime(lastDate) - getTime(firstDate))/ (1000 * 60 * 60 * 24);
			pageContext.getRequest().setAttribute(DIFFERENCE_DAY, difference);
		} catch (ParseException e) {
			throw new JspException("Error: " + e.getMessage());
		}
		return SKIP_BODY;
	}

	private long getTime(String date) throws ParseException {
		DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.parse(date).getTime();
	}
}
