package personal.carlthronson.dl.be.story;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
