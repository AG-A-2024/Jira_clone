package pbs.ap.projects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pbs.ap.tasks.Task;
import pbs.ap.users.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
public class Project extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false, length = 50)
    public String projectName;
    @Column(nullable = false, length = 1000)
    public String projectDescription;

    @ManyToOne(optional = false)
    @JsonBackReference
    public User projectOwner;
    @ManyToMany
    public List<User> projectMaintainers;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public LocalDateTime creationTime;

    public LocalDateTime releaseDate;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    public List<Task> taskList;

    public Project(String projectName, String projectDescription){
        this.projectName = projectName;
        this.projectDescription = projectDescription;
    }
}
