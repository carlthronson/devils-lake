
package personal.carlthronson.dl.be.svc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import personal.carlthronson.dl.be.entity.JobEntity;
import personal.carlthronson.dl.be.entity.PhaseEntity;
import personal.carlthronson.dl.be.entity.StatusEntity;
import personal.carlthronson.dl.be.entity.StoryEntity;
import personal.carlthronson.dl.be.entity.TaskEntity;
import personal.carlthronson.dl.be.repo.PhaseRepository;
import personal.carlthronson.dl.be.repo.SimpleRepository;
//import personal.carlthronson.dl.be.entity.PhaseEntity;
//import personal.carlthronson.dl.be.entity.StoryEntity;
//import personal.carlthronson.dl.be.entity.TaskEntity;
import personal.carlthronson.dl.be.repo.StoryRepository;
import personal.carlthronson.dl.be.repo.TaskRepository;

@Service
@Transactional
public class StoryService extends SimpleService<StoryEntity> {

    Logger logger = Logger.getLogger(StoryService.class.getName());

    @Autowired
    StoryRepository repository;

    @Override
    public SimpleRepository<StoryEntity> getSimpleRepository() {
        return this.repository;
    }

    @Override
    public JpaRepository<StoryEntity, Long> getJpaRepository() {
        return this.repository;
    }

    // ****** Custom methods ***********

    @Override
    public StoryEntity save(StoryEntity entity) {
        if (repository.existsByName(entity.getName())) {
            StoryEntity existingStory = findByName(entity.getName());
            entity.setId(existingStory.getId());
        }
        return super.save(entity);
    }

    @Autowired
    PhaseRepository phaseRepository;

    @Autowired
    TaskRepository taskRepository;

    public List<StoryEntity> findByPhase(String phaseName, Pageable pageable) {

        // Phase name parameter
        System.out.println("phaseName = " + phaseName);
        PhaseEntity phase = phaseRepository.findByName(phaseName);

        // Trigger this so paging works better
        long count = taskRepository.countAllByStatusIn(phase.getStatuses());
        System.out.println("These are the statuses: " + phase.getStatuses());
        System.out.println(
                "There are " + count + " task instances with these statuses.");

        List<StoryEntity> list = new ArrayList<>();

        // Use set to avoid duplicate stories
        if (count > pageable.getPageSize()) {
            Set<StoryEntity> result = getStories(phase, pageable);
            list.addAll(result);
        } else {
            // Get all the stories that have a task in this page of tasks
            System.out.println("Don't use paging");
            List<TaskEntity> tasks = taskRepository
                    .findAllByStatusIn(phase.getStatuses());
            System.out.println("Tasks: " + tasks.size());

            List<StoryEntity> stories = repository.findAllByTasksIn(tasks);
            // The number of stories and tasks could be different
            // Each task appears in exactly one story
            // But Story to Task is one to many
            System.out.println("Stories: " + stories.size());

            Set<StoryEntity> result = new HashSet<>();
            result.addAll(stories);
            list.addAll(result);
        }

        // One last step would be to remove tasks from stories
        // That have the wrong status
        List<StoryEntity> clean = new ArrayList<>();
        for (StoryEntity story : list) {
            List<TaskEntity> cleanTasks = new ArrayList<>();
            for (TaskEntity task : story.getTasks()) {
                if (phase.getStatuses().contains(task.getStatus())) {
                    cleanTasks.add(task);
                }
            }
            cleanTasks.sort(new Comparator<TaskEntity>() {

                @Override
                public int compare(TaskEntity o1, TaskEntity o2) {
                    return o1.getJob().getName()
                            .compareTo(o2.getJob().getName());
                }

            });
            StoryEntity cleanStory = new StoryEntity();
            cleanStory.setId(story.getId());
            cleanStory.setName(story.getName());
            cleanStory.setPhase(story.getPhase());
            cleanStory.setLabel(story.getLabel());
            cleanStory.setLocation(story.getLocation());
            cleanStory.setTasks(cleanTasks);
            clean.add(cleanStory);
        }
        clean.sort(new Comparator<StoryEntity>() {

            @Override
            public int compare(StoryEntity o1, StoryEntity o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return clean;
    }

    private Set<StoryEntity> getStories(PhaseEntity phase, Pageable pageable) {
        Set<StoryEntity> result = new HashSet<>();

        while (true) {
            // Get the (next) page of tasks
            System.out.println("Pageable: " + pageable);
            Page<TaskEntity> tasksPage = taskRepository
                    .findAllByStatusIn(phase.getStatuses(), pageable);
            System.out.println("Tasks page: " + tasksPage);
            System.out.println("Page number: " + tasksPage.getNumber());
            System.out.println("Total pages: " + tasksPage.getTotalPages());
            System.out.println("Total tasks: " + tasksPage.getTotalElements());

            if (tasksPage.getNumber() > tasksPage.getTotalPages()
                    && tasksPage.hasPrevious()) {
                pageable = tasksPage.previousPageable();
                tasksPage = taskRepository
                        .findAllByStatusIn(phase.getStatuses(), pageable);
                System.out.println("Tasks page: " + tasksPage);
                System.out.println("Total pages: " + tasksPage.getTotalPages());
                System.out.println(
                        "Total tasks: " + tasksPage.getTotalElements());
            }

            // Get all the stories that have a task in this page of tasks
            List<TaskEntity> tasks = tasksPage.getContent();
            System.out.println("Tasks: " + tasks.size());

            List<StoryEntity> stories = repository.findAllByTasksIn(tasks);
            // The number of stories and tasks could be different
            // Each task appears in exactly one story
            // But Story to Task is one to many
            System.out.println("Stories: " + stories.size());

            // Add stories one at a time until we reach pageSize
            for (int index = 0; index < stories.size()
                    && result.size() < pageable.getPageSize(); index++) {
                StoryEntity story = stories.get(index);
                result.add(story);
            }

            // If we reach pageSize stories or we run out of tasks we are done
            // here
            if (result.size() >= pageable.getPageSize()
                    || !tasksPage.hasNext()) {
                break;
            }

            // Otherwise get the next pageable for the next page of stories
            pageable = tasksPage.nextPageable();
        }
        return result;
    }

    public Page<StoryEntity> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public List<StoryEntity> findPageByPhase(String phaseName,
            Pageable pageable) {
        logger.info("findPageByPhase");
        PhaseEntity phase = phaseRepository.findByName(phaseName);
//        logger.info("Phase entity: " + phase.getName());
        Page<TaskEntity> taskPage = taskRepository
                .findAllByStatusIn(phase.getStatuses(), pageable);
//        logger.info("Tasks page: " + taskPage);
        Page<StoryEntity> storiesPage = repository
                .findAllByTasksIn(taskPage.getContent(), pageable);
//        logger.info("Stories page: " + storiesPage);
        List<StoryEntity> list = new ArrayList<>();
        for (StoryEntity entity : storiesPage.getContent()) {
//            logger.info("Story entity: " + entity.getName());
            StoryEntity storyEntity = new StoryEntity();
            storyEntity.setId(entity.getId());
            storyEntity.setName(entity.getName());
            storyEntity.setLabel(entity.getLabel());
            storyEntity.setLocation(entity.getLocation());
            List<TaskEntity> originalList = entity.getTasks();
            System.out.println("Original tasks: " + originalList.size());
            List<TaskEntity> newList = originalList.stream()
                    .filter(task -> filterTask(task, phaseName)).toList();
            System.out.println("New tasks: " + newList.size());
            storyEntity.setTasks(newList);
            storyEntity.setPhase(entity.getPhase());
            list.add(storyEntity);
        }
        list = list.stream().sorted(new Comparator<StoryEntity>() {

            // Reverse order, most recent first
            @Override
            public int compare(StoryEntity o1, StoryEntity o2) {
                JobEntity job1 = sortTasks(o1);
                JobEntity job2 = sortTasks(o2);
                return job2.getPublishedAt().compareTo(job1.getPublishedAt());
            }

            // Also in reverse order, most recent first
            private JobEntity sortTasks(StoryEntity o1) {
                List<TaskEntity> l1 = o1.getTasks().stream()
                        .sorted(new Comparator<TaskEntity>() {

                            @Override
                            public int compare(TaskEntity o1, TaskEntity o2) {
                                return o1.getJob().getPublishedAt().compareTo(
                                        o2.getJob().getPublishedAt());
                            }
                        }).toList();
                o1.setTasks(l1);
                return o1.getTasks().get(0).getJob();
            }
        }).toList();
        return list;
    }

    private Boolean filterTask(TaskEntity task, String phaseName) {
        System.out.println("Filter task - task name: " + task.getName());
        System.out.println("Filter task - target phase name: " + phaseName);
        StatusEntity statusEntity = task.getStatus();
        System.out.println(
                "Filter task - status name: " + statusEntity.getName());
        PhaseEntity phaseEntity = statusEntity.getPhase();
        System.out.println("Filter task - status phase name: " + phaseName);
        boolean result = phaseEntity.getName().compareTo(phaseName) == 0;
        System.out.println("Filter task - result: " + result);
        return result;
    }
}