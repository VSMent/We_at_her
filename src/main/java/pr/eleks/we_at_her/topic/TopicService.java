package pr.eleks.we_at_her.topic;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {
    List<Topic> topics = new ArrayList<>(Arrays.asList(
            new Topic("winter", "Winter season", "Winter is the coldest season of the year in polar and temperate zones."),
            new Topic("spring", "Spring season", "Spring, also known as springtime is one of the four temperate seasons, following winter and preceding summer."),
            new Topic("summer", "Summer season", "Summer is the hottest of the four temperate seasons, falling after spring and before autumn."),
            new Topic("autumn", "Autumn season", "Autumn, also known as fall in North American English, is one of the four temperate seasons.")
    ));

    public List<Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopic(String id) {
        return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void updateTopic(String id, Topic topic) {
        for (int i = 0; i < topics.size(); i++) {
            Topic t = topics.get(i);
            if (t.getId().equals(id)) {
                topics.set(i, topic);
                return;
            }
        }
    }
}
