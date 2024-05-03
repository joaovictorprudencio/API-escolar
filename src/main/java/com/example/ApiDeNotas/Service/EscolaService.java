package com.example.ApiDeNotas.Service; 


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.example.ApiDeNotas.Entities.Escola;
import com.example.ApiDeNotas.repository.EscolaRepository;



@Service
public class EscolaService {
 
    
@Autowired
private EscolaRepository escolaRepository;

public Escola SalvarEscola(Escola escola){
    return escolaRepository.save(escola);
}

public Optional<Escola> BuscarEscolaPorId(long id){

    return escolaRepository.findById(id);
}

public void DeletarEscola(long id){

    escolaRepository.deleteById(id);
}



public Optional<Escola> AtualizarEscola(long id, Escola escolaAtualizada){
    Optional<Escola> escolaOptional = escolaRepository.findById(id);

   if(escolaOptional.isPresent()){

     Escola escolaExistente = escolaOptional.get();

     escolaExistente.setCodigo(escolaAtualizada.getCodigo());
     escolaExistente.setNome(escolaAtualizada.getNome());
     escolaExistente.setCep(escolaAtualizada.getCep());
     escolaExistente.setTelefone(escolaAtualizada.getTelefone());
         return Optional.of(escolaRepository.save(escolaExistente));
      

   }else{
     throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A  escola não existe no banco de dados , com id: " + id);
   }
    

}






}
