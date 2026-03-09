package org.reempreende.domain.entities;

public class ServicoAgendamento {
    private long idServicosAgendamentos;
    private Servico servico;
    private Agendamento agendamento;

    public ServicoAgendamento() {}

    public ServicoAgendamento(Servico servico, Agendamento agendamento) {
        this.servico = servico;
        this.agendamento = agendamento;
    }

    public ServicoAgendamento(long idServicosAgendamentos, Servico servico, Agendamento agendamento) {
        this.idServicosAgendamentos = idServicosAgendamentos;
        this.servico = servico;
        this.agendamento = agendamento;
    }

    public long getIdServicosAgendamentos() {
        return idServicosAgendamentos;
    }

    public void setIdServicosAgendamentos(long idServicosAgendamentos) {
        this.idServicosAgendamentos = idServicosAgendamentos;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
}