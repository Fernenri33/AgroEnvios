package com.AgroEnvios.apiAE.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgroEnvios.apiAE.Models.Usuario;
import com.AgroEnvios.apiAE.Services.AuthService;
import com.AgroEnvios.apiAE.Services.UsuarioService;
import com.AgroEnvios.apiAE.Security.JwtUtil;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest auth) {
        try {
            String token = authService.login(auth.getEmail(), auth.getPassword());
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } catch (BadCredentialsException e) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap("error", "Correo o contraseña incorrectos"));
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("error", "Error al procesar la solicitud"));
        }
}


    @PostMapping("/crearUsuario")
    public ResponseEntity<?> createUser(@RequestHeader("Authorization") String authHeader, @RequestBody Usuario usuario) {
        // Validar token y rol Admin
        String token = authHeader.replace("Bearer ", "");
        if (!jwtUtil.hasRole(token, "Admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap("error", "No tienes permisos de administrador"));
        }
        Usuario savedUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @PostMapping("/crearUsuarioVacio")
    public ResponseEntity<?> createUserVacio(@RequestHeader("Authorization") String authHeader) {
        // Validar token y rol Admin
        String token = authHeader.replace("Bearer ", "");
        if (!jwtUtil.hasRole(token, "Admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap("error", "No tienes permisos de administrador"));
        }
        Usuario usuario = new Usuario();
        usuario.setPassword("cambiarContraseña");
        Usuario savedUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @GetMapping("/todosLosUsuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getUsuarioById(@RequestHeader("Authorization") String authHeader, @PathVariable int id) {

        // Validar token y rol Admin
        String token = authHeader.replace("Bearer ", "");
        if (!jwtUtil.hasRole(token, "Admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap("error", "No tienes permisos de administrador"));
        }   
        return usuarioService.getUsuarioById(id)
            .map(usuario -> ResponseEntity.ok(usuario))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Usuario()));
    }

    @GetMapping("/usuarioActual")
    public ResponseEntity<?> getUsuarioByToken(@RequestHeader("Authorization") String authHeader) {
        // Validar token
        String token = authHeader.replace("Bearer ", "");
        Usuario usuario = jwtUtil.getUserFromToken(token);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap("error", "Token inválido o expirado"));
        }
        return ResponseEntity.ok(usuario);
    }

        @GetMapping("/getRolUsuario")
        public ResponseEntity<?> getUsuarioRol(@RequestHeader("Authorization") String authHeader) {
            String token = authHeader.replace("Bearer ", "");
            // System.out.println("TOKEN: " + token);
            // System.out.println("Admin: " + jwtUtil.hasRole(token, "Admin"));
            // System.out.println("Supervisor: " + jwtUtil.hasRole(token, "Supervisor"));
            // System.out.println("Proveedor: " + jwtUtil.hasRole(token, "Proveedor"));
            String rol;
            if (jwtUtil.hasRole(token, "Admin")) {
                rol = "Admin";
            } else if (jwtUtil.hasRole(token, "Supervisor")) {
                rol = "Supervisor";
            } else if (jwtUtil.hasRole(token, "Proveedor")) {
                rol = "Proveedor";
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Collections.singletonMap("error", "Rol no autorizado"));
            }
            return ResponseEntity.ok(Collections.singletonMap("rol", rol));
        }

}
    
class AuthRequest {
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo no válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    // Getters y setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}