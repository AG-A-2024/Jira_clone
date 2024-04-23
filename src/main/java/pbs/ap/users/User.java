package pbs.ap.users;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;

@Entity
public class User extends PanacheEntity {
    public String name;
    public String lastName;
    public Roles role;
    public int indexNr;
    @Email
    public String email;
    public long phoneNumber;
    String password;

    public void setPassword(String password) {
        this.password = password;
    }
}
