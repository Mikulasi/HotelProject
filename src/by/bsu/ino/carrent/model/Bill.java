package by.bsu.ino.carrent.model;

import by.bsu.ino.carrent.model.enums.BillState;

import java.math.BigDecimal;
import java.util.Date;

public class Bill{

    private Integer id;
    private BillState state;
    private Date billDate;
    private BigDecimal cost;
    private Booking booking;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BillState getState() {
        return state;
    }

    public void setState(BillState state) {
        this.state = state;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "idBill=" + id +
                ", state=" + state +
                ", billDate=" + billDate +
                ", cost=" + cost +
                ", booking=" + booking +
                '}';
    }
}
