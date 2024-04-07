package school.sptech.crudusuario.Controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import school.sptech.crudusuario.Aluno;
import school.sptech.crudusuario.Professor;
import school.sptech.crudusuario.Usuario;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc m;
    @Autowired
    private UsuarioController usuarioController;

    @Nested
    @DisplayName("GET")
    class Get {
        @Test
        @DisplayName("Quando não há usuários, então retorna status 204")
        void quandoNaoHaUsuariosNaListaEntaoRetornaStatus204() throws Exception {

            configureListEmpty();

            m.perform(get("/usuarios"))
                    .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("Quando não há alunos, então retorna status 204")
        void quandoNaoHaAlunosNaListaEntaoRetornaStatus204() throws Exception {

            configureListEmpty();

            m.perform(get("/usuarios/alunos"))
                    .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("Quando não há professoresa, então retorna status 204")
        void quandoNaoHaProfessoresNaListaEntaoRetornaStatus204() throws Exception {

            configureListEmpty();

            m.perform(get("/usuarios/professores"))
                    .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("Quando há alunos, retorna 200")
        void alunos200() throws Exception {

            configureListOfMocks();

            m.perform(get("/usuarios/alunos"))
                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$[0].nome").value("Ismael"))
//                    .andExpect(jsonPath("$[1].placa").value("BRA2E20"))
//                    .andExpect(jsonPath("$[2].placa").value("BRA2E21"))
            ;
        }
        @Test
        @DisplayName("Quando há professores, retorna 200")
        void professores200 () throws Exception {

            configureListOfMocks();

            m.perform(get("/usuarios/professores"))
                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$[0].nome").value("Ismael"))
//                    .andExpect(jsonPath("$[1].placa").value("BRA2E20"))
//                    .andExpect(jsonPath("$[2].placa").value("BRA2E21"))
            ;
        }
    }

    private void configureListOfMocks() {

        try {

            Field usuariosField = usuarioController.getClass().getDeclaredField("alunos");
            usuariosField.setAccessible(true);

            List<Usuario> usuarios = (List<Usuario>) usuariosField.get(usuarioController);
            List<Aluno> alunos = (List<Aluno>) usuariosField.get(usuarioController);
            List<Professor> professores = (List<Professor>) usuariosField.get(usuarioController);

            usuarios.clear();
            alunos.clear();
            professores.clear();

            Aluno usuario1 = (Aluno) getInstanceAlunoForQuery(
                    1,
                    "Ismael",
                    "ismael@gmail.com",
                    "ismael123",
                    "ismael.og1",
                    2020
            );


            Professor usuario2 = (Professor) getInstanceProfessorForQuery(
                    1,
                    "Wanessa",
                    "20/01/1999",
                    "wanessa123",
                    "wanessa@dominio.com"
            );

            usuarios.add(usuario1);
            usuarios.add(usuario2);

            professores.add(usuario2);
            alunos.add(usuario1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void configureListEmpty() {
        try {
            Field usuariosField = usuarioController.getClass().getDeclaredField("usuarios");
            usuariosField.setAccessible(true);

            List<Usuario> usuarios = (List<Usuario>) usuariosField.get(usuarioController);
            List<Aluno> alunos = (List<Aluno>) usuariosField.get(usuarioController);
            List<Professor> professores = (List<Professor>) usuariosField.get(usuarioController);

            usuarios.clear();
            alunos.clear();
            professores.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getInstanceForQuery(
            int id,
            String nome,
            String dataNascimento,
            int idade,
            String senha) {
        try {

            Class<?> clazz = Class.forName("school.sptech.crudusuario.Usuario");
            Object usuario = clazz.getDeclaredConstructor().newInstance();

            setField(usuario, "id", id);
            setField(usuario, "nome", nome);
//            setField(usuario, "idade", idade);
            setField(usuario, "senha", senha);
            setField(usuario, "dataNascimento", dataNascimento);

            return usuario;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object getInstanceAlunoForQuery(
            int id,
            String nome,
            String dataNascimento,
//            int idade,
            String senha,
            String nickName,
            int codigoSala
    ) {
        try {

            Class<?> clazz = Class.forName("school.sptech.crudusuario.Aluno");
            Object aluno = clazz.getDeclaredConstructor().newInstance();

            setField(aluno, "id", id);
            setField(aluno, "nome", nome);
//            setField(usuario, "idade", idade);
            setField(aluno, "senha", senha);
            setField(aluno, "dataNascimento", dataNascimento);
            setField(aluno, "nickName", nickName);
            setField(aluno, "codigoSala", codigoSala);


            return aluno;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object getInstanceProfessorForQuery(
            int id,
            String nome,
            String dataNascimento,
//            int idade,
            String senha,
            String email
    ) {
        try {

            Class<?> clazz = Class.forName("school.sptech.crudusuario.Professor");
            Object professor = clazz.getDeclaredConstructor().newInstance();

            setField(professor, "id", id);
            setField(professor, "nome", nome);
//            setField(usuario, "idade", idade);
            setField(professor, "senha", senha);
            setField(professor, "dataNascimento", dataNascimento);
            setField(professor, "email", email);


            return professor;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void setField(Object object, String fieldName, Object value) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }

}