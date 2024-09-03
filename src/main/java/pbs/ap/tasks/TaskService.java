package pbs.ap.tasks;


import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task getTaskByTaskName(String taskName);

    boolean addTask(Task task);

    boolean update(Long id, Task task);

    Task deleteTaskById(Long id);
}
