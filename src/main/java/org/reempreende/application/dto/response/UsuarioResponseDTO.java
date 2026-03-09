package org.reempreende.application.dto.response;

import org.reempreende.domain.entities.Usuario;


public class UsuarioResponseDTO {
    private Long id;
    private String email;
    private String nome;
    private String status;
    private String tipoUsuario;

    public UsuarioResponseDTO() {}

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
        this.status = usuario.getStatus().name();
        this.tipoUsuario = usuario.getTipoUsuario().name();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }
}