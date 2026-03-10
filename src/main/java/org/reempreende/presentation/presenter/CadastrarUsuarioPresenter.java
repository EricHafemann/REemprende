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
import org.reempreende.presentation.interfaces.icadastro.ICadastroClienteView;
import org.reempreende.presentation.interfaces.icadastro.ICadastroComercianteView;
import org.reempreende.presentation.interfaces.icadastro.ICadastroView;

public class CadastrarUsuarioPresenter {

    private ICadastroView iCadastroView;
    private ICadastroComercianteView iCadastroComercianteView;
    private ICadastroClienteView iCadastroClienteView;
    private UsuarioService usuarioService;
    private UsuarioDTO usuarioDTO;

    private final UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();

    public CadastrarUsuarioPresenter(ICadastroView iCadastroView, ICadastroClienteView iCadastroClienteView, ICadastroComercianteView iCadastroComercianteView) {
        this.iCadastroView = iCadastroView;
        this.iCadastroClienteView = iCadastroClienteView;
        this.iCadastroComercianteView = iCadastroComercianteView;
        this.usuarioService = new UsuarioService(usuarioRepository);
        this.usuarioDTO = new UsuarioDTO();
    }

    public void collectInfo() {
        String cpf = null;
        String cnpj = null;
        String senhaAcesso = null;

        String nome = iCadastroView.pedirNome();
        String email = iCadastroView.pedirEmail();
        String senha = iCadastroView.pedirSenha();
        int tipoUsuario = iCadastroView.pedirTipoUsuario();
        int status = iCadastroView.pedirStatus();

        switch (tipoUsuario) {
            case 0 -> {
                cpf = iCadastroClienteView.pedirCPF();
            }
            case 1 -> {
                cnpj = iCadastroComercianteView.pedirCNPJ();
                senhaAcesso = iCadastroComercianteView.pedirSenhaAcesso();
            }
        }

        registerUser(nome, email, senha, TipoUsuario.fromCodigo(tipoUsuario), cpf, cnpj, status);
    }

    public void registerUser(String nome, String email, String senha, TipoUsuario tipoUsuario, String cpf, String cnpj, int status) {
        validateInfo(nome, email, senha);

        usuarioDTO.setNome(nome);
        usuarioDTO.setEmail(email);
        usuarioDTO.setStatus(status);
        usuarioDTO.setTipoUsuario(tipoUsuario.getCodigo());
        usuarioDTO.setSenha(senha);

        if (tipoUsuario.getCodigo() == 0) {
            usuarioDTO.setCpf(cpf);
        } else {
            usuarioDTO.setSenhaAcesso();
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
