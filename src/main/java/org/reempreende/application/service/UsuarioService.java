package org.reempreende.application.service;

import org.reempreende.domain.repository.UsuarioRepository;
import org.reempreende.domain.entities.Usuario;
import org.reempreende.domain.entities.Cliente;
import org.reempreende.domain.entities.Comerciante;
import org.reempreende.domain.entities.enums.Status;
import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.application.dto.request.UsuarioDTO;
import org.reempreende.application.dto.response.UsuarioResponseDTO;
import org.reempreende.application.dto.mapper.UsuarioMapper;
import org.reempreende.application.exception.BusinessException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO criarUsuario(UsuarioDTO dto) {
        // Validar email único
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BusinessException("Email já cadastrado");
        }

        TipoUsuario tipo = TipoUsuario.fromCodigo(dto.getTipoUsuario());

        if (tipo == TipoUsuario.CLIENTE) {
            if (dto.getCpf() == null || dto.getCpf().trim().isEmpty()) {
                throw new BusinessException("CPF é obrigatório para cliente");
            }
        } else if (tipo == TipoUsuario.COMERCIANTE) {
            if (dto.getCnpj() == null || dto.getCnpj().trim().isEmpty()) {
                throw new BusinessException("CNPJ é obrigatório para comerciante");
            }
            if (dto.getSenhaAcesso() == null || dto.getSenhaAcesso().trim().isEmpty()) {
                throw new BusinessException("Senha de acesso é obrigatória para comerciante");
            }
        }

        Usuario usuario = UsuarioMapper.toEntity(dto);

        Usuario usuarioSalvo = usuarioRepository.insert(usuario);

        return UsuarioMapper.toResponseDTO(usuarioSalvo);
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        return UsuarioMapper.toResponseDTO(usuario);
    }

    public UsuarioResponseDTO buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        return UsuarioMapper.toResponseDTO(usuario);
    }

    // Listar todos os usuários
    public List<UsuarioResponseDTO> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return UsuarioMapper.toResponseDTOList(usuarios);
    }

    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        if (!usuario.getEmail().equals(dto.getEmail())) {
            if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new BusinessException("Email já cadastrado para outro usuário");
            }
        }

        UsuarioMapper.updateEntityFromDTO(dto, usuario);

        if (usuario instanceof Cliente && dto.getCpf() != null) {
            ((Cliente) usuario).setCpf(dto.getCpf());
        } else if (usuario instanceof Comerciante) {
            if (dto.getCnpj() != null) {
                ((Comerciante) usuario).setCnpj(dto.getCnpj());
            }
            if (dto.getSenhaAcesso() != null) {
                ((Comerciante) usuario).setSenhaAcesso(dto.getSenhaAcesso());
            }
        }

        usuarioRepository.update(usuario);

        return UsuarioMapper.toResponseDTO(usuario);
    }

    public void alterarSenha(Long id, String senhaAtual, String novaSenha) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        if (!usuario.getSenha().equals(senhaAtual)) {
            throw new BusinessException("Senha atual incorreta");
        }

        usuario.setSenha(novaSenha);
        usuarioRepository.update(usuario);
    }

    // Desativar usuário
    public void desativarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        if (usuario.getStatus() == Status.INATIVO) {
            throw new BusinessException("Usuário já está inativo");
        }

        usuarioRepository.disable(id);
    }

    public void ativarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        if (usuario.getStatus() == Status.ATIVO) {
            throw new BusinessException("Usuário já está ativo");
        }

        usuarioRepository.enable(id);
    }

    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new BusinessException("Usuário não encontrado");
        }

        usuarioRepository.delete(id);
    }

    public UsuarioResponseDTO login(String email, String senha) {
        Usuario usuario = usuarioRepository.login(email, senha)
                .orElseThrow(() -> new BusinessException("Email ou senha inválidos"));

        if (usuario.getStatus() == Status.INATIVO) {
            throw new BusinessException("Usuário inativo. Contate o administrador.");
        }

        return UsuarioMapper.toResponseDTO(usuario);
    }

}