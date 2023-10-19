package personal.carlthronson.dl.be.story;

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
public class PhaseController {
    @Autowired
    PhaseService service;

    @RequestMapping(path = "/phase", method = RequestMethod.POST)
    public Phase save(@RequestBody Phase phase) {
        return service.save(phase);
    }

    @RequestMapping(path = "/phase/{name}", method = RequestMethod.GET)
    public Phase getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @RequestMapping(path = "/phase", method = RequestMethod.GET)
    public List<Phase> findAll(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        return service.findAll();
    }
}
