package org.reempreende.application.dto.request;

public class ServicoAgendamentoRequestDTO {

    private Long idServico;
    private Long idAgendamento;

    public ServicoAgendamentoRequestDTO() {}

    public ServicoAgendamentoRequestDTO(Long idServico, Long idAgendamento) {
        this.idServico = idServico;
        this.idAgendamento = idAgendamento;
    }

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public Long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }
}