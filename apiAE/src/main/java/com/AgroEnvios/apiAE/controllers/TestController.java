package com.AgroEnvios.apiAE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgroEnvios.apiAE.Security.JwtUtil;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/verifica")
    public ResponseEntity<String> verificarRol(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");

        if (jwtUtil.hasRole(token, "Admin")) {
            return ResponseEntity.ok("Tienes rol de ADMIN");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos");
        }
    }
}
