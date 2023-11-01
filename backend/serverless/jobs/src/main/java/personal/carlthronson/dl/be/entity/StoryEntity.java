package personal.carlthronson.dl.be.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "story")
public class StoryEntity {

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
    private String link;

    // Custom field
    @Getter
    @Setter
    private String location;

    /**
     * Every Story needs a Phase
     * But a Phase does not need a Story
     */
    @ManyToOne
    /**
     * The Phase is created first
     * And then the Story is created and refers to the Phase
     * Meaning Story is the owner of the relationship
     * And the story table contains the phase_id column
     */
    @JoinColumn(name = "phase_id", nullable = true, unique = false)
    /**
     * For Json
     * Every Story should include the Phase
     */
    @JsonManagedReference(value = "story-phase")
    @Getter
    @Setter
    private PhaseEntity phase;

    /**
     * A Story can haver zero or more Tasks
     * And a Task needs at least one Story
     * 
     * The Story is created first
     * And then the Task is created and refers to the Story
     * Meaning Task is the owner of the relationship
     * And the task table contains the story_id column
     */
    @OneToMany(mappedBy = "story")
    /**
     * For Json
     * Every Story should include the Tasks
     */
    @JsonManagedReference(value = "story-task")
    @Getter
    @Setter
    private List<TaskEntity> tasks;
}
