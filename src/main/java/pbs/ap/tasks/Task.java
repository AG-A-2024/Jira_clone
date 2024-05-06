package pbs.ap.tasks;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pbs.ap.projects.Project;

import java.time.LocalDateTime;


@NoArgsConstructor
@Entity
@Getter
@Setter

public class Task extends PanacheEntity {

    @NotNull
    public String taskName;
    public int sequence;
    public String description;
    @NotNull
    LocalDateTime deliveryTime;


}
