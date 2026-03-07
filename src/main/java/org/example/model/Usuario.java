package org.example.model;

import org.example.model.enums.Status;
import org.example.model.enums.TipoUsuario;

public abstract class Usuario
{
    private long id;
    private String email;
    private String senha;
    private String nome;
    private Status status;
    private TipoUsuario tipoUsuario;

    public Usuario(long id, String email, String senha, String nome, Status status, TipoUsuario tipoUsuario) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.status = status;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(String email, String senha, String nome, Status status, TipoUsuario tipoUsuario) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.status = status;
        this.tipoUsuario = tipoUsuario;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
