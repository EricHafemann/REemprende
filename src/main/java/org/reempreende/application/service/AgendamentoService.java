package org.reempreende.application.service;

import org.reempreende.application.dto.mapper.AgendamentoMapper;
import org.reempreende.application.dto.request.AgendamentoRequestDTO;
import org.reempreende.application.dto.response.AgendamentoResponseDTO;
import org.reempreende.application.exception.BusinessException;
import org.reempreende.domain.entities.Agendamento;
import org.reempreende.domain.repository.AgendamentoRepository;

public class AgendamentoService
{

   private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository)
   {
       this.agendamentoRepository = agendamentoRepository;
   }

   public AgendamentoResponseDTO insertAgendamento(AgendamentoRequestDTO dto)
   {
       if(dto.getObservacao() == null)
       {
           throw new BusinessException("A observação é obrigatória");
       }

       if(dto.getDataInicio() == null)
       {
            throw new BusinessException("A data ínico é obrigatória");
       }

       if(dto.getDataInicio().isAfter(dto.getDataFim()))
       {
           throw new BusinessException("A data de ínicio não pode ser depois da data de fim");
       }

       if(dto.getDataFim().isAfter(dto.getDataInicio()))
       {
           throw new BusinessException("A data de fim não pode ser antes da data de ínicio");
       }

       if(dto.getDataFim() == null)
       {
           throw new BusinessException("A data Fim é obrigatória");
       }

       Agendamento agendamento = (Agendamento) AgendamentoMapper.toEntityComerciante(dto);
       Agendamento agendamentoSalvo = agendamentoRepository.insert(agendamento);

       return AgendamentoMapper.toResponseDTO(agendamentoSalvo);
   }
}
