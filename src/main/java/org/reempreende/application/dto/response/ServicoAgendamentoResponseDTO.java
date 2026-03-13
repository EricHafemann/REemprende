package org.reempreende.application.dto.response;

import org.reempreende.domain.entities.Agendamento;
import org.reempreende.domain.entities.Servico;
import org.reempreende.domain.entities.ServicoAgendamento;

public class ServicoAgendamentoResponseDTO {

    private long idServicosAgendamentos;
    private Servico servico;
    private Agendamento agendamento;

    public ServicoAgendamentoResponseDTO() {
    }

    public ServicoAgendamentoResponseDTO(long idServicosAgendamentos, Servico servico, Agendamento agendamento) {
        this.idServicosAgendamentos = idServicosAgendamentos;
        this.servico = servico;
        this.agendamento = agendamento;
    }

    public ServicoAgendamentoResponseDTO(ServicoAgendamento servicoAgendamento) {
        this.idServicosAgendamentos = servicoAgendamento.getIdServicosAgendamentos();
        this.servico = servicoAgendamento.getServico();
        this.agendamento = servicoAgendamento.getAgendamento();
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
