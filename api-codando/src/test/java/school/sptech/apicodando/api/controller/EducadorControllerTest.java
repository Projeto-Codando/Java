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
    @DisplayName("Teste de salvar um Educador")
    public class EducadorSaveTeste {

        @Test
        @DirtiesContext
        @DisplayName("Deve retornar erro quando o nome está faltando")
        void testSalvarEducadorSemNome() throws Exception {
            var json = """
                    {
                      "sobrenome": "Cunha",
                      "email": "matheus.cunha@email.com",
                      "senha": "senha123456"
                    }
                """;

            mockMvc.perform(post("/educadores")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("Deve retornar erro quando o sobrenome está faltando")
        void testSalvarEducadorSemSobrenome() throws Exception {
            var json = """
                    {
                      "nome": "Matheus",
                      "email": "matheus.cunha@email.com",
                      "senha": "senha123456"
                    }
                """;

            mockMvc.perform(post("/educadores")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("Deve retornar erro quando o email está faltando")
        void testSalvarEducadorSemEmail() throws Exception {
            var json = """
                    {
                      "nome": "Matheus",
                      "sobrenome": "Cunha",
                      "senha": "senha123456"
                    }
                """;

            mockMvc.perform(post("/educadores")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("Deve retornar erro quando o email está inválido")
        void testSalvarEducadorComEmailInvalido() throws Exception {
            var json = """
                    {
                      "nome": "Matheus",
                      "sobrenome": "Cunha",
                      "email": "ronaldo2002",
                      "senha": "senha123456"
                    }
                """;

            mockMvc.perform(post("/educadores")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("Deve retornar erro quando a senha está faltando")
        void testSalvarEducadorSemSenha() throws Exception {
            var json = """
                    {
                      "nome": "Matheus",
                      "sobrenome": "Cunha",
                      "email": "matheus.cunha@email.com"
                    }
                """;

            mockMvc.perform(post("/educadores")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest());
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