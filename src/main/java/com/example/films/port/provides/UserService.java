package com.example.films.port.provides;

import com.example.films.auth.login.UserDTO;

public interface UserService {
    void saveUser(UserDTO user);
    boolean isUserExist(String email);
}
