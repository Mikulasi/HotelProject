package by.bsu.ino.carrent.model;

import by.bsu.ino.carrent.model.enums.AccessLevel;

import java.util.Date;

public class Customer {
	private Integer id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String addressStreet;
    private String addressCity;
    private String addressCountry;
    private String mobilephone;
    private String email;
    private String login;
    private String password;
    private AccessLevel level;

    public Integer getId() {
        return id;
    }
	public void setId(int id)
	{
		this.id = id;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public Date getDob()
	{
		return dob;
	}
	public void setDob(Date dob)
	{
		this.dob = dob;
	}
	public String getAddressStreet()
	{
		return addressStreet;
	}
	public void setAddressStreet(String addressStreet)
	{
		this.addressStreet = addressStreet;
	}
	public String getAddressCity()
	{
		return addressCity;
	}
	public void setAddressCity(String addressCity)
	{
		this.addressCity = addressCity;
	}
	public String getAddressCountry()
	{
		return addressCountry;
	}
	public void setAddressCountry(String addressCountry)
	{
		this.addressCountry = addressCountry;
	}
	public String getMobilephone()
	{
		return mobilephone;
	}
	public void setMobilephone(String mobilephone)
	{
		this.mobilephone = mobilephone;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccessLevel getLevel() {
        return level;
    }

    public void setLevel(AccessLevel level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", addressStreet='" + addressStreet + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressCountry='" + addressCountry + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", level='" + level +'\'' +
                '}';
    }
}
