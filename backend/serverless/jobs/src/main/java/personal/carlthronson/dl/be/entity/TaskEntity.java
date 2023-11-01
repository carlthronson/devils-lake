package personal.carlthronson.dl.be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "task")
public class TaskEntity extends DataEntity {

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

    // Custom field
    @Getter
    @Setter
    private String location;

    /**
     * Every Task needs a Status
     * But a Status does not need a Task
     */
    @ManyToOne
    /**
     * The Status is created first
     * And then the Task is created and refers to the Status
     * Meaning Task is the owner of the relationship
     * And the task table contains the status_id column
     */
    @JoinColumn(name = "status_id", nullable = true, unique = false)
    /**
     * For Json
     * Every Task should include the Status
     */
    @JsonManagedReference(value = "task-status")
    @Getter
    @Setter
    private StatusEntity status;

    /**
     * Every Task needs a Job
     * And every Job needs a Task
     */
    @OneToOne
    /**
     * The Job is created first
     * And then the Task is created and refers to the Job
     * Meaning Task is the owner of the relationship
     * And the task table contains the job_id column
     */
    @JoinColumn(name = "job_id", nullable = true, unique = false)
    /**
     * For Json
     * Every Task should include the Job
     */
    @JsonManagedReference(value = "task-job")
    @Getter
    @Setter
    private JobEntity job;

    /**
     * Every Task needs a Story
     * And every Story needs a Task
     */
    @OneToOne
    /**
     * The Story is created first
     * And then the Task is created and refers to the Story
     * Meaning Task is the owner of the relationship
     * And the task table contains the story_id column
     */
    @JoinColumn(name = "story_id", nullable = true, unique = false)
    /**
     * For Json
     * The Task should not include the Story
     */
    @JsonBackReference(value = "story-task")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    @Setter
    private StoryEntity story;
}
