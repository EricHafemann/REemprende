package org.reempreende.application.service;

import org.reempreende.domain.repository.UsuarioRepository;
import org.reempreende.domain.repository.ComercianteRepository;
import org.reempreende.domain.entities.Usuario;
import org.reempreende.domain.entities.Comerciante;
import org.reempreende.domain.entities.enums.Status;
import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.application.dto.request.UsuarioRequestDTO;
import org.reempreende.application.dto.response.UsuarioResponseDTO;
import org.reempreende.application.dto.mapper.UsuarioMapper;
import org.reempreende.application.exception.BusinessException;

public class ComercianteService {

    private final UsuarioRepository usuarioRepository;
    private final ComercianteRepository comercianteRepository;

    public ComercianteService(UsuarioRepository usuarioRepository, ComercianteRepository comercianteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.comercianteRepository = comercianteRepository;
    }

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        dto.setTipoUsuario(TipoUsuario.COMERCIANTE.getCodigo());
        dto.setStatus(Status.ATIVO.getCodigo());

        if (dto.getCnpj() == null || dto.getCnpj().trim().isEmpty()) {
            throw new BusinessException("CNPJ é obrigatório");
        }

        if (dto.getSenhaAcesso() == null || dto.getSenhaAcesso().trim().isEmpty()) {
            throw new BusinessException("Senha de acesso é obrigatória");
        }

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BusinessException("Email já cadastrado");
        }

        if (comercianteRepository.existsByCnpj(dto.getCnpj())) {
            throw new BusinessException("CNPJ já cadastrado");
        }

        Comerciante comerciante = (Comerciante) UsuarioMapper.toEntity(dto);

        Usuario usuarioSalvo = usuarioRepository.insert(comerciante);
        comerciante.setId(usuarioSalvo.getId());
        comercianteRepository.insert(comerciante);

        return UsuarioMapper.toResponseDTO(usuarioSalvo);
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Comerciante comerciante = comercianteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comerciante não encontrado"));
        return UsuarioMapper.toResponseDTO(comerciante);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Comerciante comerciante = comercianteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comerciante não encontrado"));

        if (!comerciante.getEmail().equals(dto.getEmail())) {
            if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new BusinessException("Email já cadastrado para outro usuário");
            }
        }

        if (!comerciante.getCnpj().equals(dto.getCnpj())) {
            if (comercianteRepository.existsByCnpj(dto.getCnpj())) {
                throw new BusinessException("CNPJ já cadastrado para outro comerciante");
            }
        }

        comerciante.setEmail(dto.getEmail());
        comerciante.setNome(dto.getNome());
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            comerciante.setSenha(dto.getSenha());
        }
        comerciante.setCnpj(dto.getCnpj());
        if (dto.getSenhaAcesso() != null && !dto.getSenhaAcesso().isEmpty()) {
            comerciante.setSenhaAcesso(dto.getSenhaAcesso());
        }

        usuarioRepository.update(comerciante);
        comercianteRepository.update(comerciante);

        return UsuarioMapper.toResponseDTO(comerciante);
    }

    public void deletar(Long id) {
        Comerciante comerciante = comercianteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comerciante não encontrado"));

        comercianteRepository.delete(id);
        usuarioRepository.delete(id);
    }

    public void desativar(Long id) {
        Comerciante comerciante = comercianteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comerciante não encontrado"));

        if (comerciante.getStatus() == Status.INATIVO) {
            throw new BusinessException("Comerciante já está inativo");
        }

        usuarioRepository.disable(id);
    }

    public void ativar(Long id) {
        Comerciante comerciante = comercianteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comerciante não encontrado"));

        if (comerciante.getStatus() == Status.ATIVO) {
            throw new BusinessException("Comerciante já está ativo");
        }

        usuarioRepository.enable(id);
    }

    public boolean validarSenhaAcesso(Long id, String senhaAcesso) {
        Comerciante comerciante = comercianteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comerciante não encontrado"));
        return comerciante.getSenhaAcesso().equals(senhaAcesso);
    }
}