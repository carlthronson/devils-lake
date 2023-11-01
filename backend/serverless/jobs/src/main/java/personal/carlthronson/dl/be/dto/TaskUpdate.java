package personal.carlthronson.dl.be.dto;

import lombok.Getter;
import lombok.Setter;

public class TaskUpdate {

    @Getter
    @Setter
    private Long taskId;
    
    @Getter
    @Setter
    private Long statusId;
}
