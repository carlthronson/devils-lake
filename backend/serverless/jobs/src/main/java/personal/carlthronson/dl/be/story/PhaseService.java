package personal.carlthronson.dl.be.story;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PhaseService {
    @Autowired
    PhaseRepository repository;

    public Phase save(Phase phase) {
        return repository.save(phase);
    }

//    public Phase findOne(String name) {
//        Phase probe = new Phase();
//        probe.setName(name);
//        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("name",
//                ExampleMatcher.GenericPropertyMatchers.exact());
//        Example<Phase> example = Example.of(probe, matcher);
//        Optional<Phase> optional = repository.findOne(example);
//        if (optional.isPresent()) {
//            return optional.get();
//        }
//        return new Phase();
//    }

    public List<Phase> findAll() {
        return repository.findAll();
    }

    public Phase findOne(Phase probe) {
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("name",
                ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Phase> example = Example.of(probe, matcher);
        Optional<Phase> optionalPhase = repository.findOne(example);
        if (optionalPhase.isPresent()) {
            return optionalPhase.get();
        }
        return probe;
    }

    public Phase getByName(String name) {
        return repository.getByName(name);
    }
}
