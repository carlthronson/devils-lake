package personal.carlthronson.dl.be.dto;

import lombok.Getter;
import lombok.Setter;
import personal.carlthronson.dl.be.entity.TaskEntity;

public class Task {

    public Task() {
        // TODO Auto-generated constructor stub
    }

    public Task(TaskEntity entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.setName(entity.getName());
            this.setLabel(entity.getLabel());
            this.setJob(new Job(entity.getJob()));
            this.setStatus(new Status(entity.getStatus()));
            this.setLocation(entity.getLocation());
            // Don't serialize story
        }
    }

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    private String label;
    
    @Getter
    @Setter
    private String location;

    @Getter
    @Setter
    private Job job;

    @Getter
    @Setter
    private Status status;

    @Getter
    @Setter
    private Story story;
}
