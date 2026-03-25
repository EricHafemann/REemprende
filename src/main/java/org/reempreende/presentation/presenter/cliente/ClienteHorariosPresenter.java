package org.reempreende.presentation.presenter.cliente;

import org.reempreende.application.dto.response.AgendamentoResponseDTO;
import org.reempreende.application.exception.BusinessException;
import org.reempreende.application.service.AgendamentoService;
import org.reempreende.infrastructure.utility.Util;
import org.reempreende.presentation.interfaces.icliente.IClienteViewHorarios;
import org.reempreende.presentation.router.AppRouter;

import java.util.List;

public class ClienteHorariosPresenter {
    private AppRouter appRouter;
    private IClienteViewHorarios view;
    private AgendamentoService agendamentoService;

    public ClienteHorariosPresenter(AppRouter appRouter, IClienteViewHorarios view, AgendamentoService agendamentoService) {
        this.appRouter = appRouter;
        this.view = view;
        this.agendamentoService = agendamentoService;
    }

    public ClienteHorariosPresenter(IClienteViewHorarios view, AgendamentoService agendamentoService) {
        this.view = view;
        this.agendamentoService = agendamentoService;
    }

    public void showHorarios() {
        view.mostrarTela();

        List<AgendamentoResponseDTO> agendamentos = null;

        try {
            agendamentos = agendamentoService.findAvailable();
        } catch(BusinessException e) {
            view.exibirErro(e.getMessage());
            return;
        } catch(Exception e) {
            view.exibirErro("Erro ao buscar horários: " + e.getMessage());
            return;
        }

        if (agendamentos.isEmpty()) {
            view.exibirErro("Não há horários disponíveis no momento.");
            return;
        }

        for (AgendamentoResponseDTO agendamento : agendamentos) {
            view.exibirHorarios(agendamento.exibirInfo(), true);
        }

        Util.digiteEnterParaContinuar();
    }
}
