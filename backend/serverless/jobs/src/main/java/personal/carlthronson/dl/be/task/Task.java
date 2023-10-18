package personal.carlthronson.dl.be.task;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import personal.carlthronson.dl.be.job.Job;
import personal.carlthronson.dl.be.story.Story;

@Entity(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String label;

    @Getter
    @Setter
    private String link;

    @Getter
    @Setter
    private String location;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false, unique = false)
    private Status status;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "story_id", nullable = true, unique = false)
    @JsonBackReference
    private Story story;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "job_id", nullable = true, unique = false)
    private Job job;
}
