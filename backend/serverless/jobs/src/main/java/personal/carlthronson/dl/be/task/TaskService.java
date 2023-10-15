package personal.carlthronson.dl.be.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.carlthronson.dl.be.story.Story;

@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public Task create(Task task) {
        return repository.save(task);
    }

    public List<Task> list() {
        return repository.findAll();
    }

    public List<Task> list(Story story) {
        Task task = new Task();
        task.setStory(story);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("status",
                ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Task> example = Example.of(task, matcher);
        return repository.findAll(example);
    }
}
