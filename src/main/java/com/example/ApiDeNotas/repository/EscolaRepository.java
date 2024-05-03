package com.example.ApiDeNotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiDeNotas.Entities.Escola;

public interface EscolaRepository extends JpaRepository <Escola, Long> {
    
}
