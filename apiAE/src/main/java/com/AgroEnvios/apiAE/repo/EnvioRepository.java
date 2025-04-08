package com.AgroEnvios.apiAE.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AgroEnvios.apiAE.Models.Envio;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Integer> {


}
