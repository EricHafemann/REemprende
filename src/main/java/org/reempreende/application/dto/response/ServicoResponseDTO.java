package org.reempreende.application.dto.response;

public class ServicoResponseDTO {

    private long idServico;
    private String avaliacao;
    private String descricao;
    private double duracaoHoras;
    private long idComerciante;

    public ServicoResponseDTO() {
    }

    public ServicoResponseDTO(long idServico, String avaliacao, String descricao, double duracaoHoras, long idComerciante) {
        this.idServico = idServico;
        this.avaliacao = avaliacao;
        this.descricao = descricao;
        this.duracaoHoras = duracaoHoras;
        this.idComerciante = idComerciante;
    }

    public long getIdServico() {return idServico;}

    public void setIdServico(long idServico) {this.idServico = idServico;}

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(double duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }

    public long getIdComerciante() {
        return idComerciante;
    }

    public void setIdComerciante(long idComerciante) {
        this.idComerciante = idComerciante;
    }
}
