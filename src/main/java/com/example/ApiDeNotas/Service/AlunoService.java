package com.example.ApiDeNotas.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ApiDeNotas.Entities.Aluno;
import com.example.ApiDeNotas.Entities.Escola;
import com.example.ApiDeNotas.repository.AlunoRepository;

@Service
public class AlunoService {
    @Autowired
    EscolaService escolaservice;
    @Autowired
    AlunoRepository alunoRepository;


   

    public Aluno salvarAluno(Aluno aluno) {
        Escola escolaDoAluno = aluno.getEscola();
        long escolaId = escolaDoAluno.getId();
        Escola escola = escolaservice.BuscarEscolaPorId(escolaId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Escola não encontrada com o ID: " + escolaId));
        aluno.setEscola(escola);
        return alunoRepository.save(aluno);
    }


    public Optional<Aluno> AtualizarAluno(Long id , Aluno NovosDados ){
        Optional<Aluno> AlunoOptional = alunoRepository.findById(id);
        if(AlunoOptional.isPresent()){
            Aluno alunoExistente = AlunoOptional.get();
            alunoExistente.setMatricula(NovosDados.getMatricula());
            alunoExistente.setNome(NovosDados.getNome());
            alunoExistente.setNascimento(NovosDados.getNascimento());
            alunoExistente.setEmail(NovosDados.getEmail());
            alunoExistente.setEscola(NovosDados.getEscola());
            
            return Optional.of(alunoRepository.save(alunoExistente));
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse aluno não foi encontrado por id: " + id);
        }
  
    }

   public Optional<Aluno> BuscarAlunoPorId(Long id){
        return alunoRepository.findById(id);
    
   }

   public void ExcluirAluno(Long id){
        alunoRepository.deleteById(id);
   }
   

}
