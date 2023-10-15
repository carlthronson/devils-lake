package personal.carlthronson.dl.be.story;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import personal.carlthronson.dl.be.task.Task;

@Entity(name = "story")
public class Story {
    @Id
//    @GeneratedValue
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
    private List<Task> tasks;
}
