package org.reempreende.application.service;

import org.reempreende.application.dto.mapper.ServicoMapper;
import org.reempreende.application.dto.request.ServicoRequestDTO;
import org.reempreende.application.dto.response.ServicoResponseDTO;
import org.reempreende.application.exception.BusinessException;
import org.reempreende.domain.entities.Comerciante;
import org.reempreende.domain.entities.Servico;
import org.reempreende.domain.entities.Usuario;
import org.reempreende.domain.repository.ServicoAgendamentoRepository;
import org.reempreende.domain.repository.ServicoRepository;
import org.reempreende.domain.repository.UsuarioRepository;
import org.reempreende.presentation.exception.InvalidFieldException;

import java.util.List;
import java.util.Optional;

public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final ServicoAgendamentoRepository servicoAgendamentoRepository;
    private final AgendamentoService agendamentoService;
    private final UsuarioRepository usuarioRepository;

    public ServicoService(ServicoRepository servicoRepository, ServicoAgendamentoRepository servicoAgendamentoRepository, AgendamentoService agendamentoService, UsuarioRepository usuarioRepository) {
        this.servicoRepository = servicoRepository;
        this.servicoAgendamentoRepository = servicoAgendamentoRepository;
        this.agendamentoService = agendamentoService;
        this.usuarioRepository = usuarioRepository;
    }

    public ServicoResponseDTO insertServico(ServicoRequestDTO dto) {

        if (dto == null) return null;

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(dto.getIdComerciante());

        if (usuarioOpt.isEmpty()) {
            throw new BusinessException("Usuário não encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        if (!(usuario instanceof Comerciante comerciante)) {
            throw new BusinessException("O usuário informado não é um comerciante");
        }

        Servico servicoInsert = ServicoMapper.toEntity(dto, comerciante);

        if(servicoInsert.getComerciante() == null) {
            throw new BusinessException("Comerciante não pode ser nulo !");
        }
        if(servicoInsert.getDuracaoHoras() <= 0) {
            throw new BusinessException("Duração deve ser maior que zero !");
        }
        if(servicoInsert.getDescricao() == null || servicoInsert.getDescricao().trim().length() < 5) {
            throw new BusinessException("Descrição precisa ter no mínimo 5 caracteres !");
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

    public Long countByComercianteId (Long idComerciante)
    {
        if(idComerciante < 0)
        {
            throw new InvalidFieldException("O ID não pode ser nulo ou menor que 0");
        }

        Long countByComercianteId = servicoRepository.countByComercianteId(idComerciante);

        return countByComercianteId;
    }

    public List<ServicoResponseDTO> findByComercianteId (Long idComerciante)
    {
        if(idComerciante <= 0)
        {
            throw new BusinessException("ID do Comerciante não pode ser menor ou igual a 0");
        }

        List<Servico> listServicos = servicoRepository.findByComercianteId(idComerciante);

        return ServicoMapper.toResponseDTOList(listServicos);
    }

    public void deleteServico(Long idServico, Long idAgendamento)
    {
        if(servicoRepository.existsById(idServico))
        {
            throw new BusinessException("Serviço não encontrado com esse Id !");
        }

        if(agendamentoService.existById(idAgendamento))
        {
            throw new BusinessException("Serviço não encontrado com esse Id !");
        }

        servicoAgendamentoRepository.deleteByAgendamentoId(idAgendamento);
        agendamentoService.delete(idAgendamento);

        servicoRepository.delete(idServico);
    }
}
