package personal.carlthronson.dl.be.job;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
    @Column(name = "published_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Getter @Setter OffsetDateTime publishedAt;
}
