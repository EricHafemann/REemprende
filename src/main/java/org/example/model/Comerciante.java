package org.example.model;

import org.example.model.enums.Status;

public class Comerciante extends Usuario{
    private String cnpj;
    private String senhaAcesso;

    public Comerciante(long id, String email, String senha, String nome, Status status, String cnpj, String senhaAcesso) {
        super(id, email, senha, nome, status);
        this.cnpj = cnpj;
        this.senhaAcesso = senhaAcesso;
    }

    public Comerciante(String email, String senha, String nome, Status status, String cnpj, String senhaAcesso) {
        super(email, senha, nome, status);
        this.cnpj = cnpj;
        this.senhaAcesso = senhaAcesso;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }
}
