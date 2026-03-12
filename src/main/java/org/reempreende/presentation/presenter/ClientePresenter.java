package org.reempreende.presentation.presenter;

import org.reempreende.application.dto.request.UsuarioDTO;
import org.reempreende.application.service.UsuarioService;
import org.reempreende.presentation.interfaces.icadastro.ICadastroClienteView;
import org.reempreende.presentation.router.AppRouter;

public class ClientePresenter {

    private final ICadastroClienteView cadastroView;
    private final UsuarioService usuarioService;

    public ClientePresenter(AppRouter appRouter, ICadastroClienteView cadastroView, UsuarioService usuarioService) {
        this.cadastroView = cadastroView;
        this.usuarioService = usuarioService;
    }

    public void iniciarCadastro(UsuarioDTO usuarioDTO) {
        String cpf = cadastroView.pedirCPF();

        usuarioDTO.setCpf(cpf);

        usuarioService.criarUsuario(usuarioDTO);
    }
}
