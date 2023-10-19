package personal.carlthronson.dl.be.story;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import personal.carlthronson.dl.be.task.Status;

@Entity(name="phase")
public class Phase {

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
    @OneToMany
    @JoinColumn(name = "phase_id", nullable = true, unique = false)
    @JsonManagedReference
    private List<Status> statuses;
}
