package br.com.APi.Api.repository;

import br.com.APi.Api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterfaceUsuario extends JpaRepository<Usuario, Integer> {

    Usuario findByEmail(String email);
    Usuario findBySenha(String senha);




    Usuario findEmailById(Integer id);
}
