package org.reempreende.presentation.presenter.inicio.login;

import org.reempreende.application.dto.response.UsuarioResponseDTO;
import org.reempreende.application.service.ComercianteService;
import org.reempreende.application.service.UsuarioService;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.presentation.exception.InvalidFieldException;
import org.reempreende.presentation.interfaces.ilogin.ILoginUsuario;
import org.reempreende.presentation.router.AppRouter;

public class LoginPresenter {
    private final AppRouter appRouter;
    private final ILoginUsuario loginUsuario;
    private final UsuarioService usuarioService;
    private final ComercianteService comercianteService;
    private final Sessao sessao;

    public LoginPresenter(AppRouter appRouter, ILoginUsuario loginUsuario,
                          UsuarioService usuarioService, ComercianteService comercianteService, Sessao sessao) {
        this.appRouter = appRouter;
        this.loginUsuario = loginUsuario;
        this.usuarioService = usuarioService;
        this.comercianteService = comercianteService;
        this.sessao = sessao;
    }

    public void login() {
        String email = loginUsuario.askEmail();
        String senha = loginUsuario.askSenha();

        try {
            UsuarioResponseDTO usuarioResponseDTO = usuarioService.login(email, senha);

            if (usuarioResponseDTO.getTipoUsuario().equals("COMERCIANTE")) {
                String senhaAcesso = loginUsuario.askSenhaAcesso();

                boolean isValido = comercianteService.validarSenhaAcesso
                        (usuarioResponseDTO.getId(),senhaAcesso);

                if (isValido) {
                    sessao.startSession(usuarioResponseDTO);
                    appRouter.startComercianteView();
                } else {
                    loginUsuario.exibirErro("E-mail ou senha inválido!");
                }
            } else if (usuarioResponseDTO.getTipoUsuario().equals("CLIENTE")) {
                sessao.startSession(usuarioResponseDTO);
                appRouter.startClientView();
            }
        } catch (InvalidFieldException e) {
           loginUsuario.exibirErro(e.getMessage());
        }
    }

}
