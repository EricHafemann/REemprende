package org.reempreende.presentation.presenter;

import org.reempreende.application.dto.response.UsuarioResponseDTO;
import org.reempreende.application.service.ComercianteService;
import org.reempreende.application.service.UsuarioService;
import org.reempreende.presentation.interfaces.icliente.IClienteView;
import org.reempreende.presentation.interfaces.ilogin.ILoginUsuario;
import org.reempreende.presentation.router.AppRouter;

public class LoginPresenter {
    private final AppRouter appRouter;
    private final ILoginUsuario loginUsuario;
    private final UsuarioService usuarioService;
    private final IClienteView clienteView;
    private final ComercianteService comercianteService;

    public LoginPresenter(AppRouter appRouter, ILoginUsuario loginUsuario,
                          UsuarioService usuarioService, ComercianteService comercianteService, IClienteView clienteView) {
        this.appRouter = appRouter;
        this.loginUsuario = loginUsuario;
        this.usuarioService = usuarioService;
        this.clienteView = clienteView;
        this.comercianteService = comercianteService;
    }

    public void login() {
        String email = loginUsuario.askEmail();
        String senha = loginUsuario.askSenha();

        UsuarioResponseDTO usuarioResponseDTO = usuarioService.login(email, senha);

        if (usuarioResponseDTO.getTipoUsuario().equals("COMERCIANTE")) {
            String senhaAcesso = loginUsuario.askSenhaAcesso();

            boolean isValido = comercianteService.validarSenhaAcesso
                    (usuarioResponseDTO.getId(),senhaAcesso);

            if (isValido) {
                // start comerciante view
            } else {
                // exibir erro
            }
        } else if (usuarioResponseDTO.getTipoUsuario().equals("CLIENTE")) {
            appRouter.startClientView();
        }
    }
}
