package com.brian.jwtmock.repository;

import com.brian.jwtmock.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
    public class UserRepositoryMock {

        private final List<User> users = new ArrayList<>();

        public UserRepositoryMock() {
            // Cargamos usuarios mockeados en memoria
            users.add(new User("admin", "admin1234"));
            users.add(new User("brian", "password123"));
        }

        public User findByUsername(String username) {
            return users.stream()
                    .filter(user -> user.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);
        }
    }


