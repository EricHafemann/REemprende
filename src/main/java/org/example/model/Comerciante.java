package org.example.model;

public class Comerciante {
    private String cnpj;
    private String senhaAcesso;

    public Comerciante(String cnpj, String senhaAcesso) {
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
