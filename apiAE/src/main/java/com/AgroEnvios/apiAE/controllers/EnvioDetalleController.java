package com.AgroEnvios.apiAE.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgroEnvios.apiAE.Models.ApiResponse;
import com.AgroEnvios.apiAE.Models.EnvioDetalle;
import com.AgroEnvios.apiAE.Security.JwtUtil;
import com.AgroEnvios.apiAE.Services.EnvioDetalleService;

@RestController
@RequestMapping("/api/")
public class EnvioDetalleController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EnvioDetalleService envioDetalleService;

    @GetMapping("/envioDetalle/{idEnvio}")
    public ResponseEntity<ApiResponse<List<EnvioDetalle>>> getEnvioDetalles(@RequestHeader("Authorization") String authHeader, @PathVariable Integer idEnvio) {
        String token = authHeader.replace("Bearer ", "");

        if (jwtUtil.hasRole(token, "Admin") || jwtUtil.hasRole(token, "Proveedor")) {
            List<EnvioDetalle> envioDetalles = envioDetalleService.getEnvioDetallesByEnvioId(idEnvio);
            return ResponseEntity.ok(new ApiResponse<>(envioDetalles, "Operacion exitosa"));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(null, "No tienes permisos"));
        }
    }

    @PostMapping("/envioDetalle/crear")
    public ResponseEntity<ApiResponse<EnvioDetalle>> crearEnvioDetalle(@RequestHeader("Authorization") String authHeader, EnvioDetalle envioDetalle) {
        String token = authHeader.replace("Bearer ", "");

        if (jwtUtil.hasRole(token, "Admin") || jwtUtil.hasRole(token, "Proveedor")) {
            envioDetalleService.crearEnvioDetalle(envioDetalle);
            return ResponseEntity.ok(new ApiResponse<>(envioDetalle, "Operacion exitosa"));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(null, "No tienes permisos"));
        }
    }

    @PostMapping("/envioDetalle/actualizar")
    public ResponseEntity<ApiResponse<EnvioDetalle>> actualizarEnvioDetalle(@RequestHeader("Authorization") String authHeader, EnvioDetalle envioDetalle) {
        String token = authHeader.replace("Bearer ", "");

        if (jwtUtil.hasRole(token, "Admin") || jwtUtil.hasRole(token, "Proveedor")) {
            envioDetalleService.actualizarEnvioDetalle(envioDetalle);
            return ResponseEntity.ok(new ApiResponse<>(envioDetalle, "Operacion exitosa"));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(null, "No tienes permisos"));
        }
    }
    @PostMapping("/envioDetalle/eliminar/{idEnvioDetalle}")
    public ResponseEntity<ApiResponse<Void>> eliminarEnvioDetalle(@RequestHeader("Authorization") String authHeader, @PathVariable Integer idEnvioDetalle) {
        String token = authHeader.replace("Bearer ", "");

        if (jwtUtil.hasRole(token, "Admin") || jwtUtil.hasRole(token, "Proveedor")) {
            envioDetalleService.eliminarEnvioDetalle(idEnvioDetalle);
            return ResponseEntity.ok(new ApiResponse<>(null, "Operacion exitosa"));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(null, "No tienes permisos"));
        }
    }
    
}
