package com.brian.jwtmock.repository;

import com.brian.jwtmock.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryMock {

    private final List<User> users = new ArrayList<>();

    public UserRepositoryMock(PasswordEncoder passwordEncoder) {
        // Usuarios mockeados con contraseña encriptada
        users.add(new User("admin", passwordEncoder.encode("admin1234")));
        users.add(new User("brian", passwordEncoder.encode("password123")));
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



