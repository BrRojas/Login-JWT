package com.brian.jwtmock.repository;

import com.brian.jwtmock.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRepositoryMock {

    private final List<User> users = new ArrayList<>();

    public UserRepositoryMock() {
        // Usuarios mockeados en memoria
        users.add(new User("admin", "admin1234"));
        users.add(new User("brian", "password123"));
    }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    public void save(User user) {
        users.add(user);
    }
}


