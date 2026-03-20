package org.reempreende.infrastructure.sessao;

import org.reempreende.application.dto.response.UsuarioResponseDTO;

public class Sessao {
    private UsuarioResponseDTO usuarioLogado;

    public void startSession(UsuarioResponseDTO usuarioResponseDTO) {
        this.usuarioLogado = usuarioResponseDTO;
    }

    public void logout() {
        this.usuarioLogado = null;
    }

    public UsuarioResponseDTO getUsuarioLogado() {
        return usuarioLogado;
    }

}
