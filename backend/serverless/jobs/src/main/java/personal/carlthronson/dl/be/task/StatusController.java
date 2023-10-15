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
    StatusService service;

    @RequestMapping(path = "/status", method = RequestMethod.POST)
    public Status create(@RequestBody Status status) {
        return service.create(status);
    }

    @RequestMapping(path = "/status", method = RequestMethod.GET)
    public List<Status> list(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        try {
            return service.list();
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
