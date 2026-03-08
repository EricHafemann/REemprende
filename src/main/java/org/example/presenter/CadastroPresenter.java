package org.example.presenter;

import org.example.model.Cliente;
import org.example.model.Comerciante;
import org.example.model.enums.TipoUsuario;
import org.example.service.UsuarioService;
import org.example.view.CadastroView;

public class CadastroPresenter {
/*
    private CadastroView cadastroView;
    private UsuarioService usuarioService;

    public CadastroPresenter() {
        this.cadastroView = new CadastroView();
        this.usuarioService = new UsuarioService();
    }

    public void realizarCadastro(String nome, String email, String senha, TipoUsuario tipoUsuario) {
        validarInfo(nome, email, senha);

        if (tipoUsuario == TipoUsuario.CLIENTE) {
            Cliente cliente = new Cliente();
        } else if (tipoUsuario == TipoUsuario.COMERCIANTE) {
            Comerciante comerciante = new Comerciante();
        }

        // voltar
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
 */
}
