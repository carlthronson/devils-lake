package personal.carlthronson.dl.be.entity;

import java.util.List;

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
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "status")
public class StatusEntity {

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

    /**
     * Every Status needs a Phase
     * But a Phase does not need a Status
     */
    @ManyToOne
    /**
     * The Phase is created first
     * And then the Status is created and refers to the Phase
     * Meaning Status is the owner of the relationship
     * And the status table contains the phase_id column
     */
    @JoinColumn(name = "phase_id", nullable = true, unique = false)
    /**
     * For Json
     * Every Status should include the Phase
     */
    @JsonManagedReference(value = "status-phase")
    @Getter
    @Setter
    private PhaseEntity phase;

    /**
     * A Status can haver zero or more Tasks
     * And a Task must have exactly one Status
     * 
     * The Status is created first
     * And then the Task is created and refers to the Status
     * Meaning Task is the owner of the relationship
     * And the task table contains the status_id column
     */
    @OneToMany(mappedBy = "status")
    /**
     * For Json
     * The Status should not include the Tasks
     */
    @JsonBackReference(value = "task-status")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    @Setter
    private List<TaskEntity> tasks;

    /**
     * A Status can haver zero or more Titles
     * And a Title must have exactly one Status
     * 
     * The Status is created first
     * And then the Title is created and refers to the Status
     * Meaning Title is the owner of the relationship
     * And the title table contains the status_id column
     */
    @OneToMany(mappedBy = "status")
    /**
     * For Json
     * The Status should not include the Titles
     */
    @JsonBackReference(value = "title-status")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    @Setter
    private List<TitleEntity> titles;
}
