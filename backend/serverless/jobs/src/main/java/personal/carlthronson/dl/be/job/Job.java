package personal.carlthronson.dl.be.job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Getter @Setter long linkedinid;
    @Getter @Setter String linkedinurl;
    @Getter @Setter String title;
    @Getter @Setter String contracttype;
    @Getter @Setter String experiencelevel;
    @Getter @Setter String salary;
    @Getter @Setter String sector;
    @Getter @Setter String worktype;
}
