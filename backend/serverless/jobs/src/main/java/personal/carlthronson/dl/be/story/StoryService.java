package personal.carlthronson.dl.be.story;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import personal.carlthronson.dl.be.task.Task;
import personal.carlthronson.dl.be.task.TaskRepository;

@Service
@Transactional
public class StoryService {

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    PhaseService phaseService;

    @Autowired
    TaskRepository taskRepository;

    public List<Story> findByPhase(String phaseName, Pageable pageable) {

        // Phase name parameter
        System.out.println("phaseName = " + phaseName);
        Phase phase = phaseService.getByName(phaseName);

        // Trigger this so paging works better
        long count = taskRepository.countAllByStatusIn(phase.getStatuses());
        System.out.println("These are the statuses: " + phase.getStatuses());
        System.out.println("There are " + count + " task instances with these statuses.");

        List<Story> list = new ArrayList<>();

        // Use set to avoid duplicate stories
        if (count > pageable.getPageSize()) {
            Set<Story> result = getStories(phase, pageable);
            list.addAll(result);
        } else {
            // Get all the stories that have a task in this page of tasks
            System.out.println("Don't use paging");
            List<Task> tasks = taskRepository.findAllByStatusIn(phase.getStatuses());
            System.out.println("Tasks: " + tasks.size());

            List<Story> stories = storyRepository.findAllByTasksIn(tasks);
            // The number of stories and tasks could be different
            // Each task appears in exactly one story
            // But Story to Task is one to many
            System.out.println("Stories: " + stories.size());

            Set<Story> result = new HashSet<>();
            result.addAll(stories);
            list.addAll(result);
        }

        // One last step would be to remove tasks from stories
        // That have the wrong status
        return list;
    }

    private Set<Story> getStories(Phase phase, Pageable pageable) {
        Set<Story> result = new HashSet<>();

        while (true) {
            // Get the (next) page of tasks
            System.out.println("Pageable: " + pageable);
            Page<Task> tasksPage = taskRepository.findAllByStatusIn(phase.getStatuses(), pageable);
            System.out.println("Tasks page: " + tasksPage);
            System.out.println("Page number: " + tasksPage.getNumber());
            System.out.println("Total pages: " + tasksPage.getTotalPages());
            System.out.println("Total tasks: " + tasksPage.getTotalElements());

            if (tasksPage.getNumber() > tasksPage.getTotalPages() &&
                    tasksPage.hasPrevious()) {
                pageable = tasksPage.previousPageable();
                tasksPage = taskRepository.findAllByStatusIn(phase.getStatuses(), pageable);
                System.out.println("Tasks page: " + tasksPage);
                System.out.println("Total pages: " + tasksPage.getTotalPages());
                System.out.println("Total tasks: " + tasksPage.getTotalElements());
            }

            // Get all the stories that have a task in this page of tasks
            List<Task> tasks = tasksPage.getContent();
            System.out.println("Tasks: " + tasks.size());

            List<Story> stories = storyRepository.findAllByTasksIn(tasks);
            // The number of stories and tasks could be different
            // Each task appears in exactly one story
            // But Story to Task is one to many
            System.out.println("Stories: " + stories.size());

            // Add stories one at a time until we reach pageSize
            for (int index = 0; index < stories.size() && result.size() < pageable.getPageSize(); index++) {
                Story story = stories.get(index);
                result.add(story);
            }

            // If we reach pageSize stories or we run out of tasks we are done here
            if (result.size() >= pageable.getPageSize() || !tasksPage.hasNext()) {
                break;
            }

            // Otherwise get the next pageable for the next page of stories
            pageable = tasksPage.nextPageable();
        }
        return result;
    }

    public Story save(Story story) {
//      System.out.println("Request to story controller: " + story);
      if (story.getId() == 0 && storyRepository.existsByName(story.getName())) {
//          Story probe = new Story();
//          probe.setName(story.getName());
//          ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("name",
//                  ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
//          Example<Story> example = Example.of(probe, matcher);
//          Optional<Story> optional = storyRepository.findOne(example);
//          if (optional.isPresent()) {
//              return optional.get();
//          }
          return storyRepository.getByName(story.getName());
      }
      return storyRepository.save(story);
    }

    public Story getById(Long id) {
        return storyRepository.getById(id);
    }

    public Page<Story> findAll(Pageable pageable) {
        return storyRepository.findAll(pageable);
    }
}
