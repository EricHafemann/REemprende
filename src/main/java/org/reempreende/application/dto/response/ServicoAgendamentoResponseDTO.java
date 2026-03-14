package org.reempreende.application.dto.response;

import org.reempreende.domain.entities.ServicoAgendamento;

public class ServicoAgendamentoResponseDTO {

    private Long idServicosAgendamentos;
    private Long idServico;
    private Long idAgendamento;

    public ServicoAgendamentoResponseDTO() {}

    public ServicoAgendamentoResponseDTO(Long idServicosAgendamentos, Long idServico, Long idAgendamento) {
        this.idServicosAgendamentos = idServicosAgendamentos;
        this.idServico = idServico;
        this.idAgendamento = idAgendamento;
    }

    public ServicoAgendamentoResponseDTO(ServicoAgendamento servicoAgendamento) {
        this.idServicosAgendamentos = servicoAgendamento.getIdServicosAgendamentos();
        this.idServico = servicoAgendamento.getServico().getIdServico();
        this.idAgendamento = servicoAgendamento.getAgendamento().getIdAgendamento();
    }


    public Long getIdServicosAgendamentos() { return idServicosAgendamentos; }
    public void setIdServicosAgendamentos(Long idServicosAgendamentos) { this.idServicosAgendamentos = idServicosAgendamentos; }

    public Long getIdServico() { return idServico; }
    public void setIdServico(Long idServico) { this.idServico = idServico; }

    public Long getIdAgendamento() { return idAgendamento; }
    public void setIdAgendamento(Long idAgendamento) { this.idAgendamento = idAgendamento; }
}