package allie.Booking;

import allie.Car.CarModel;
import allie.Item;
import allie.User.UserModel;

import java.sql.Date;
import java.util.Calendar;

public class BookingModel implements Item {
    private long BookingId;
    private UserModel User;
    private CarModel Car;
    private Date DateOfBooking;
    private int LengthOfBookingInDays;
    private boolean isFinished;
    private static int counter = 0;

    public Date CalculateDateOfReturn() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateOfBooking);
        calendar.add(Calendar.DATE, LengthOfBookingInDays);
        Date dateOfReturn = new Date(calendar.getTimeInMillis());
        return dateOfReturn;
    }

    public BookingModel(){

    }
    public BookingModel(UserModel User, CarModel Car, Date date, int days) {
        BookingId = counter++;
        this.User = User;
        this.Car = Car;
        DateOfBooking = date;
        LengthOfBookingInDays = days;
        isFinished = false;
    }

    public void CloseBooking() {
        setFinished(true);
    }

    public long getId() {
        return BookingId;
    }

    public void setId(long bookingId) {
        BookingId = bookingId;
    }

    public UserModel getUser() {
        return User;
    }

    public void setUser(UserModel user) {
        User = user;
    }

    public CarModel getCar() {
        return Car;
    }

    public void setCar(CarModel car) {
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
        return String.format("User %s %s rented a %s %s for %s days", this.User.getFirstName(), this.User.getLastName(), this.Car.getCarModelModel().getBrand().getName(), this.Car.getCarModelModel().getModelName(), this.getLengthOfBookingInDays());
    }
}
