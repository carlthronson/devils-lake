package personal.carlthronson.dl.be.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import personal.carlthronson.dl.be.task.Task;
import personal.carlthronson.dl.be.task.TaskService;

public class TitleService {

    @Autowired
    TitleRepository repository;

    @Autowired
    JobService jobService;
    
    @Autowired
    TaskService taskService;

    public Title save(Title title) {
        Title savedTitle = repository.save(title);
        List<Job> jobs = jobService.findAllByTitle(title.name);
        for (Job job: jobs) {
            List<Task> tasks = taskService.findAllByJob(job.getId());
            for (Task task: tasks) {
                task.setStatus(savedTitle.getStatus());
            }
        }
        return savedTitle;
    }

    public Title getById(Long id) {
        return repository.getById(id);
    }

    public List<Title> findAll() {
        return repository.findAll();
    }

}
