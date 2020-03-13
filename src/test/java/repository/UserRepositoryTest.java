package repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pr.eleks.we_at_her.Application;
import pr.eleks.we_at_her.dto.RoleDto;
import pr.eleks.we_at_her.entities.User;
import pr.eleks.we_at_her.repositories.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

//@AutoConfigureTestDatabase
//@SpringBootTest
//@Transactional
//@ComponentScan(basePackages = {"pr.eleks.we_at_her"})
//@AutoConfigureMockMvc
@DataJpaTest
@SpringJUnitConfig(Application.class)
public class UserRepositoryTest {

//    @Autowired
//    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void user_SaveFind() {
        String username = "test";
        String password = "user";
        long cityId = 12345L;
        String email = "EMaIL";
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setCityId(cityId);
        user.setEmail(email);
        user.setRole(RoleDto.USER);

        userRepository.save(user);

        User foundUser = userRepository.findByUsername(username);

        assertThat(foundUser).isNotNull();
        assertThat(user.getUsername())
                .isNotBlank()
                .isEqualTo(foundUser.getUsername());
        assertThat(user.getPassword())
                .isNotBlank()
                .isEqualTo(foundUser.getPassword());
        assertThat(user.getEmail())
                .isNotBlank()
                .isEqualTo(foundUser.getEmail());
        assertThat(user.getCityId())
                .isNotNull()
                .isEqualTo(foundUser.getCityId());
        assertThat(user.getRole())
                .isEqualTo(foundUser.getRole());
    }
}

