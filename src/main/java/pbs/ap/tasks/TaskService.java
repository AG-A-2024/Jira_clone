package pbs.ap.tasks;


import io.smallrye.mutiny.Uni;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getAllTasks();
    Task getTaskById(Long id);
   Task getTaskByTaskName(String taskName);
   Uni<Task> addTask(Task task);
    Task update(Task task);
    Task deleteTaskById(Long id);
    Optional<Task> findTaskById(Long id);
}
