package pr.eleks.we_at_her;

import io.github.cdimascio.dotenv.Dotenv;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new org.modelmapper.ModelMapper();
    }

    @Bean
    public Dotenv dotenv(){
        return Dotenv.load();
    }
}
