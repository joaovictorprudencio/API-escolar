package com.example.ApiDeNotas.Service; 



import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.example.ApiDeNotas.Entities.Escola;
import com.example.ApiDeNotas.repository.EscolaRepository;

@ExtendWith(MockitoExtension.class)
public class EscolaServiceTest {

    @Mock
    private EscolaRepository escolaRepository;

   @InjectMocks
   private EscolaService escolaService;

    @Test
    public void testSalvarEscola() {
        // Criar uma escola
        Escola escola = new Escola();
        escola.setCodigo(12345);
        escola.setNome("Escola Teste");
        escola.setCep(60175055);
        escola.setTelefone("97330821");

        when(escolaRepository.save(escola)).thenReturn(escola);

        Escola savedEscola = escolaService.SalvarEscola(escola);
        
        assertThat(savedEscola.getNome()).isEqualTo("Escola Teste");


    }

    @Test
   public void TestBuscar(){
    Escola escola = new Escola();
    escola.setId((long)1);
    escola.setCodigo(12345);
    escola.setNome("Escola Teste");
    escola.setCep(60175055);
    escola.setTelefone("97330821");

    when(escolaRepository.findById((long)1)).thenReturn(Optional.of(escola));

    Optional<Escola> escolatal = escolaService.BuscarEscolaPorId((long)1);

    Escola escolaEncontrada = escolatal.get();

    assertEquals(60175055, escolaEncontrada.getCep());

   }

   @Test
   public void TestUpdateEscola(){
    Escola escola = new Escola();
    escola.setId((long)1);
    escola.setCodigo(12345);
    escola.setNome("Escola Teste");
    escola.setCep(60175055);
    escola.setTelefone("97330821");

    Escola escolaAtualizada = new Escola();
    escolaAtualizada.setId((long)1);
    escolaAtualizada.setCodigo(12345678);
    escolaAtualizada.setNome("Escola atualizada");
    escolaAtualizada.setCep(60175055);
    escolaAtualizada.setTelefone("97330821");

    when(escolaRepository.findById((long)1)).thenReturn(Optional.of(escola));
    when(escolaRepository.save(Mockito.any(Escola.class))).thenReturn(escolaAtualizada);
     
    Optional<Escola> escolaOptional = escolaService.AtualizarEscola((long) 1, escolaAtualizada);

     Escola escolaCorreta = escolaOptional.get();

     assertEquals("Escola atualizada", escolaCorreta.getNome());

   }


   @Test
   public void TestExcluirEscola(){
    Escola escola = new Escola();
    escola.setId((long)1);
    escola.setCodigo(12345);
    escola.setNome("Escola Teste");
    escola.setCep(60175055);
    escola.setTelefone("97330821");

    doNothing().when(escolaRepository).deleteById((long)1);

        
         escolaService.DeletarEscola((long)1);


        Optional<Escola> EscolaDeletada =  escolaService.BuscarEscolaPorId((long)1);

         assertFalse(EscolaDeletada.isPresent());

   }

}
