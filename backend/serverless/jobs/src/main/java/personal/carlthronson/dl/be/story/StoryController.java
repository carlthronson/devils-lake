package personal.carlthronson.dl.be.story;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Story save(@RequestBody Story story) {
        return service.save(story);
    }

    @RequestMapping(path = "/story/{id}", method = RequestMethod.GET)
    public Story getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @RequestMapping(path = "/story", method = RequestMethod.GET)
    public Page<Story> findAll(@RequestParam("pageNumber") Optional<Integer> pageNumber,
            @RequestParam("pageSize") Optional<Integer> pageSize, Principal principal) {

        Pageable pageable = PageRequest.of(pageNumber.isPresent() ? pageNumber.get() : 1,
                pageSize.isPresent() ? pageSize.get() : 10);

        return service.findAll(pageable);
    }

    @RequestMapping(path = "/story/phase/{phaseName}", method = RequestMethod.GET)
    public List<Story> findByPhase(@PathVariable("phaseName") String phaseName,
            @RequestParam("pageNumber") Optional<Integer> pageNumber,
            @RequestParam("pageSize") Optional<Integer> pageSize, Principal principal) {

        System.out.println("Entering story controller endpoint /story/phase/{phaseName}");

        Pageable pageable = PageRequest.of(pageNumber.isPresent() ? pageNumber.get() : 1,
                pageSize.isPresent() ? pageSize.get() : 10);

        return service.findByPhase(phaseName, pageable);
    }
}
