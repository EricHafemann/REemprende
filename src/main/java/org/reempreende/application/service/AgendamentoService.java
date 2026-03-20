package org.reempreende.application.service;

import org.reempreende.application.dto.mapper.AgendamentoMapper;
import org.reempreende.application.dto.mapper.UsuarioMapper;
import org.reempreende.application.dto.request.AgendamentoRequestDTO;
import org.reempreende.application.dto.response.AgendamentoResponseDTO;
import org.reempreende.application.dto.response.UsuarioResponseDTO;
import org.reempreende.application.exception.BusinessException;
import org.reempreende.domain.entities.Agendamento;
import org.reempreende.domain.repository.AgendamentoRepository;
import org.reempreende.infrastructure.exception.RepositoryException;
import org.reempreende.infrastructure.exception.UsuarioNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

   public AgendamentoResponseDTO findById(long id)
   {
       Agendamento agendamento = agendamentoRepository.findById(id)
               .orElseThrow(() -> new BusinessException("Agendamento não encontrado"));
       return AgendamentoMapper.toResponseDTO(agendamento);
   }

   public boolean existById(long id)
   {
       if(id <= 0)
       {
           throw new IllegalArgumentException("ID do cliente não pode ser negativo ou igual a 0");
       }

       return agendamentoRepository.existsById(id);

   }

   public long countAgendamentosByIdClient (long idClient)
   {
       if(idClient <= 0)
       {
           throw new IllegalArgumentException("ID do cliente não pode ser negativo ou igual a 0");
       }

       return agendamentoRepository.countByClientId(idClient);
   }

   public List<AgendamentoResponseDTO> findAll()
   {
       List<Agendamento> agendamentos = agendamentoRepository.findAll();

       if (agendamentos.isEmpty())
       {
           throw new BusinessException("Não agendamentos para listar");
       }

       List<AgendamentoResponseDTO> dtos = AgendamentoMapper.toResponseDTOList(agendamentos);

        return dtos;
   }

    public boolean isAvailable(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            throw new BusinessException("A Data não pode ser nula");
        }

        if (startTime.isBefore(LocalDateTime.now().minusMinutes(10))) {
            throw new BusinessException("A Data já passou do prazo");
        }

        return agendamentoRepository.isAvailable(startTime, endTime);
    }

   public AgendamentoResponseDTO update(Long id, AgendamentoRequestDTO dto)
   {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Agendamento não encontrado"));

        AgendamentoMapper.updateEntityFromDTO(dto,agendamento,agendamento.getCliente());

        agendamentoRepository.update(agendamento);

        return AgendamentoMapper.toResponseDTO(agendamento);
   }

   public void delete(long id)
   {
       if(agendamentoRepository.findById(id).isEmpty())
       {
           throw new BusinessException("Nenhum agendamento com esse id foi encontrado");
       }

       agendamentoRepository.delete(id);
   }

   public List<AgendamentoResponseDTO> findByClientId(long id)
   {
       if(id <= 0)
       {
           throw new BusinessException("Id de cliente inválido");
       }

       try
       {
           List<Agendamento> agendamentos = agendamentoRepository.findByClientId(id);

           if(agendamentos.isEmpty())
           {
               throw new BusinessException("Nenhum agendamento com esse id foi encontrado");
           }

           List<AgendamentoResponseDTO> dtos = AgendamentoMapper.toResponseDTOList(agendamentos);

           return dtos;

       }

       catch (RepositoryException e)
       {
           throw new BusinessException("Erro ao buscar agendamento do cliente: " + e);
       }
   }

   public List<AgendamentoResponseDTO> findByDate(LocalDateTime dateTime)
   {
       if(dateTime == null)
       {
           throw new BusinessException("A data não poder ser nula");
       }

       List<Agendamento> agendamentos = agendamentoRepository.findByDate(dateTime);

       if (agendamentos.isEmpty())
       {
           throw new BusinessException("Nenhum agendamento com essa data foi encontrado");
       }

       List<AgendamentoResponseDTO> dtos = AgendamentoMapper.toResponseDTOList(agendamentos);

       return dtos;
   }

   public List<AgendamentoResponseDTO> findUpcoming()
   {
       List<Agendamento> agendamentos = agendamentoRepository.findUpcoming();

       if(agendamentos.isEmpty())
       {
           throw new BusinessException("Nenhum agendamento futuro encontrado");
       }

       List<AgendamentoResponseDTO> dtos = AgendamentoMapper.toResponseDTOList(agendamentos);

       return dtos;
   }

   public List<AgendamentoResponseDTO> findPast()
   {
       List<Agendamento> agendamentos = agendamentoRepository.findPast();

       if(agendamentos.isEmpty())
       {
           throw new BusinessException("Nenhum agendamento passado encontrado");
       }

       List<AgendamentoResponseDTO> dtos = AgendamentoMapper.toResponseDTOList(agendamentos);

       return dtos;
   }
}
