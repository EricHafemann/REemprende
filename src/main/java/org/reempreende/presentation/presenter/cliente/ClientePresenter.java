package org.reempreende.presentation.presenter.cliente;

import org.reempreende.presentation.interfaces.icliente.IClienteView;
import org.reempreende.presentation.router.AppRouter;

import java.util.OptionalInt;

public class ClientePresenter {
    private AppRouter appRouter;
    private IClienteView view;


    public ClientePresenter(AppRouter appRouter, IClienteView view) {
        this.appRouter = appRouter;
        this.view = view;
    }

    public void selecionarOpcoes() {
        boolean continuar = true;

        while (continuar) {
            OptionalInt opcaoCaixa = view.mostrarTela();

            int opcao =  opcaoCaixa.orElse(-1);

            switch (opcao) {
                case 1 -> appRouter.clientViewHorarios();
                case 2 -> appRouter.clientAgendarHorarioDisponivel();
                case 3 -> appRouter.clientViewHistory();
                case 4 -> appRouter.updateCliente();
                case 0 -> {
                    view.exibirMensagem("Voltando...");
                    appRouter.logout();
                    continuar = false;
                }
                default -> {
                    view.exibirErro("Opção inválida! Tente novamente:");
                }
            }
        }

    }
}
