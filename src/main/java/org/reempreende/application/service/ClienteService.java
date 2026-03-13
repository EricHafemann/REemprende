package org.reempreende.application.service;

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

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
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

    public UsuarioResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));
        return UsuarioMapper.toResponseDTO(cliente);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        if (!cliente.getEmail().equals(dto.getEmail())) {
            if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new BusinessException("Email já cadastrado para outro usuário");
            }
        }

        if (!cliente.getCpf().equals(dto.getCpf())) {
            if (clienteRepository.existsByCpf(dto.getCpf())) {
                throw new BusinessException("CPF já cadastrado para outro cliente");
            }
        }

        cliente.setEmail(dto.getEmail());
        cliente.setNome(dto.getNome());
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            cliente.setSenha(dto.getSenha());
        }
        cliente.setCpf(dto.getCpf());

        usuarioRepository.update(cliente);
        clienteRepository.update(cliente);

        return UsuarioMapper.toResponseDTO(cliente);
    }

    public void deletar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        clienteRepository.delete(id);
        usuarioRepository.delete(id);
    }

    public void desativar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        if (cliente.getStatus() == Status.INATIVO) {
            throw new BusinessException("Cliente já está inativo");
        }

        usuarioRepository.disable(id);
    }

    public void ativar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        if (cliente.getStatus() == Status.ATIVO) {
            throw new BusinessException("Cliente já está ativo");
        }

        usuarioRepository.enable(id);
    }
}