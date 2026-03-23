package org.reempreende.application.service;

import org.reempreende.application.dto.request.UsuarioUpdateDTO;
import org.reempreende.domain.repository.UsuarioRepository;
import org.reempreende.domain.repository.ClienteRepository;
import org.reempreende.domain.entities.Usuario;
import org.reempreende.domain.entities.Cliente;
import org.reempreende.domain.entities.enums.Status;
import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.application.dto.request.UsuarioRequestDTO;
import org.reempreende.application.dto.response.UsuarioResponseDTO;
import org.reempreende.application.dto.mapper.UsuarioMapper;
import org.reempreende.application.exception.BusinessException;

public class ClienteService {

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;

    public ClienteService(UsuarioRepository usuarioRepository, ClienteRepository clienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
    }

    public UsuarioResponseDTO insert(UsuarioRequestDTO dto) {
        dto.setTipoUsuario(TipoUsuario.CLIENTE.getCodigo());
        dto.setStatus(Status.ATIVO.getCodigo());

        if (dto.getCpf() == null || dto.getCpf().trim().isEmpty()) {
            throw new BusinessException("CPF é obrigatório");
        }

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BusinessException("Email já cadastrado");
        }

        if (clienteRepository.existsByCpf(dto.getCpf())) {
            throw new BusinessException("CPF já cadastrado");
        }

        Cliente cliente = (Cliente) UsuarioMapper.toEntity(dto);

        Usuario usuarioSalvo = usuarioRepository.insert(cliente);
        cliente.setId(usuarioSalvo.getId());
        clienteRepository.insert(cliente);

        return UsuarioMapper.toResponseDTO(usuarioSalvo);
    }

    public UsuarioResponseDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));
        return UsuarioMapper.toResponseDTO(cliente);
    }

    public UsuarioResponseDTO update(Long id, UsuarioUpdateDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        if (dto.getNome() != null && !dto.getNome().isEmpty()) {
            if (dto.getNome().length() < 2 || dto.getNome().length() > 100) {
                throw new BusinessException("Nome deve ter entre 2 e 100 caracteres");
            }
            cliente.setNome(dto.getNome());
        }

        if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
            if (!isValidEmail(dto.getEmail())) {
                throw new BusinessException("Formato de e-mail inválido");
            }

            if (dto.getEmail().length() > 254) {
                throw new BusinessException("E-mail muito longo (máximo 254 caracteres)");
            }

            if (dto.getEmail().equals(cliente.getEmail())) {
                throw new BusinessException("Troque por um E-mail diferente do atual");
            }

            if (!cliente.getEmail().equals(dto.getEmail())) {
                if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
                    throw new BusinessException("Email já cadastrado para outro usuário");
                }
            }
            cliente.setEmail(dto.getEmail());
        }

        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            if (dto.getSenha().length() < 8) {
                throw new BusinessException("Senha deve ter no mínimo 8 caracteres");
            }

            String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).+$";
            if (!dto.getSenha().matches(regex)) {
                throw new BusinessException("Senha inválida! Deve conter letra maiúscula, minúscula, número e caractere especial");
            }

            cliente.setSenha(dto.getSenha());
        }

        usuarioRepository.update(cliente);
        clienteRepository.update(cliente);

        return UsuarioMapper.toResponseDTO(cliente);
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }

        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    public void delete(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        clienteRepository.delete(id);
        usuarioRepository.delete(id);
    }

    public void deactivate(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        if (cliente.getStatus() == Status.INATIVO) {
            throw new BusinessException("Cliente já está inativo");
        }

        usuarioRepository.disable(id);
    }

    public void activate(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        if (cliente.getStatus() == Status.ATIVO) {
            throw new BusinessException("Cliente já está ativo");
        }

        usuarioRepository.enable(id);
    }
}