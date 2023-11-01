package personal.carlthronson.dl.be.job;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

    Job getById(Long id);

    Job getByLinkedinid(Long linkedinid);

    boolean existsByLinkedinid(long linkedinid);

    List<Job> findAllByTitle(String title);
}
