package org.reempreende.application.service;

import org.reempreende.application.dto.mapper.ServicoMapper;
import org.reempreende.application.dto.request.ServicoRequestDTO;
import org.reempreende.application.dto.response.ServicoResponseDTO;
import org.reempreende.application.exception.BusinessException;
import org.reempreende.domain.entities.Comerciante;
import org.reempreende.domain.entities.Servico;
import org.reempreende.domain.repository.ServicoRepository;

import java.util.List;

public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public ServicoResponseDTO insertServico(ServicoRequestDTO dto, Comerciante comerciante) {

        // Validações Básicas
        if (dto == null) return null;

        Servico servicoInsert = ServicoMapper.toEntity(dto, comerciante);

        if(servicoInsert.getComerciante() == null)
        {
            throw new BusinessException("Comerciante não pode ser nulo !");
        }
        if(servicoInsert.getDuracaoHoras() < 0)
        {
            throw new BusinessException("Duração não pode ter valores negativos !");
        }
        if(servicoInsert.getDescricao().length() < 10 )
        {
            throw new BusinessException("Descrição precisa ter no mínimo 10 caracteres !");
        }

        Servico servicoSalvo = servicoRepository.insert(servicoInsert);

        return ServicoMapper.toResponseDTO(servicoSalvo);

    }

    public ServicoResponseDTO findById(Long id)
    {
        Servico servicoFind = servicoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Serviço não encontrado com esse Id !"));

        return ServicoMapper.toResponseDTO(servicoFind);
    }

    public List<ServicoResponseDTO> findAll()
    {
        List<Servico> servicoFind = servicoRepository.findAll();
        return ServicoMapper.toResponseDTOList(servicoFind);
    }

    public ServicoResponseDTO updateServico(Long id, ServicoRequestDTO dto)
    {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Serviço não encontrado com esse Id !"));


        ServicoMapper.updateEntityFromDTO(dto, servico, servico.getComerciante());

        servicoRepository.update(servico);

        return ServicoMapper.toResponseDTO(servico);
    }

    public void deleteServico(Long id)
    {
        if(servicoRepository.existsById(id))
        {
            throw new BusinessException("Serviço não encontrado com esse Id !");
        }

        servicoRepository.delete(id);
    }
}
