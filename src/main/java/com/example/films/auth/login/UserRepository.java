package com.example.films.auth.login;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDTO, Long> {
    List<UserDTO> findByEmail(String email);
}
