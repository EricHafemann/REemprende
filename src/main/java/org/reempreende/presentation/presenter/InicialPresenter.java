package org.reempreende.presentation.presenter;

import org.reempreende.presentation.interfaces.icadastro.ICadastroView;
import org.reempreende.presentation.interfaces.inicial.IInicialView;
import org.reempreende.presentation.router.AppRouter;

public class InicialPresenter {

    private final IInicialView inicialView;
    private final AppRouter appRouter;

    public InicialPresenter(AppRouter appRouter, IInicialView inicialView) {
        this.inicialView = inicialView;
        this.appRouter = appRouter;
    }

    public void iniciar() {
        int opcao = inicialView.mostrarTelaInicial();

        switch (opcao) {
            case 1 -> {
                appRouter.cadastroUsuario();
            }
        }
    }

}
