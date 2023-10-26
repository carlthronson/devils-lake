package personal.carlthronson.dl.be.task;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllByStatusIn(List<Status> statuses, Pageable pageable);

    long countAllByStatusIn(List<Status> statuses);

    List<Task> findAllByStatusIn(List<Status> statuses);

    List<Task> findAllByJob(Long id);
}
