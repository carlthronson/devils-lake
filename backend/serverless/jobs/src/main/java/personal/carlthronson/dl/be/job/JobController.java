package personal.carlthronson.dl.be.job;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
@Transactional
public class JobController {
    @Autowired
    JobRepository repository;

    @RequestMapping(path = "/job", method = RequestMethod.POST)
    public Job save(@RequestBody Job job) {
        return repository.save(job);
    }

    @RequestMapping(path = "/job/{id}", method = RequestMethod.GET)
    public Job findOne(@PathVariable Integer id) {
        Job job = new Job();
        job.setId(id);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("id",
                ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Job> example = Example.of(job, matcher);
        Optional<Job> optional = repository.findOne(example);
        if (optional.isPresent()) {
            return optional.get();
        }
        return new Job();
    }

    @RequestMapping(path = "/job", method = RequestMethod.GET)
    public List<Job> findAll(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        return repository.findAll();
    }
}
