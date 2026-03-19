package org.reempreende.presentation.presenter;

import org.reempreende.presentation.interfaces.icliente.IClienteView;
import org.reempreende.presentation.router.AppRouter;
import org.reempreende.presentation.view.cliente.ClienteView;

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
                case 0 -> {
                    view.exibirMensagem("Voltando...");
                    continuar = false;
                }
                default -> {
                    view.exibirErro("Opção inválida! Tente novamente:");
                }
            }
        }

    }
}
