package com.example.ApiDeNotas.controllers;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.ApiDeNotas.Entities.Aluno;
import com.example.ApiDeNotas.Service.AlunoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
public class ALunoController {
    
    @Autowired
    private AlunoService alunoService;

    @PostMapping("/aluno/cadastro")
    public ResponseEntity<Aluno> SalvarAluno(@RequestBody Aluno aluno) {
        Aluno alunosalvo = alunoService.salvarAluno(aluno);
        return new ResponseEntity<>(alunosalvo, HttpStatus.CREATED);
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<Aluno> BuscarAlunoPorId(@PathVariable Long id) {
        ResponseEntity<Aluno> response;
        Optional<Aluno> alunoOptional = alunoService.BuscarAlunoPorId(id);
        if(alunoOptional.isPresent()){
           response = new ResponseEntity<>(alunoOptional.get(), HttpStatus.OK);
        }else{
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/aluno/atualizar/{id}")
    public ResponseEntity<Optional<Aluno>> AtualizarAluno(@PathVariable Long id, @RequestBody Aluno  NovosDados) {
        Optional<Aluno> alunOptional = alunoService.BuscarAlunoPorId(id);
        if(alunOptional.isPresent()){
            Optional<Aluno> alunoAtualizado = alunoService.AtualizarAluno(id, NovosDados);
            return ResponseEntity.ok().body(alunoAtualizado);
        }else{
            return ResponseEntity.notFound().build();
        }
        
    }

    @DeleteMapping("/aluno/excluir/{id}")
    public ResponseEntity<Void>  ExcluirAluno(@PathVariable Long id){
            alunoService.ExcluirAluno(id);
            return ResponseEntity.ok().build(); 
    }

    
    
    






}
