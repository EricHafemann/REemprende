package org.reempreende.presentation.presenter;

import org.reempreende.presentation.interfaces.icadastro.ICadastroView;
import org.reempreende.presentation.interfaces.inicial.IInicialView;

public class InicialPresenter {

    private final IInicialView inicialView;

    public InicialPresenter(IInicialView inicialView) {
        this.inicialView = inicialView;
    }

    public void iniciar() {
        int opcao = inicialView.mostrarTelaInicial();
        trocarDeView(opcao);
    }

    public void trocarDeView(int opcao) {
        switch (opcao) {
            case 1 -> {
                ICadastroView iCadastroView = new CadastroView();
                CadastrarUsuarioPresenter cadastrarUsuarioPresenter = new CadastrarUsuarioPresenter(iCadastroView);

                cadastrarUsuarioPresenter.collectInfo();
            }
        }
    }
}
