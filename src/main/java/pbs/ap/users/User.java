package pbs.ap.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
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

    public String indexNr;
    @Email
    public String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns =
    @JoinColumn(name = "id"))
    public Set<String> roles;

    String password;
    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }
}
