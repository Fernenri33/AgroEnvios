package com.AgroEnvios.apiAE.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroEnvios.apiAE.Models.EnvioDetalle;
import com.AgroEnvios.apiAE.Models.Envio;
import com.AgroEnvios.apiAE.repo.EnvioDetalleRepository;
import com.AgroEnvios.apiAE.repo.EnvioRepository;

@Service
public class EnvioDetalleService {

    @Autowired
    private EnvioDetalleRepository envioDetalleRepository;

    @Autowired
    private EnvioRepository envioRepository;


    public EnvioDetalleService() {
    }

    public void crearEnvioDetalle(EnvioDetalle envioDetalle) {
        envioDetalleRepository.save(envioDetalle);
    }

    public void crearEnvioDetalle(){
        EnvioDetalle envioDetalle = new EnvioDetalle();
        envioDetalleRepository.save(envioDetalle);
    }

    public EnvioDetalle obtenerEnvioDetallePorId(Integer id) {
        return envioDetalleRepository.findById(id).orElse(null);
    }
    
    public void eliminarEnvioDetalle(Integer id) {
        envioDetalleRepository.deleteById(id);
    }

    public void actualizarEnvioDetalle(EnvioDetalle envioDetalle) {
        if (envioDetalleRepository.existsById(envioDetalle.getId())) {
            envioDetalleRepository.save(envioDetalle);
        } else {
            throw new RuntimeException("EnvioDetalle no se encontró: " + envioDetalle.getId());
        }
    }

    public List<EnvioDetalle> getEnvioDetallesByEnvioId(Integer idEnvio) {

        Envio envio = envioRepository.findById(idEnvio).orElse(null);
        List<EnvioDetalle> envioDetalles = envioDetalleRepository.findByEnvio(envio);

        return envioDetalles;
        
    }
}
