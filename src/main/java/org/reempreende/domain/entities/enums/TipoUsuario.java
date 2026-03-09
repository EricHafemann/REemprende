package org.reempreende.domain.entities.enums;

public enum TipoUsuario {

    CLIENTE(0, "Usuário final que contrata serviços"),
    COMERCIANTE(1 , "Comerciante ou prestador de serviços");

    private final int codigo;
    private final String descricao;


    TipoUsuario(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoUsuario fromCodigo(int codigo) {
        for (TipoUsuario tipo : TipoUsuario.values()) {
            if (tipo.getCodigo() == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código de TipoUsuario inválido: " + codigo);
    }

}