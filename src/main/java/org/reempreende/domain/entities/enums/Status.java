package org.reempreende.domain.entities.enums;

public enum Status {
    ATIVO(1, "Registro ativo"),
    INATIVO(2, "Registro inativo temporariamente"),
    BLOQUEADO(3, "Registro bloqueado"),
    DESATIVADO(4, "Registro desativado permanentemente");

    private final int codigo;
    private final String descricao;

    Status(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status fromCodigo(int codigo) {
        for (Status s : Status.values()) {
            if (s.getCodigo() == codigo) {
                return s;
            }
        }
        throw new IllegalArgumentException("Código de status inválido: " + codigo);
    }
}