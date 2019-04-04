package com.example.films.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

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
        insertUser("any","any");
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

    private UserDTO insertUser(String email, String password) {
        return userRepository.save(new UserDTO(email, password));
    }
}
