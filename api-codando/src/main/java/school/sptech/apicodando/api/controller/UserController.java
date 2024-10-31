package school.sptech.apicodando.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apicodando.api.domain.aluno.TokenRequestDTO;
import school.sptech.apicodando.service.alunoService.AlunoService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private AlunoService service;

    @PostMapping("/{id}/register-token")
    public ResponseEntity<String> registerToken(@PathVariable Integer id, @RequestBody TokenRequestDTO tokenRequest) {
        service.updateFcmToken(id, tokenRequest.getToken());
        return ResponseEntity.ok("Token registrado com sucesso!");
    }

}