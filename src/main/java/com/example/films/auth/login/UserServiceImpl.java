package com.example.films.auth.login;

import com.example.films.port.provides.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public void saveUser(UserDTO user) {
        userRepository.save(user);
    }

    @Override
    public boolean isUserExist(String email) {
        return userRepository.findByEmail(email).size() > 0;
    }
}
