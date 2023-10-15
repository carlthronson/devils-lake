package personal.carlthronson.dl.be.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatusService {

    @Autowired
    private StatusRepository repository;

    public Status create(Status status) {
        return repository.save(status);
    }

    public List<Status> list() {
        return repository.findAll();
    }

    public Status findByName(String statusName) {
        Status status = new Status();
        status.setName(statusName);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("name",
                ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Status> example = Example.of(status, matcher);
        return repository.findOne(example);
    }

}
