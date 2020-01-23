package pr.eleks.we_at_her.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pr.eleks.we_at_her.topic.Topic;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    public List<Course> getAllCoursesByTopicId(String topicId) {
        return new ArrayList<>(courseRepository.findCoursesByTopicId(topicId));
    }

    public Course getCourse(String id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    @PostConstruct
    public void init() {
//        courseRepository.saveAll(Arrays.asList(
//                new Course("winter", "Winter season", "Winter is the coldest season of the year in polar and temperate zones."),
//                new Course("spring", "Spring season", "Spring, also known as springtime is one of the four temperate seasons, following winter and preceding summer."),
//                new Course("summer", "Summer season", "Summer is the hottest of the four temperate seasons, falling after spring and before autumn."),
//                new Course("autumn", "Autumn season", "Autumn, also known as fall in North American English, is one of the four temperate seasons.")
//        ));
    }
}
