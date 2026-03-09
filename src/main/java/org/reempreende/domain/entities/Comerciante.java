package org.reempreende.domain.entities;

import org.reempreende.domain.entities.enums.Status;
import org.reempreende.domain.entities.enums.TipoUsuario;

public class Comerciante extends Usuario{
    private String cnpj;
    private String senhaAcesso;

    public Comerciante(long id, String email, String senha, String nome, Status status, TipoUsuario tipoUsuario, String cnpj, String senhaAcesso) {
        super(id, email, senha, nome, status, tipoUsuario);
        this.cnpj = cnpj;
        this.senhaAcesso = senhaAcesso;
    }

    public Comerciante(String email, String senha, String nome, Status status, TipoUsuario tipoUsuario, String cnpj, String senhaAcesso) {
        super(email, senha, nome, status, tipoUsuario);
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
