package org.reempreende.application.service;

import org.reempreende.application.dto.request.UsuarioUpdateDTO;
import org.reempreende.domain.entities.Cliente;
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

    public UsuarioResponseDTO insert(UsuarioRequestDTO dto) {
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

    public UsuarioResponseDTO findById(Long id) {
        Comerciante comerciante = comercianteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comerciante não encontrado"));
        return UsuarioMapper.toResponseDTO(comerciante);
    }

    public UsuarioResponseDTO update(Long id, UsuarioUpdateDTO dto) {
        Comerciante comerciante = comercianteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comerciante não encontrado"));

        if (dto.getNome() != null && !dto.getNome().isEmpty()) {
            if (dto.getNome().length() < 2 || dto.getNome().length() > 100) {
                throw new BusinessException("Nome deve ter entre 2 e 100 caracteres");
            }
            comerciante.setNome(dto.getNome());
        }

        if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
            if (!isValidEmail(dto.getEmail())) {
                throw new BusinessException("Formato de e-mail inválido");
            }

            if (dto.getEmail().length() > 254) {
                throw new BusinessException("E-mail muito longo (máximo 254 caracteres)");
            }

            if (dto.getEmail().equals(comerciante.getEmail())) {
                throw new BusinessException("Troque por um E-mail diferente do atual");
            }

            if (!comerciante.getEmail().equals(dto.getEmail())) {
                if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
                    throw new BusinessException("Email já cadastrado para outro usuário");
                }
            }
            comerciante.setEmail(dto.getEmail());
        }

        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            if (dto.getSenha().length() < 8) {
                throw new BusinessException("Senha deve ter no mínimo 8 caracteres");
            }

            String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).+$";
            if (!dto.getSenha().matches(regex)) {
                throw new BusinessException("Senha inválida! Deve conter letra maiúscula, minúscula, número e caractere especial");
            }

            comerciante.setSenha(dto.getSenha());
        }

        usuarioRepository.update(comerciante);
        comercianteRepository.update(comerciante);

        return UsuarioMapper.toResponseDTO(comerciante);
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }

        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    public void delete(Long id) {
        Comerciante comerciante = comercianteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comerciante não encontrado"));

        comercianteRepository.delete(id);
        usuarioRepository.delete(id);
    }

    public void deactivate(Long id) {
        Comerciante comerciante = comercianteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Comerciante não encontrado"));

        if (comerciante.getStatus() == Status.INATIVO) {
            throw new BusinessException("Comerciante já está inativo");
        }

        usuarioRepository.disable(id);
    }

    public void activate(Long id) {
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