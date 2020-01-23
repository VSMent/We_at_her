package pr.eleks.we_at_her.course;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {
    public List<Course> findCoursesByTopicId(String topicId);

}
