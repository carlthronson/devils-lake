package personal.carlthronson.dl.be.job;

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
public class TitleController {
    @Autowired
    TitleService service;

    @RequestMapping(path = "/title", method = RequestMethod.POST)
    public Title save(@RequestBody Title title) {
        return service.save(title);
    }

    @RequestMapping(path = "/title/{id}", method = RequestMethod.GET)
    public Title getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @RequestMapping(path = "/title", method = RequestMethod.GET)
    public List<Title> findAll(@RequestParam("limit") Optional<Integer> limit, Principal principal) {
        return service.findAll();
    }

}
