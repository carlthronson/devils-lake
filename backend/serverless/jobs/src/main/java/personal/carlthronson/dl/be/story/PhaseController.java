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
    PhaseService service;

    @RequestMapping(path = "/phase", method = RequestMethod.POST)
    public Phase create(@RequestBody Phase phase) {
        return service.create(phase);
    }

    @RequestMapping(path = "/phase", method = RequestMethod.GET)
    public List<Phase> list(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        try {
            return service.list();
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
