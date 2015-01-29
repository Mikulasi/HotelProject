package by.bsu.ino.carrent.model;

import by.bsu.ino.carrent.model.enums.BookingState;

import java.math.BigDecimal;
import java.util.Date;

public class Booking {
	private Integer id;
    private Customer customer;
    private Car car;
	private Date dateBookingMade;
	private Date firstDate;
	private Date lastDate;
	private BigDecimal totalCost;
	private String comments;
    private BookingState state;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id)
	{
		this.id = id;
	}
	public Date getDateBookingMade()
	{
		return dateBookingMade;
	}
	public void setDateBookingMade(Date dateBookingMade)
	{
		this.dateBookingMade = dateBookingMade;
	}
	public Date getFirstDate()
	{
		return firstDate;
	}
	public void setFirstDate(Date firstDate)
	{
		this.firstDate = firstDate;
	}
	public Date getLastDate()
	{
		return lastDate;
	}
	public void setLastDate(Date lastDate)
	{
		this.lastDate = lastDate;
	}
	public BigDecimal getTotalCost()
	{
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost)
	{
		this.totalCost = totalCost;
	}
	public String getComments()
	{
		return comments;
	}
	public void setComments(String comments)
	{
		this.comments = comments;
	}

    public BookingState getState() {
        return state;
    }

    public void setState(BookingState state) {
        this.state = state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", dateBookingMade=" + dateBookingMade +
                ", firstDate=" + firstDate +
                ", lastDate=" + lastDate +
                ", totalCost=" + totalCost +
                ", comments='" + comments + '\'' +
                ", state=" + state +
                ", customer=" + customer +
                ", room" + car +
                '}';
    }
}
