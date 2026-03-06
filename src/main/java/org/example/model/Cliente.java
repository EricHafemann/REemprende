package org.example.model;

public class Cliente extends Usuario{

    private String cpf;

    public Cliente(long id, String email, String senha, String nome, String cpf) {
        super(id, email, senha, nome);
        this.cpf = cpf;
    }

    public Cliente(String email, String senha, String nome, String cpf) {
        super(email, senha, nome);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
