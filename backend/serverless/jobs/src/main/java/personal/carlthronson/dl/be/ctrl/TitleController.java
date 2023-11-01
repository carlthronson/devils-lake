package personal.carlthronson.dl.be.ctrl;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import personal.carlthronson.dl.be.dto.Title;
import personal.carlthronson.dl.be.entity.StatusEntity;
import personal.carlthronson.dl.be.entity.TitleEntity;
import personal.carlthronson.dl.be.svc.TitleService;

@RestController
@EnableWebMvc
@Transactional
public class TitleController {

    @Autowired
    TitleService service;
    Logger logger = Logger.getLogger(TitleController.class.getName());

    @RequestMapping(path = "/title/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    @RequestMapping(path = "/title", method = RequestMethod.POST, consumes = "application/json")
    public TitleEntity save(@RequestBody Title title) {
        logger.info("Request body: " + title);
        TitleEntity titleEntity = new TitleEntity();
        titleEntity.setId(title.getId());
        titleEntity.setName(title.getName());
        titleEntity.setLabel(title.getLabel());
        StatusEntity statusEntity = new StatusEntity();
        statusEntity.setId(title.getStatus().getId());
        titleEntity.setStatus(statusEntity);
        return service.save(titleEntity);
    }

    @RequestMapping(path = "/title/getbyid/{id}", method = RequestMethod.GET)
    public Title getById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        TitleEntity titleEntity = service.getById(id);
        Title title = new Title(titleEntity);
        return title;
    }

    @RequestMapping(path = "/title/findbyid/{id}", method = RequestMethod.GET)
    public TitleEntity findById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        return service.findById(id);
    }

    @RequestMapping(path = "/title/findallbyid/{id}", method = RequestMethod.GET)
    public List<TitleEntity> findAllById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        return service.findAllById(id);
    }

    @RequestMapping(path = "/title/findbyname/{name}", method = RequestMethod.GET)
    public TitleEntity findByName(@PathVariable("name") String name) {
        logger.info("Path variable: " + name);
        return service.findByName(name);
    }

    @RequestMapping(path = "/title/findallbyname/{name}", method = RequestMethod.GET)
    public List<TitleEntity> findAllByName(@PathVariable("name") String name) {
        logger.info("Path variable: " + name);
        return service.findAllByName(name);
    }

    @RequestMapping(path = "/title/findbylabel/{label}", method = RequestMethod.GET)
    public TitleEntity findByLabel(@PathVariable("label") String label) {
        logger.info("Path variable: " + label);
        return service.findByLabel(label);
    }

    @RequestMapping(path = "/title/findallbylabel/{label}", method = RequestMethod.GET)
    public List<TitleEntity> findAllByLabel(@PathVariable("label") String label) {
        logger.info("Path variable: " + label);
        return service.findAllByLabel(label);
    }

    @RequestMapping(path = "/title/findall", method = RequestMethod.GET)
    public List<TitleEntity> findAll(
            @RequestParam("limit") Optional<Integer> limit,
            Principal principal) {
        logger.info("Request param: " + limit);
        return service.findAll();
    }
}
