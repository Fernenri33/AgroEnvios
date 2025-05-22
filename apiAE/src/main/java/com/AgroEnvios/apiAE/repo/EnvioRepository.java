package com.AgroEnvios.apiAE.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AgroEnvios.apiAE.Models.Envio;
import com.AgroEnvios.apiAE.Models.Usuario;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Integer> {

    List<Envio> findByProveedor(Usuario proveedor);


}
