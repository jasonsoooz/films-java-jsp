package com.example.films.auth;

import com.example.films.auth.login.UserDTO;
import com.example.films.auth.login.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void reset() {
        userRepository.deleteAll();
    }

    @Test
    void insertAndRetrieveUsers() {
        String email = "joe@bloggs.com";
        String password = "password";

        insertUser(email, password);

        assertThat(userRepository.findAll().size()).isEqualTo(1);
        UserDTO user = userRepository.findAll().get(0);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    void deleteUsers() {
        insertUser("any", "any");
        assertThat(userRepository.findAll().size()).isEqualTo(1);

        userRepository.deleteAll();

        assertThat(userRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    void updateUser() {
        UserDTO user = insertUser("any", "any");
        assertThat(userRepository.findAll().size()).isEqualTo(1);

        String updatedEmail = "updatedEmail";
        String updatedPassword = "updatedPassword";
        user.setEmail(updatedEmail);
        user.setPassword(updatedPassword);
        userRepository.save(user);

        assertThat(userRepository.findAll().size()).isEqualTo(1);
        UserDTO retrievedUser = userRepository.findAll().get(0);
        assertThat(retrievedUser.getEmail()).isEqualTo(updatedEmail);
        assertThat(retrievedUser.getPassword()).isEqualTo(updatedPassword);
    }

    @Test
    @DisplayName("find user by email when email does not exist")
    void findUserByEmail_whenEmailNotExist() {
        List<UserDTO> emails = userRepository.findByEmail("notExist");

        assertThat(emails.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("find user by email when email exists")
    void findUserByEmail_whenEmailExists() {
        String email = "exists";
        String password = "password";
        insertUser(email, password);
        insertUser(email, password);
        assertThat(userRepository.findAll().size()).isEqualTo(2);

        List<UserDTO> emails = userRepository.findByEmail(email);

        assertThat(emails.size()).isEqualTo(2);
        UserDTO user1 = emails.get(0);
        assertThat(user1.getEmail()).isEqualTo(email);
        assertThat(user1.getPassword()).isEqualTo(password);
        UserDTO user2 = emails.get(1);
        assertThat(user2.getEmail()).isEqualTo(email);
        assertThat(user2.getPassword()).isEqualTo(password);
    }

    private UserDTO insertUser(String email, String password) {
        return userRepository.save(new UserDTO(email, password));
    }
}
