package com.AgroEnvios.apiAE.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroEnvios.apiAE.Models.Envio;
import com.AgroEnvios.apiAE.Models.Usuario;
import com.AgroEnvios.apiAE.repo.EnvioRepository;

@Service
public class EnviosService {

@Autowired
private EnvioRepository envioRepository;

    public List<Envio> getAllEnvios() {
        return envioRepository.findAll();
    }

    public Envio getEnvioById(Integer id) {
        return envioRepository.findById(id).orElse(null);
    }

    public Envio createEnvio(Envio envio) {
        return envioRepository.save(envio);
    }

    public Envio updateEnvio(Envio envio) {
        return envioRepository.save(envio);
    }

    public void deleteEnvio(Integer id) {
        envioRepository.deleteById(id);
    }

    public List<Envio> getEnviosByUsuario (Usuario usuario) {
        return envioRepository.findByProveedor(usuario);
    }
}
