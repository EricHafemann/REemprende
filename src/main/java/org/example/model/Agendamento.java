package org.example.model;

import java.time.LocalDate;

public class Agendamento {

    private long idAgendamento;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String Observacao;

    public Agendamento(long idAgendamento, LocalDate dataInicio,
                       LocalDate dataFim, String observacao) {
        this.idAgendamento = idAgendamento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        Observacao = observacao;
    }

    public long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String observacao) {
        Observacao = observacao;
    }
}
