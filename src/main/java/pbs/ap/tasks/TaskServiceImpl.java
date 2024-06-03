package pbs.ap.tasks;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;


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
    public Uni<Task> addTask(Task task) {
        return Uni.createFrom().item(() -> {
            if (isTaskValid(task)) {
                Task savedTask = entityManager.merge(task);
                LOG.debugf("Added or updated task with id %d", savedTask.id);
                return savedTask;
            } else {
                throw new IllegalArgumentException("Cannot add task: all fields must be filled");
            }
        });
    }

    private boolean isTaskValid(Task task) {
        return task != null &&
                task.getTaskName() != null && !task.getTaskName().isEmpty() &&
                task.getSequence() != 0 &&
                task.getDescription() != null && !task.getDescription().isEmpty() &&
                task.getDeliveryTime() != null;
    }

    @Override
    @Transactional
    public Task update(Task task) {
        Task existingTask = Task.findById(task.id);
        if (existingTask == null) {
            throw new NotFoundException("Task with id " + task.id + " not found");
        }
        existingTask.setTaskName(task.getTaskName());
        existingTask.setSequence(task.getSequence());
        existingTask.setDescription(task.getDescription());
        existingTask.setDeliveryTime(task.getDeliveryTime());

        return existingTask;
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

    @Override
    public Optional<Task> findTaskById(Long id) {
        return Optional.ofNullable(Task.findById(id));
    }


}

