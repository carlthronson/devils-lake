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

import com.fasterxml.jackson.databind.ObjectMapper;

import personal.carlthronson.dl.be.dto.Status;
import personal.carlthronson.dl.be.entity.PhaseEntity;
import personal.carlthronson.dl.be.entity.StatusEntity;
import personal.carlthronson.dl.be.svc.StatusService;

@RestController
@EnableWebMvc
@Transactional
public class StatusController {

    @Autowired
    StatusService service;
    Logger logger = Logger.getLogger(StatusController.class.getName());
    ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    @RequestMapping(path = "/status/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    @RequestMapping(path = "/status", method = RequestMethod.POST)
    public StatusEntity save(@RequestBody Status requestBody) {
        logger.info("Request body: " + requestBody);
        StatusEntity statusEntity1 = new StatusEntity();
        statusEntity1.setId(requestBody.getId());
        statusEntity1.setName(requestBody.getName());
        statusEntity1.setLabel(requestBody.getLabel());
        PhaseEntity phase = new PhaseEntity();
        phase.setId(requestBody.getPhase().getId());
        statusEntity1.setPhase(phase);
        StatusEntity statusEntity = statusEntity1;
        statusEntity = service.save(statusEntity);
        return statusEntity;
    }

    @RequestMapping(path = "/status/getbyid/{id}", method = RequestMethod.GET)
    public Status getById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        StatusEntity statusEntity = service.getById(id);
        Status status = new Status(statusEntity);
        return status;
    }

    @RequestMapping(path = "/status/findbyid/{id}", method = RequestMethod.GET)
    public StatusEntity findById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        return service.findById(id);
    }

    @RequestMapping(path = "/status/findallbyid/{id}", method = RequestMethod.GET)
    public List<StatusEntity> findAllById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        return service.findAllById(id);
    }

    @RequestMapping(path = "/status/findbyname/{name}", method = RequestMethod.GET)
    public StatusEntity findByName(@PathVariable("name") String name) {
        logger.info("Path variable: " + name);
        return service.findByName(name);
    }

    @RequestMapping(path = "/status/findallbyname/{name}", method = RequestMethod.GET)
    public List<StatusEntity> findAllByName(@PathVariable("name") String name) {
        logger.info("Path variable: " + name);
        return service.findAllByName(name);
    }

    @RequestMapping(path = "/status/findbylabel/{label}", method = RequestMethod.GET)
    public StatusEntity findByLabel(@PathVariable("label") String label) {
        logger.info("Path variable: " + label);
        return service.findByLabel(label);
    }

    @RequestMapping(path = "/status/findallbylabel/{label}", method = RequestMethod.GET)
    public List<StatusEntity> findAllByLabel(
            @PathVariable("label") String label) {
        logger.info("Path variable: " + label);
        return service.findAllByLabel(label);
    }

    @RequestMapping(path = "/status/findall", method = RequestMethod.GET)
    public List<StatusEntity> findAll(
            @RequestParam("limit") Optional<Integer> limit,
            Principal principal) {
        logger.info("Request param limit: " + limit);
        return service.findAll(limit).getContent();
    }
}
