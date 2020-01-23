package pr.eleks.we_at_her;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public org.modelmapper.ModelMapper modelMapper(){
        return new org.modelmapper.ModelMapper();
    }
}