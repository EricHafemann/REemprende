package org.example.model;

public class Servico
{
    private long idServico;
    private String avalicao;
    private String descricao;
    private double duracaoHoras;

    public Servico(long idServico, String avalicao, String descricao, double duracaoHoras)
    {
        this.idServico = idServico;
        this.avalicao = avalicao;
        this.descricao = descricao;
        this.duracaoHoras = duracaoHoras;
    }

    public Servico(String avalicao, String descricao, double duracaoHoras)
    {
        this.avalicao = avalicao;
        this.descricao = descricao;
        this.duracaoHoras = duracaoHoras;
    }

    public long getIdServico()
    {
        return idServico;
    }

    public void setIdServico(long idServico)
    {
        this.idServico = idServico;
    }

    public String getAvalicao()
    {
        return avalicao;
    }

    public void setAvalicao(String avalicao)
    {
        this.avalicao = avalicao;
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
}
