package com.AgroEnvios.apiAE.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AgroEnvios.apiAE.Models.Usuario;
import com.AgroEnvios.apiAE.dto.RegisterRequest;
import com.AgroEnvios.apiAE.dto.TokenResponse;
import com.AgroEnvios.apiAE.repo.TokenRepository;
import com.AgroEnvios.apiAE.repo.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public TokenResponse register(RegisterRequest request) {

        var user = Usuario.builder()
            .nombre(request.name())
            .email(request.email())
            .password(passwordEncoder.encode(request.password()))
            .build();
            var saveUser = usuarioRepository.save(user);
    }
    
}
