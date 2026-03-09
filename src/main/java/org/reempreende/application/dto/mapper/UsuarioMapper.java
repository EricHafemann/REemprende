package org.reempreende.application.dto.mapper;

import org.reempreende.application.exception.TipoUsuarioInvalidException;
import org.reempreende.domain.entities.Usuario;
import org.reempreende.domain.entities.Cliente;
import org.reempreende.domain.entities.Comerciante;
import org.reempreende.domain.entities.enums.Status;
import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.application.dto.request.UsuarioDTO;
import org.reempreende.application.dto.response.UsuarioResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;

        TipoUsuario tipo = TipoUsuario.fromCodigo(dto.getTipoUsuario());

        switch (tipo) {
            case CLIENTE:
                // Verifica se tem CPF
                if (dto.getCpf() == null || dto.getCpf().isEmpty()) {
                    throw new IllegalArgumentException("CPF é obrigatório para cliente");
                }

                return new Cliente(
                        0,
                        dto.getEmail(),
                        dto.getSenha(),
                        dto.getNome(),
                        Status.fromCodigo(dto.getStatus()),
                        tipo,
                        dto.getCpf()
                );

            case COMERCIANTE:
                // Verifica se tem CNPJ e senhaAcesso
                if (dto.getCnpj() == null || dto.getCnpj().isEmpty()) {
                    throw new IllegalArgumentException("CNPJ é obrigatório para comerciante");
                }
                if (dto.getSenhaAcesso() == null || dto.getSenhaAcesso().isEmpty()) {
                    throw new IllegalArgumentException("Senha de acesso é obrigatória para comerciante");
                }

                return new Comerciante(
                        0,
                        dto.getEmail(),
                        dto.getSenha(),
                        dto.getNome(),
                        Status.fromCodigo(dto.getStatus()),
                        tipo,
                        dto.getCnpj(),
                        dto.getSenhaAcesso()
                );

            default:
                throw new TipoUsuarioInvalidException("Tipo de usuário inválido: " + tipo);
        }
    }

    public static UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) return null;
        return new UsuarioResponseDTO(usuario);
    }

    public static List<UsuarioResponseDTO> toResponseDTOList(List<Usuario> usuarios) {
        if (usuarios == null) return null;
        return usuarios.stream()
                .map(UsuarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public static void updateEntityFromDTO(UsuarioDTO dto, Usuario usuario) {
        if (dto == null || usuario == null) return;

        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setStatus(Status.fromCodigo(dto.getStatus()));

        if (usuario instanceof Cliente) {
            ((Cliente) usuario).setCpf(dto.getCpf());
        } else if (usuario instanceof Comerciante) {
            ((Comerciante) usuario).setCnpj(dto.getCnpj());
            ((Comerciante) usuario).setSenhaAcesso(dto.getSenhaAcesso());
        }
    }
}