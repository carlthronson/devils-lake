package personal.carlthronson.dl.be.story;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
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
public class StoryController {
    @Autowired
    StoryService service;

    @RequestMapping(path = "/story", method = RequestMethod.POST)
    public Story create(@RequestBody Story story) {
        return service.create(story);
    }

    @RequestMapping(path = "/story", method = RequestMethod.GET)
    public List<Story> list(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        try {
            return service.list();
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

    @RequestMapping(path = "/story/list/{phase}", method = RequestMethod.GET)
    public List<Story> list(@PathVariable String phase,
            @RequestParam("limit") Optional<Integer> limit,
            Principal principal) {
        try {
            return service.list(phase);
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
