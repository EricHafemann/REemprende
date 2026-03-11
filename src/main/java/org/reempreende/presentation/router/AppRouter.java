package org.reempreende.presentation.router;

import org.reempreende.presentation.interfaces.icadastro.ICadastroClienteView;
import org.reempreende.presentation.interfaces.inicial.IInicialView;
import org.reempreende.presentation.presenter.CadastrarUsuarioPresenter;
import org.reempreende.presentation.presenter.ClientePresenter;
import org.reempreende.presentation.presenter.ComerciantePresenter;
import org.reempreende.presentation.presenter.InicialPresenter;
import org.reempreende.presentation.view.cliente.CadastroClienteView;
import org.reempreende.presentation.view.inicio.InicialView;
import org.reempreende.presentation.view.usuario.CadastroBaseView;

public class AppRouter {
    public void iniciarSistema() {
        IInicialView inicialView = new InicialView();
        InicialPresenter inicialPresenter = new InicialPresenter(this, inicialView);

        inicialPresenter.iniciar();
    }

    public void cadastroUsuario() {
        IInicialView inicialView = new InicialView();
        ComerciantePresenter comerciantePresenter = new ComerciantePresenter();

        ICadastroClienteView cadastroClienteView = new CadastroClienteView();
        ClientePresenter clientePresenter = new ClientePresenter(this, cadastroClienteView);

        CadastroBaseView cadastroBaseView = new CadastroBaseView();

        CadastrarUsuarioPresenter cadastrarUsuarioPresenter = new CadastrarUsuarioPresenter(this, inicialView, cadastroBaseView, clientePresenter,
                comerciantePresenter);

        cadastrarUsuarioPresenter.collectInfo();
    }
}
