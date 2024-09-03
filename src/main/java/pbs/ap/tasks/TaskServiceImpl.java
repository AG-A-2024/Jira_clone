package pbs.ap.tasks;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;
import pbs.ap.users.User;


import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@ApplicationScoped
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private static final Logger LOG = Logger.getLogger(TaskServiceImpl.class);
    private final EntityManager entityManager;
    @Override
    public List<Task> getAllTasks() {
        LOG.debug(">>>getAllTasks<<<");
        return Task.listAll();
    }



    @Override
    public Task getTaskById(Long id) {
        return Task.findById(id);
    }

    @Override
    public Task getTaskByTaskName(String taskName) {
        return Task.find("taskName", taskName).firstResult();
    }


  /*  @Override
    @Transactional
    public Uni<Task> addTask(Task task) {
        return Uni.createFrom().item(() -> {
            entityManager.persist(task);
            return task;
        });
    } */

    @Override
    @Transactional
    public boolean addTask(Task task) {
        task.persistAndFlush();
        return task.isPersistent();
    }

    @Override
    @Transactional
    public boolean update(Long id, Task task) {
   /*     Optional<Task> existingTaskOpt = Task.findByIdOptional(task.id);
        if (existingTaskOpt.isEmpty()) {
            throw new NotFoundException("Task with id " + task.id + " not found");
        }
        Task existingTask = existingTaskOpt.get();
        existingTask.taskName = task.taskName;
        existingTask.description = task.description;

        return existingTask;*/
        Optional<Task> existingTaskOpt = Task.findByIdOptional(id);
        if (existingTaskOpt.isPresent()) {
            try {
                Task existingTask = existingTaskOpt.get();
                existingTask.taskName = task.taskName;
                existingTask.description = task.description;
                return addTask(existingTask);
            } catch (Exception e) {
                LOG.error(">>>updateTask<<< ERROR: ", e);
            }
        }
        return false;
    }

    @Override
    @Transactional
    public Task deleteTaskById(Long id) {
        Task taskToDelete = Task.findById(id);
        if (taskToDelete != null) {
            taskToDelete.delete();
        }
        return taskToDelete;
    }



}

