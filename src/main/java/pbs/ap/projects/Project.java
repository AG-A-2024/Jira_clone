package pbs.ap.projects;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pbs.ap.tasks.Task;
import pbs.ap.users.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Project extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false, length = 50)
    public String projectName;
    @Column(nullable = false, length = 1000)
    public String projectDescription;
    @ManyToMany
    public List<User> projectMaintainers;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public LocalDateTime creationTime;

    public LocalDateTime releaseDate;
    @OneToMany
    public List<Task> taskList;

    public Project(String projectName, String projectDescription){
        this.projectName = projectName;
        this.projectDescription = projectDescription;
    }
}
