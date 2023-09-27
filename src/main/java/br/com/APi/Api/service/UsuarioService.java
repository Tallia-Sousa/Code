package br.com.APi.Api.service;


import br.com.APi.Api.model.Usuario;
import br.com.APi.Api.repository.InterfaceUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
     @Service
public class UsuarioService {

         private InterfaceUsuario repository;
         private PasswordEncoder passwordesconder;

         @Autowired
         public UsuarioService(InterfaceUsuario repository) {
             this.repository = repository;
             this.passwordesconder = new BCryptPasswordEncoder();


         }

         public List<Usuario> listarUsuarios() {
             List<Usuario> list = repository.findAll();
             return list;
         }


         public Usuario criarUsuarios(Usuario usuario) {
             String esconder = this.passwordesconder.encode(usuario.getSenha());
             usuario.setSenha(esconder);
             Usuario usuarioNovo = repository.save(usuario);

             return usuarioNovo;
         }

         public Usuario atualizarUsuarios(Usuario usuario) {
             String esconder = this.passwordesconder.encode(usuario.getSenha());
             usuario.setSenha(esconder);
             Usuario usuarioNovo = repository.save(usuario);
             return usuarioNovo;
         }

         public Boolean deletarUsuarios(Integer id) {
             repository.deleteById(id);
             return true;
         }
         public Boolean autenticarUsuarios(Usuario usuario){
//             Recuperar o usuário com base no email do usuário, posso fazer isso com base no id tbm,
//             talvez eu mude
             Usuario usuarioArmazenado = repository.findByEmail(usuario.getEmail());
             if(usuarioArmazenado != null) {
                 String senhaArmazenada = usuarioArmazenado.getSenha();
                 if (passwordesconder.matches(usuario.getSenha(), senhaArmazenada)) {
                     return true; // Autenticação bem-sucedida
                 }
             }
             return false;
     }}