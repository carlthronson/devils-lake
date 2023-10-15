package personal.carlthronson.dl.be.story;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StoryService {

    @Autowired
    private StoryRepository repository;

    public Story create(Story story) {
        return repository.save(story);
    }

    public List<Story> list() {
        return repository.findAll();
    }

    public List<Story> list(Phase phase) {
        Story story = new Story();
        story.setPhase(phase);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("phase",
                ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Story> example = Example.of(story, matcher);
        return repository.findAll(example);
    }
}
