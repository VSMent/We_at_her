package pr.eleks.we_at_her.repositories;

import org.springframework.data.repository.CrudRepository;
import pr.eleks.we_at_her.entities.Course;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {
    public List<Course> findCoursesByTopicId(String topicId);

}
