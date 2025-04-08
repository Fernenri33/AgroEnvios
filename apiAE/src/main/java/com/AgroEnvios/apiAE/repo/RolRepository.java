package com.AgroEnvios.apiAE.repo;

import com.AgroEnvios.apiAE.Models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
