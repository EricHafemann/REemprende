package org.reempreende.application.dto.response;

import org.reempreende.domain.entities.Servico;
import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.infrastructure.utility.TextoUtil;

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

    public ServicoResponseDTO(Servico servico) {
        this.idServico = servico.getIdServico();
        this.avaliacao = servico.getAvaliacao();
        this.descricao = servico.getDescricao();
        this.duracaoHoras = servico.getDuracaoHoras();
        this.idComerciante = servico.getComerciante().getId();

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

    public void exibirInfo ()
    {
        System.out.println(Cores.VERDE + TextoUtil.transformar("   Inoformações do Serviço   " )+ Cores.RESET);
        System.out.println(Cores.NEGRITO + TextoUtil.transformar("Id                - " + getIdServico()) + Cores.RESET);
        System.out.println(Cores.NEGRITO + TextoUtil.transformar("Avaliação         - " + getAvaliacao()) + Cores.RESET );
        System.out.println(Cores.NEGRITO + TextoUtil.transformar("Descrição         - " + getDescricao()) + Cores.RESET );
        System.out.println(Cores.NEGRITO + TextoUtil.transformar("Duração           - " + getDuracaoHoras()) + Cores.RESET);
        System.out.println(Cores.NEGRITO + TextoUtil.transformar("Id do comerciante - " + getIdComerciante()) + Cores.RESET );
    }
}
