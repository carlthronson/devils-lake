package personal.carlthronson.dl.be.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskService {

    @Autowired
    TaskRepository repository;

    public Task save(Task task) {
        return repository.save(task);
    }

    public Task getById(Long id) {
        return repository.getById(id);
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public List<Task> findAllByJob(Long jobId) {
        return repository.findAllByJob(jobId);
    }

}
