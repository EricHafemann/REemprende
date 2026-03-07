package org.example.presenter;

import org.example.view.CadastroView;

public class InicialPresenter {

    private final CadastroView cadastroView;

    public InicialPresenter() {
        this.cadastroView = new CadastroView();
    }

    public void trocarDeView(int opcao) {
        switch (opcao) {
            case 1 -> cadastroView.cadastrar();
        }
    }
}
