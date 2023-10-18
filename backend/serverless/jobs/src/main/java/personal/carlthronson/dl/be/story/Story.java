package personal.carlthronson.dl.be.story;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import personal.carlthronson.dl.be.task.Task;

@Entity(name = "story")
public class Story {
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
    @JoinColumn(name = "phase_id", nullable = false, unique = false)
    private Phase phase;

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "story_id", nullable = true, unique = false)
    @JsonManagedReference
    private List<Task> tasks;
}
