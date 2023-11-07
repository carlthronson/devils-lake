package personal.carlthronson.dl.be.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import personal.carlthronson.dl.be.entity.JobEntity;
import personal.carlthronson.dl.be.entity.StatusEntity;
import personal.carlthronson.dl.be.entity.TaskEntity;

@Repository
@Transactional
public interface TaskRepository
        extends JpaRepository<TaskEntity, Long>, SimpleRepository<TaskEntity> {

    Page<TaskEntity> findAllByStatusIn(List<StatusEntity> statuses,
            Pageable pageable);

    long countAllByStatusIn(List<StatusEntity> statuses);

    List<TaskEntity> findAllByStatusIn(List<StatusEntity> statuses);

    List<TaskEntity> findAllByJob(JobEntity job);

    TaskEntity findByJob(JobEntity job);

    boolean existsByJob(JobEntity job);

    TaskEntity getByJob(JobEntity jobEntity);

    List<TaskEntity> findAllByStatus(StatusEntity status);
}
