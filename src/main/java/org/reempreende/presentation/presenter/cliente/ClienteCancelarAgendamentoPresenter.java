package org.reempreende.presentation.presenter.cliente;

import org.reempreende.application.dto.response.AgendamentoResponseDTO;
import org.reempreende.application.service.AgendamentoService;
import org.reempreende.infrastructure.sessao.Sessao;
import org.reempreende.presentation.interfaces.icliente.IClienteCancelarAgendamentoView;
import org.reempreende.presentation.router.AppRouter;

import java.util.List;

public class ClienteCancelarAgendamentoPresenter {
    private AppRouter appRouter;
    private Sessao sessao;
    private AgendamentoService agendamentoService;
    private IClienteCancelarAgendamentoView view;

    public ClienteCancelarAgendamentoPresenter(AppRouter appRouter, Sessao sessao, AgendamentoService agendamentoService,
                                               IClienteCancelarAgendamentoView view) {
        this.appRouter = appRouter;
        this.sessao = sessao;
        this.agendamentoService = agendamentoService;
        this.view = view;
    }

    public void cancelarAgendamento() {
        List<AgendamentoResponseDTO> agendamentos = agendamentoService.findByClientId(sessao.getUsuarioLogado().getId());

        for (AgendamentoResponseDTO agendamentoResponseDTO : agendamentos) {
            view.exibirHorarioCliente(agendamentos.toString());
        }

        long idAgendamento = view.askHorarioId();

        try {
            AgendamentoResponseDTO agendamentoResponseDTO = agendamentoService.findById(idAgendamento);

            if (agendamentoResponseDTO.getIdCliente() != sessao.getUsuarioLogado().getId() ||
                    agendamentoResponseDTO.getIdCliente() == null) {

            }
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
    }

}
