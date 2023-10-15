package personal.carlthronson.dl.be.task;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "task")
public class Task {
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
    @JoinColumn(name = "status_id", nullable = false, unique = false)
    private Status status;
}
