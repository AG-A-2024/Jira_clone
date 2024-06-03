package pbs.ap.tasks;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;

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


    @Override
    @Transactional
    public Task addTask(Task task) {
        Task savedTask = entityManager.merge(task);
        LOG.debugf("Added or updated task with id %d", savedTask.id);
        return savedTask;
    }

    @Override
    @Transactional
    public Task update(Long id, Task taskToUpdate) {
        Task existingTask = Task.findById(id);
        if (existingTask == null) {
            throw new NotFoundException("Task with id " + id + " not found");
        }
        
        existingTask.setTaskName(taskToUpdate.getTaskName());
        existingTask.setSequence(taskToUpdate.getSequence());
        existingTask.setDescription(taskToUpdate.getDescription());
        existingTask.setDeliveryTime(taskToUpdate.getDeliveryTime());

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




