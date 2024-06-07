package school.sptech.apicodando.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import school.sptech.apicodando.api.domain.educador.repository.EducadorRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Teste de integração EducadorController")
class EducadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EducadorRepository educadorRepository;

    @Nested
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Teste de buscar por IdEducador")
    public class EducadorSaveTeste {test



        @Test
        @DirtiesContext
        @DisplayName("1 - Deve salvar um educador com sucesso")
        void testSalvarEducadorComSucesso() throws Exception {



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
        @DisplayName("2 - Deve retornar erro ao tentar buscar um educador que não existe")
        void testBuscarEducadorInexistente() throws Exception {
            // Implementação do teste
        }
    }


    @Nested
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Teste de buscar por IdEducador")
    public class EducadorBuscarPorIdTeste {

        @Test
        @DirtiesContext
        @DisplayName("1 - Deve buscar um educador com sucesso")
        void testBuscarEducadorComSucesso() throws Exception {
            // Implementação do teste
        }

        @Test
        @DirtiesContext
        @DisplayName("2 - Deve retornar erro ao tentar buscar um educador que não existe")
        void testBuscarEducadorInexistente() throws Exception {
            // Implementação do teste
        }
    }

    @Nested
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Teste de excluir por IdEducador")
    public class EducadorExcluirPorIdTeste {

        @Test
        @DirtiesContext
        @DisplayName("1 - Deve deletar um educador com sucesso")
        void testDeleteEducadorComSucesso() throws Exception {
            // Implementação do teste
        }

        @Test
        @DirtiesContext
        @DisplayName("2 - Deve retornar erro ao tentar deletar um educador que não existe")
        void testDeleteEducadorInexistente() throws Exception {
            // Implementação do teste
        }
    }

    @Nested
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Teste de atualizar por IdEducador")
    public class EducadorAtualizarPorIdTeste {

        @Test
        @DirtiesContext
        @DisplayName("1 - Deve atualizar um educador com sucesso")
        void testUpdateEducadorComSucesso() throws Exception {
            // Implementação do teste
        }

        @Test
        @DirtiesContext
        @DisplayName("2 - Deve retornar erro ao tentar atualizar um educador que não existe")
        void testUpdateEducadorInexistente() throws Exception {
            // Implementação do teste
        }
    }
}