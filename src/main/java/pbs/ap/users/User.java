package pbs.ap.users;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pbs.ap.projects.Project;

import java.util.Set;

@Entity
@NoArgsConstructor
public class User extends PanacheEntity {
    public String name;
    public String lastName;
    public Roles role;
    public String indexNr;
    @Email
    public String email;
    public long phoneNumber;
    @Getter
    @Setter
    private String password;
    //@ManyToMany(mappedBy = "studenci")
    //public Set<Project> projects;
}
