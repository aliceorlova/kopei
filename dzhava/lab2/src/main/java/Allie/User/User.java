package Allie.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
    @Column(name="uswerId")
    @javax.persistence.Id
    private long Id;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="customerPassword")
    private String password;
    @Column(name="isBlocked")
    private boolean isBlocked;
 //   private List<Booking> bookings;

    public Long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public User() {

    }

    public User(Long id, String fName, String lName, String email, String password) {
        setId(id);
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.password = password;
        //   bookings = new ArrayList<>();
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

    @Override
    public String toString() {
        return String.format("Allie.User: name: %s, %s, email: %s, is blocked? %s", this.getFirstName(), this.getLastName(), this.getEmail(), this.isBlocked());
    }


}
