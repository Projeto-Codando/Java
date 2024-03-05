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

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        if (usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        } return ResponseEntity.status(200).body(usuarios);
    }

    //verbo GET
    @GetMapping("/alunos")
    public ResponseEntity<List<Aluno>> listarAlunos(){
        if(alunos.isEmpty() && usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        } else{
            return ResponseEntity.status(200).body(alunos);
        }
    }

    //verbo POST
    @PostMapping("/alunos")
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno p){
        alunos.add(p);
        usuarios.add(p);
        return ResponseEntity.status(201).body(p);
    }

    //verbo PUT
    @PutMapping("/alunos/{indice}")
    public ResponseEntity<Aluno> atualizarAluno(@RequestBody Aluno p, @PathVariable int indice){
        if(exitePorI(indice, "aluno")){
            alunos.set(indice, p);
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getNome().equalsIgnoreCase(p.getNome())){
                    usuarios.set(i, p);
                    break;
                }
            }
            return ResponseEntity.status(200).body(p);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/alunos/{indice}")
    public ResponseEntity<Professor> deletarAluno(@PathVariable int indice){
        if (exitePorI(indice, "aluno")){
            alunos.remove(indice);
            usuarios.remove(indice);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }


    //verbo GET
    @GetMapping("/professores")
    public ResponseEntity<List<Professor>> listaProfessores(){
        if(professores.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.status(200).body(professores);
        }
    }

    //verbo POST
    @PostMapping("/professores")
    public ResponseEntity<Professor> cadastrarProfessor(@RequestBody Professor p){
        professores.add(p);
        usuarios.add(p);
        return ResponseEntity.status(201).body(p);
    }

    //verbo PUT
    @PutMapping("/professores/{indice}")
    public ResponseEntity<Professor> atualizarProfessor(@RequestBody Professor p, @PathVariable int indice){
        if(exitePorI(indice, "professor")){
            professores.set(indice, p);
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getUsername().equalsIgnoreCase(p.getUsername())){
                    usuarios.set(i, p);
                    break;
                }
            }
            return ResponseEntity.status(200).body(p);

        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/professores/{indice}")
    public ResponseEntity<Professor> deletarProfessor(@PathVariable int indice){
        if (exitePorI(indice, "professor")){
            professores.remove(indice);
            usuarios.remove(indice);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    public boolean exitePorI(int i, String q){
        if (q.equals("aluno")){
            if(i < alunos.size() && i >= 0){
                return true;
            } else {
                return false;
            }
        } else if (q.equals("professor")) {
            if (i < professores.size() && i >= 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
