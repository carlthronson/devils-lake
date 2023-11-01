package personal.carlthronson.dl.be.dto;

import lombok.Getter;
import lombok.Setter;
import personal.carlthronson.dl.be.entity.PhaseEntity;

public class Phase {

    public Phase() {
        // TODO Auto-generated constructor stub
    }

    public Phase(PhaseEntity entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.setName(entity.getName());
            this.setLabel(entity.getLabel());
            // Don't serialize statues
            // Don't serialize stories
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
}
