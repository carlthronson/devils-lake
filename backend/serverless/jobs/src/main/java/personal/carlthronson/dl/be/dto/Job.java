package personal.carlthronson.dl.be.dto;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import personal.carlthronson.dl.be.entity.JobEntity;

public class Job {

    public Job() {
        // TODO Auto-generated constructor stub
    }

    public Job(JobEntity entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.setName(entity.getName());
            this.setLabel(entity.getLabel());
            this.setContracttype(entity.getContracttype());
            this.setExperiencelevel(entity.getExperiencelevel());
            this.setLinkedinid(entity.getLinkedinid());
            this.setLinkedinurl(entity.getLinkedinurl());
            this.setPublishedAt(entity.getPublishedAt());
            this.setSalary(entity.getSalary());
            this.setSector(entity.getSector());
            this.setWorktype(entity.getWorktype());
            // Don't serialize task
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
    long linkedinid;

    // Custom field
    @Getter
    @Setter
    String linkedinurl;

    // Custom field
    @Getter
    @Setter
    String contracttype;

    // Custom field
    @Getter
    @Setter
    String experiencelevel;

    // Custom field
    @Getter
    @Setter
    String salary;

    // Custom field
    @Getter
    @Setter
    String sector;

    // Custom field
    @Getter
    @Setter
    String worktype;

    // Custom field
    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
    OffsetDateTime publishedAt;

    @Getter
    @Setter
    private Task task;
}
