package org.reempreende.application.dto.mapper;

import org.reempreende.application.dto.request.AgendamentoRequestDTO;
import org.reempreende.application.dto.request.ServicoRequestDTO;
import org.reempreende.application.dto.response.ServicoResponseDTO;
import org.reempreende.domain.entities.Agendamento;
import org.reempreende.domain.entities.Cliente;
import org.reempreende.domain.entities.Comerciante;
import org.reempreende.domain.entities.Servico;

import java.util.List;
import java.util.stream.Collectors;

public class ServicoMapper {

    public static Servico toEntity (ServicoRequestDTO dto, Comerciante comerciante)
    {
        if(dto == null) return  null;

        return new Servico(
                dto.getAvaliacao(),
                dto.getDescricao(),
                dto.getDuracaoHoras(),
                comerciante
        );
    }
    public static ServicoResponseDTO toResponseDTO (Servico servico)
    {
        if(servico == null) return  null;
        return new ServicoResponseDTO(servico);
    }

    public static List<ServicoResponseDTO> toResponseDTOList (List<Servico> servicos)
    {
        if(servicos == null) return  null;
        return servicos.stream().
                map(ServicoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public static void updateEntityFromDTO(ServicoRequestDTO dto, Servico servico, Comerciante comerciante) {
        if (dto == null || servico == null) return;

        servico.setAvaliacao(dto.getAvaliacao());
        servico.setDescricao(dto.getDescricao());
        servico.setDuracaoHoras(dto.getDuracaoHoras());
        if (comerciante != null) {
            servico.setComerciante(comerciante);
        }
    }
}


