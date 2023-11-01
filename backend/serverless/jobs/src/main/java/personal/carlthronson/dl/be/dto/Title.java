package personal.carlthronson.dl.be.dto;

import lombok.Getter;
import lombok.Setter;
import personal.carlthronson.dl.be.entity.TitleEntity;

public class Title {

    public Title() {
        // TODO Auto-generated constructor stub
    }

    public Title(TitleEntity entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.setName(entity.getName());
            this.setLabel(entity.getLabel());
            this.setStatus(new Status(entity.getStatus()));
        }
    }

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

    @Getter
    @Setter
    private Status status;
}
