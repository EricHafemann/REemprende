package org.reempreende.presentation.presenter.cliente;

import org.reempreende.application.service.ClienteService;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.exception.InvalidFieldException;
import org.reempreende.presentation.interfaces.icliente.IClienteDeletarView;
import org.reempreende.presentation.router.AppRouter;

public class ClienteDeletarPresenter {
    private final Util u = new Util();

    private final AppRouter appRouter;
    private final Sessao sessao;
    private final IClienteDeletarView view;
    private final ClienteService clienteService;

    public ClienteDeletarPresenter(AppRouter appRouter, IClienteDeletarView view, Sessao sessao, ClienteService clienteService) {
        this.appRouter = appRouter;
        this.view = view;
        this.sessao = sessao;
        this.clienteService = clienteService;
    }

    public void delete() {
        try {
            view.mostrarTela();
            String confirmacao = view.confirmarDeletar();

            do {
                if (confirmacao.equals("DELETAR")) {
                    clienteService.delete(sessao.getUsuarioLogado().getId());
                    sessao.logout();

                    view.exibirSucesso("Cliente deletado com sucesso!");
                    view.exibirMensagem("Saindo...");

                    u.delay(1500);

                    appRouter.startSystem();
                } else if (confirmacao.equalsIgnoreCase("SAIR")) {
                    view.exibirMensagem("Saindo...");

                    u.delay(1500);
                    return;
                }
            } while (!confirmacao.equals("DELETAR"));
        } catch (InvalidFieldException e) {
            view.exibirErro(e.getMessage());
        }
    }

}
