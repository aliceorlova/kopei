package app.Booking;


import app.Car.Car;
import app.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @Column(name = "bookingId", updatable = false, nullable = false)
    private long BookingId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private app.User.User User;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private app.Car.Car Car;
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
        this.Car = Car;
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
        return String.format("Allie.User %s %s rented a car model № %s for %s days", this.User.getFirstName(), this.User.getLastName(), this.Car.getCarModelModel().getId(), this.getLengthOfBookingInDays());
    }

}
