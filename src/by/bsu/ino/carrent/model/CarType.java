package by.bsu.ino.carrent.model;

import java.math.BigDecimal;

public class CarType
{
	private Integer id;
	private String carType;
	private String carDescription;
	private BigDecimal carPrice;

    public Integer getId() {
        return id;
    }
    public void setId(int id)
	{
		this.id = id;
	}
	public String getCarType()
	{
		return carType;
	}
	public void setCarType(String carType)
	{
		this.carType = carType;
	}
	public String getCarDescription()
	{
		return carDescription;
	}
	public void setCarDescription(String carDescription)
	{
		this.carDescription = carDescription;
	}
	public BigDecimal getCarPrice()
	{
		return carPrice;
	}
	public void setCarPrice(BigDecimal carPrice)
	{
		this.carPrice = carPrice;
	}

    @Override
    public String toString() {
        return "CarType{" +
                "id=" + id +
                ", carType='" + carType + '\'' +
                ", carDescription='" + carDescription + '\'' +
                ", carPrice=" + carPrice +
                '}';
    }
}
