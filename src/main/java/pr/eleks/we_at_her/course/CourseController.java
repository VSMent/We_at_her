package pr.eleks.we_at_her.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pr.eleks.we_at_her.topic.Topic;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
    @RequestMapping("/topics/{topicId}/courses")
    public List<Course> getAllCoursesByTopicId(@PathVariable String topicId) {
        return courseService.getAllCoursesByTopicId(topicId);
    }

    @RequestMapping("/topics/{topicId}/courses/{id}")
    public Course getCourse(@PathVariable String id) {
        return courseService.getCourse(id);
    }

    @RequestMapping(value = "/topics/{topicId}/courses", method = RequestMethod.POST)
    public void addCourse(@PathVariable String topicId, @RequestBody Course course) {
//        course.setTopic(new Topic(topicId,"",""));
        courseService.addCourse(course);
    }

    @RequestMapping(value = "/topics/{topicId}/courses/{id}", method = RequestMethod.PUT)
    public void updateCourse(@PathVariable String topicId,@RequestBody Course course) {
//        course.setTopic(new Topic(topicId,"",""));
        courseService.updateCourse(course);
    }

    @RequestMapping(value = "/topics/{topicId}/courses/{id}", method = RequestMethod.DELETE)
    public void updateCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
    }

}
