package org.reempreende.presentation.presenter;

import org.reempreende.presentation.interfaces.icadastro.ICadastroClienteView;
import org.reempreende.presentation.router.AppRouter;

public class ClientePresenter {

    private final ICadastroClienteView cadastroView;

    public ClientePresenter(AppRouter appRouter, ICadastroClienteView cadastroView) {
        this.cadastroView = cadastroView;
    }

    public void iniciarCadastro() {

    }
}
