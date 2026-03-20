package org.reempreende.application.dto.response;

import org.reempreende.domain.entities.Agendamento;
import org.reempreende.infrastructure.utility.Cores;
import org.reempreende.infrastructure.utility.TextoUtil;
import org.reempreende.infrastructure.utility.Util;

import java.time.format.DateTimeFormatter;

public class AgendamentoResponseDTO {
    private Long idAgendamento;
    private String dataInicio;
    private String dataFim;
    private String observacao;
    private String nomeCliente;
    private Long idCliente;

    public AgendamentoResponseDTO() {}

    public AgendamentoResponseDTO(Agendamento agendamento) {
        this.idAgendamento = agendamento.getIdAgendamento();
        this.dataInicio = agendamento.getDataInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.dataFim = agendamento.getDataFim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.observacao = agendamento.getObservacao();
        this.nomeCliente = agendamento.getCliente().getNome();
        this.idCliente = agendamento.getCliente().getId();
    }

    public Long getIdAgendamento() { return idAgendamento; }
    public void setIdAgendamento(Long idAgendamento) { this.idAgendamento = idAgendamento; }

    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }

    public String getDataFim() { return dataFim; }
    public void setDataFim(String dataFim) { this.dataFim = dataFim; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    private static final Util u = new Util();

    public String exibirInfo() {
        return
                "  ──────────────────────────────────  \n" +
                "  ➜ ID        : " + idAgendamento + "\n" +
                "  ➜ Início    : " + dataInicio + "\n" +
                "  ➜ Fim       : " + dataFim + "\n" +
                "  ➜ Obs       : " + observacao + "\n" +
                "  ──────────────────────────────────\n";
    }
}