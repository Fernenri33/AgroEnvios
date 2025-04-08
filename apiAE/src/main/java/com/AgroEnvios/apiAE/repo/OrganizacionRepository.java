package com.AgroEnvios.apiAE.repo;

import com.AgroEnvios.apiAE.Models.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizacionRepository extends JpaRepository<Organizacion, Integer> {
}
