package pr.eleks.we_at_her.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public Topic getTopic(String id) {
        return topicRepository.findById(id).orElse(null);
    }

    public void addTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public void updateTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public void deleteTopic(String id) {
        topicRepository.deleteById(id);
    }

    @PostConstruct
    public void init() {
        topicRepository.saveAll(Arrays.asList(
                new Topic("winter", "Winter season", "Winter is the coldest season of the year in polar and temperate zones."),
                new Topic("spring", "Spring season", "Spring, also known as springtime is one of the four temperate seasons, following winter and preceding summer."),
                new Topic("summer", "Summer season", "Summer is the hottest of the four temperate seasons, falling after spring and before autumn."),
                new Topic("autumn", "Autumn season", "Autumn, also known as fall in North American English, is one of the four temperate seasons.")
        ));
    }
}
