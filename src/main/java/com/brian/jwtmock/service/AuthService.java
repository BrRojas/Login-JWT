package com.brian.jwtmock.service;


import com.brian.jwtmock.model.LoginRequestDTO;
import com.brian.jwtmock.model.User;
import com.brian.jwtmock.repository.UserRepositoryMock;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepositoryMock userRepository;

    public AuthService(UserRepositoryMock userRepository) {
        this.userRepository = userRepository;
    }

    public String login(LoginRequestDTO request) {
        User user = userRepository.findByUsername(request.getUsername());

        if (user != null && user.getPassword().equals(request.getPassword())) {
            return "MOCKED_JWT_TOKEN_FOR_" + user.getUsername().toUpperCase();
        }
        throw new RuntimeException("Usuario o contraseña inválidos");
    }
}
