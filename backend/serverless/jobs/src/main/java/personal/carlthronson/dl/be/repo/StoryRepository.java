package personal.carlthronson.dl.be.story;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import personal.carlthronson.dl.be.task.Task;

@Repository
@Transactional
public interface StoryRepository extends JpaRepository<Story, Long> {

    Story getById(Long id);

    Story getByName(String name);

    List<Story> findAllByTasksIn(List<Task> list);

    boolean existsByName(String name);
}
