package org.reempreende.domain.entities;

import org.reempreende.domain.entities.enums.Status;
import org.reempreende.domain.entities.enums.TipoUsuario;

public class Cliente extends Usuario{

    private String cpf;

    public Cliente(long id, String email, String senha, String nome, Status status, TipoUsuario tipoUsuario, String cpf) {
        super(id, email, senha, nome, status, tipoUsuario);
        this.cpf = cpf;
    }

    public Cliente(String email, String senha, String nome, Status status, TipoUsuario tipoUsuario, String cpf) {
        super(email, senha, nome, status, tipoUsuario);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


}
