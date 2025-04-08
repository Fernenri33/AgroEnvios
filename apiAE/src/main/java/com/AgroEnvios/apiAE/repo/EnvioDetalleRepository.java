package com.AgroEnvios.apiAE.repo;

import com.AgroEnvios.apiAE.Models.EnvioDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioDetalleRepository extends JpaRepository<EnvioDetalle, Integer> {
}

