package com.AgroEnvios.apiAE.controllers;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HolaMundoController {

    @GetMapping("/hola")
    public Map<String, String> holaMundo() {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "¡Hola Mundo desde Spring Boot!");
        return response;
    }
}