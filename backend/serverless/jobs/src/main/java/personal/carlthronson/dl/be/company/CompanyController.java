package personal.carlthronson.dl.be.company;

import java.security.Principal;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class CompanyController {
    @RequestMapping(path = "/company", method = RequestMethod.POST)
    public Company createCompany(@RequestBody Company newCompany) {
        Company result = newCompany.clone();
        return result;
    }

    @RequestMapping(path = "/company", method = RequestMethod.GET)
    public Company[] listCompanys(@RequestParam("limit") Optional<Integer> limit, Principal principal) {

        Company result = new Company();
        result.name = "Hello world";
        result.size = "1";
        result.url = "";
        return new Company[] { result };
    }

    // TODO
//    @RequestMapping(path = "/company/{companyId}", method = RequestMethod.GET)
//    public Company listCompanies() {
//        Company newCompany = new Company();
//        newCompany.setId(UUID.randomUUID().toString());
//        newCompany.setBreed(CompanyData.getRandomBreed());
//        newCompany.setDateOfBirth(CompanyData.getRandomDoB());
//        newCompany.setName(CompanyData.getRandomName());
//        return newCompany;
//    }

}
