package com.AgroEnvios.apiAE.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AgroEnvios.apiAE.Models.Organizacion;
import com.AgroEnvios.apiAE.repo.OrganizacionRepository;

@Service
public class OrganizacionService {

    @Autowired
    private OrganizacionRepository organizacionRepository;

    // Metodo para listar todas las organizaciones
    public List<Organizacion> obtenerTodasLasOrganizaciones() {
        return organizacionRepository.findAll();
    }

    // Metodo para buscar una organizacion por su ID
    public Organizacion obtenerOrganizacionPorId(int id) {
        return organizacionRepository.findById(id).orElse(null);
    }

    // Metodo para guardar una nueva organizacion
    public Organizacion guardarOrganizacion(Organizacion organizacion) {
        return organizacionRepository.save(organizacion);
    }
}
