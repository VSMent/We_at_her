package pr.eleks.we_at_her.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
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

    public void addCourse(String topicId, Course course) {
        course.setTopic(new Topic(topicId, "", ""));
        courseRepository.save(course);
    }

    public void updateCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

//    @DependsOn("pr.eleks.we_at_her.topic.Topic")
//    @Order(2)
//    @PostConstruct
//    public void init() {
//        courseRepository.saveAll(Arrays.asList(
//                new Course("skiing", "Winter season Skiing Course", "You can go skiing during winter.", "winter"),
//                new Course("swimming", "Summer season Swimming Course", "You can go swimming during summer.", "summer"),
//                new Course("jogging", "Summer season Jogging Course", "You can go jogging during summer.", "summer"),
//                new Course("walking", "Autumn season Walking Course", "You can go walking during autumn.", "autumn")
//        ));
//    }
}
