package allie.User;


import allie.Booking.BookingDAO;
import allie.Booking.BookingModel;
import allie.Car.CarModel;
import allie.Item;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class UserModel implements Item {
    private long Id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isBlocked;
    private BookingDAO bookingDAO = new BookingDAO();

    @Override
    public long getId() {
        return Id;
    }
    @Override
    public void setId(long id) {
        Id = id;
    }
    public UserModel(){

    }
    public UserModel(Long id, String fName, String lName, String email, String password) {
        setId(id);
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void RentACar(CarModel carModel, int days) throws SQLException {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        BookingModel booking = new BookingModel(this, carModel, date, days);
        bookingDAO.Add(booking);
        System.out.println(booking.toString());
    }

    @Override
    public String toString() {
        return String.format("User: name: %s, %s, email: %s, is blocked? %s", this.getFirstName(), this.getLastName(), this.getEmail(), this.isBlocked());
    }


}
