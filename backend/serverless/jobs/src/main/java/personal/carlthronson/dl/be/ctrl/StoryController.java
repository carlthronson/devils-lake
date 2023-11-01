package personal.carlthronson.dl.be.ctrl;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

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

import personal.carlthronson.dl.be.dto.Story;
import personal.carlthronson.dl.be.entity.PhaseEntity;
import personal.carlthronson.dl.be.entity.StoryEntity;
import personal.carlthronson.dl.be.svc.StoryService;

@RestController
@EnableWebMvc
@Transactional
public class StoryController {

    @Autowired
    StoryService service;
    Logger logger = Logger.getLogger(StoryController.class.getName());

    @RequestMapping(path = "/story/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    @RequestMapping(path = "/story", method = RequestMethod.POST)
    public StoryEntity save(@RequestBody Story story) {
        logger.info("Request body: " + story);
        StoryEntity storyEntity = new StoryEntity();
        storyEntity.setId(story.getId());
        storyEntity.setName(story.getName());
        storyEntity.setLabel(story.getLabel());
        PhaseEntity phaseEntity = new PhaseEntity();
        phaseEntity.setId(story.getPhase().getId());
        storyEntity.setPhase(phaseEntity);
        return service.save(storyEntity);
    }

    @RequestMapping(path = "/story/getbyid/{id}", method = RequestMethod.GET)
    public Story getById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        StoryEntity storyEntity = service.getById(id);
        Story story = new Story(storyEntity);
        return story;
    }

    @RequestMapping(path = "/story/findbyid/{id}", method = RequestMethod.GET)
    public StoryEntity findById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        return service.findById(id);
    }

    @RequestMapping(path = "/story/findallbyid/{id}", method = RequestMethod.GET)
    public List<StoryEntity> findAllById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        return service.findAllById(id);
    }

    @RequestMapping(path = "/story/findbyname/{name}", method = RequestMethod.GET)
    public StoryEntity findByName(@PathVariable("name") String name) {
        logger.info("Path variable: " + name);
        return service.findByName(name);
    }

    @RequestMapping(path = "/story/findallbyname/{name}", method = RequestMethod.GET)
    public List<StoryEntity> findAllByName(@PathVariable("name") String name) {
        logger.info("Path variable: " + name);
        return service.findAllByName(name);
    }

    @RequestMapping(path = "/story/findbylabel/{label}", method = RequestMethod.GET)
    public StoryEntity findByLabel(@PathVariable("label") String label) {
        logger.info("Path variable: " + label);
        return service.findByLabel(label);
    }

    @RequestMapping(path = "/story/findallbylabel/{label}", method = RequestMethod.GET)
    public List<StoryEntity> findAllByLabel(@PathVariable("label") String label) {
        logger.info("Path variable: " + label);
        return service.findAllByLabel(label);
    }

    @RequestMapping(path = "/story/findall", method = RequestMethod.GET)
    public List<StoryEntity> findAll(
            @RequestParam("limit") Optional<Integer> limit,
            Principal principal) {
        logger.info("Request param: " + limit);
        return service.findAll();
    }

    // Custom methods

    @RequestMapping(path = "/story/page", method = RequestMethod.GET)
    public Page<StoryEntity> findPage(
            @RequestParam("pageNumber") Optional<Integer> pageNumber,
            @RequestParam("pageSize") Optional<Integer> pageSize,
            Principal principal) {
        logger.info("Request param pageNumber: " + pageNumber);
        logger.info("Request param pageSize: " + pageSize);

        Pageable pageable = PageRequest.of(
                pageNumber.isPresent() ? pageNumber.get() : 1,
                pageSize.isPresent() ? pageSize.get() : 10);

        return service.findAll(pageable);
    }

    @RequestMapping(path = "/story/phase/{phaseName}", method = RequestMethod.GET)
    public List<StoryEntity> findByPhase(
            @PathVariable("phaseName") String phaseName,
            @RequestParam("pageNumber") Optional<Integer> pageNumber,
            @RequestParam("pageSize") Optional<Integer> pageSize,
            Principal principal) {

        logger.info("Path variable phaseName: " + phaseName);
        logger.info("Request param pageNumber: " + pageNumber);
        logger.info("Request param pageSize: " + pageSize);

        Pageable pageable = PageRequest.of(
                pageNumber.isPresent() ? pageNumber.get() : 0,
                pageSize.isPresent() ? pageSize.get() : 1000);

        return service.findPageByPhase(phaseName, pageable);
    }
}
