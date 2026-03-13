package org.reempreende.application.dto.request;

import java.time.LocalDateTime;

public class AgendamentoRequestDTO {
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String observacao;
    private Long idCliente;

    public AgendamentoRequestDTO() {}

    public LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDateTime dataInicio) { this.dataInicio = dataInicio; }

    public LocalDateTime getDataFim() { return dataFim; }
    public void setDataFim(LocalDateTime dataFim) { this.dataFim = dataFim; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }
}