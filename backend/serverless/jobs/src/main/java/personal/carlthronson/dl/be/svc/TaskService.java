package personal.carlthronson.dl.be.svc;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import personal.carlthronson.dl.be.entity.JobEntity;
import personal.carlthronson.dl.be.entity.StatusEntity;
import personal.carlthronson.dl.be.entity.StoryEntity;
import personal.carlthronson.dl.be.entity.TaskEntity;
import personal.carlthronson.dl.be.repo.SimpleRepository;
import personal.carlthronson.dl.be.repo.StatusRepository;
import personal.carlthronson.dl.be.repo.StoryRepository;
import personal.carlthronson.dl.be.repo.TaskRepository;

@Service
@Transactional
public class TaskService extends SimpleService<TaskEntity> {

    @Autowired
    TaskRepository repository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    StoryRepository storyRepository;

    @Override
    public SimpleRepository<TaskEntity> getSimpleRepository() {
        return this.repository;
    }

    @Override
    public JpaRepository<TaskEntity, Long> getJpaRepository() {
        return this.repository;
    }

    // ****** Custom methods ***********

    public List<TaskEntity> findAllByJob(Long id) {
        JobEntity job = new JobEntity();
        job.setId(id);
        List<TaskEntity> list = this.repository.findAllByJob(job);
        return list;
    }

    public Optional<TaskEntity> update(Long taskId, Long statusId) {
        List<TaskEntity> tasks = this.repository.findAllById(taskId);
        List<StoryEntity> stories = this.storyRepository
                .findAllByTasksIn(tasks);
        Predicate<? super TaskEntity> predicate = new Predicate<TaskEntity>() {

            @Override
            public boolean test(TaskEntity t) {
                return t.getId().compareTo(taskId) == 0;
            }
        };

        for (StoryEntity story : stories) {
            Optional<TaskEntity> optionalTaskEntity = story.getTasks().stream()
                    .filter(predicate).findFirst();
            if (optionalTaskEntity.isPresent()) {
                StatusEntity statusEntity = this.statusRepository
                        .getById(statusId);
                TaskEntity taskEntity = optionalTaskEntity.get();
                taskEntity.setStatus(statusEntity);
                taskEntity = save(taskEntity);
                return Optional.of(taskEntity);
            }
        }
        return Optional.empty();
    }

    @Override
    public TaskEntity save(TaskEntity entity) {
        JobEntity jobEntity = entity.getJob();
        if (jobEntity != null) {
            if (this.repository.existsByJob(jobEntity)) {
                TaskEntity existingEntity = this.repository.getByJob(jobEntity);
                entity.setId(existingEntity.getId());
            }
        }
        return this.repository.save(entity);
    }

    public List<TaskEntity> findAllByStatus(String statusName) {
        StatusEntity status = this.statusRepository.findByName(statusName);
        List<TaskEntity> list = this.repository.findAllByStatus(status);
        return list;
    }

    public Long count() {
        return this.repository.count();
    }
}
