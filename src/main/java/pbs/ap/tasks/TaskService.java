package pbs.ap.tasks;


import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getAllTasks();
    Task getTaskById(Long id);
   Task getTaskByTaskName(String taskName);
   Task addTask(Task task);
    Task update(Long id, Task task);
    Task deleteTaskById(Long id);
    Optional<Task> findTaskById(Long id);
}
