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

import personal.carlthronson.dl.be.dto.Contact;
import personal.carlthronson.dl.be.entity.PhaseEntity;
import personal.carlthronson.dl.be.entity.ContactEntity;
import personal.carlthronson.dl.be.svc.ContactService;

@RestController
@EnableWebMvc
@Transactional
public class ContactController {

    @Autowired
    ContactService service;
    Logger logger = Logger.getLogger(ContactController.class.getName());
    ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    @RequestMapping(path = "/contact/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    @RequestMapping(path = "/contact", method = RequestMethod.POST)
    public ContactEntity save(@RequestBody Contact requestBody) {
        logger.info("Request body: " + requestBody);
        ContactEntity contactEntity1 = new ContactEntity();
        contactEntity1.setId(requestBody.getId());
        contactEntity1.setName(requestBody.getName());
        contactEntity1.setLabel(requestBody.getLabel());
        ContactEntity contactEntity = contactEntity1;
        contactEntity = service.save(contactEntity);
        return contactEntity;
    }

    @RequestMapping(path = "/contact/getbyid/{id}", method = RequestMethod.GET)
    public Contact getById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        ContactEntity contactEntity = service.getById(id);
        Contact contact = new Contact(contactEntity);
        return contact;
    }

    @RequestMapping(path = "/contact/findbyid/{id}", method = RequestMethod.GET)
    public ContactEntity findById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        return service.findById(id);
    }

    @RequestMapping(path = "/contact/findallbyid/{id}", method = RequestMethod.GET)
    public List<ContactEntity> findAllById(@PathVariable("id") Long id) {
        logger.info("Path variable: " + id);
        return service.findAllById(id);
    }

    @RequestMapping(path = "/contact/findbyname/{name}", method = RequestMethod.GET)
    public ContactEntity findByName(@PathVariable("name") String name) {
        logger.info("Path variable: " + name);
        return service.findByName(name);
    }

    @RequestMapping(path = "/contact/findallbyname/{name}", method = RequestMethod.GET)
    public List<ContactEntity> findAllByName(@PathVariable("name") String name) {
        logger.info("Path variable: " + name);
        return service.findAllByName(name);
    }

    @RequestMapping(path = "/contact/findbylabel/{label}", method = RequestMethod.GET)
    public ContactEntity findByLabel(@PathVariable("label") String label) {
        logger.info("Path variable: " + label);
        return service.findByLabel(label);
    }

    @RequestMapping(path = "/contact/findallbylabel/{label}", method = RequestMethod.GET)
    public List<ContactEntity> findAllByLabel(
            @PathVariable("label") String label) {
        logger.info("Path variable: " + label);
        return service.findAllByLabel(label);
    }

    @RequestMapping(path = "/contact/findall", method = RequestMethod.GET)
    public List<ContactEntity> findAll(
            @RequestParam("limit") Optional<Integer> limit,
            Principal principal) {
        logger.info("Request param limit: " + limit);
        return service.findAll(limit).getContent();
    }
}
