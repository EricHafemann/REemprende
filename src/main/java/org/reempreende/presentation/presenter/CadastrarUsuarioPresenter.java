package org.reempreende.presentation.presenter;

import org.reempreende.application.service.UsuarioService;
import org.reempreende.domain.entities.enums.TipoUsuario;
import org.reempreende.presentation.interfaces.icadastro.ICadastroView;
import org.reempreende.presentation.view.CadastroView;

public class CadastrarUsuarioPresenter {

    private ICadastroView iCadastroView;
    private UsuarioService usuarioService;

    public CadastrarUsuarioPresenter(ICadastroView iCadastroView) {
        this.iCadastroView = new CadastroView();
        this.usuarioService = new UsuarioService();
    }

    public void realizarCadastro(String nome, String email, String senha, TipoUsuario tipoUsuario) {
        validarInfo(nome, email, senha);

        // inserir
    }

    public void validarInfo(String nome, String email, String senha) {
        if (nome == null || nome.length() < 2 || nome.length() > 1000) {
            // chamar erro view
        }

        if (email == null || email.isBlank() || !email.contains("@") || email.length() > 254) {
            // chamar erro view
        }

        if (senha == null || senha.length() < 8) {
            // chamar erro view
        }

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).+$";

        if (!senha.matches(regex)) {
            // chamar erro view
        }
    }
}
