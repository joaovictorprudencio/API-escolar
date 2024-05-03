package com.example.ApiDeNotas.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import com.example.ApiDeNotas.Entities.Escola;
import com.example.ApiDeNotas.Service.EscolaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class EscolaController {
    

    @Autowired
    EscolaService escolaService;
    

    @PostMapping("/escola/cadastro")
    public ResponseEntity<Escola> SalvarEscola(@RequestBody Escola escola) {
      Escola NovaEscola = escolaService.SalvarEscola(escola);
        return new ResponseEntity<>(NovaEscola, HttpStatus.CREATED);
        
    }

    @GetMapping("/escola/buscar/{id}")
    public ResponseEntity<Escola> BuscarEscolaPorId(@PathVariable Long id){
      ResponseEntity<Escola> resposta;
       Optional<Escola> escolaOptional = escolaService.BuscarEscolaPorId(id);
       if(escolaOptional.isPresent()){
          resposta = new ResponseEntity<>(escolaOptional.get() , HttpStatus.OK);
       }else{
         resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

      return resposta;

    }

    @PutMapping("/escola/atualizar/ {id}")
    public ResponseEntity<Optional<Escola>> AtualizarEscola(@PathVariable Long id , @RequestBody Escola escola){

      Optional<Escola> Buscar = escolaService.BuscarEscolaPorId(id);
      if(Buscar.isPresent()){
        Optional<Escola> escolaAtualizada = escolaService.AtualizarEscola(id, escola);
        return ResponseEntity.ok().body(escolaAtualizada);
      } else {
          return ResponseEntity.notFound().build();
      }
   
    }
    
    @DeleteMapping("/escola/excluir/{id}")
    public ResponseEntity<Void> ExcluirEscola(@PathVariable Long id){
       escolaService.DeletarEscola(id);
      return ResponseEntity.ok().build();
    }





}
