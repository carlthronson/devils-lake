package personal.carlthronson.dl.be.job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import personal.carlthronson.dl.be.task.Status;

@Entity(name = "title")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Getter @Setter String name;
    @JoinColumn(name = "status_id", nullable = false, unique = false)
    @Getter @Setter Status status;
}
