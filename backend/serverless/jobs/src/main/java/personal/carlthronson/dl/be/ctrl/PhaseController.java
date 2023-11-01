package personal.carlthronson.dl.be.ctrl;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import jakarta.transaction.Transactional;
import personal.carlthronson.dl.be.dto.Phase;
import personal.carlthronson.dl.be.entity.PhaseEntity;
import personal.carlthronson.dl.be.svc.PhaseService;

@RestController
@EnableWebMvc
@Transactional
public class PhaseController {

    @Autowired
    PhaseService service;
    Logger logger = Logger.getLogger(PhaseController.class.getName());

    @RequestMapping(path = "/phase/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    @RequestMapping(path = "/phase", method = RequestMethod.POST)
    public PhaseEntity save(@RequestBody Phase phase) {
        logger.info("Request body: " + phase);
        PhaseEntity entity = new PhaseEntity();
        entity.setId(phase.getId());
        entity.setName(phase.getName());
        entity.setLabel(phase.getLabel());
        return service.save(entity);
    }

    @RequestMapping(path = "/phase/getbyid/{id}", method = RequestMethod.GET)
    public Phase getById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        PhaseEntity phaseEntity = service.getById(id);
        Phase phase = new Phase(phaseEntity);
        return phase;
    }

    @RequestMapping(path = "/phase/findbyid/{id}", method = RequestMethod.GET)
    public PhaseEntity findById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        return service.findById(id);
    }

    @RequestMapping(path = "/phase/findallbyid/{id}", method = RequestMethod.GET)
    public List<PhaseEntity> findAllById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        return service.findAllById(id);
    }

    @RequestMapping(path = "/phase/findbyname/{name}", method = RequestMethod.GET)
    public PhaseEntity findByName(@PathVariable("name") String name) {
        logger.info("Path variable: " + name);
        return service.findByName(name);
    }

    @RequestMapping(path = "/phase/findallbyname/{name}", method = RequestMethod.GET)
    public List<PhaseEntity> findAllByName(@PathVariable("name") String name) {
        logger.info("Path variable: " + name);
        return service.findAllByName(name);
    }

    @RequestMapping(path = "/phase/findbylabel/{label}", method = RequestMethod.GET)
    public PhaseEntity findByLabel(@PathVariable("label") String label) {
        logger.info("Path variable: " + label);
        return service.findByLabel(label);
    }

    @RequestMapping(path = "/phase/findallbylabel/{label}", method = RequestMethod.GET)
    public List<PhaseEntity> findAllByLabel(@PathVariable("label") String label) {
        logger.info("Path variable: " + label);
        return service.findAllByLabel(label);
    }

    @RequestMapping(path = "/phase/findall", method = RequestMethod.GET)
    public List<PhaseEntity> findAll(
            @RequestParam("limit") Optional<Integer> limit,
            Principal principal) {
        logger.info("Request param: " + limit);
        return service.findAll(limit).getContent();
    }
}
