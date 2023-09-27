package br.com.APi.Api.controllers;

import br.com.APi.Api.repository.InterfaceUsuario;
import br.com.APi.Api.model.Usuario;
import br.com.APi.Api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
//@CrossOrigin(origins = "https://localhost:8080/usuarios")
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService service;

    @Autowired
    private InterfaceUsuario repository;


    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listaUsuarios() {
        return ResponseEntity.status(200).body(service.listarUsuarios());
    }


    @PostMapping
    public ResponseEntity<Object> criarUsuario(@Valid @RequestBody Usuario usuario) {
        //com base no email
        Usuario usuarioo = repository.findByEmail(usuario.getEmail());
//        Usuario id = repository.findEmailById(usuario.getId());
//        Usuario usuarioo = repository.findEmailById(usuario.getId());

        if (usuarioo == null) {
            service.criarUsuarios(usuario);
            return ResponseEntity.status(201).body(usuario); // Usuário criado com sucesso
        } else {
            return ResponseEntity.status(422).build(); // Usuário já existe
        }


    }

    @PostMapping("/login")
    public ResponseEntity<String> login( @RequestBody Usuario usuario) {
        // Chama o método de autenticação do UserService
        boolean autenticado = service.autenticarUsuarios(usuario);

        if (autenticado == true) {
            return ResponseEntity.ok("Autenticado com sucesso"); // Retorna uma resposta de sucesso
        } else {
            return ResponseEntity.status(401).body("Falha na autenticação"); // Retorna uma resposta de erro não autorizado (401)
        }
    }





    @PutMapping
    public ResponseEntity<Usuario >atualizarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.status(201).body(service.atualizarUsuarios(usuario));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Integer id){
        service.deletarUsuarios(id);
        return ResponseEntity.status(204).build();

    }
        }