package by.bsu.ino.carrent.model;

import by.bsu.ino.carrent.model.enums.CarStatus;

public class Car {

	private Integer id;
	private String comments;
    private CarStatus carStatus;
    private CarType carType;
    private String carNumber;

    public String getCarNumber() {
        return carNumber;
    }
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
    public Integer getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
	public String getComments()
	{
		return comments;
	}
	public void setComments(String comments)
	{
		this.comments = comments;
	}
    public CarStatus getCarStatus() {
        return carStatus;
    }
    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", comments='" + comments + '\'' +
                ", carStatus=" + carStatus +
                ", carType=" + carType +
                ", carNumber='" + carNumber + '\'' +
                '}';
    }
}
