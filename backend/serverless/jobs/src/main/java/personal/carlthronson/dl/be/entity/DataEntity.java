package personal.carlthronson.dl.be.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

public abstract class DataEntity {

    // Every entity needs an id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private Long id;

    // Every entity needs a name
    @Getter
    @Setter
    String name;

    // Custom field
    @Getter
    @Setter
    private String label;
}
