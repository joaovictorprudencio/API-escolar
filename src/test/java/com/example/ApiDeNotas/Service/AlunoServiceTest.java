package com.example.ApiDeNotas.Service;





import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.ApiDeNotas.Entities.Aluno;
import com.example.ApiDeNotas.Entities.Escola;
import com.example.ApiDeNotas.repository.AlunoRepository;
import com.example.ApiDeNotas.repository.EscolaRepository;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private EscolaRepository escolaRepository;

    @Mock
    private EscolaService escolaService;

    @InjectMocks
    private AlunoService alunoService;


    @Test
    public void testSalvarAluno() {
        // Criar uma escola
        Escola escola = new Escola();
        escola.setNome("escola");
        escola.setTelefone("");
        escola.setCodigo(1212);
        escola.setCep(300);
        escola.setId((long)1);

        // Criar um aluno 
        Aluno aluno = new Aluno();
        aluno.setMatricula(123456);
        aluno.setNome("João");
        aluno.setEmail("null");
        aluno.setEscola(escola);
      
       
        when(escolaService.BuscarEscolaPorId((long)1)).thenReturn(Optional.of(escola));
        
        when(alunoRepository.save(aluno)).thenReturn(aluno);
        
        Aluno AlunoSalvo = alunoService.salvarAluno(aluno);
        
       assertEquals(123456, AlunoSalvo.getMatricula());
    }
    


   @Test
   public void testBuscarAlunoPorId(){
   
        Escola escola = new Escola();
        escola.setId(1);
          
        Aluno aluno = new Aluno();
        aluno.setId((long) 1);
        aluno.setMatricula(123456);
        aluno.setNome("João");
        aluno.setEscola(escola); 
      

         when(alunoRepository.findById((long) 1)).thenReturn(Optional.of(aluno));

         Optional<Aluno> alunoOptional = alunoService.BuscarAlunoPorId((long) 1);

         Aluno alunoPego = alunoOptional.get();
          
         assertEquals(123456, alunoPego.getMatricula());
      

    }

    @Test
    public void TestAtualizar(){
        Aluno aluno = new Aluno();
        aluno.setId((long) 1);
        aluno.setMatricula(123456);
        aluno.setNome("João");

        Aluno aluno2 = new Aluno();
        aluno2.setId((long) 1);
        aluno2.setMatricula(3030);
        aluno2.setNome("Caarlros");
       

        when(alunoRepository.findById((long)1)).thenReturn(Optional.of(aluno));
        when(alunoRepository.save(Mockito.any(Aluno.class))).thenReturn(aluno2);

        Optional<Aluno> aluOptional = alunoService.AtualizarAluno((long)1, aluno2);

        Aluno AlunoNovo = aluOptional.get();

        assertEquals(3030, AlunoNovo.getMatricula());
    }
    
     @Test
    public void TestDeletar(){

         doNothing().when(alunoRepository).deleteById((long) 1);
         

        alunoService.ExcluirAluno((long) 1);

        verify(alunoRepository).deleteById((long) 1);


    }









}


