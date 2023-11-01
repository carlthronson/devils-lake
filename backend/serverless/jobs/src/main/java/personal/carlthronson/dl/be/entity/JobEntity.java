package personal.carlthronson.dl.be.entity;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "job")
public class JobEntity {

    // Every entity needs an id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    // Every entity needs a name
    @Getter
    @Setter
    String name;

    // Custom field
    @Getter
    @Setter
    private String label;

//    @Getter
//    @Setter
//    String companyName;

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
    @Column(name = "published_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    OffsetDateTime publishedAt;

    /**
     * A Job must have exactly one Task
     * And a Task must have exactly one Job
     * 
     * The Job is created first
     * And then the Task is created and refers to the Job
     * Meaning Task is the owner of the relationship
     * And the task table contains the job_id column
     */
    @OneToOne(mappedBy = "job")
    /**
     * For Json
     * The Job should not include the Task
     */
    @JsonBackReference(value = "task-job")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    @Setter
    private TaskEntity task;
}
