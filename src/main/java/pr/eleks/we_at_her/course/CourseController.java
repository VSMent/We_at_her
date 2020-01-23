package pr.eleks.we_at_her.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/topics/{topicId}/courses")
    public List<Course> getAllCoursesByTopicId(@PathVariable String topicId) {
        return courseService.getAllCoursesByTopicId(topicId);
    }

    @GetMapping("/topics/{topicId}/courses/{id}")
    public Course getCourse(@PathVariable String id) {
        return courseService.getCourse(id);
    }

    @PostMapping("/topics/{topicId}/courses")
    public void addCourse(@PathVariable String topicId, @RequestBody Course course) {
        courseService.addCourse(topicId, course);
    }

    @PutMapping("/topics/{topicId}/courses/{id}")
    public void updateCourse(@RequestBody Course course) {
        courseService.updateCourse(course);
    }

    @DeleteMapping("/topics/{topicId}/courses/{id}")
    public void updateCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
    }

}
