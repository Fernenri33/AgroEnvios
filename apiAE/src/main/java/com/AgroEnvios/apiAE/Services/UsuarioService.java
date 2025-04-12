package com.AgroEnvios.apiAE.Services;

import com.AgroEnvios.apiAE.Models.Usuario;
import com.AgroEnvios.apiAE.Security.JwtUtil;
import com.AgroEnvios.apiAE.Security.UserDetailsServiceImpl;
import com.AgroEnvios.apiAE.repo.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Método para obtener todos los usuarios
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método para obtener un usuario por email
    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Método para obtener un usuario por ID
    public Optional<Usuario> getUsuarioById(int id) {
        return usuarioRepository.findById(id);
    }

    // Método para eliminar un usuario por ID
    public void deleteUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

public String login(String email, String password) {
    try {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );
    } catch (AuthenticationException e) {
        throw new BadCredentialsException("Correo o contraseña incorrectos");
    }

    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
    return jwtUtil.generateToken(userDetails);
}

    public Usuario saveUsuario(Usuario usuario) {
        // Encriptar la contraseña antes de guardar el usuario
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

}