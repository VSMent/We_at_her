package pr.eleks.we_at_her.hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String sayHi() {
        return "Greetings from Spring Boot!";
    }

}