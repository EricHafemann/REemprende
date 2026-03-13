package org.reempreende.application.dto.request;

public class ServicoRequestDTO
{

    private String avaliacao;
    private String descricao;
    private double duracaoHoras;
    private Long idComerciante;

    public ServicoRequestDTO() {
    }

    public ServicoRequestDTO(String avaliacao, String descricao, double duracaoHoras, Long idComerciante) {
        this.avaliacao = avaliacao;
        this.descricao = descricao;
        this.duracaoHoras = duracaoHoras;
        this.idComerciante = idComerciante;
    }

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

    public Long getIdComerciante() {
        return idComerciante;
    }

    public void setIdComerciante(Long idComerciante) {
        this.idComerciante = idComerciante;
    }
}
