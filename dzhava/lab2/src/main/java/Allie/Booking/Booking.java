package Allie.Booking;

import Allie.User.User;
import Allie.Car.Car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Calendar;

@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @Column(name = "bookingId")
    private long BookingId;
    private User User;
    private Car Car;
    @Column(name = "userId")
    private long UserId;
    @Column(name = "carId")
    private long CarId;
    @Column(name = "dateOfBooking")
    private Date DateOfBooking;

    public Date getDateOfReturn() {
        return DateOfReturn;
    }

    public void setDateOfReturn() {
        DateOfReturn = CalculateDateOfReturn();
    }

    private Date DateOfReturn;
    private int LengthOfBookingInDays;
    private boolean isFinished;
    private static int counter = 0;

    public Date CalculateDateOfReturn() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateOfBooking);
        calendar.add(Calendar.DATE, LengthOfBookingInDays);
        return new Date(calendar.getTimeInMillis());
    }

    public Booking() {

    }

    public Booking(User User, Car Car, Date date, int days) {
        BookingId = counter++;
        this.User = User;
        UserId = User.getId();
        CarId = Car.getId();
        this.Car = Car;
        UserId = User.getId();
        CarId = Car.getId();
        DateOfBooking = date;
        LengthOfBookingInDays = days;
        isFinished = false;
        setDateOfReturn();
    }

    public void CloseBooking() {
        setFinished(true);
    }


    public Long getId() {
        return BookingId;
    }

    public void setId(long bookingId) {
        BookingId = bookingId;
    }

    public User getUser() {
        return User;
    }

    public void setUserId(long id) {
        this.UserId = id;
    }

    public void setCarId(long id) {
        this.CarId = id;
    }

    public void setUser(User user) {
        User = user;
    }

    public Car getCar() {
        return Car;
    }

    public void setCar(Car car) {
        Car = car;
    }

    public Date getDateOfBooking() {
        return DateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        DateOfBooking = dateOfBooking;
    }

    public int getLengthOfBookingInDays() {
        return LengthOfBookingInDays;
    }

    public void setLengthOfBookingInDays(int lengthOfBookingInDays) {
        LengthOfBookingInDays = lengthOfBookingInDays;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public String toString() {
        return String.format("Allie.User %s %s rented a car model № %s for %s days", this.User.getFirstName(), this.User.getLastName(), this.Car.getModelId(), this.getLengthOfBookingInDays());
    }

    public long getUserId() {
        return UserId;
    }

    public long getCarId() {
        return CarId;
    }
}
