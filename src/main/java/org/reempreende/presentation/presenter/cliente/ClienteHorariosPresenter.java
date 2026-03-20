package org.reempreende.presentation.presenter.cliente;

import org.reempreende.application.dto.response.AgendamentoResponseDTO;
import org.reempreende.application.service.AgendamentoService;
import org.reempreende.domain.entities.Agendamento;
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

    public void exibirHorarios() {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.findAll();

        for (AgendamentoResponseDTO agendamento : agendamentos) {
            if (agendamento.getIdCliente() == null) {
                view.exibirHorarios(agendamento);
            }
        }
    }
}
