package org.reempreende.application.dto.request;

public class UsuarioDTO {
    // Campos comuns
    private String email;
    private String senha;
    private String nome;
    private Integer status;
    private Integer tipoUsuario;

    // Campos específicos
    private String cpf;
    private String cnpj;
    private String senhaAcesso;

    // Construtores
    public UsuarioDTO() {}

    // Getters e Setters de todos os campos
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

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getSenhaAcesso() { return senhaAcesso; }
    public void setSenhaAcesso(String senhaAcesso) { this.senhaAcesso = senhaAcesso; }
}