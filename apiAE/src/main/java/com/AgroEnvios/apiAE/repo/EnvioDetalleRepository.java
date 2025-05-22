package com.AgroEnvios.apiAE.repo;

import com.AgroEnvios.apiAE.Models.Envio;
import com.AgroEnvios.apiAE.Models.EnvioDetalle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioDetalleRepository extends JpaRepository<EnvioDetalle, Integer> {

    List<EnvioDetalle> findByEnvio(Envio Envio);
}

