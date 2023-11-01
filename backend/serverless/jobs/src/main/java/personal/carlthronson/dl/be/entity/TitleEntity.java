package personal.carlthronson.dl.be.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "title")
public class TitleEntity {

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
     * Every Title needs a Status
     * But a Status does not need a Title
     */
    @ManyToOne
    /**
     * The Status is created first
     * And then the Title is created and refers to the Status
     * Meaning Title is the owner of the relationship
     * And the title table contains the status_id column
     */
    @JoinColumn(name = "status_id", nullable = true, unique = false)
    /**
     * For Json
     * Every Title should include the Status
     */
    @JsonManagedReference(value = "title-status")
    @Getter
    @Setter
    private StatusEntity status;
}
