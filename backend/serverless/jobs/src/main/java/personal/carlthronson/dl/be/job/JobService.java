package personal.carlthronson.dl.be.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobService {

    @Autowired
    JobRepository repository;

    public Job save(Job job) {
        if (job.getId() == 0 && repository.existsByLinkedinid(job.getLinkedinid())) {
            Job existingJob = repository.getByLinkedinid(job.getLinkedinid());
            job.setId(existingJob.getId());
        }
        repository.save(job);
        return job;
    }

    public Job getById(Long id) {
        return repository.getById(id);
    }

    public List<Job> findAll() {
        return repository.findAll();
    }

    public List<Job> findAllByTitle(String title) {
        return repository.findAllByTitle(title);
    }
}
