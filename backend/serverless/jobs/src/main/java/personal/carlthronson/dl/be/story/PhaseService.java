package personal.carlthronson.dl.be.story;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhaseService {

    @Autowired
    private PhaseRepository repository;

    public Phase create(Phase phase) {
        return repository.save(phase);
    }

    public List<Phase> list() {
        return repository.findAll();
    }

    public Phase findByName(String phaseName) {
        Phase phase = new Phase();
        phase.setName(phaseName);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("name",
                ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Phase> example = Example.of(phase, matcher);
        return repository.findOne(example);
    }

}
