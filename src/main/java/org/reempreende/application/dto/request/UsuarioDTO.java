package org.reempreende.application.dto.request;

public class UsuarioDTO {
    private String email;
    private String senha;
    private String nome;
    private Integer status;
    private Integer tipoUsuario;

    public UsuarioDTO() {}

    public UsuarioDTO(String email, String senha, String nome, Integer status, Integer tipoUsuario) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.status = status;
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public Integer getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(Integer tipoUsuario) { this.tipoUsuario = tipoUsuario; }
}