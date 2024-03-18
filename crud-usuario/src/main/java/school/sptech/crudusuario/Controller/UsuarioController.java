package school.sptech.crudusuario.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.crudusuario.Aluno;
import school.sptech.crudusuario.Professor;
import school.sptech.crudusuario.Usuario;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    List<Aluno> alunos = new ArrayList<>();
    List<Professor> professores = new ArrayList<>();
    List<Usuario> usuarios = new ArrayList<>();

    private int id = 0;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> usuariosPorId(@PathVariable int id) {

        Usuario p = buscarUsuarioPorId(id);

        if (p == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(p);
    }

    //verbo GET
    @GetMapping("/alunos")
    public ResponseEntity<List<Aluno>> listarAlunos() {
        if (alunos.isEmpty() && usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(alunos);
    }

    //verbo POST
    @PostMapping("/alunos")
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno a) {

        if (nickExistente(a.getNickName(), a.getId())) {
            return ResponseEntity.status(409).build();
        }

        if (contemCaracterEspecial(a.getNickName())) {
            return ResponseEntity.status(400).build();
        }

        a.setId(++id);
        alunos.add(a);
        usuarios.add(a);
        return ResponseEntity.status(201).body(a);
    }

    //verbo PUT
    @PutMapping("/alunos/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@RequestBody Aluno a, @PathVariable int id) {

        Usuario u = buscarUsuarioPorId(id);
        Aluno aluno = buscarAlunoPorId(id);

        if (u == null) {
            return ResponseEntity.status(404).build();
        }

//        if (u.getId() != a.getId()) {
//            return ResponseEntity.status(403).build();
//            // http status 403 == forbidden
//        }

        u.setDataNascimento(a.getDataNascimento());
        u.setNome(a.getNome());
        u.setSenha(a.getSenha());

        aluno.setDataNascimento(a.getDataNascimento());
        aluno.setNome(a.getNome());
        aluno.setSenha(a.getSenha());
        aluno.setNickName(a.getNickName());

        return ResponseEntity.status(200).body(a);
    }

    @DeleteMapping("/alunos/{id}")
    public ResponseEntity<Aluno> deletarAluno(@PathVariable int id) {
        Usuario usuarioDeletado = buscarUsuarioPorId(id);
        Aluno alunodeletado = buscarAlunoPorId(id);

        if (alunodeletado == null) {
            return ResponseEntity.status(404).build();
        }

        alunos.remove(alunodeletado);
        usuarios.remove(usuarioDeletado);

        return ResponseEntity.status(200).body(alunodeletado);
    }

    //PROFESSORES//

    //verbo GET
    @GetMapping("/professores")
    public ResponseEntity<List<Professor>> listaProfessores() {
        if (professores.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(professores);
    }



    //verbo POST
    @PostMapping("/professores")
    public ResponseEntity<Professor> cadastrarProfessor(@RequestBody Professor p) {

        Usuario usuario = buscarUsuarioPorId(p.getId());

        if (usuario != null){
            return ResponseEntity.status(409).build();
        }

        if (emailExistente(p.getEmail(), p.getId())) {
            return ResponseEntity.status(409).build();
        }

        if (!emailContemArroba(p.getEmail())) {
            return ResponseEntity.status(400).build();
        }

        p.setId(++id);
        professores.add(p);
        usuarios.add(p);
        return ResponseEntity.status(201).body(p);
    }

    //verbo PUT
    @PutMapping("/professores/{id}")
    public ResponseEntity<Professor> atualizarProfessor(@RequestBody Professor p, @PathVariable int id) {

        Professor professor = buscarProfessorPorId(id);
        Usuario usuario = buscarUsuarioPorId(id);

        if (professor == null) {
            return ResponseEntity.status(404).build();
        }

        if (!emailContemArroba(p.getEmail())) {
            return ResponseEntity.status(400).build();
        }

        professor.setNome(p.getNome());
        professor.setDataNascimento(p.getDataNascimento());
        professor.setEmail(p.getEmail());
        professor.setSenha(p.getSenha());

        usuario.setNome(p.getNome());
        usuario.setDataNascimento(p.getDataNascimento());
//        usuario.setEmail(p.getEmail());
        usuario.setSenha(p.getSenha());

        return ResponseEntity.status(200).body(p);

//        if (exitePorI(id, "professor")) {
//            professores.set(id, p);
//            for (int i = 0; i < usuarios.size(); i++) {
//                if (usuarios.get(i).getUsername().equalsIgnoreCase(p.getUsername())) {
//                    usuarios.set(i, p);
//                    break;
//                }
//            }
//            return ResponseEntity.status(200).body(p);
//
//        }

    }

    @DeleteMapping("/professores/{id}")
    public ResponseEntity<Professor> deletarProfessor(@PathVariable int id) {

        Professor professor = buscarProfessorPorId(id);
        Usuario usuario = buscarUsuarioPorId(id);

        if (professor == null) {
            return ResponseEntity.status(404).build();
        }

        professores.remove(professor);
        usuarios.remove(usuario);

        return ResponseEntity.status(200).body(professor);
//        if (exitePorI(id, "professor")) {
//            professores.remove(id);
//            usuarios.remove(id);
//            return ResponseEntity.status(200).build();
//        }
//        return ResponseEntity.status(404).build();
    }

//

    public boolean emailContemArroba(String email) {
        if (email.contains("@")) {
            return true;
        }
        return false;
    }

    public boolean contemCaracterEspecial(String s) {
        if (s.contains("@") || s.contains("#") || s.contains("$") || s.contains("¨%") || s.contains("º") ||
                s.contains("(") || s.contains(")") || s.contains("!") || s.contains("+") || s.contains("-") ||
                s.contains("/") || s.contains("*") || s.contains(",") || s.contains(";") || s.contains(":") ||
                s.contains("'") || s.contains("<") || s.contains(">") || s.contains("=") || s.contains("§") ||
                s.contains("{") || s.contains("}") || s.contains("[") || s.contains("]") || s.contains("^") ||
                s.contains("£") || s.contains("¢") || s.contains("²") || s.contains("¹") || s.contains("ª")
        ) {
            return true;
        }
        return false;
    }

    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public Aluno buscarAlunoPorId(int u) {
        for (Aluno a : alunos) {
            if (u == a.getId()) {
                return a;
            }
        }
        return null;
    }

    public Professor buscarProfessorPorId(int id) {
        for (Professor p : professores) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }


    public boolean emailExistente(String email, int id) {
        for (Professor p : professores) {
            if (p.getEmail().equals(email) && p.getId() != id) {
                return true;
            }
        }
        return false;
    }

    public boolean nickExistente(String nick, int id) {
        for (Aluno a : alunos) {
            if (a.getNickName().equals(nick) && a.getId() != id) {
                return true;
            }
        }
        return false;
    }

//    public boolean exitePorI(int i, String q) {
//        if (q.equals("aluno")) {
//            if (i < alunos.size() && i >= 0) {
//                return true;
//            } else {
//                return false;
//            }
//        } else if (q.equals("professor")) {
//            if (i < professores.size() && i >= 0) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//        return false;
//}

}