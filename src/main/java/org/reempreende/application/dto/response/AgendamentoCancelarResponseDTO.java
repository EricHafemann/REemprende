package org.reempreende.application.dto.response;

import org.reempreende.domain.entities.Agendamento;

import java.time.format.DateTimeFormatter;

public class AgendamentoCancelarResponseDTO {
    private Long idAgendamento;
    private String dataInicio;
    private String dataFim;
    private String observacao;
    private Long idCliente;

    public AgendamentoCancelarResponseDTO(Agendamento agendamento) {
        this.idAgendamento = agendamento.getIdAgendamento();
        this.dataInicio = agendamento.getDataInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.dataFim = agendamento.getDataFim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.observacao = agendamento.getObservacao();
        this.idCliente = agendamento.getCliente().getId();
    }

    public Long getIdAgendamento() {
        return idAgendamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }


    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
}


