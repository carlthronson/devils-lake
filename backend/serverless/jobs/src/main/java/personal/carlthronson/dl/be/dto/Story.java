package personal.carlthronson.dl.be.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import personal.carlthronson.dl.be.entity.StoryEntity;

public class Story {

    public Story() {
        // TODO Auto-generated constructor stub
    }

    public Story(StoryEntity entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.setName(entity.getName());
            this.setLabel(entity.getLabel());
            this.setLink(entity.getLink());
            this.setLocation(entity.getLocation());
            this.setPhase(new Phase(entity.getPhase()));
            this.setTasks(entity.getTasks().stream()
                    .map(taskEntity -> new Task(taskEntity)).toList());
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

    @Getter
    @Setter
    private String link;

    @Getter
    @Setter
    private String location;

    @Getter
    @Setter
    private Phase phase;

    @Getter
    @Setter
    private List<Task> tasks;
}
