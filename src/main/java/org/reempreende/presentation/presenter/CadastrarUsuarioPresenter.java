package org.reempreende.presentation.presenter;

import org.reempreende.application.dto.request.UsuarioDTO;
import org.reempreende.application.service.UsuarioService;
import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.domain.repository.UsuarioRepository;
import org.reempreende.infrastructure.repository.UsuarioRepositoryImpl;
import org.reempreende.presentation.exception.InvalidFieldException;
import org.reempreende.presentation.exception.InvalidPasswordException;
import org.reempreende.presentation.interfaces.icadastro.ICadastroClienteView;
import org.reempreende.presentation.interfaces.icadastro.ICadastroComercianteView;
import org.reempreende.presentation.interfaces.inicial.IInicialView;
import org.reempreende.presentation.router.AppRouter;
import org.reempreende.presentation.view.usuario.CadastroBaseView;

public class CadastrarUsuarioPresenter {
    private UsuarioService usuarioService;
    private UsuarioDTO usuarioDTO;

    private final CadastroBaseView cadastroBaseView;
    private final IInicialView inicialView;

    private final AppRouter appRouter;

    public CadastrarUsuarioPresenter(AppRouter appRouter, IInicialView inicialView, CadastroBaseView cadastroBaseView,
                                     UsuarioDTO usuarioDTO, UsuarioService usuarioService) {
        this.appRouter = appRouter;
        this.inicialView = inicialView;
        this.cadastroBaseView = cadastroBaseView;
        this.usuarioDTO = usuarioDTO;
        this.usuarioService = usuarioService;
    }

    public void collectInfo() {
        int tipoUsuario = inicialView.selecionarTipoUsuario();

        String nome = cadastroBaseView.pedirNome();
        String email = cadastroBaseView.pedirEmail();
        String senha = cadastroBaseView.pedirSenha();
        int status = cadastroBaseView.pedirStatus();

        registerUserDto(nome, email, senha, TipoUsuario.fromCodigo(tipoUsuario), status);

        switch (tipoUsuario) {
            case 0 -> {
                appRouter.registerClient(usuarioDTO);
            }
            case 1 -> {
                //comerciantePresenter.getClass();
            }
        }

    }

    public UsuarioDTO registerUserDto(String nome, String email, String senha, TipoUsuario tipoUsuario, int status) {
        validateInfo(nome, email, senha);

        usuarioDTO.setNome(nome);
        usuarioDTO.setEmail(email);
        usuarioDTO.setStatus(status);
        usuarioDTO.setTipoUsuario(tipoUsuario.getCodigo());
        usuarioDTO.setSenha(senha);

        return usuarioDTO;
    }

    public void validateInfo(String nome, String email, String senha) {
        if (nome == null || nome.length() < 2 || nome.length() > 1000) {
            throw new InvalidFieldException("Nome inválido!");
        }

        if (email == null || email.isBlank() || !email.contains("@") || email.length() > 254) {
            throw new InvalidFieldException("E-mail inválido!");
        }

        if (senha == null) {
            throw new InvalidPasswordException("Senha inválida!");
        }

        if (senha.length() < 8) {
            throw new InvalidPasswordException("Senha inválida! (Possui menos de 8 carácteres)");
        }

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).+$";

        if (!senha.matches(regex)) {
            throw new InvalidPasswordException("Senha inválida! Não possui letra maíscula ou minúscula, ou números e carácteres especiais");
        }
    }
}
