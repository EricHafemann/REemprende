package org.reempreende.presentation.presenter;

import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.inicial.IInicialView;
import org.reempreende.presentation.router.AppRouter;

import java.util.OptionalInt;

public class InicialPresenter {

    private final IInicialView inicialView;
    private final AppRouter appRouter;

    public InicialPresenter(AppRouter appRouter, IInicialView inicialView) {
        this.inicialView = inicialView;
        this.appRouter = appRouter;
    }

    public void iniciar() {
        boolean continuar = true;

        while (continuar) {
            OptionalInt opcaoCaixa = inicialView.mostrarTela();

            int opcao = opcaoCaixa.orElse(-1);

            switch (opcao) {
                case 1 -> {
                    appRouter.registerUser();
                }
                case 2 -> {
                    appRouter.login();
                }
                case 0 -> {
                    inicialView.sair("Saindo do sistema...");
                    System.exit(0);
                }
                default -> {
                    inicialView.exibirErro("Opção inválida! Tente novamente:");
                }
            }
        }
    }

}
