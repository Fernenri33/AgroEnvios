package com.AgroEnvios.apiAE.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AgroEnvios.apiAE.Models.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

}
