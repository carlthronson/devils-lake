package personal.carlthronson.dl.be.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import personal.carlthronson.dl.be.entity.JobEntity;
import personal.carlthronson.dl.be.entity.TaskEntity;
import personal.carlthronson.dl.be.entity.TitleEntity;
import personal.carlthronson.dl.be.repo.SimpleRepository;
import personal.carlthronson.dl.be.repo.TitleRepository;

@Service
@Transactional
public class TitleService extends SimpleService<TitleEntity> {

    @Autowired
    TitleRepository repository;

    @Override
    public SimpleRepository<TitleEntity> getSimpleRepository() {
        return this.repository;
    }

    @Override
    public JpaRepository<TitleEntity, Long> getJpaRepository() {
        return this.repository;
    }

    // ****** Custom methods ***********

    @Autowired
    JobService jobService;

    @Autowired
    TaskService taskService;

    public TitleEntity save(TitleEntity title) {
        System.out.println("Title: " + title);
//        TitleEntity entity = new TitleEntity(title);
        TitleEntity result = repository.save(title);
        System.out.println("Saved title: " + result);
        List<JobEntity> jobs = jobService.findAllByName(result.getName());
        for (JobEntity job : jobs) {
            System.out.println("Job: " + job);
            List<TaskEntity> tasks = taskService.findAllByJob(job.getId());
            for (TaskEntity task : tasks) {
                System.out.println("Task: " + task);
//                task.setStatus(title.getStatus());
            }
        }
        return result;
    }
}
