package org.reempreende.presentation.presenter;

import org.reempreende.application.dto.request.UsuarioDTO;
import org.reempreende.application.service.UsuarioService;
import org.reempreende.domain.entities.Cliente;
import org.reempreende.domain.entities.Usuario;
import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.domain.repository.UsuarioRepository;
import org.reempreende.infrastucture.repository.UsuarioRepositoryImpl;
import org.reempreende.presentation.exception.InvalidFieldException;
import org.reempreende.presentation.exception.InvalidPasswordException;
import org.reempreende.presentation.interfaces.icadastro.ICadastroView;

public class CadastrarUsuarioPresenter {

    private ICadastroView iCadastroView;
    private UsuarioService usuarioService;
    private UsuarioDTO usuarioDTO;

    private final UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();

    public CadastrarUsuarioPresenter(ICadastroView iCadastroView) {
        this.iCadastroView = iCadastroView;
        this.usuarioService = new UsuarioService(usuarioRepository);
        this.usuarioDTO = new UsuarioDTO();
    }

    public void collectInfo() {
        TipoUsuario tipoUsuario = null;

        String nome = iCadastroView.pedirNome();
        String email = iCadastroView.pedirEmail();
        String senha = iCadastroView.pedirSenha();
        int opcao = iCadastroView.pedirTipoUsuario();

        switch (opcao) {
            case 0 -> {

            }
        }

        registerUser(nome, email, senha, tipoUsuario);
    }

    public void registerUser(String nome, String email, String senha, TipoUsuario tipoUsuario) {
        validateInfo(nome, email, senha);

        if (tipoUsuario.getCodigo() == 0) {
            usuarioDTO = new UsuarioDTO();
            usuarioDTO.setCpf();

        } else {

        }

        iCadastroView.exibirSucesso("DEU CERTO");
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
