package by.bsu.ino.carrent.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodayTag extends TagSupport {

	private String mFormat;

	public void setFormat(String format) {
		this.mFormat = format;
	}

	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			Date today = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat(mFormat);
			out.print(dateFormatter.format(today));

		} catch (IOException e) {
			throw new JspException("Error: " + e.getMessage());
		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
