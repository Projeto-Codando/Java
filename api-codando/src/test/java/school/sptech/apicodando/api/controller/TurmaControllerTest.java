package school.sptech.apicodando.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import school.sptech.apicodando.api.domain.aluno.repository.AlunoRepository;
import school.sptech.apicodando.api.domain.turma.repository.TurmaRepository;
import school.sptech.apicodando.util.ErrorMessageHelper;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Teste de integração EducadorController")
class TurmaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TurmaRepository turmaRepository;

    @Nested
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("\uD83D\uDCBE Teste de cadastrar aluno")
    class TurmaSaveTeste {
        @Test
        @DirtiesContext
        @DisplayName("Deve retornar erro quando o nome está faltando")
        void testSalvarTurmaSemNome() throws Exception {
            var json = """
                        {

                          "senha": "senha123456",
                          "fkEscolaridade": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                          "fkEducador": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                          "fkGrade": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                          "statusTurma": true
                        }
                    """;

            mockMvc.perform(post("/turmas")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DirtiesContext
        @DisplayName("Deve retornar erro quando a senha está faltando")
        void testSalvarTurmaSemSenha() throws Exception {
            var json = """
                        {
                          "nome": "Turma 1",
                          "fkEscolaridade": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                          "fkEducador": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                          "fkGrade": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                          "statusTurma": true
                        }
                    """;

            mockMvc.perform(post("/turmas")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

//        @Test
//        @DirtiesContext
//        @DisplayName("Deve retornar erro quando a fkEscolaridade está faltando")
//        void testSalvarTurmaSemFkEscolaridade() throws Exception {
//            var json = """
//                        {
//                          "nome": "Turma 1",
//                          "senha": "senha123456",
//
//                          "fkEducador": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
//                          "fkGrade": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
//                          "statusTurma": true
//                        }
//                    """;
//
//            mockMvc.perform(post("/turmas")
//                            .contentType("application/json")
//                            .content(json))
//                    .andExpect(status().isNotFound());
//        }

        @Test
        @DirtiesContext
        @DisplayName("Deve retornar erro quando a fkEducador não corresponde a um Educador")
        void testSalvarTurmaSemFkEducador() throws Exception {
            var json = """
                        {
                          "nome": "Turma 1",
                          "senha": "senha123456",
                          "fkEscolaridade": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                          
                          "fkGrade": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                          "statusTurma": true
                        }
                    """;

            mockMvc.perform(post("/turmas")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DirtiesContext
        @DisplayName("Deve retornar erro quando a fkGrade não corresponde a uma grade existente")
        void testSalvarTurmaSemFkGrade() throws Exception {
            var json = """
                        {
                          "nome": "Turma 1",
                          "senha": "senha123456",
                          "fkEscolaridade": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                          "fkEducador": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                          
                          "statusTurma": true
                        }
                    """;

            mockMvc.perform(post("/turmas")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DirtiesContext
        @DisplayName("Deve retornar erro quando statusTurma está faltando")
        void testSalvarTurmaSemStatusTurma() throws Exception {
            var json = """
                          {
                            "nome": "Turma 1",
                    "senha": "senha123456",
                    "fkEscolaridade": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                    "fkEducador": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                    "fkGrade": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                                    
                          }
                      """;

            mockMvc.perform(post("/turmas")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest());
        }
    }
}