package org.reempreende.domain.entities;

import java.time.LocalDateTime;

public class Agendamento {

    private long idAgendamento;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String observacao;
    private Cliente cliente;

    public Agendamento(long idAgendamento, LocalDateTime dataInicio, LocalDateTime dataFim, String observacao, Cliente cliente) {
        this.idAgendamento = idAgendamento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.observacao = observacao;
        this.cliente = cliente;
    }

    public Agendamento(LocalDateTime dataInicio, LocalDateTime dataFim, String observacao, Cliente cliente) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.observacao = observacao;
        this.cliente = cliente;
    }

    public long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Cliente getCliente() {return cliente;}

    public void setCliente(Cliente cliente) {this.cliente = cliente;}

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

}
