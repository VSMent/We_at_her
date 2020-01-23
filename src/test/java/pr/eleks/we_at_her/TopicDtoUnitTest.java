package pr.eleks.we_at_her;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import pr.eleks.we_at_her.dto.TopicDto;
import pr.eleks.we_at_her.entities.Topic;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopicDtoUnitTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertTopicEntityToTopicDto_thenCorrect() {
        Topic topic = new Topic();
        topic.setId("Lo");
        topic.setName("Lorem");
        topic.setDescription("www.test.com");

        TopicDto topicDto = modelMapper.map(topic, TopicDto.class);
        assertEquals(topic.getId(), topicDto.getId());
        assertEquals(topic.getName(), topicDto.getName());
        assertEquals(topic.getDescription(), topicDto.getDescription());
    }

    @Test
    public void whenConvertTopicDtoToTopicEntity_thenCorrect() {
        TopicDto topicDto = new TopicDto();
        topicDto.setId("RE");
        topicDto.setName("Ipsum");
        topicDto.setDescription("www.test.com");

        Topic topic = modelMapper.map(topicDto, Topic.class);
        assertEquals(topicDto.getId(), topic.getId());
        assertEquals(topicDto.getName(), topic.getName());
        assertEquals(topicDto.getDescription(), topic.getDescription());
    }
}