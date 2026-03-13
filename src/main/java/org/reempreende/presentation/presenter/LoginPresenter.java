package org.reempreende.presentation.presenter;

import org.reempreende.application.dto.response.UsuarioResponseDTO;
import org.reempreende.application.service.UsuarioService;
import org.reempreende.presentation.interfaces.icliente.IClienteView;
import org.reempreende.presentation.interfaces.ilogin.ILoginUsuario;
import org.reempreende.presentation.router.AppRouter;

public class LoginPresenter {
    private final AppRouter appRouter;
    private final ILoginUsuario loginUsuario;
    private final UsuarioService usuarioService;
    private final IClienteView clienteView;


    public LoginPresenter(AppRouter appRouter, ILoginUsuario loginUsuario, UsuarioService usuarioService, IClienteView clienteView) {
        this.appRouter = appRouter;
        this.loginUsuario = loginUsuario;
        this.usuarioService = usuarioService;
        this.clienteView = clienteView;
    }

    public void login() {
        String email = loginUsuario.askEmail();
        String senha = loginUsuario.askSenha();

        UsuarioResponseDTO usuarioResponseDTO = usuarioService.login(email, senha);

        if (usuarioResponseDTO.getTipoUsuario().equals("COMERCIANTE")) {
            String senhaAcesso = loginUsuario.askSenhaAcesso();
        } else if (usuarioResponseDTO.getTipoUsuario().equals("CLIENTE")) {
            appRouter.startClientView();
        }
    }
}
