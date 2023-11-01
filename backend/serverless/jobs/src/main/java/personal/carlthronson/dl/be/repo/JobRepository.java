package personal.carlthronson.dl.be.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import personal.carlthronson.dl.be.entity.JobEntity;

@Repository
@Transactional
public interface JobRepository
        extends JpaRepository<JobEntity, Long>, SimpleRepository<JobEntity> {

    JobEntity getByLinkedinid(Long linkedinid);

    boolean existsByLinkedinid(long linkedinid);
}
