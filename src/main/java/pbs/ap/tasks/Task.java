package pbs.ap.tasks;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Sort;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pbs.ap.projects.Project;

import java.time.LocalDateTime;
import java.util.Optional;


@NoArgsConstructor
@Entity
public class Task extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull
    @Column(length = 50, nullable = false)
    public String taskName;
    public Long sequenceNr;
    @Column(length = 1000)
    public String description;
    @CreationTimestamp
    @NotNull
    public LocalDateTime creationDateTime;
    @ManyToOne(optional = false)
    @JsonBackReference
    public Project project;

    @PrePersist
    public void generateTaskNumber() {
        if (this.sequenceNr == null) {
            this.sequenceNr = getNextTaskNumberForProject(this.project.id);
        }
    }

    private Long getNextTaskNumberForProject(Long projectId) {
        Optional<Task> maxTaskNumber = Task.find("project.id = ?1", Sort.by("sequenceNr").descending(), projectId).firstResultOptional();
        return maxTaskNumber.map(task -> task.sequenceNr + 1).orElse(1L);
    }
}
