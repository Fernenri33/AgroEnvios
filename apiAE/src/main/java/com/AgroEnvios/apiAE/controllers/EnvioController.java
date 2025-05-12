package com.AgroEnvios.apiAE.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AgroEnvios.apiAE.Enums.Estado;
import com.AgroEnvios.apiAE.Models.ApiResponse;
import com.AgroEnvios.apiAE.Models.Envio;
import com.AgroEnvios.apiAE.Security.JwtUtil;
import com.AgroEnvios.apiAE.Services.EnviosService;

@RestController
@RequestMapping("/api/")
public class EnvioController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EnviosService enviosService;

@GetMapping("/getTodosLosEnvios")
public ResponseEntity<ApiResponse<List<Envio>>> getAllEnvios(@RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");

    if (jwtUtil.hasRole(token, "Admin") || jwtUtil.hasRole(token, "Supervisor") || jwtUtil.hasRole(token, "Proveedor")) {
        List<Envio> envios = enviosService.getAllEnvios();
        return ResponseEntity.ok(new ApiResponse<>(envios, "Operacion exitosa"));
    } else {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse<>(null, "No tienes permisos"));
    }
}

@PostMapping("/crearEnvio")
public ResponseEntity<ApiResponse<Envio>> createEnvio(@RequestHeader("Authorization") String authHeader, @RequestBody Envio envio) {
    String token = authHeader.replace("Bearer ", "");

    if (jwtUtil.hasRole(token, "Admin") || jwtUtil.hasRole(token, "Proveedor")) {
        Envio createdEnvio = enviosService.createEnvio(envio);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(createdEnvio, "Envio creado exitosamente"));
    } else {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse<>(null, "No tienes permisos"));
    }
}

@PostMapping("/editarEnvio")
public ResponseEntity<ApiResponse<Envio>> editarEnvio(@RequestHeader("Authorization") String authHeader, @RequestBody Envio envio) {
    String token = authHeader.replace("Bearer ", "");

    if (jwtUtil.hasRole(token, "Admin") || jwtUtil.hasRole(token, "Proveedor")) {
        Envio editedEnvio = enviosService.updateEnvio(envio);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(editedEnvio, "Envio editado exitosamente"));
    } else {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse<>(null, "No tienes permisos"));
    }
}

@PostMapping("/aceptarEnvio")
public ResponseEntity<ApiResponse<Envio>> revisarEnvio(@RequestHeader("Authorization") String authHeader, @RequestBody Envio envio, @RequestParam String estado) {
    String token = authHeader.replace("Bearer ", "");

    if (jwtUtil.hasRole(token, "Admin") || jwtUtil.hasRole(token, "Supervisor")) {
        try {
            envio.setEstado(Estado.valueOf(estado.toUpperCase())); // Convertir el String al enum
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(null, "Estado no válido"));
        }

        Envio updatedEnvio = enviosService.updateEnvio(envio);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(updatedEnvio, "Envio editado exitosamente"));
    } else {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse<>(null, "No tienes permisos"));
    }
}

@PostMapping("/eliminarEnvio")

public ResponseEntity<ApiResponse<Envio>> eliminarEnvio(@RequestHeader("Authorization") String authHeader, @RequestBody Envio envio) {
    String token = authHeader.replace("Bearer ", "");

    if (jwtUtil.hasRole(token, "Admin")) {

        Envio DeleteEnvio = envio;
        enviosService.deleteEnvio(DeleteEnvio.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(DeleteEnvio, "Envio eliminado exitosamente"));
    } else {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse<>(null, "No tienes permisos"));
    }
}

}
