package org.reempreende.application.dto.mapper;

import org.reempreende.application.dto.request.ServicoAgendamentoRequestDTO;
import org.reempreende.application.dto.response.ServicoAgendamentoResponseDTO;
import org.reempreende.domain.entities.Agendamento;
import org.reempreende.domain.entities.Servico;
import org.reempreende.domain.entities.ServicoAgendamento;

import java.util.List;
import java.util.stream.Collectors;

public class ServicoAgendamentoMapper {

    public static ServicoAgendamento toEntity (ServicoAgendamentoRequestDTO dto, Servico servico, Agendamento agendamento)
    {
        if(dto == null) return null;
        return new ServicoAgendamento(servico, agendamento);
    }

    public static ServicoAgendamentoResponseDTO toResponseDTO(ServicoAgendamento servicoAgendamento) {
        if (servicoAgendamento == null) return null;
        return new ServicoAgendamentoResponseDTO(servicoAgendamento);
    }

    public static List<ServicoAgendamentoResponseDTO> toResponseDTOList (List<ServicoAgendamento> list)
    {
        if(list == null) return null;
        return list.stream()
                .map(ServicoAgendamentoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


}
