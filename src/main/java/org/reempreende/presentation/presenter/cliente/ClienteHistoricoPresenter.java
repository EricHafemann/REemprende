package org.reempreende.presentation.presenter.cliente;

import org.reempreende.application.dto.response.AgendamentoResponseDTO;
import org.reempreende.application.dto.response.UsuarioResponseDTO;
import org.reempreende.application.service.AgendamentoService;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icliente.IClienteViewHistorico;
import org.reempreende.presentation.router.AppRouter;

import java.util.List;

public class ClienteHistoricoPresenter {
    private AppRouter appRouter;
    private IClienteViewHistorico view;
    private AgendamentoService agendamentoService;
    private Sessao sessao;

    public ClienteHistoricoPresenter(AppRouter appRouter, IClienteViewHistorico view, AgendamentoService agendamentoService, Sessao sessao) {
        this.appRouter = appRouter;
        this.view = view;
        this.agendamentoService = agendamentoService;
        this.sessao = sessao;
    }

    public void showHistory() {
        view.mostrarTela();

        try {
            UsuarioResponseDTO usuarioLogado = sessao.getUsuarioLogado();

            List<AgendamentoResponseDTO> historicos = agendamentoService.findByClientId(usuarioLogado.getId());

            for (AgendamentoResponseDTO historico : historicos)
            {
                view.verHistoricoAgendamentos(historico.exibirInfo());
            }

            Util.digiteEnterParaContinuar();
        } catch(Exception e) {
            view.exibirErro(e.getMessage());
            Util.next();
            Util.digiteEnterParaContinuar();
        }
    }

}
