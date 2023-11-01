package personal.carlthronson.dl.be.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import personal.carlthronson.dl.be.entity.JobEntity;
import personal.carlthronson.dl.be.repo.JobRepository;
import personal.carlthronson.dl.be.repo.SimpleRepository;

@Service
@Transactional
public class JobService extends SimpleService<JobEntity> {

    @Autowired
    JobRepository repository;

    @Override
    public SimpleRepository<JobEntity> getSimpleRepository() {
        return this.repository;
    }

    @Override
    public JpaRepository<JobEntity, Long> getJpaRepository() {
        return this.repository;
    }
}
