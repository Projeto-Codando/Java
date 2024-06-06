package school.sptech.apicodando.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import school.sptech.apicodando.api.domain.aluno.Aluno;
import school.sptech.apicodando.api.domain.aluno.repository.AlunoRepository;
import school.sptech.apicodando.api.domain.turma.repository.TurmaRepository;
import school.sptech.apicodando.util.ErrorMessageHelper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Teste de integração AlunoController")
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TurmaRepository turmaRepository;
    @MockBean
    private AlunoRepository alunoRepository;


    @Nested
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("\uD83D\uDCBE Teste de cadastrar aluno")
    public class AlunoCadastroTeste {

        @Test
        @DirtiesContext
        @DisplayName("1 - Deve salvar um aluno com sucesso")
        void testSaveAluno() throws Exception {

            var json = """
                        {
                          "nome": "Matheus",
                          "sobrenome": "Cunha",
                          "apelido": "ismael.og1",
                          "senha": "senha123456",
                          "senhaTurma": "senha1234"
                        }
                    """;

            mockMvc.perform(post("/alunos")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.idAluno").exists())
                    .andExpect(jsonPath("$.nome").value("Matheus"))
                    .andExpect(jsonPath("$.sobrenome").value("Cunha"))
                    .andExpect(jsonPath("$.apelido").value("ismael.og1"))
                    .andExpect(jsonPath("$.status").value(true)) // ou false, dependendo do valor esperado
                    .andExpect(jsonPath("$.moedas").value(0)) // ou outro valor, dependendo do valor esperado
                    .andExpect(jsonPath("$.idTurma").exists())
                    .andExpect(jsonPath("$.idAvatar").value(0)) // ou outro valor, dependendo do valor esperado
                    .andExpect(jsonPath("$.avatares").isArray());
        }

        @Test
        @DirtiesContext
        @DisplayName("2 - Deve retornar erro ao tentar salvar um aluno sem nome")
        void testSaveAlunoSemNome() throws Exception {

            var erros = List.of("must not be blank");

            var json = """
                        {
                          
                          "sobrenome": "Cunha",
                          "apelido": "ismael.og1",
                          "senha": "senha123456",
                          "senhaTurma": "senha1234"
                        }
                    """;

            MvcResult mvcResult = mockMvc.perform(post("/alunos")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            ErrorMessageHelper.assertContainsErrorMessages(mvcResult, erros);
        }

        @Test
        @DirtiesContext
        @DisplayName("2 - Deve retornar erro ao tentar salvar um aluno sem sobrenome")
        void testSaveAlunoSemSobrenome() throws Exception {

            var erros = List.of("must not be blank");

            var json = """
                        {
                          "nome": "Matheus",
                          
                          "apelido": "ismael.og1",
                          "senha": "senha123456",
                          "senhaTurma": "senha1234"
                        }
                    """;

            MvcResult mvcResult = mockMvc.perform(post("/alunos")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            ErrorMessageHelper.assertContainsErrorMessages(mvcResult, erros);
        }

        @Test
        @DirtiesContext
        @DisplayName("2 - Deve retornar erro ao tentar salvar um aluno sem apelido")
        void testSaveAlunoSemApelido() throws Exception {

            var erros = List.of("must not be blank");

            var json = """
                        {
                          "nome": "Matheus",
                          "sobrenome": "Cunha",
                          
                          "senha": "senha123456",
                          "senhaTurma": "senha1234"
                        }
                    """;

            MvcResult mvcResult = mockMvc.perform(post("/alunos")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            ErrorMessageHelper.assertContainsErrorMessages(mvcResult, erros);
        }

        @Test
        @DirtiesContext
        @DisplayName("3 - Deve retornar erro ao tentar salvar um aluno com apelido já usado")
        void testSaveAlunoComApelidoJaUsado() throws Exception {
            var json = """
                    {
                      "nome": "Matheus",
                      "sobrenome": "Cunha",
                      "apelido": "ismael.og1", 
                      "senha": "senha123456",
                      "senhaTurma": "senha1234"
                    }
                """;

            mockMvc.perform(post("/alunos")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isConflict());
        }

        @Test
        @DirtiesContext
        @DisplayName("4 - Deve retornar erro ao tentar salvar um aluno sem senha")
        void testSaveAlunoSemSenha() throws Exception {
            var json = """
                    {
                      "nome": "Matheus",
                      "sobrenome": "Cunha",
                      "apelido": "ismael.og1",
                      "senhaTurma": "senha1234"
                    }
                """;

            mockMvc.perform(post("/alunos")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("5 - Deve retornar erro ao tentar salvar um aluno sem senha da turma")
        void testSaveAlunoSemSenhaTurma() throws Exception {
            var json = """
                    {
                      "nome": "Matheus",
                      "sobrenome": "Cunha",
                      "apelido": "ismael.og1",
                      "senha": "senha123456"
                    }
                """;

            mockMvc.perform(post("/alunos")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("5 - Deve retornar erro ao tentar salvar um aluno sem senha da turma")
        void testSaveAlunoComSenhaTurmaInexistente() throws Exception {
            var json = """
                   {
                  "nome": "Matheus",
                  "sobrenome": "Cunha",
                  "apelido": "ismael.og1",
                  "senha": "senha123456",
                  "senhaTurma": "RONALDO2002"
                }
                """;


            Mockito.when(turmaRepository.findBySenha("RONALDO2002")).thenReturn(Optional.empty());



            mockMvc.perform(post("/alunos")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DirtiesContext
        @DisplayName("6 - Deve retornar erro ao tentar salvar um aluno sem a senha da turma estar associada a uma turma")
        void testSaveAlunoSemSenhaDaTurmaEstarAssociadaAUmaTurma() throws Exception {
            var json = """
                    {
                      "nome": "Matheus",
                      "sobrenome": "Cunha",
                      "apelido": "ismael.og1",
                      "senha": "senha123456",
                      "senhaTurma": "senha1234" // esta senha da turma não está associada a nenhuma turma
                    }
                """;

            mockMvc.perform(post("/alunos")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isNotFound());
        }

    }

    @Nested
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("\uD83D\uDCBE Teste de login aluno")
    public class AlunoLoginTeste {

//        @Test
//        @DirtiesContext
//        @DisplayName("1 - Deve salvar um aluno com sucesso")
//        void testSaveAluno() throws Exception {
//
//        }

    }

    @Nested
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("\uD83D\uDCBE Teste de buscar por IdAluno")
    public class AlunoExcluirPorIdTeste {

        @Test
        @DirtiesContext
        @DisplayName("1 - Deve deletar um aluno com sucesso")
        void testDeleteAlunoComSucesso() throws Exception {
            UUID idAluno = UUID.randomUUID();

            // Cria um objeto Aluno
            Aluno alunoExistente = new Aluno();
            alunoExistente.setIdAluno(idAluno);
            // outros atributos do aluno...

            // Configura o Mockito para retornar um Optional contendo o alunoExistente quando o método findById for chamado
            Mockito.when(alunoRepository.findById(idAluno)).thenReturn(Optional.of(alunoExistente));

            // Configura o Mockito para não fazer nada quando o método delete for chamado
            Mockito.doNothing().when(alunoRepository).delete(alunoExistente);

            mockMvc.perform(delete("/alunos/{id}", idAluno))
                    .andExpect(status().isOk());

            // Verifica se o método delete do alunoRepository foi chamado com o alunoExistente
            Mockito.verify(alunoRepository, Mockito.times(1)).delete(alunoExistente);
        }

        @Test
        @DirtiesContext
        @DisplayName("2 - Deve retornar erro ao tentar deletar um aluno que não existe")
        void testDeleteAlunoInexistente() throws Exception {
            UUID idAluno = UUID.randomUUID();

            // Configura o Mockito para retornar um Optional.empty() quando o método findById for chamado
            Mockito.when(alunoRepository.findById(idAluno)).thenReturn(Optional.empty());

            mockMvc.perform(delete("/alunos/{id}", idAluno))
                    .andExpect(status().isNotFound());
        }

    }

    @Nested
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("\uD83D\uDCBE Teste de atualizar por IdAluno")
    public class AlunoAtualizarPorIdTeste {

        @MockBean
        private AlunoRepository alunoRepository;

        @Test
        @DirtiesContext
        @DisplayName("1 - Deve atualizar um aluno com sucesso")
        void testUpdateAlunoComSucesso() throws Exception {
            UUID idAluno = UUID.randomUUID();

            // Cria um objeto Aluno
            Aluno alunoExistente = new Aluno();
            alunoExistente.setIdAluno(idAluno);
            // outros atributos do aluno...

            // Configura o Mockito para retornar um Optional contendo o alunoExistente quando o método findById for chamado
            Mockito.when(alunoRepository.findById(idAluno)).thenReturn(Optional.of(alunoExistente));

            // Configura o Mockito para retornar o alunoExistente quando o método save for chamado
            Mockito.when(alunoRepository.save(Mockito.any(Aluno.class))).thenReturn(alunoExistente);

            var json = """
                {
                  "nome": "Matheus",
                  "sobrenome": "Cunha",
                  "apelido": "ismael.og1",
                  "senha": "senha123456",
                  "senhaTurma": "senha1234"
                }
            """;

            mockMvc.perform(MockMvcRequestBuilders.put("/alunos/{id}", idAluno)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isOk());

            // Verifica se o método save do alunoRepository foi chamado
            Mockito.verify(alunoRepository, Mockito.times(1)).save(Mockito.any(Aluno.class));
        }

        @Test
        @DirtiesContext
        @DisplayName("2 - Deve retornar erro ao tentar atualizar um aluno que não existe")
        void testUpdateAlunoInexistente() throws Exception {
            UUID idAluno = UUID.randomUUID();

            // Configura o Mockito para retornar um Optional.empty() quando o método findById for chamado
            Mockito.when(alunoRepository.findById(idAluno)).thenReturn(Optional.empty());

            var json = """
                {
                  "nome": "Matheus",
                  "sobrenome": "Cunha",
                  "apelido": "ismael.og1",
                  "senha": "senha123456",
                  "senhaTurma": "senha1234"
                }
            """;

            mockMvc.perform(MockMvcRequestBuilders.put("/alunos/{id}", idAluno)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isNotFound());
        }}


}
