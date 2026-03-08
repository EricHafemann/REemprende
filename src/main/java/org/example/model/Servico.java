package org.example.model;

public class Servico
{
    private long idServico;
    private String avaliacao;
    private String descricao;
    private double duracaoHoras;
    private Comerciante comerciante;

    public Servico(long idServico, String avalicao, String descricao, double duracaoHoras, Comerciante comerciante) {
        this.idServico = idServico;
        this.avaliacao = avalicao;
        this.descricao = descricao;
        this.duracaoHoras = duracaoHoras;
        this.comerciante = comerciante;
    }

    public Servico(String avalicao, String descricao, double duracaoHoras, Comerciante comerciante) {
        this.avaliacao = avalicao;
        this.descricao = descricao;
        this.duracaoHoras = duracaoHoras;
        this.comerciante = comerciante;
    }

    public long getIdServico()
    {
        return idServico;
    }

    public void setIdServico(long idServico)
    {
        this.idServico = idServico;
    }

    public String getAvaliacao()
    {
        return avaliacao;
    }

    public void setAvaliacao(String avalicao)
    {
        this.avaliacao = avalicao;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public double getDuracaoHoras()
    {
        return duracaoHoras;
    }

    public void setDuracaoHoras(double duracaoHoras)
    {
        this.duracaoHoras = duracaoHoras;
    }

    public Comerciante getComerciante() {
        return comerciante;
    }

    public void setComerciante(Comerciante comerciante) {
        this.comerciante = comerciante;
    }
}
