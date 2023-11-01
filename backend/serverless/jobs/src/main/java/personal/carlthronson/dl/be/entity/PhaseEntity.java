package personal.carlthronson.dl.be.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "phase")
public class PhaseEntity {

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
     * A Phase can haver zero or more Statuses
     * And a Status must have exactly one Phase
     * 
     * The Phase is created first
     * And then the Status is created and refers to the Phase
     * Meaning Status is the owner of the relationship
     * And the status table contains the phase_id column
     */
    @OneToMany(mappedBy = "phase")
    /**
     * For Json
     * The Phase should not include the Statuses
     */
    @JsonBackReference(value = "status-phase")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    @Setter
    private List<StatusEntity> statuses;

    /**
     * A Phase can haver zero or more Stories
     * And a Story must have exactly one Phase
     * 
     * The Phase is created first
     * And then the Story is created and refers to the Phase
     * Meaning Story is the owner of the relationship
     * And the story table contains the phase_id column
     */
    @OneToMany(mappedBy = "phase")
    /**
     * For Json
     * The Phase should not include the Stories
     */
    @JsonBackReference(value = "story-phase")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    @Setter
    private List<StoryEntity> stories;
}
