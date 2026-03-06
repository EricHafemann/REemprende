package org.example.model;

public class Usuario
{
    private long id;
    private String email;
    private String senha;
    private String nome;


    public Usuario(long id, String email, String senha, String nome)
    {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }

    public Usuario(String email, String senha, String nome)
    {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
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
}
