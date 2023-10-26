package personal.carlthronson.dl.be.task;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
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
public class TaskController {
    @Autowired
    TaskRepository repository;

    @RequestMapping(path = "/task", method = RequestMethod.POST)
    public Task save(@RequestBody Task task) {
        return repository.save(task);
    }

    @RequestMapping(path = "/task/{id}", method = RequestMethod.GET)
    public Task findOne(@PathVariable Integer id) {
        Task task = new Task();
        task.setId(id);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("id",
                ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Task> example = Example.of(task, matcher);
        Optional<Task> optional = repository.findOne(example);
        if (optional.isPresent()) {
            return optional.get();
        }
        return new Task();
    }

    @RequestMapping(path = "/task", method = RequestMethod.GET)
    public List<Task> findAll(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            List<Task> list = new ArrayList<>();
            Task task = new Task();
            task.setName("exception");
            OutputStream out = new ByteArrayOutputStream();
            PrintWriter writer = new PrintWriter(out);
            for (StackTraceElement element : ex.getStackTrace()) {
                writer.println(element.toString());
            }
            writer.flush();
            task.setLabel(out.toString());
            list.add(task);
            return list;
        }
    }
}
