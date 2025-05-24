package com.AgroEnvios.apiAE.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import com.AgroEnvios.apiAE.Models.ApiResponse;
import com.AgroEnvios.apiAE.Models.Producto;
import com.AgroEnvios.apiAE.Security.JwtUtil;
import com.AgroEnvios.apiAE.Services.ProductoService;

@Controller
@RequestMapping("/api/")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private JwtUtil jwtUtil;

        @GetMapping("/getTodosLosProductos")
        public ResponseEntity<ApiResponse<List<Producto>>> getAllProductos(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");

        if (jwtUtil.hasRole(token, "Admin") || jwtUtil.hasRole(token, "Proveedor") || jwtUtil.hasRole(token, "Supervisor")) {
            List<Producto> productos = productoService.obtenerTodosLosProductos();
            return ResponseEntity.ok(new ApiResponse<>(productos, "Operacion exitosa"));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(null, "No tienes permisos"));
        }
    }
    
    
}
