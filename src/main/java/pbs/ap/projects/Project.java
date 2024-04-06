package pbs.ap.projects;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import pbs.ap.tasks.Task;
import pbs.ap.users.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Project extends PanacheEntity {
    @NotNull
    public String projectName;
    @ManyToOne(optional = false)
    public User projectOwner;
    @OneToMany
    public List<User> projectMaintainers;
    @CreationTimestamp
    public LocalDateTime creationTime;

    @OneToMany
    public List<Task> taskList;

}
