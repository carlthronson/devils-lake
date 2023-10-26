package personal.carlthronson.dl.be.job;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    JobService service;

    @RequestMapping(path = "/job", method = RequestMethod.POST)
    public Job save(@RequestBody Job job) {
        return service.save(job);
    }

    @RequestMapping(path = "/job/{id}", method = RequestMethod.GET)
    public Job getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @RequestMapping(path = "/job", method = RequestMethod.GET)
    public List<Job> findAll(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        return service.findAll();
    }

    @RequestMapping(path = "/job/title/{title}", method = RequestMethod.GET)
    public List<Job> findAllByTitle(@PathVariable("title") String title,
            @RequestParam("limit") Optional<Integer> limit, Principal principal) {
        return service.findAllByTitle(title);
    }

}
