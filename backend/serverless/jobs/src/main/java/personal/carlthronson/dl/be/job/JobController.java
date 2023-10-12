package personal.carlthronson.dl.be.job;

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
public class JobController {
    @RequestMapping(path = "/job", method = RequestMethod.POST)
    public Job createJob(@RequestBody Job newJob) {
        Job result = newJob.clone();
        return result;
    }

    @RequestMapping(path = "/job", method = RequestMethod.GET)
    public Job[] listJobs(@RequestParam("limit") Optional<Integer> limit, Principal principal) {

        Job result = new Job();
        result.title = "Engineer";
        return new Job[] { result };
    }

    // TODO
    @RequestMapping(path = "/job/{jobId}", method = RequestMethod.GET)
    public Job listCompanies() {
        Job newJob = new Job();
        return newJob;
    }

}
