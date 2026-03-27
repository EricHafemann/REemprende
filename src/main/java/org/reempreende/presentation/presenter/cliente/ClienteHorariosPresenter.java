package org.reempreende.presentation.presenter.cliente;

import org.reempreende.application.dto.response.AgendamentoResponseDTO;
import org.reempreende.application.exception.BusinessException;
import org.reempreende.application.service.AgendamentoService;
import org.reempreende.infrastructure.utility.Cores;
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

    public void showHorarios() {
        view.mostrarTela();

        long idServico = view.askIdServico();

        List<AgendamentoResponseDTO> agendamentos = null;

        try {
            agendamentos = agendamentoService.findAgendamentosByServicoId(idServico);
        } catch(BusinessException e) {
            view.exibirErro(e.getMessage());
            Util.digiteEnterParaContinuar();
            return;
        } catch(Exception e) {
            view.exibirErro("Erro ao buscar horários: " + e.getMessage());
            Util.digiteEnterParaContinuar();
            return;
        }

        if (agendamentos.isEmpty()) {
            view.exibirErro("Não há horários disponíveis no momento.");
            return;
        }

        for (AgendamentoResponseDTO agendamento : agendamentos) {
            if (agendamento.getIdCliente() == null) {
                String mensagem = Cores.VERDE + agendamento.exibirInfo() + Cores.RESET;

                view.exibirHorarios(mensagem);
            } else {
                String mensagem = Cores.VERMELHO + agendamento.exibirInfo() + Cores.RESET;

                view.exibirHorarios(mensagem);
            }
        }

        Util.digiteEnterParaContinuar();
    }
}
