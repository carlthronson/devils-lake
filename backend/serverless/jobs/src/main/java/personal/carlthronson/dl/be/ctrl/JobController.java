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

import personal.carlthronson.dl.be.dto.Job;
import personal.carlthronson.dl.be.entity.JobEntity;
import personal.carlthronson.dl.be.svc.JobService;

@RestController
@EnableWebMvc
@Transactional
public class JobController {

    @Autowired
    JobService service;
    Logger logger = Logger.getLogger(PhaseController.class.getName());

    @RequestMapping(path = "/job/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    @RequestMapping(path = "/job", method = RequestMethod.POST)
    public JobEntity save(@RequestBody Job job) {
        logger.info("Request body: " + job);
        JobEntity jobEntity = new JobEntity();
        jobEntity.setId(job.getId());
        jobEntity.setName(job.getName());
        jobEntity.setLabel(job.getLabel());
        jobEntity.setLinkedinid(job.getLinkedinid());
        jobEntity.setLinkedinurl(job.getLinkedinurl());
        jobEntity.setPublishedAt(job.getPublishedAt());
        jobEntity.setSalary(job.getSalary());
        return service.save(jobEntity);
    }

    @RequestMapping(path = "/job/getbyid/{id}", method = RequestMethod.GET)
    public Job getById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        JobEntity jobEntity = service.getById(id);
        Job job = new Job(jobEntity);
        return job;
    }

    @RequestMapping(path = "/job/findbyid/{id}", method = RequestMethod.GET)
    public JobEntity findById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        return service.findById(id);
    }

    @RequestMapping(path = "/job/findallbyid/{id}", method = RequestMethod.GET)
    public List<JobEntity> findAllById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        return service.findAllById(id);
    }

    @RequestMapping(path = "/job/findbyname/{name}", method = RequestMethod.GET)
    public JobEntity findByName(@PathVariable("name") String name) {
        logger.info("Path variable: " + name);
        return service.findByName(name);
    }

    @RequestMapping(path = "/job/findallbyname/{name}", method = RequestMethod.GET)
    public List<JobEntity> findAllByName(@PathVariable("name") String name) {
        logger.info("Path variable: " + name);
        return service.findAllByName(name);
    }

    @RequestMapping(path = "/job/findbylabel/{label}", method = RequestMethod.GET)
    public JobEntity findByLabel(@PathVariable("label") String label) {
        logger.info("Path variable: " + label);
        return service.findByLabel(label);
    }

    @RequestMapping(path = "/job/findallbylabel/{label}", method = RequestMethod.GET)
    public List<JobEntity> findAllByLabel(@PathVariable("label") String label) {
        logger.info("Path variable: " + label);
        return service.findAllByLabel(label);
    }

    @RequestMapping(path = "/job/findall", method = RequestMethod.GET)
    public List<JobEntity> findAll(
            @RequestParam("limit") Optional<Integer> limit,
            Principal principal) {
        logger.info("Request param: " + limit);
        return service.findAll(limit).getContent();
    }
}
