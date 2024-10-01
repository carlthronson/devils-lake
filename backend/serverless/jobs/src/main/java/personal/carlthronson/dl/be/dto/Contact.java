package personal.carlthronson.dl.be.dto;

import lombok.Getter;
import lombok.Setter;
import personal.carlthronson.dl.be.entity.ContactEntity;

public class Contact {

    public Contact() {
        // TODO Auto-generated constructor stub
    }

    public Contact(ContactEntity entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.setName(entity.getName());
            this.setLabel(entity.getLabel());
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
