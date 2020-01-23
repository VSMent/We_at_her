package pr.eleks.we_at_her.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import pr.eleks.we_at_her.dto.TopicDto;
import pr.eleks.we_at_her.entities.Topic;
import pr.eleks.we_at_her.services.TopicService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TopicController {

    private TopicService topicService;
    private ModelMapper modelMapper;

    public TopicController(TopicService topicService, ModelMapper modelMapper) {
        this.topicService = topicService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/topics")
    public List<TopicDto> getAllTopics() {
        List<Topic> topics = topicService.getAllTopics();
        return topics.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/topics/{id}")
    public TopicDto getTopic(@PathVariable String id) {
        return convertToDto(topicService.getTopic(id));
    }

    @PostMapping("/topics")
    public void addTopic(@RequestBody TopicDto topicDto) {
        topicService.addTopic(convertToEntity(topicDto));
    }

    @PutMapping("/topics/{id}")
    public void updateTopic(@RequestBody TopicDto topicDto) {
        topicService.updateTopic(convertToEntity(topicDto));
    }

    @DeleteMapping("/topics/{id}")
    public void deleteTopic(@PathVariable String id) {
        topicService.deleteTopic(id);
    }


    private TopicDto convertToDto(Topic topic) {
        return modelMapper.map(topic, TopicDto.class);
    }

    private Topic convertToEntity(TopicDto topicDto) {
        return modelMapper.map(topicDto, Topic.class);
    }

}
