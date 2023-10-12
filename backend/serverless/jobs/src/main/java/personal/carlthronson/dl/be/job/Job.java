package personal.carlthronson.dl.be.job;

import java.util.UUID;

public class Job {

    public String id;
    public String title;

    public Job() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    protected Job clone() {
        Job result = new Job();
        result.title = new String(this.title);
        return result;
    }
}