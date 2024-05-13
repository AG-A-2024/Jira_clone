package pbs.ap.users;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import pbs.ap.projects.Project;

import java.util.Set;

@Entity
public class User extends PanacheEntity {
    public String name;
    public String lastName;
    public Roles role;
    public String indexNr;
    @Email
    public String email;
    public long phoneNumber;
    String password;
    //@ManyToMany(mappedBy = "studenci")
    //public Set<Project> projects;
    public void setPassword(String password) {
        this.password = password;
    }
}
