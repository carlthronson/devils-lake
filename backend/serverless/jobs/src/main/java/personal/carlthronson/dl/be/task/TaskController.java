package personal.carlthronson.dl.be.task;

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
public class TaskController {
    @Autowired
    TaskService service;

    @RequestMapping(path = "/task", method = RequestMethod.POST)
    public Task save(@RequestBody Task task) {
        return service.save(task);
    }

    @RequestMapping(path = "/task/{id}", method = RequestMethod.GET)
    public Task findOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @RequestMapping(path = "/task", method = RequestMethod.GET)
    public List<Task> findAll(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        return service.findAll();
    }

    @RequestMapping(path = "/task/job/{jobId}", method = RequestMethod.GET)
    public List<Task> findAll(@PathVariable("jobId") Long jobId,
            @RequestParam("limit") Optional<Integer> limit, Principal principal) {
        return service.findAllByJob(jobId);
    }
}
