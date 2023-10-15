package personal.carlthronson.dl.be.task;

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

import personal.carlthronson.dl.be.story.Story;
import personal.carlthronson.dl.be.story.StoryService;

@RestController
@EnableWebMvc
@Transactional
public class TaskController {
    @Autowired
    TaskService service;

    @Autowired
    StoryService storyService;

    @RequestMapping(path = "/task", method = RequestMethod.POST)
    public Task create(@RequestBody Task task) {
        return service.create(task);
    }

    @RequestMapping(path = "/task", method = RequestMethod.GET)
    public List<Task> list(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        try {
            return service.list();
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

    @RequestMapping(path = "/task/list/{storyId}", method = RequestMethod.GET)
    public List<Task> list(@PathVariable Integer storyId,
            @RequestParam("limit") Optional<Integer> limit,
            Principal principal) {
        try {
            Story story = storyService.findById(storyId);
            return service.list(story);
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
