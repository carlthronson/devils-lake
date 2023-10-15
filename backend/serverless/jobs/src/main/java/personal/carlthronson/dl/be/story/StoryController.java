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
public class StoryController {
    @Autowired
    StoryRepository repository;

    @RequestMapping(path = "/story", method = RequestMethod.POST)
    public Story save(@RequestBody Story story) {
        return repository.save(story);
    }

    @RequestMapping(path = "/story/{id}", method = RequestMethod.GET)
    public Story findOne(@PathVariable Integer id) {
        Story story = new Story();
        story.setId(id);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("id",
                ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Story> example = Example.of(story, matcher);
        return repository.findOne(example);
    }

    @RequestMapping(path = "/story", method = RequestMethod.GET)
    public List<Story> findAll(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            List<Story> list = new ArrayList<>();
            Story story = new Story();
            story.setName("exception");
            OutputStream out = new ByteArrayOutputStream();
            PrintWriter writer = new PrintWriter(out);
            for (StackTraceElement element : ex.getStackTrace()) {
                writer.println(element.toString());
            }
            writer.flush();
            story.setLabel(out.toString());
            list.add(story);
            return list;
        }
    }

    // TODO better query
    @RequestMapping(path = "/story/phase/{phaseName}", method = RequestMethod.GET)
    public List<Story> findByExample(@PathVariable String phaseName,
            @RequestParam("limit") Optional<Integer> limit,
            Principal principal) {
        try {
            List<Story> list = repository.findAll();
            List<Story> result = new ArrayList<>();
            for (Story story: list) {
                if (story.getPhase().getName().compareTo(phaseName) == 0) {
                    result.add(story);
                }
            }
            return result;
        } catch (Exception ex) {
            List<Story> list = new ArrayList<>();
            Story story = new Story();
            story.setName("exception");
            OutputStream out = new ByteArrayOutputStream();
            PrintWriter writer = new PrintWriter(out);
            for (StackTraceElement element : ex.getStackTrace()) {
                writer.println(element.toString());
            }
            writer.flush();
            story.setLabel(out.toString());
            list.add(story);
            return list;
        }
    }
}
