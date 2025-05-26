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
import com.AgroEnvios.apiAE.Security.JwtUtil;
import com.AgroEnvios.apiAE.Models.Organizacion;
import com.AgroEnvios.apiAE.Services.OrganizacionService;

@Controller
@RequestMapping("/api/")
public class OrganizacionController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private OrganizacionService organizacionService;

    @GetMapping("/getTodasLasOrganizaciones")
    public ResponseEntity<ApiResponse<List<Organizacion>>> getAllProductos(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");

        if (jwtUtil.hasRole(token, "Admin") || jwtUtil.hasRole(token, "Proveedor") || jwtUtil.hasRole(token, "Supervisor")) {
            List<Organizacion> organizaciones = organizacionService.obtenerTodasLasOrganizaciones();
            return ResponseEntity.ok(new ApiResponse<>(organizaciones, "Operacion exitosa"));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(null, "No tienes permisos"));
        }
    }
    
}
