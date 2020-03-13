package pr.eleks.we_at_her.configs

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.client.RestTemplate

@Configuration
class AppConfig {
    @Bean
    fun mapper() = jacksonObjectMapper()

    @Bean
    fun restTemplate() = RestTemplate()

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder(12)
}