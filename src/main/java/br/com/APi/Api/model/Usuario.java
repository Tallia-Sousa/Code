package br.com.APi.Api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
//    @NotBlank(message = "O nome é obrigatorio")
//    @Size(min = 3, message = "o nome tem que ter pelo menos 3 caracteres")
    @Column(name = "Nome", length = 200, nullable = false)
    private String nome;
//    @Email(message = "insira uma email valido")
//    @NotBlank(message = "O email é obrigatorio")
    @Column(name = "Email", length = 50, nullable = false, unique = true)
    private String email;
//    @NotBlank(message = "A senha é obrigatorio")
//    @Column(name = "Senha",  nullable = false)
    private String senha;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }



    public void setSenha(String senha) {
        this.senha = senha;
    }
}
