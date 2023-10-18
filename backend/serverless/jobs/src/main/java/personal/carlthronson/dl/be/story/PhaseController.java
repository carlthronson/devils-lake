package personal.carlthronson.dl.be.story;

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
public class PhaseController {
    @Autowired
    PhaseRepository repository;

    @RequestMapping(path = "/phase", method = RequestMethod.POST)
    public Phase save(@RequestBody Phase phase) {
        return repository.save(phase);
    }

    @RequestMapping(path = "/phase/{name}", method = RequestMethod.GET)
    public Phase findOne(@PathVariable String name) {
        Phase phase = new Phase();
        phase.setName(name);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("name",
                ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Phase> example = Example.of(phase, matcher);
        Optional<Phase> optional = repository.findOne(example);
        if (optional.isPresent()) {
            return optional.get();
        }
        return new Phase();
    }

    @RequestMapping(path = "/phase", method = RequestMethod.GET)
    public List<Phase> findAll(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            List<Phase> list = new ArrayList<>();
            Phase phase = new Phase();
            phase.setName("exception");
            OutputStream out = new ByteArrayOutputStream();
            PrintWriter writer = new PrintWriter(out);
            for (StackTraceElement element : ex.getStackTrace()) {
                writer.println(element.toString());
            }
            writer.flush();
            phase.setLabel(out.toString());
            list.add(phase);
            return list;
        }
    }
}
