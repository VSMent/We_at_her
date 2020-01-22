package pr.eleks.we_at_her.topic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicController {

    @RequestMapping("/topics")
    public List<Topic> getAllTopics(){
        return Arrays.asList(
                new Topic("spring","Spring Framework","Spring Framework Description"),
                new Topic("autumn","Autumn Framework","Autumn Framework Description"),
                new Topic("summer","Summer Framework","Summer Framework Description"),
                new Topic("winter","Winter Framework","Winter Framework Description")
        );
    }
}
