package org.reempreende.presentation.presenter;

import org.reempreende.application.dto.request.UsuarioRequestDTO;
import org.reempreende.application.service.UsuarioService;
import org.reempreende.domain.entities.enums.Status;
import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.presentation.exception.InvalidFieldException;
import org.reempreende.presentation.exception.InvalidPasswordException;
import org.reempreende.presentation.interfaces.inicial.IInicialView;
import org.reempreende.presentation.router.AppRouter;
import org.reempreende.presentation.view.cadastro.CadastroBaseView;

public class CadastrarUsuarioPresenter {
    private UsuarioService usuarioService;
    private UsuarioRequestDTO usuarioRequestDTO;

    private final CadastroBaseView cadastroBaseView;
    private final IInicialView inicialView;

    private final AppRouter appRouter;

    public CadastrarUsuarioPresenter(AppRouter appRouter, IInicialView inicialView, CadastroBaseView cadastroBaseView,
                                     UsuarioRequestDTO usuarioRequestDTO, UsuarioService usuarioService) {
        this.appRouter = appRouter;
        this.inicialView = inicialView;
        this.cadastroBaseView = cadastroBaseView;
        this.usuarioRequestDTO = usuarioRequestDTO;
        this.usuarioService = usuarioService;
    }

    public void collectInfo() {
        int tipoUsuario = inicialView.selecionarTipoUsuario();

        if (tipoUsuario < 1 || tipoUsuario > 2) {
            inicialView.exibirErro("Opção de Tipo de Usuário inválido!");
            return;
        }

        String nome = cadastroBaseView.pedirNome();
        String email = cadastroBaseView.pedirEmail();
        String senha = cadastroBaseView.pedirSenha();

        registerUserDto(nome, email, senha, TipoUsuario.fromCodigo(tipoUsuario), Status.ATIVO.getCodigo());

        switch (tipoUsuario) {
            case 0 -> {
                appRouter.registerClient(usuarioRequestDTO);
            }
            case 1 -> {
                appRouter.registerComerciante(usuarioRequestDTO);
            }
            default -> {
                inicialView.exibirErro("Opção inválida! Tente novamente:");
            }
        }

    }

    public UsuarioRequestDTO registerUserDto(String nome, String email, String senha, TipoUsuario tipoUsuario, int status) {
        validateInfo(nome, email, senha);

        usuarioRequestDTO.setNome(nome);
        usuarioRequestDTO.setEmail(email);
        usuarioRequestDTO.setStatus(status);
        usuarioRequestDTO.setTipoUsuario(tipoUsuario.getCodigo());
        usuarioRequestDTO.setSenha(senha);

        return usuarioRequestDTO;
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
