package org.reempreende.application.dto.mapper;

import org.reempreende.domain.entities.Agendamento;
import org.reempreende.domain.entities.Cliente;
import org.reempreende.application.dto.request.AgendamentoRequestDTO;
import org.reempreende.application.dto.response.AgendamentoResponseDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class AgendamentoMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static Agendamento toEntityCliente(AgendamentoRequestDTO dto, Cliente cliente) {
        if (dto == null) return null;
        return new Agendamento(
                dto.getDataInicio(),
                dto.getDataFim(),
                dto.getObservacao(),
                cliente
        );
    }

    public static Agendamento toEntityComerciante(AgendamentoRequestDTO dto) {
        if (dto == null) return null;
        return new Agendamento(
                dto.getDataInicio(),
                dto.getDataFim(),
                dto.getObservacao(),
                null
        );
    }

    public static AgendamentoRequestDTO toRequestDTO(AgendamentoResponseDTO response) {
        if (response == null) return null;
        
        return new AgendamentoRequestDTO(
                LocalDateTime.parse(response.getDataInicio(), FORMATTER),
                LocalDateTime.parse(response.getDataFim(), FORMATTER),
                response.getObservacao(),
                response.getIdCliente()
        );
    }

    public static AgendamentoResponseDTO toResponseDTO(Agendamento agendamento) {
        if (agendamento == null) return null;
        return new AgendamentoResponseDTO(agendamento);
    }

    public static List<AgendamentoResponseDTO> toResponseDTOList(List<Agendamento> agendamentos) {
        if (agendamentos == null) return null;
        return agendamentos.stream()
                .map(AgendamentoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public static void updateEntityFromDTO(AgendamentoRequestDTO dto, Agendamento agendamento, Cliente cliente) {
        if (dto == null || agendamento == null) return;

        agendamento.setDataInicio(dto.getDataInicio());
        agendamento.setDataFim(dto.getDataFim());
        agendamento.setObservacao(dto.getObservacao());
        if (cliente != null) {
            agendamento.setCliente(cliente);
        }
    }
}