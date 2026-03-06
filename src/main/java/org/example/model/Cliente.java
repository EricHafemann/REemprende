package org.example.model;

import org.example.model.enums.Status;

public class Cliente extends Usuario{

    private String cpf;

    public Cliente(long id, String email, String senha, String nome, Status status, String cpf) {
        super(id, email, senha, nome, status);
        this.cpf = cpf;
    }

    public Cliente(String email, String senha, String nome, Status status, String cpf) {
        super(email, senha, nome, status);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
