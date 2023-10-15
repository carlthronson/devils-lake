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
public class StatusController {
    @Autowired
    StatusRepository repository;

    @RequestMapping(path = "/status", method = RequestMethod.POST)
    public Status save(@RequestBody Status status) {
        return repository.save(status);
    }

    @RequestMapping(path = "/status/{name}", method = RequestMethod.GET)
    public Status findOne(@PathVariable String name) {
        Status status = new Status();
        status.setName(name);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("name",
                ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Status> example = Example.of(status, matcher);
        return repository.findOne(example);
    }

    @RequestMapping(path = "/status", method = RequestMethod.GET)
    public List<Status> findAll(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            List<Status> list = new ArrayList<>();
            Status status = new Status();
            status.setName("exception");
            OutputStream out = new ByteArrayOutputStream();
            PrintWriter writer = new PrintWriter(out);
            for (StackTraceElement element : ex.getStackTrace()) {
                writer.println(element.toString());
            }
            writer.flush();
            status.setLabel(out.toString());
            list.add(status);
            return list;
        }
    }
}
