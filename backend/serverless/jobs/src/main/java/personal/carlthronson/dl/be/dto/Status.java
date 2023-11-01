package personal.carlthronson.dl.be.dto;

import lombok.Getter;
import lombok.Setter;
import personal.carlthronson.dl.be.entity.StatusEntity;

public class Status {

    public Status() {
    }

    public Status(StatusEntity entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.setName(entity.getName());
            this.setLabel(entity.getLabel());
            this.setPhase(new Phase(entity.getPhase()));
            // Don't serialize tasks
            // Don't serialize titles
        }
    }

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    private String label;

//    @Getter
//    @Setter
//    private Long phase_id;

    @Getter
    @Setter
    private Phase phase;
}
