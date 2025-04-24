package com.brian.jwtmock.service;


import com.brian.jwtmock.model.LoginRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public  String login(LoginRequestDTO request){
        if ("admin".equals(request.getUsername())&& "admin1234".equals(request.getPassword())){
            return "MOCKED_JWT_TOKEN_FOR_ADMIN";
        }
        throw new RuntimeException("Usuario o contraseña invalidos");
    }
}
