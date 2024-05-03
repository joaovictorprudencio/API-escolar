package com.example.ApiDeNotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiDeNotas.Entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>  {
    
}
