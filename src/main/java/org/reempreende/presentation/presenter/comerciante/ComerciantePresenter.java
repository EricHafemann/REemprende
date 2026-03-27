package org.reempreende.presentation.presenter.comerciante;

import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icomerciante.IComercianteView;
import org.reempreende.presentation.router.AppRouter;

import java.util.OptionalInt;

public class ComerciantePresenter {
    private AppRouter appRouter;
    private IComercianteView view;

    public ComerciantePresenter(AppRouter appRouter, IComercianteView view) {
        this.appRouter = appRouter;
        this.view = view;
    }

    public void selectOptions() {
        boolean continuar = true;

        while (continuar) {
            try {
                OptionalInt opcaoCaixa = view.mostrarTela();

                int opcao = opcaoCaixa.orElse(-1);

                switch (opcao) {
                    case 1 -> appRouter.servicoComerciante();
                    case 2 -> appRouter.atualizarComerciante();
                    case 0 -> {
                        view.exibirMensagem("Saindo...");
                        appRouter.logout();
                        continuar = false;
                    }
                    default -> {
                        view.exibirErro("Opção inválida! Tente novamente:");
                        Util.digiteEnterParaContinuar();
                    }
                }
            } catch (Exception e) {
                System.out.println("Opção inválida! Tente novamente:");
                Util.next();
                Util.digiteEnterParaContinuar();
            }

        }



    }
}
