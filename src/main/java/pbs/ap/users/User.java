package pbs.ap.users;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import pbs.ap.projects.Project;


import java.util.Set;

@Entity
@NoArgsConstructor
public class User extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    public String name;
    @NotBlank
    public String lastName;

    public String indexNr;
    @Email(regexp = "^(.+)@(pbs\\.edu\\.pl)$")
    @NotBlank
    @Column(unique = true)
    public String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectOwner")
    @JsonManagedReference
    public Set<Project> ownedProjects;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns =
    @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    public Set<Roles> roles;

    String password;
    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }
}
